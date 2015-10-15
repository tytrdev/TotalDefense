package com.mygdx.totaldefense.collision;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.managers.Triggers;

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
            body.setActive(false);
        }

        destroyables.clear();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Filter af = a.getFilterData();
        Filter bf = b.getFilterData();

        if(af.categoryBits == ICollisionBits.AUDIO_TRIGGER) {
            Triggers.activate(a.getBody());
        }
        else if(af.categoryBits == ICollisionBits.DOOR_TRIGGER) {
            Triggers.activateWhenPossible(a.getBody());
        }
        else if(bf.categoryBits == ICollisionBits.AUDIO_TRIGGER) {
            Triggers.activate(b.getBody());
        }
        else if(bf.categoryBits == ICollisionBits.DOOR_TRIGGER) {
            Triggers.activateWhenPossible(b.getBody());
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
