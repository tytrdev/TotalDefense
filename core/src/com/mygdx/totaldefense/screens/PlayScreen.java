package com.mygdx.totaldefense.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.TotalDefense;
import com.mygdx.totaldefense.components.*;
import com.mygdx.totaldefense.managers.Assets;
import com.mygdx.totaldefense.systems.*;
import com.mygdx.totaldefense.util.ICollisionBits;
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

    // Entities
    private Entity player;
    private Entity camera;

    public PlayScreen(TotalDefense game) {
        this.game = game;

        guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);

        engine = new PooledEngine();
        engine.addSystem(new CameraSystem(engine));
        engine.addSystem(new BodySystem(world));
        engine.addSystem(new ControllerSystem());
        engine.addSystem(new RenderingSystem(game.batch, LevelParser.from(IMapPath.test2, world)));
        engine.addSystem(new ProjectileSystem(engine));

        player = createPlayer(engine, world);
        camera = createCamera(engine, player);
    }

    @Override
    public void render(float delta) {
        update(delta);
        drawUI();
    }

    private void update(float delta) {
        engine.update(delta);
        world.step(delta, 6, 2);
    }

    private void drawUI() {

    }

    // Utility methods
    private static Entity createPlayer(PooledEngine engine, World world) {
        Entity player = engine.createEntity();

        // AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        HealthComponent health = engine.createComponent(HealthComponent.class);
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        ControllerComponent controller = engine.createComponent(ControllerComponent.class);
        ProjectileComponent projectile = engine.createComponent(ProjectileComponent.class);

        textureComponent.region = Assets.player;
        health.health = 100;

        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(textureComponent.region.getRegionWidth() / 2, textureComponent.region.getRegionHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.filter.categoryBits = ICollisionBits.PLAYER;
        fixtureDef.filter.maskBits = ICollisionBits.WALL;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        bodyComponent.bodyDef = bodyDef;
        bodyComponent.body = body;
        bodyComponent.shape = shape;
        bodyComponent.fixtureDef = fixtureDef;
        bodyComponent.fixture = fixture;
        bodyComponent.moveSpeed.set(100, 100);

        projectile.damage = 10;
        projectile.speed.set(1000, 1000);
        projectile.timeBetweenShots = 0.1f;

        player.add(transform);
        player.add(textureComponent);
        player.add(health);
        player.add(bodyComponent);
        player.add(controller);
        player.add(projectile);

        engine.addEntity(player);

        return player;
    }

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
