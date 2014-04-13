package de.techlogic.heroes.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;

import de.techlogic.heroes.view.Space;

public class Astroid extends MoveableEntity {

	private Random rnd = new Random();

	public Astroid(Space space, Vector2 position, float witdh, float height,
			float speed, float rotation) {
		super(space, witdh, height, speed);
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.gravityScale = 1;
		bodyDef.angle = rotation;
		bodyDef.fixedRotation = false;
		bodyDef.position.set(position);

		float x = rnd.nextInt(10) + rnd.nextFloat();
		float y = rnd.nextInt(10) + rnd.nextFloat();
		if (rnd.nextBoolean()) {
			x = x * -1;
		}
		if (rnd.nextBoolean()) {
			y = y * -1;
		}

		bodyDef.linearVelocity.set(x, y);

		fixtureDef.density = 100;
		CircleShape c = new CircleShape();
		fixtureDef.restitution = 0.3f;

		c.setRadius(Math.max(witdh, height) / 2);
		fixtureDef.shape = c;
		texture = new Texture("data/asteroid.png");

	}

	@Override
	public void setBody(Body body) {
		super.setBody(body);
		body.setUserData(this);
	}

	@Override
	public void destroy() {
		super.destroy();
		Gold gold = new Gold(space, getPostition(), 2.5f, 2.5f, 0, 0);
		 gold.setBody(space.getWorld().createBody(gold.getBodyDef()));
		
	}
}
