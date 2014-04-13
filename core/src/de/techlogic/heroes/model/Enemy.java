package de.techlogic.heroes.model;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;

import de.techlogic.heroes.view.LightRenderer;
import de.techlogic.heroes.view.Space;

public class Enemy extends Shootable {

	public Enemy(Space space, Vector2 postition, float witdh, float height,
			float speed, float rotation) {
		super(space, witdh, height, speed);
		bodyDef.gravityScale = 1;
		bodyDef.angle = rotation;
		bodyDef.fixedRotation = false;
		Double d = Math.toRadians(0);
		bodyDef.angle = d.floatValue();
		bodyDef.awake = true;
		bodyDef.active = true;
		bodyDef.position.set(postition);
		bodyDef.linearVelocity.set(0, 0);
		direction.rotate(45);

		fixtureDef.density = 0;
		CircleShape c = new CircleShape();

		c.setRadius(Math.max(witdh, height) / 2);
		fixtureDef.shape = c;

		texture = new Texture("data/blue1.png");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		health = 8;

	}

	@Override
	public void createLights(RayHandler rayHandler) {
		PointLight p = new PointLight(rayHandler, 1000);
		p.setColor(0, 0, 0.9f, 0.5f);
		p.attachToBody(body, 0, 0);
		p.setSoft(true);
		p.setDistance(100);
		lights = new Light[] { p };
	}

	@Override
	public void setBody(Body body) {
		super.setBody(body);
		body.setUserData(this);
	}

	@Override
	public void update() {
		Ship ship = space.getShip();

		super.update();

		float distance = getPostition().cpy().sub(ship.getPostition()).len();

		if (distance <= 250) {
			updateRotation(ship);

			if (distance <= 50) {
				startShooting();
			} else {
				stopShooting();
			}

			if (distance <= 25) {
				if (ship.getBody().getLinearVelocity().len() <= 40) {
					body.setLinearVelocity(ship.getBody().getLinearVelocity());

				} else {
					body.setLinearVelocity(ship.getBody().getLinearVelocity()
							.cpy().nor().scl(40));

				}

			} else {
				if (body.getLinearVelocity().len() <= 40) {

					body.setLinearVelocity(body
							.getLinearVelocity()
							.cpy()
							.add(ship.getPostition().cpy()
									.sub(body.getPosition()).scl(0.5f)));
			
					
				}
			}

		} else {

			body.setLinearVelocity(0, 0);

		}

	}

	public void advance(float delta, Ship ship) {

	}

	@Override
	protected void shoot() {
		if (shooting) {
			if (time >= 5) {
				Bullet b = new Rockets(space, this, space.getShip(), 1, 1.5f,
						50);
				b.setBody(space.getWorld().createBody(b.getBodyDef()));
				b.createLights(LightRenderer.rayHandler);
				time = 0;
				shot.play();
			}

		}
	}

	private void updateRotation(Ship ship) {

		Vector2 newDir = ship.getPostition().cpy().sub(getPostition()).nor();
		Vector2 oldDir = getDirection();

		float angle = newDir.angle() - oldDir.angle();

		if (angle < 0) {

			startRotationNotClockWhise();
		} else if (angle > 10) {
			startRotationClockWhise();
		} else {
			stopRotationClockWhise();
			stopRotationNotClockWhise();
			startShooting();
		}

	}

}
