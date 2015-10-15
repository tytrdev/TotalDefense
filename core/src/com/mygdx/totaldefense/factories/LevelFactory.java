package com.mygdx.totaldefense.factories;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.totaldefense.managers.Triggers;
import com.mygdx.totaldefense.screens.PlayScreen;
import com.mygdx.totaldefense.triggers.OccurenceLevel;
import com.mygdx.totaldefense.triggers.Trigger;
import com.mygdx.totaldefense.triggers.TriggerType;
import com.mygdx.totaldefense.world.Level;

/**
 * Created by dubforce on 10/1/15.
 */
public class LevelFactory {
    public static Level from(PlayScreen playScreen, String mapPath, World world) {
        TiledMap map = new TmxMapLoader().load(mapPath);

        // add Box2D objects to world, and create entities?
        MapObjects walls = map.getLayers().get("Walls").getObjects();
        MapObjects triggers = map.getLayers().get("Triggers").getObjects();

        for(MapObject wall : walls) {
            BodyFactory.wall(world, (RectangleMapObject)wall);
        }

        for(MapObject trigger : triggers) {
            MapProperties triggerProperties = trigger.getProperties();
            TriggerType triggerType = TriggerType.valueOf(triggerProperties.get("type").toString());

            if(triggerType.equals(TriggerType.AUDIO)) {
                OccurenceLevel occurenceLevel = OccurenceLevel.valueOf(triggerProperties.get("level").toString());

                Body triggerBody = BodyFactory.audioTrigger(world, (RectangleMapObject) trigger);
                Trigger audioTrigger = TriggerFactory.audio(
                        triggerBody,
                        occurenceLevel,
                        triggerProperties.get("audioFile").toString()
                );

                Triggers.addTrigger(triggerBody, audioTrigger);
            }
            else if(triggerType.equals(TriggerType.DOOR)) {
                Body triggerBody = BodyFactory.door(world, (RectangleMapObject) trigger);
                Trigger doorTrigger = TriggerFactory.door(playScreen, triggerProperties.get("level").toString());

                Triggers.addTrigger(triggerBody, doorTrigger);
            }
        }

        return new Level(map, world);
    }
}
