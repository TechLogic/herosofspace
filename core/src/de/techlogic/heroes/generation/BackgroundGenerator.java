package de.techlogic.heroes.generation;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import de.techlogic.heroes.generation.background.Background;
import de.techlogic.heroes.generation.background.Star;

public class BackgroundGenerator {

	private int step;
	private Random random;
	private ArrayList<Star> stars;
	private int levelWidth;
	private int starCount = 0;

	public BackgroundGenerator(int levelWidth) {
		this.levelWidth = levelWidth + 200;
	}

	public Background generateBackground() {
		random = new Random();
		step = 20;
		stars = new ArrayList<Star>();
		Vector2 position = new Vector2(-1 * levelWidth / 2, -1 * levelWidth / 2);
		Star star;
		Vector2 starPosition;

		while (position.y <= levelWidth / 2) {
			position.y += step;
			while (position.x <= levelWidth / 2) {
				position.x += step;
				starPosition = position.cpy();
				changeStarPosition(starPosition);
				float r = random.nextFloat() + 1f;
				float g = random.nextFloat() + 0.75f;
				float b = 0;
				float a = 0;
				while (a <= 0.5f) {
					a = random.nextFloat();
				}

				Color starColor = new Color(r, g, b, a);
				star = new Star(starPosition.x, starPosition.y, starColor,
						random.nextFloat() / 2);
				stars.add(star);
				starCount++;
			}
			position.x = -1 * levelWidth / 2;
		}
		float r = random.nextFloat() + 0.5f;
		float g = random.nextFloat() + 0.5f;
		float b = random.nextFloat() + 0.5f;
		Color c = new Color(r, g, b, 0);
		float f = 1;
		while (f > 0.25f && f > 0.1f) {
			f = random.nextFloat();
		}

		c.a = f;
		Gdx.app.log("Stars created", starCount + " Stars created!");
		return new Background(stars, c);

	}

	private void changeStarPosition(Vector2 position) {
		if (random.nextBoolean()) {
			position.x += random.nextInt(step);
		} else {
			position.x -= random.nextInt(step);
		}
		if (random.nextBoolean()) {
			position.y += random.nextInt(step);
		} else {
			position.y -= random.nextInt(step);
		}
	}

}
