package com.mygdx.totaldefense.controllers;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dubforce on 10/18/15.
 */
public class Controllers {
    private static Vector2 leftAxis = new Vector2();
    private static Vector2 rightAxis = new Vector2();

    public static Vector2 getLeftAxis() {
        return leftAxis;
    }

    public static void setLeftAxis(Vector2 leftAxis) {
        Controllers.leftAxis.set(leftAxis);
    }

    public static Vector2 getRightAxis() {
        return rightAxis;
    }

    public static void setRightAxis(Vector2 rightAxis) {
        Controllers.rightAxis.set(rightAxis);
    }
}
