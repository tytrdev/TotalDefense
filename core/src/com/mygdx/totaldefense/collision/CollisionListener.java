package com.mygdx.totaldefense.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dubforce on 10/4/15.
 */
public class CollisionListener implements ContactListener {
    private World world;

    private List<Body> destroyables;

    public CollisionListener(World world) {
        this.world = world;
        destroyables = new ArrayList<>();
    }

    public void update(float deltaTime) {
        for(Body body : destroyables) {
            world.destroyBody(body);
        }

        destroyables.clear();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Filter af = a.getFilterData();
        Filter bf = b.getFilterData();

        if(af.categoryBits == ICollisionBits.ENEMY_PROJECTILE) {
            System.out.println("Adding A to destroyables");
            destroyables.add(a.getBody());
        }
        else if(bf.categoryBits == ICollisionBits.ENEMY_PROJECTILE) {
            System.out.println("Adding B to destroyables");
            destroyables.add(b.getBody());
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
