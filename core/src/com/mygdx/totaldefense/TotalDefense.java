package com.mygdx.totaldefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.totaldefense.managers.Assets;
import com.mygdx.totaldefense.managers.Settings;
import com.mygdx.totaldefense.managers.Sounds;
import com.mygdx.totaldefense.screens.MainMenu;

public class TotalDefense extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		// load the game data
		Settings.load();
		Assets.load();
		Sounds.load();

		// set the screen
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// calls current screens render method
		super.render();
	}
}
