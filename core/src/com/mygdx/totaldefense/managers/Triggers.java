package com.mygdx.totaldefense.managers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.totaldefense.triggers.Trigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dubforce on 10/14/15.
 */
public class Triggers {
    private static Map<Body, Trigger> triggerMap;
    private static List<Body> triggerables;
    private static List<Body> destructables;
    private static World world;

    public static void load() {
        triggerMap = new HashMap<>();
        triggerables = new ArrayList<>();
        destructables = new ArrayList<>();
    }

    public static void addTrigger(Body body, Trigger trigger) {
        triggerMap.put(body, trigger);
    }

    public static void activate(Body body) {
        Trigger trigger = triggerMap.get(body);

        if(trigger != null) {
            trigger.trigger();
        }
    }

    public static void activateWhenPossible(Body body) {
        triggerables.add(body);
    }

    public static void remove(Body body) {
        destructables.add(body);
    }

    public static void update() {
        for(Body body : triggerables) {
            Trigger trigger = triggerMap.get(body);

            if(trigger != null) {
                trigger.trigger();
            }
        }

        triggerables.clear();

        for(Body body : destructables) {
            Trigger trigger = triggerMap.get(body);

            if(trigger != null) {
                //audioTrigger.dispose();
                triggerMap.remove(body);

                if(world != null) {
                    world.destroyBody(body);
                }
            }
        }

        destructables.clear();
    }

    public static void dispose() {
        for(Map.Entry<Body, Trigger> entry : triggerMap.entrySet()) {
            entry.getValue().dispose();
        }

        triggerMap.clear();
    }

    public static void setWorld(World world) {
        Triggers.world = world;
    }
}
