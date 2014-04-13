package de.techlogic.heroes.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import de.techlogic.heroes.view.Space;

public class Wall extends Entity {

	public Wall(Space space, Vector2 pos, float width, float height) {
		super(space, width, height);

		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(pos);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		fixtureDef.shape = shape;
		fixtureDef.restitution = 1f;

	}

	@Override
	public void setBody(Body body) {
		this.body = body;
		fixture = body.createFixture(fixtureDef);
		body.setUserData(this);
	}
}
