package com.mygdx.totaldefense.screens;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.totaldefense.TotalDefense;
import com.mygdx.totaldefense.collision.CollisionListener;
import com.mygdx.totaldefense.controllers.Gamepad;
import com.mygdx.totaldefense.factories.CameraFactory;
import com.mygdx.totaldefense.factories.EnemyFactory;
import com.mygdx.totaldefense.factories.PlayerFactory;
import com.mygdx.totaldefense.managers.Sounds;
import com.mygdx.totaldefense.managers.Triggers;
import com.mygdx.totaldefense.systems.*;
import com.mygdx.totaldefense.util.IConversions;
import com.mygdx.totaldefense.util.IMapPath;
import com.mygdx.totaldefense.factories.LevelFactory;
import com.mygdx.totaldefense.world.Level;

/**
 * Created by dubforce on 9/29/15.
 */
public class PlayScreen extends ScreenAdapter {
    public enum State {
        PLAYING, CUSTSCNE, PAUSED
    }

    private TotalDefense game;

    // Cameras
    private OrthographicCamera guiCam;

    // Entity System
    private PooledEngine engine;

    // Box2D world
    private World world;
    private RayHandler rayHandler;
    private Level level;

    // State
    private State state;

    public PlayScreen(TotalDefense game) {
        this.game = game;

        state = State.PLAYING;

        guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        engine = new PooledEngine();

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new CollisionListener(world));
        Triggers.setWorld(world);

        // global light settings
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.05f);

        engine.addSystem(new CameraSystem());
        engine.addSystem(new BodySystem());
        engine.addSystem(new ControllerSystem());
        engine.addSystem(new RenderingSystem(game.batch, rayHandler));
        engine.addSystem(new ProjectileSystem(engine, world));
        engine.addSystem(new AISystem());
        engine.addSystem(new LightSystem());
        engine.addSystem(new HealthSystem(engine, world));

        level = LevelFactory.from(this, IMapPath.demo, engine, world, rayHandler);
        engine.getSystem(RenderingSystem.class).setLevel(level);

        Controllers.addListener(new Gamepad());
    }

    @Override
    public void render(float delta) {
        switch(state) {
            case PAUSED:
                // no update
                break;
            case PLAYING:
                update(delta);
                break;
            case CUSTSCNE:
                // no update
                break;
            default:
                break;
        }

        // draw the GUI. May become unnecessary
        //drawUI();
    }

    private void update(float delta) {
        world.step(delta, 6, 2);
        engine.update(delta);
        Triggers.update();
    }

    private void drawUI() {

    }

    public void setLevel(String levelName) {
        // Dispose of all triggers
        Triggers.dispose();

        // remove all entities from the engine
        engine.removeAllEntities();

        // clear world of all bodies
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);

        for(Body body : bodies) {
            world.destroyBody(body);
        }

        // remove all lights from the world
        rayHandler.removeAll();

        // generate new level
        level = LevelFactory.from(this, "maps/" + levelName, engine, world, rayHandler);

        // set level in rendering system
        engine.getSystem(RenderingSystem.class).setLevel(level);
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void dispose() {
        super.dispose();

        // dispose of all triggers
        Triggers.dispose();
    }
}
