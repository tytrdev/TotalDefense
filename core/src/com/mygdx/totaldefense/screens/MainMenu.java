package com.mygdx.totaldefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.totaldefense.TotalDefense;
import com.mygdx.totaldefense.managers.Assets;

/**
 * Created by dubforce on 9/28/15.
 */
public class MainMenu extends ScreenAdapter {
    // the global game instance, used to switch screens from this screen
    private TotalDefense game;

    // screen variables
    private OrthographicCamera guiCamera;
    private Rectangle playBounds;

    // mouse location
    private Vector3 mousePosition;

    public MainMenu(TotalDefense game) {
        this.game = game;

        guiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        playBounds = new Rectangle(0, 0, Assets.playButton.getWidth(), Assets.playButton.getHeight());

        mousePosition = new Vector3();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void pause() {
        super.pause();
    }

    public void update(float delta) {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            guiCamera.unproject(mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(playBounds.contains(mousePosition.x, mousePosition.y)) {
                game.setScreen(new PlayScreen(game));
                this.dispose();
            }
        }
    }

    public void draw() {
        guiCamera.update();
        game.batch.setProjectionMatrix(guiCamera.combined);

        game.batch.begin();
        game.batch.draw(Assets.playButton, playBounds.x, playBounds.y);
        game.batch.end();
    }
}
