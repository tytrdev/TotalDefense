package com.mygdx.totaldefense.factories;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.collision.ICollisionBits;
import com.mygdx.totaldefense.components.BodyComponent;
import com.mygdx.totaldefense.components.TextureComponent;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/9/15.
 */
public class BodyFactory {
    public static BodyComponent player(PooledEngine engine, World world, TextureComponent tc) {
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(
                Gdx.graphics.getWidth() / 2 * IConversions.PPM,
                Gdx.graphics.getHeight() / 2 * IConversions.PPM
        );

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
                tc.region.getRegionWidth() * IConversions.PPM / 2, // hx
                tc.region.getRegionHeight() * IConversions.PPM / 2  // hy
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.filter.categoryBits = ICollisionBits.PLAYER;
        fixtureDef.filter.maskBits = ICollisionBits.WALL | ICollisionBits.ENEMY_PROJECTILE;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        bodyComponent.bodyDef = bodyDef;
        bodyComponent.body = body;
        bodyComponent.shape = shape;
        bodyComponent.fixtureDef = fixtureDef;
        bodyComponent.fixture = fixture;
        bodyComponent.moveSpeed.set(10, 10);

        return bodyComponent;
    }
}
