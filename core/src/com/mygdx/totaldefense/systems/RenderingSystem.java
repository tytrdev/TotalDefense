package com.mygdx.totaldefense.systems;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.totaldefense.components.TextureComponent;
import com.mygdx.totaldefense.components.TransformComponent;
import com.mygdx.totaldefense.managers.Settings;
import com.mygdx.totaldefense.util.IConversions;
import com.mygdx.totaldefense.world.Level;

import java.util.Comparator;

/**
 * Created by dubforce on 9/30/15.
 */
public class RenderingSystem extends IteratingSystem {
    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera camera;

    // map
    private Level level;
    private RayHandler rayHandler;

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<TextureComponent> texM;

    public RenderingSystem(SpriteBatch batch, Level level, RayHandler rayHandler) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());

        this.batch = batch;
        this.level = level;
        this.rayHandler = rayHandler;

        tm = ComponentMapper.getFor(TransformComponent.class);
        texM = ComponentMapper.getFor(TextureComponent.class);

        renderQueue = new Array<>();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0f);

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity a, Entity b) {
                return (int)Math.signum(tm.get(b).position.z - tm.get(a).position.z);
            }
        };
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // render tiled map
        level.render(camera);

        batch.begin();

        for(Entity entity : renderQueue) {
            TextureComponent texture = texM.get(entity);

            if (texture.region == null) {
                continue;
            }

            TransformComponent transform = tm.get(entity);

            float width = texture.region.getRegionWidth();
            float height = texture.region.getRegionHeight();
            float x = width * 0.5f;
            float y = height * 0.5f;

            batch.draw(
                    texture.region,
                    transform.position.x - x, transform.position.y - y,
                    x, y,
                    width, height,
                    transform.scale.x, transform.scale.y,
                    transform.rotation
            );
        }

        batch.end();
        renderQueue.clear();

        if(Settings.debug) {
            level.debug(camera);
        }

        if(Settings.renderLight) {
            rayHandler.setCombinedMatrix(
                    camera.combined,
                    camera.position.x * IConversions.PPM, camera.position.y * IConversions.PPM,
                    camera.viewportWidth * IConversions.PPM, camera.viewportHeight * IConversions.PPM
            );
            rayHandler.updateAndRender();
        }
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Level getLevel() {
        return level;
    }
}
