package com.mygdx.totaldefense.triggers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.totaldefense.managers.Triggers;
import com.mygdx.totaldefense.screens.PlayScreen;

/**
 * Created by dubforce on 10/18/15.
 */
public class CutsceneTrigger implements Trigger {
    private PlayScreen game;
    private Body triggerBody;
    private Music audio;

    public CutsceneTrigger(PlayScreen game, Body triggerBody, String audioFile) {
        this.game = game;
        this.triggerBody = triggerBody;

        audio = Gdx.audio.newMusic(Gdx.files.internal("sounds/" + audioFile));
    }

    @Override
    public void trigger() {
        game.setState(PlayScreen.State.CUSTSCNE);

        audio.play();

        audio.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                game.setState(PlayScreen.State.PLAYING);
            }
        });

        Triggers.remove(triggerBody);
    }

    @Override
    public void dispose() {
        if(audio != null) {
            audio.dispose();
        }
    }
}
