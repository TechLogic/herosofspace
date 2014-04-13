package de.techlogic.heroes.model;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;

import de.techlogic.heroes.screens.Game;
import de.techlogic.heroes.view.Space;

public class Gold extends MoveableEntity {

	public Gold(Space space, Vector2 position, float witdh, float height,
			float speed, float rotation) {
		super(space, witdh, height, speed);

		bodyDef.type = BodyType.DynamicBody;
		bodyDef.gravityScale = 1;
		bodyDef.angle = rotation;
		bodyDef.fixedRotation = false;
		bodyDef.position.set(position);
		fixtureDef.density = 0;
		CircleShape c = new CircleShape();
		fixtureDef.restitution = 0;
		fixtureDef.filter.categoryBits =(short) 20;

		c.setRadius(Math.max(witdh, height) /2);
		fixtureDef.shape = c;
		texture = new Texture("data/coin.png");

	}

	@Override
	public void setBody(Body body) {
		super.setBody(body);
		body.setUserData(this);
	}

	@Override
	public void hit(int damage) {
	}

	@Override
	public void update() {
		super.update();
		float distance = calculateDistanceToShip();
		if (distance <= 1000) {
			if (distance <= 40) {
				destroy = true;
			} else {
				Vector2 impulse = new Vector2(space.getShip().getPostition());
				impulse.sub(getPostition());
				impulse.nor().scl(0.2f);
				body.setTransform(getPostition().add(impulse), getRotation());
			}
		}

	}

	private float calculateDistanceToShip() {
		return getPostition().dst2(space.getShip().getPostition());
	}

	@Override
	public void destroy() {
		super.destroy();
		Game.addGold(100);

	}
	
	@Override
	public void createLights(RayHandler rayHandler) {
		PointLight p = new PointLight(rayHandler, 1000);
		p.setColor(0,0,1f,1f);
		p.attachToBody(body, 0, 0);
		p.setSoft(true);
		p.setDistance(2.5f);
		lights = new  Light[]{p};
}

}
