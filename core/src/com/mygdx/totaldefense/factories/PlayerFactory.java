package com.mygdx.totaldefense.factories;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.collision.ICollisionBits;
import com.mygdx.totaldefense.components.*;
import com.mygdx.totaldefense.managers.Assets;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/8/15.
 */
public class PlayerFactory {
    public static Entity player(PooledEngine engine, World world, RayHandler rayHandler, RectangleMapObject spawn) {
        Entity player = engine.createEntity();

        // AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        HealthComponent health = engine.createComponent(HealthComponent.class);
        ControllerComponent controller = engine.createComponent(ControllerComponent.class);
        ProjectileComponent projectile = engine.createComponent(ProjectileComponent.class);
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);

        textureComponent.region = Assets.player;
        health.health = 100;
        projectile.damage = 10;
        projectile.speed.set(1000, 1000);
        projectile.timeBetweenShots = 0.1f;
        projectile.collisionCategory = ICollisionBits.PROJECTILE;
        projectile.targetCollisionCategory = ICollisionBits.ENEMY;
        projectile.length = 500 * IConversions.PPM;

        // Keeping a reference to the light so it can be modified by powerups.
        BodyComponent bodyComponent = BodyFactory.player(engine, world, textureComponent, spawn);
        LightComponent lightComponent = LightFactory.flashlight(engine, rayHandler);

        playerComponent.playerLight = LightFactory.playerLight(rayHandler, bodyComponent.body);

        player.add(transform);
        player.add(textureComponent);
        player.add(health);
        player.add(bodyComponent);
        player.add(controller);
        player.add(projectile);
        player.add(lightComponent);
        player.add(playerComponent);

        engine.addEntity(player);

        return player;
    }
}
