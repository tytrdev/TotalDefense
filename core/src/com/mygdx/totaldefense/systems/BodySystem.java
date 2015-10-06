package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.totaldefense.components.BodyComponent;
import com.mygdx.totaldefense.components.TransformComponent;

/**
 * Created by dubforce on 9/29/15.
 */
public class BodySystem extends IteratingSystem {
    private World world;

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BodyComponent> bm;

    public BodySystem(World world) {
        super(Family.all(TransformComponent.class, BodyComponent.class).get());

        this.world = world;

        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        BodyComponent body = bm.get(entity);

        transform.position.set(body.body.getPosition().x, body.body.getPosition().y, transform.position.z);
    }
}
