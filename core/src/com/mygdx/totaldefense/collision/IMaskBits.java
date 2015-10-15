package com.mygdx.totaldefense.collision;

/**
 * Created by dubforce on 10/8/15.
 */
public interface IMaskBits {
    // TODO: Make mask bit constants for code cleanliness
    short PLAYER = ICollisionBits.WALL | ICollisionBits.ENEMY_PROJECTILE | ICollisionBits.AUDIO_TRIGGER |
            ICollisionBits.DOOR_TRIGGER;

    short TRIGGER = ICollisionBits.PLAYER;
}
