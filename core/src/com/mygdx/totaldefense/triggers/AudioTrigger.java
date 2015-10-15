package com.mygdx.totaldefense.triggers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.totaldefense.managers.Sounds;
import com.mygdx.totaldefense.managers.Triggers;

/**
 * Created by dubforce on 10/14/15.
 */
public class AudioTrigger implements Trigger {
    private Body triggerBody;
    private OccurenceLevel occurences;
    private Sound sound;

    public AudioTrigger(Body triggerBody, OccurenceLevel occurences, Sound sound) {
        this.triggerBody = triggerBody;
        this.occurences = occurences;
        this.sound = sound;
    }

    @Override
    public void trigger() {
        if(sound != null) {
            Sounds.playSound(sound);
        }

        if(occurences.equals(OccurenceLevel.ONCE)) {
            Triggers.remove(triggerBody);
        }
    }

    @Override
    public void dispose() {
        if(sound != null) {
            sound.dispose();
        }
    }
}
