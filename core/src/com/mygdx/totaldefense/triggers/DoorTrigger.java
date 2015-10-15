package com.mygdx.totaldefense.triggers;

import com.mygdx.totaldefense.TotalDefense;
import com.mygdx.totaldefense.screens.PlayScreen;

/**
 * Created by dubforce on 10/15/15.
 */
public class DoorTrigger implements Trigger {
    private PlayScreen playScreen;
    private String level;

    public DoorTrigger(PlayScreen playScreen, String level) {
        this.playScreen = playScreen;
        this.level = level;
    }

    @Override
    public void trigger() {
        playScreen.setLevel(level);
    }

    @Override
    public void dispose() {

    }
}
