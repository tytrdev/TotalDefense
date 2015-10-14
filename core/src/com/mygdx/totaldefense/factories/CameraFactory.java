package com.mygdx.totaldefense.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.totaldefense.components.CameraComponent;
import com.mygdx.totaldefense.systems.RenderingSystem;

/**
 * Created by dubforce on 10/14/15.
 */
public class CameraFactory {
    public static Entity camera(PooledEngine engine, Entity target) {
        Entity entity = engine.createEntity();

        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;

        entity.add(camera);

        engine.addEntity(entity);

        return entity;
    }
}
