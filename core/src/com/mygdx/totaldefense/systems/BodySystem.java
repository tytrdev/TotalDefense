package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.totaldefense.components.BodyComponent;
import com.mygdx.totaldefense.components.TransformComponent;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 9/29/15.
 */
public class BodySystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BodyComponent> bm;

    public BodySystem() {
        super(Family.all(TransformComponent.class, BodyComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        BodyComponent body = bm.get(entity);

        if(body.body == null) {
            getEngine().removeEntity(entity);
        }
        else {
            transform.position.set(body.body.getPosition().x * IConversions.METERS_TO_PIXELS,
                    body.body.getPosition().y * IConversions.METERS_TO_PIXELS, transform.position.z);
        }
    }
}
