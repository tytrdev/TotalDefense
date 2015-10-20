package com.mygdx.totaldefense.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by dubforce on 10/17/15.
 */
public class Bodies {
    private static final ObjectMap<Body, Entity> objectMap = new ObjectMap<>();

    public static void add(Body body, Entity entity) {
        objectMap.put(body, entity);
    }

    public static Entity get(Body body) {
        return objectMap.get(body);
    }

    public static void remove(Body body) {
        objectMap.remove(body);
    }
}
