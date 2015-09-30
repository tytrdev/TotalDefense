package com.mygdx.totaldefense.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.totaldefense.managers.Settings;

/**
 * Created by dubforce on 9/28/15.
 */
public class Sounds {
    public static Sound bulletSound;
    public static Music mainTheme;

    public static void load() {
        // sound asset loading
    }

    public static void playSound(Sound sound) {
        if(Settings.soundEnabled) {
            sound.play(1);
        }
    }
}
