package com.mygdx.totaldefense.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.collision.ICollisionBits;
import com.mygdx.totaldefense.components.*;
import com.mygdx.totaldefense.managers.Assets;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/8/15.
 */
public class EnemyFactory {
    public static Entity enemy(PooledEngine engine, World world, Entity target) {
        Entity enemy = engine.createEntity();

        // AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        HealthComponent health = engine.createComponent(HealthComponent.class);
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        ProjectileComponent projectile = engine.createComponent(ProjectileComponent.class);
        ControllerComponent controller = engine.createComponent(ControllerComponent.class);
        AIComponent ai = engine.createComponent(AIComponent.class);

        textureComponent.region = Assets.enemy;
        health.health = 100;

        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(400 * IConversions.PIXELS_TO_METERS / 2, 200 * IConversions.PIXELS_TO_METERS / 2);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);
        body.setUserData(enemy);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(textureComponent.region.getRegionWidth() * IConversions.PIXELS_TO_METERS / 2,
                textureComponent.region.getRegionHeight() * IConversions.PIXELS_TO_METERS / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.filter.categoryBits = ICollisionBits.ENEMY;
        fixtureDef.filter.maskBits = ICollisionBits.WALL | ICollisionBits.PLAYER | ICollisionBits.PROJECTILE;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        bodyComponent.bodyDef = bodyDef;
        bodyComponent.body = body;
        bodyComponent.shape = shape;
        bodyComponent.fixtureDef = fixtureDef;
        bodyComponent.fixture = fixture;
        bodyComponent.moveSpeed.set(10, 10);

        projectile.damage = 10;
        projectile.speed.set(1000, 1000);
        projectile.timeBetweenShots = 1f;
        projectile.collisionCategory = ICollisionBits.ENEMY_PROJECTILE;
        projectile.targetCollisionCategory = ICollisionBits.PLAYER;

        ai.target = target;

        enemy.add(transform);
        enemy.add(textureComponent);
        enemy.add(health);
        enemy.add(bodyComponent);
        enemy.add(projectile);
        enemy.add(controller);
        enemy.add(ai);

        engine.addEntity(enemy);

        return enemy;
    }
}
