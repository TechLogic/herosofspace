package de.techlogic.heroes.model;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;

import de.techlogic.heroes.view.Space;

public class Ship extends Shootable {

	private int maxHealth;
	private int maxSpeed = 80;
	private int acceleration = 10;

	public Ship(Space space, float witdh, float height, float speed,
			float rotation) {
		super(space, witdh, height, speed);
		bodyDef.gravityScale = 1;
		bodyDef.angle = rotation;
		bodyDef.fixedRotation = true;
		Double d = Math.toRadians(45);
		bodyDef.angle = d.floatValue();
		bodyDef.awake = true;
		bodyDef.active = true;
		bodyDef.linearVelocity.set(0, 0);
		direction.rotate(45);

		fixtureDef.density = 100;
		CircleShape c = new CircleShape();

		c.setRadius(Math.max(witdh, height) / 2);
		fixtureDef.shape = c;

		texture = new Texture("data/F5S2.png");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		maxHealth = 50;
		health = 50;

	}

	@Override
	public void setBody(Body body) {
		super.setBody(body);
		body.setUserData(this);
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	@Override
	public void createLights(RayHandler rayHandler) {
		PointLight p = new PointLight(rayHandler, 1000);
		p.setColor(1, 1, 1, 0.35f);
		p.attachToBody(body, 0, 0);
		p.setSoft(true);
		p.setDistance(100);
		lights = new  Light[]{p};
}

	@Override
	public void update() {
		super.update();

		Vector2 temp = new Vector2(velocity.x * Gdx.graphics.getDeltaTime(),
				velocity.y * Gdx.graphics.getDeltaTime());

		Double newX = Math.cos(getRotation()) * temp.x
				- Math.sin(getRotation()) * temp.y;
		Double newY = Math.cos(getRotation()) * temp.y
				+ Math.sin(getRotation()) * temp.x;

		Vector2 rotationChange = new Vector2(newX.floatValue() * acceleration,
				newY.floatValue() * acceleration);

		Vector2 newVelocity = body.getLinearVelocity().cpy()
				.add(rotationChange);
		if (newVelocity.len() <= maxSpeed) {
			body.setLinearVelocity(newVelocity);
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);

	}

}
