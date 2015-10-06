package com.mygdx.totaldefense.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dubforce on 9/30/15.
 */
public class ControllerComponent implements Component {
    public final Vector2 leftAxis = new Vector2();
    public final Vector2 rightAxis = new Vector2();
}
