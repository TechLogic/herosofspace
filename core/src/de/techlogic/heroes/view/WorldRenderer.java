package de.techlogic.heroes.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.techlogic.heroes.generation.AstroidGenerator;
import de.techlogic.heroes.generation.BackgroundGenerator;
import de.techlogic.heroes.generation.background.Background;
import de.techlogic.heroes.model.Entity;
import de.techlogic.heroes.model.Ship;

public class WorldRenderer {

	private Space space;
	private SpriteBatch batch;
	private Ship ship;
	private OrthographicCamera cam;
	private float oldRotation;
	private BackgroundGenerator generator;
	private Background background;
	private int zoom = 10;
	
	private Texture texture;

	public WorldRenderer(Space space,OrthographicCamera camera) {
		this.space = space;
		generator = new BackgroundGenerator(space.getLevelWidth());
		batch = new SpriteBatch();
		cam = camera;
		cam.setToOrtho(false, Gdx.graphics.getWidth() / zoom,
				Gdx.graphics.getHeight() / zoom);

		cam.update();

		batch.setProjectionMatrix(cam.combined);

		ship = space.getShip();
		ship.setPostition(new Vector2(0, 0));

		oldRotation = ship.getRotation();

		background = generator.generateBackground();
		
		AstroidGenerator gen = new AstroidGenerator();
		
		texture = gen.createAstroid();
		

	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void render() {

		// Update the position of the camera
		Double d = Math.toDegrees(ship.getRotation());

		cam.position.x = ship.getPostition().x;
		cam.position.y = ship.getPostition().y;
		if (oldRotation != d.floatValue()) {
			cam.rotate(oldRotation - d.floatValue());
			oldRotation = d.floatValue();
		}
		cam.update();

		background.render(cam);
		// Rendering of all entities
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		for (Entity e : space.getEntities()) {
			if (e.getPostition().x + e.getWitdh() >= cam.position.x
					- cam.viewportWidth
					&& e.getPostition().x - e.getWitdh() <= cam.position.x
							+ cam.viewportWidth
					&& e.getPostition().y + e.getHeight() >= cam.position.y
							- cam.viewportHeight
					&& e.getPostition().y - e.getHeight() <= cam.position.y
							+ cam.viewportHeight) {
				e.draw(batch);
			}
		}
		batch.draw(texture, 0, 0);
		batch.end();

	}

	public void dispose() {
		batch.dispose();
	}
}
