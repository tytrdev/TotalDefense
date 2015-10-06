package com.mygdx.totaldefense.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by dubforce on 9/29/15.
 */
public class BodyComponent implements Component {
    public BodyDef bodyDef = null;
    public Body body = null;
    public Shape shape = null;
    public FixtureDef fixtureDef = null;
    public Fixture fixture = null;

    public final Vector2 moveSpeed = new Vector2();
}
