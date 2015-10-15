package com.mygdx.totaldefense.factories;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.totaldefense.components.LightComponent;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/13/15.
 */
public class LightFactory {
    public static PointLight playerLight(RayHandler rayHandler, Body body) {
        PointLight playerLight = new PointLight(rayHandler, 10);
        playerLight.setDistance(3f);
        playerLight.setColor(Color.WHITE);

        playerLight.attachToBody(body);

        return playerLight;
    }

    public static LightComponent flashlight(PooledEngine engine, RayHandler rayHandler) {
        LightComponent lightComponent = engine.createComponent(LightComponent.class);

        lightComponent.light = new ConeLight(
                rayHandler, 10, Color.WHITE,
                500 * IConversions.PPM,
                100 * IConversions.PPM,
                100 * IConversions.PPM,
                0f, 30f
        );

        lightComponent.light.setColor(0.4f, 0.4f, 0.4f, 1f);

        return lightComponent;
    }
}
