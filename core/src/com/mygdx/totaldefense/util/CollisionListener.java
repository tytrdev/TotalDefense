package com.mygdx.totaldefense.util;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by dubforce on 10/4/15.
 */
public class CollisionListener implements ContactListener {
    private PooledEngine engine;
    private World world;

    public CollisionListener(PooledEngine engine, World world) {
        this.engine = engine;
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {

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
