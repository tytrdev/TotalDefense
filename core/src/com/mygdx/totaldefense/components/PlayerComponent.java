package com.mygdx.totaldefense.components;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Component;

/**
 * Created by dubforce on 10/8/15.
 */
public class PlayerComponent implements Component {
    // identifies the entity as the player
    public PointLight playerLight = null;
}
