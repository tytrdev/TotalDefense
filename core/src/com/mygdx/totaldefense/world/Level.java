package com.mygdx.totaldefense.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/1/15.
 */
public class Level {
    private TiledMap map;
    private World world;

    // renderer
    private TiledMapRenderer renderer;
    private Box2DDebugRenderer debugRenderer;

    // map properties
    private int mapWidth;
    private int mapHeight;
    private int tilePixelWidth;
    private int tilePixelHeight;

    private int mapPixelWidth;
    private int mapPixelHeight;

    public Level(TiledMap map, World world) {
        this.map = map;
        this.world = world;
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.debugRenderer = new Box2DDebugRenderer();

        MapProperties prop = map.getProperties();

        this.mapWidth = prop.get("width", Integer.class);
        this.mapHeight = prop.get("height", Integer.class);
        this.tilePixelWidth = prop.get("tilewidth", Integer.class);
        this.tilePixelHeight = prop.get("tileheight", Integer.class);
        this.mapPixelWidth = this.mapWidth * this.tilePixelWidth;
        this.mapPixelHeight = this.mapHeight * this.tilePixelHeight;
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void debug(OrthographicCamera camera) {
        debugRenderer.render(world, camera.combined.scl(IConversions.METERS_TO_PIXELS));
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public int getMapPixelWidth() {
        return mapPixelWidth;
    }

    public int getMapPixelHeight() {
        return mapPixelHeight;
    }

    public World getWorld() {
        return world;
    }
}
