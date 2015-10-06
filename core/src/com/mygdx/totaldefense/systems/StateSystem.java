package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.totaldefense.components.StateComponent;

/**
 * Created by dubforce on 9/30/15.
 */
public class StateSystem extends IteratingSystem {
    private ComponentMapper<StateComponent> sm;

    public StateSystem() {
        super(Family.all(StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        sm.get(entity).time += deltaTime;
    }
}
