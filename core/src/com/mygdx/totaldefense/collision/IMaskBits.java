package com.mygdx.totaldefense.collision;

/**
 * Created by dubforce on 10/8/15.
 */
public interface IMaskBits {
    // TODO: Make mask bit constants for code cleanliness
    short PLAYER = ICollisionBits.WALL | ICollisionBits.ENEMY_PROJECTILE | ICollisionBits.AUDIO_TRIGGER |
            ICollisionBits.DOOR_TRIGGER | ICollisionBits.STATIC_OBJECT;

    short WALL = ICollisionBits.PLAYER | ICollisionBits.PROJECTILE |
            ICollisionBits.ENEMY | ICollisionBits.ENEMY_PROJECTILE | ICollisionBits.LIGHT;

    short TRIGGER = ICollisionBits.PLAYER;

    // ALL

    short LIGHT = ICollisionBits.ENEMY | ICollisionBits.WALL | ICollisionBits.STATIC_OBJECT;
}
