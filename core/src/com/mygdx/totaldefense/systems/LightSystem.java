package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.totaldefense.components.LightComponent;
import com.mygdx.totaldefense.components.TransformComponent;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/11/15.
 */
public class LightSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<LightComponent> lm;

    public LightSystem() {
        super(Family.all(TransformComponent.class, LightComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        lm = ComponentMapper.getFor(LightComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        LightComponent light = lm.get(entity);

        light.light.setPosition(
                transform.position.x * IConversions.PPM,
                transform.position.y * IConversions.PPM
        );

        light.light.setDirection(transform.rotation + 90f);
    }
}
