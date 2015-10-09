package com.mygdx.totaldefense.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.totaldefense.collision.ICollisionBits;

/**
 * Created by dubforce on 9/30/15.
 */
public class ProjectileComponent implements Component {
    public int damage = 10;
    public final Vector2 speed = new Vector2(100, 100);
    public float timeBetweenShots = 1f; // 1 second
    public float timeSinceLastShot = 0f;
    public short collisionCategory = ICollisionBits.ENEMY_PROJECTILE;
    public short targetCollisionCategory = ICollisionBits.PLAYER;
}
