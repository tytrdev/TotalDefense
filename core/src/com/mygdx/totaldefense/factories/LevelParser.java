package com.mygdx.totaldefense.factories;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.collision.ICollisionBits;
import com.mygdx.totaldefense.util.IConversions;
import com.mygdx.totaldefense.world.Level;

/**
 * Created by dubforce on 10/1/15.
 */
public class LevelParser {
    public static Level from(String mapPath, World world) {
        TiledMap map = new TmxMapLoader().load(mapPath);

        // add Box2D objects to world, and create entities?
        MapObjects walls = map.getLayers().get("Walls").getObjects();

        for(MapObject wall : walls) {
            // Initialize body component
            RectangleMapObject object = (RectangleMapObject)wall;

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            object.getRectangle().getPosition(bodyDef.position); // sets body position
            bodyDef.position.scl(IConversions.PPM);

            // Create a body in the world using our definition
            Body body = world.createBody(bodyDef);

            PolygonShape shape = getRectangle(object);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 1f;
            fixtureDef.filter.categoryBits = ICollisionBits.WALL;
            fixtureDef.filter.maskBits = ICollisionBits.PLAYER | ICollisionBits.PROJECTILE |
                    ICollisionBits.ENEMY | ICollisionBits.ENEMY_PROJECTILE;

            body.createFixture(fixtureDef);

            shape.dispose();
        }

        return new Level(map, world);
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();

        Vector2 center = new Vector2(rectangle.getWidth() * IConversions.PPM / 2,
                rectangle.getHeight() * IConversions.PPM / 2);

        polygon.setAsBox(center.x, center.y, center, 0.0f);

        return polygon;
    }
}
