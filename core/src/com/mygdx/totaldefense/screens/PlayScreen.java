package com.mygdx.totaldefense.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.TotalDefense;
import com.mygdx.totaldefense.collision.CollisionListener;
import com.mygdx.totaldefense.components.*;
import com.mygdx.totaldefense.factories.EnemyFactory;
import com.mygdx.totaldefense.factories.PlayerFactory;
import com.mygdx.totaldefense.managers.Assets;
import com.mygdx.totaldefense.systems.*;
import com.mygdx.totaldefense.collision.ICollisionBits;
import com.mygdx.totaldefense.util.IConversions;
import com.mygdx.totaldefense.util.IMapPath;
import com.mygdx.totaldefense.util.LevelParser;

/**
 * Created by dubforce on 9/29/15.
 */
public class PlayScreen extends ScreenAdapter {
    private TotalDefense game;

    // Cameras
    private OrthographicCamera guiCam;

    // Entity System
    private PooledEngine engine;

    // Box2D world
    private World world;

    // Collision listener
    private CollisionListener collisionListener;

    // Entities
    private Entity player;
    private Entity camera;
    private Entity firstEnemy;

    public PlayScreen(TotalDefense game) {
        this.game = game;

        guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        engine = new PooledEngine();
        world = new World(new Vector2(0, 0), true);

        engine.addSystem(new CameraSystem());
        engine.addSystem(new BodySystem());
        engine.addSystem(new ControllerSystem());
        engine.addSystem(new RenderingSystem(game.batch, LevelParser.from(IMapPath.test2, world)));
        engine.addSystem(new ProjectileSystem(engine));
        engine.addSystem(new AISystem());

        collisionListener = new CollisionListener(world);
        world.setContactListener(collisionListener);

        player = PlayerFactory.player(engine, world);
        camera = createCamera(engine, player);
        firstEnemy = EnemyFactory.enemy(engine, world, player);
    }

    @Override
    public void render(float delta) {
        update(delta);
        drawUI();
    }

    private void update(float delta) {
        world.step(delta, 6, 2);
        collisionListener.update(delta);
        engine.update(delta);
    }

    private void drawUI() {

    }

    // Utility methods
    private static Entity createCamera(PooledEngine engine, Entity target) {
        Entity entity = engine.createEntity();

        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;

        entity.add(camera);

        engine.addEntity(entity);

        return entity;
    }
}
