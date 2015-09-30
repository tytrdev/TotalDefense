package com.mygdx.totaldefense.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by dubforce on 9/28/15.
 */
public class Assets {
    // GUI assets
    public static Texture playButton;

    public static Texture sprite;
    public static TiledMap map;

    public static void load() {
        playButton = new Texture("UI/menus/play.png");
    }
}
