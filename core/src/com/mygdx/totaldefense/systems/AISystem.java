package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.totaldefense.components.AIComponent;
import com.mygdx.totaldefense.components.BodyComponent;
import com.mygdx.totaldefense.components.ControllerComponent;
import com.mygdx.totaldefense.components.TransformComponent;

/**
 * Created by dubforce on 10/8/15.
 */
public class AISystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BodyComponent> bm;
    private ComponentMapper<ControllerComponent> cm;
    private ComponentMapper<AIComponent> am;

    public AISystem() {
        super(Family.all(TransformComponent.class, BodyComponent.class, ControllerComponent.class, AIComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);
        cm = ComponentMapper.getFor(ControllerComponent.class);
        am = ComponentMapper.getFor(AIComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        BodyComponent body = bm.get(entity);
        ControllerComponent controller = cm.get(entity);
        AIComponent ai = am.get(entity);

        TransformComponent targetTransform = tm.get(ai.target);

        Vector3 direction = targetTransform.position.cpy().sub(transform.position);

        // fix jittery movement
        if(direction.x > -50 && direction.x < 50) {
            direction.x = 0;
        }
        if(direction.y > -50 && direction.y < 50) {
            direction.y = 0;
        }

        body.body.setLinearVelocity(
                body.moveSpeed.x * Math.signum(direction.x),
                body.moveSpeed.y * Math.signum(direction.y)
        );

        controller.rightAxis.set(Math.signum(direction.x), Math.signum(direction.y));
    }
}
