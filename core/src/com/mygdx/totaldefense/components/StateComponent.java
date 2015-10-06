package com.mygdx.totaldefense.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by dubforce on 9/29/15.
 */
public class StateComponent implements Component {
    private int state = 0;
    public float time = 0.0f;

    public int get() {
        return state;
    }

    public void set(int state) {
        this.state = state;
        time = 0.0f;
    }
}
