package com.mygdx.totaldefense.util;

/**
 * Created by dubforce on 10/4/15.
 */
public interface ICollisionBits {
    short PLAYER = 0x1;
    short WALL = PLAYER << 1;
    short PROJECTILE = WALL << 1;
    short ENEMY = PROJECTILE << 1;
}
