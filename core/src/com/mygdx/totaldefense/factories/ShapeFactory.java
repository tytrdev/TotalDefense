package com.mygdx.totaldefense.factories;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.totaldefense.util.IConversions;

/**
 * Created by dubforce on 10/14/15.
 */
public class ShapeFactory {
    public static PolygonShape fromRectangle(RectangleMapObject mapObject) {
        Rectangle rectangle = mapObject.getRectangle();
        PolygonShape polygon = new PolygonShape();

        Vector2 center = new Vector2(rectangle.getWidth() * IConversions.PPM / 2,
                rectangle.getHeight() * IConversions.PPM / 2);

        polygon.setAsBox(center.x, center.y, center, 0.0f);

        return polygon;
    }
}
