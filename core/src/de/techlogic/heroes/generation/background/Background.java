package de.techlogic.heroes.generation.background;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;

public class Background {

	private ArrayList<Star> stars;
	private ShapeRenderer shape;
	private Color color;

	public Background(ArrayList<Star> stars, Color color) {
		this.stars = stars;
		this.color = color;
		shape = new ShapeRenderer();

	}

	public ArrayList<Star> getStars() {
		return stars;
	}

	public void render(OrthographicCamera cam) {
		shape.setProjectionMatrix(cam.combined);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shape.begin(ShapeType.Filled);
		for (Star s : stars) {
			if (isStarVisible(s, cam))
				s.draw(shape);
		}

		Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shape.setProjectionMatrix(normalProjection);
		shape.setColor(color);
		shape.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shape.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	private boolean isStarVisible(Star star, OrthographicCamera cam) {
		return (star.getX() >= cam.position.x - cam.viewportWidth
				&& star.getX() <= cam.position.x + cam.viewportWidth 
				&& star.getY() >= cam.position.y - cam.viewportHeight  && star
				.getY() <= cam.position.y + cam.viewportHeight

		);
	}
}
