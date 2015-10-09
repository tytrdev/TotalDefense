package com.mygdx.totaldefense.factories;

import com.mygdx.totaldefense.components.BodyComponent;

/**
 * Created by dubforce on 10/9/15.
 */
public class BodyFactory {
    public static BodyComponent bodyComponent() {
        return new BodyComponent();
    }
}
