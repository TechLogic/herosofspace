package de.techlogic.heroes.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import de.techlogic.heroes.model.Ship;
import de.techlogic.heroes.screens.Game;

public class UserInterface {

	private ShapeRenderer shapeRenderer;
	private Box2DDebugRenderer debugRenderer;
	private BitmapFont font;

	private Space space;
	private OrthographicCamera cam;

	private SpriteBatch batch;
	private SpriteBatch debugBatch;

	public UserInterface(Space space, OrthographicCamera cam) {
		this.space = space;
		this.cam = cam;
		if (Game.debugging) {
			
			debugRenderer = new Box2DDebugRenderer();
		}
		
		shapeRenderer = new ShapeRenderer();

		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("data/font.ttf"));

		font = gen.generateFont(15);
		font.setColor(1, 1, 1, 1);

		batch = new SpriteBatch();

		debugBatch = new SpriteBatch();

	}

	public void render() {

		// Rendering of the User Interface
		Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		shapeRenderer.setProjectionMatrix(normalProjection);

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,
				GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeType.Filled);
		Color c = new Color(Color.DARK_GRAY);
		c.add(0, 0, 0, -0.75f);
		shapeRenderer.setColor(c);
		shapeRenderer.rect(Gdx.graphics.getWidth() - 275, Gdx.graphics.getHeight()
				- font.getLineHeight() * 5, 275, font.getLineHeight() * 5);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

		batch.begin();
		font.draw(batch, "Health:", Gdx.graphics.getWidth() - 250,
				Gdx.graphics.getHeight());
		font.draw(batch, "Gold: " + Game.getGold(),
				Gdx.graphics.getWidth() - 250,
				Gdx.graphics.getHeight() - font.getLineHeight());

		batch.end();

		Ship ship = space.getShip();
		float max = ship.getMaxHealth();
		float health = ship.getHealth();

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,
				GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
		shapeRenderer.rect(Gdx.graphics.getWidth() - 125, Gdx.graphics.getHeight()
				- font.getLineHeight(), 100, font.getLineHeight());
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(Gdx.graphics.getWidth() - 125, Gdx.graphics.getHeight()
				- font.getLineHeight(), 100 * health / max,
				font.getLineHeight());
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

		// Rendering of the Debugging tools like FPS or physic shapes
		if (Game.debugging) {

			debugRenderer.render(space.getWorld(), cam.combined);

			debugBatch.begin();
			font.draw(debugBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(),
					0, Gdx.graphics.getHeight());
			font.draw(debugBatch, "X: " + Math.round(ship.getPostition().x), 0,
					Gdx.graphics.getHeight() - font.getCapHeight() - 10);
			font.draw(debugBatch, "Y: " + Math.round(ship.getPostition().y), 0,
					Gdx.graphics.getHeight() - 2 * font.getCapHeight() - 20);

			debugBatch.end();

			shapeRenderer.setProjectionMatrix(cam.combined);
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.MAGENTA);
			Vector2 temp = new Vector2(ship.getPostition());
			temp.add(ship.getDirection().cpy().nor()
					.scl(ship.getFixture().getShape().getRadius()));
			shapeRenderer.line(ship.getPostition(), temp);
			shapeRenderer.end();

			shapeRenderer.setProjectionMatrix(cam.combined);
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.MAGENTA);
			temp = new Vector2(space.getE().getPostition());
			temp.add(space.getE().getDirection().cpy().nor()
					.scl(space.getE().getFixture().getShape().getRadius()));
			shapeRenderer.line(space.getE().getPostition(), temp);

			shapeRenderer.end();

		}

	}
}
