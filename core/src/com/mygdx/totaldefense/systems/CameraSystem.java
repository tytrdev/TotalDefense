package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.totaldefense.components.CameraComponent;
import com.mygdx.totaldefense.components.TransformComponent;
import com.mygdx.totaldefense.world.Level;

/**
 * Created by dubforce on 9/29/15.
 */
public class CameraSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<CameraComponent> cm;

    public CameraSystem() {
        super(Family.all(CameraComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        cm = ComponentMapper.getFor(CameraComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraComponent camera = cm.get(entity);

        RenderingSystem renderingSystem = getEngine().getSystem(RenderingSystem.class);
        int mapWidth = renderingSystem.getLevel().getMapPixelWidth();
        int mapHeight = renderingSystem.getLevel().getMapPixelHeight();

        if(camera.target != null) {
            TransformComponent target = tm.get(camera.target);

            if(target != null) {
                camera.camera.position.x = target.position.x;
                camera.camera.position.y = target.position.y;

                if(target.position.x < Gdx.graphics.getWidth() / 2) {
                    camera.camera.position.x = Gdx.graphics.getWidth() / 2;
                }
                else if(target.position.x > mapWidth - Gdx.graphics.getWidth() / 2) {
                    camera.camera.position.x = mapWidth - Gdx.graphics.getWidth() / 2;
                }

                if(target.position.y < Gdx.graphics.getHeight() / 2) {
                    camera.camera.position.y = Gdx.graphics.getHeight() / 2;
                }
                else if(target.position.y > mapHeight - Gdx.graphics.getHeight() / 2) {
                    camera.camera.position.y = mapHeight - Gdx.graphics.getHeight() / 2;
                }
            }
        }
    }
}
