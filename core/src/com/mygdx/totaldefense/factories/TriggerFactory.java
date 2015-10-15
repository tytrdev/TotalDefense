package com.mygdx.totaldefense.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.totaldefense.screens.PlayScreen;
import com.mygdx.totaldefense.triggers.AudioTrigger;
import com.mygdx.totaldefense.triggers.DoorTrigger;
import com.mygdx.totaldefense.triggers.OccurenceLevel;

/**
 * Created by dubforce on 10/14/15.
 */
public class TriggerFactory {
    public static AudioTrigger audio(Body triggerBody, OccurenceLevel occurenceLevel, String audioFile) {
        return new AudioTrigger(
                triggerBody,
                occurenceLevel,
                Gdx.audio.newSound(Gdx.files.internal("sounds/" + audioFile))
        );
    }

    public static DoorTrigger door(PlayScreen screen, String level) {
        return new DoorTrigger(
                screen,
                level
        );
    }
}
