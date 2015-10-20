package com.mygdx.totaldefense.components;

import box2dLight.ConeLight;
import box2dLight.Light;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dubforce on 10/11/15.
 */
public class LightComponent implements Component {
    public Light light;
    public Vector2 offset;
}
