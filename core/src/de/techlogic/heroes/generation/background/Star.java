package de.techlogic.heroes.generation.background;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Star {

	private Color color;
	private float x;
	private float y;
	private float radius;

	public Star(float x, float y, Color color, float radius) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getRadius() {
		return radius;
	}

	public void draw(ShapeRenderer renderer) {

		renderer.setColor(color);
		renderer.circle(x, y, radius);

	}
}
