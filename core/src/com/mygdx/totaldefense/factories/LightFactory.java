package com.mygdx.totaldefense.factories;

import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.mygdx.totaldefense.collision.ICollisionBits;
import com.mygdx.totaldefense.collision.IMaskBits;
import com.mygdx.totaldefense.components.LightComponent;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/13/15.
 */
public class LightFactory {
    public static PointLight playerLight(RayHandler rayHandler, Body body) {
        PointLight playerLight = new PointLight(rayHandler, 10);
        playerLight.setColor(Color.WHITE);
        playerLight.setDistance(3f);

        playerLight.attachToBody(body);

        return playerLight;
    }

    public static LightComponent flashlight(PooledEngine engine, RayHandler rayHandler) {
        LightComponent lightComponent = engine.createComponent(LightComponent.class);

        lightComponent.light = new ConeLight(
                rayHandler, 10, Color.WHITE,
                800 * IConversions.PPM,
                100 * IConversions.PPM,
                100 * IConversions.PPM,
                0f, 20f
        );

        lightComponent.light.setColor(new Color(0.3f, 0.3f, 0.3f, 1f));

        Filter filter = new Filter();
        filter.categoryBits = ICollisionBits.LIGHT;
        filter.maskBits = IMaskBits.LIGHT;

        lightComponent.light.setContactFilter(filter);

        return lightComponent;
    }

    public static LightComponent testFlashlight(PooledEngine engine, RayHandler rayHandler) {
        LightComponent lightComponent = engine.createComponent(LightComponent.class);

        lightComponent.light = new DirectionalLight(
                rayHandler, 10,
                Color.WHITE,
                90f
        );



        return lightComponent;
    }
}
