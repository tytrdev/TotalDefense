package com.mygdx.totaldefense.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.components.*;
import com.mygdx.totaldefense.managers.Assets;
import com.mygdx.totaldefense.collision.ICollisionBits;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/4/15.
 */
public class ProjectileSystem extends IteratingSystem {
    private PooledEngine engine;

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<ControllerComponent> cm;
    private ComponentMapper<ProjectileComponent> pm;

    public ProjectileSystem(PooledEngine engine) {
        super(Family.all(TransformComponent.class, ControllerComponent.class, ProjectileComponent.class).get());

        this.engine = engine;

        tm = ComponentMapper.getFor(TransformComponent.class);
        cm = ComponentMapper.getFor(ControllerComponent.class);
        pm = ComponentMapper.getFor(ProjectileComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        ControllerComponent controller = cm.get(entity);
        ProjectileComponent prefab = pm.get(entity);

        prefab.timeSinceLastShot += deltaTime;

        // if there is some right rotation
        if(!controller.rightAxis.equals(Vector2.Zero) && prefab.timeSinceLastShot >= prefab.timeBetweenShots) {
            spawnProjectile(transform, controller, prefab);
        }
    }

    private void spawnProjectile(TransformComponent targetTransform, ControllerComponent targetController, ProjectileComponent prefab) {
        Entity projectile = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        transform.position.set(targetTransform.position);
        transform.position.z = 5;
        textureComponent.region = Assets.bullet;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(targetTransform.position.x * IConversions.PPM,
                        targetTransform.position.y * IConversions.PPM);

        // Create a body in the world using our definition
        Body body = engine.getSystem(RenderingSystem.class).getLevel().getWorld().createBody(bodyDef);
        body.setUserData(projectile);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(textureComponent.region.getRegionWidth() * IConversions.PPM / 2,
                    textureComponent.region.getRegionHeight() * IConversions.PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.filter.categoryBits = prefab.collisionCategory;
        fixtureDef.filter.maskBits = (short) (prefab.targetCollisionCategory | ICollisionBits.WALL);

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        bodyComponent.bodyDef = bodyDef;
        bodyComponent.body = body;
        bodyComponent.shape = shape;
        bodyComponent.fixtureDef = fixtureDef;
        bodyComponent.fixture = fixture;
        bodyComponent.moveSpeed.set(prefab.speed);
        bodyComponent.body.setLinearVelocity(prefab.speed.x * targetController.rightAxis.x, prefab.speed.y * targetController.rightAxis.y);

        projectile.add(transform);
        projectile.add(bodyComponent);
        projectile.add(textureComponent);

        prefab.timeSinceLastShot = 0f;

        //Sounds.playSound(Sounds.bulletSound);
    }
}
