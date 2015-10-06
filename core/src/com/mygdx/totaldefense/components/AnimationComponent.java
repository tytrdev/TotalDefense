package com.mygdx.totaldefense.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by dubforce on 9/29/15.
 */
public class AnimationComponent implements Component {
    public IntMap<Animation> animations = new IntMap<>();
}
