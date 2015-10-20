package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.totaldefense.components.BodyComponent;
import com.mygdx.totaldefense.components.TransformComponent;
import com.mygdx.totaldefense.managers.Bodies;
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

        transform.position.set(body.body.getPosition().x * IConversions.MPP,
                body.body.getPosition().y * IConversions.MPP, transform.position.z);

        Bodies.add(body.body, entity);
    }
}
