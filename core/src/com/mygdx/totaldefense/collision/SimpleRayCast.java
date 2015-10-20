package com.mygdx.totaldefense.collision;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

/**
 * Created by dubforce on 10/17/15.
 */
public class SimpleRayCast implements RayCastCallback {
    private Fixture closestFixture;
    private float closestFraction = 100f;

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
        if(fraction < closestFraction) {
            closestFixture = fixture;
            closestFraction = fraction;
        }

        return fraction;
    }

    public Fixture getClosestFixture() {
        return closestFixture;
    }
}
