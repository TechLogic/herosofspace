package de.techlogic.heroes.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import de.techlogic.heroes.view.Space;

public abstract class MoveableEntity extends Entity {

	protected float speed;
	protected Vector2 velocity;

	private boolean rotateClockWhise, rotateNotClockWhise;

	public MoveableEntity(Space space,float witdh, float height, float speed) {
		super(space ,witdh, height);
		this.speed = speed;
		velocity = new Vector2(0, 0);
		rotateClockWhise = false;
		rotateNotClockWhise = false;

		bodyDef.type = BodyType.DynamicBody;
	}

	public void startRotationClockWhise() {
		rotateClockWhise = true;

	}

	public void startRotationNotClockWhise() {
		rotateNotClockWhise = true;

	}

	public void stopRotationClockWhise() {
		rotateClockWhise = false;

	}

	public void stopRotationNotClockWhise() {
		rotateNotClockWhise = false;

	}

	protected void rotate(boolean rotateClockwise) {
		Double d = Math.toRadians(1);
		if (rotateClockwise == true) {

			setRotation(getRotation() + d.floatValue());
		} else {
			setRotation(getRotation() - d.floatValue());
		}
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getRotation() {
		return body.getAngle();
	}

	public void setRotation(float rotation) {
		body.setTransform(body.getPosition(), rotation);
	}

	@Override
	public void update() {
		if (rotateClockWhise == true) {
			rotate(true);
		}
		if (rotateNotClockWhise == true) {
			rotate(false);
		}

	}

	@Override
	public void draw(SpriteBatch batch) {
		Double d = Math.toDegrees(getRotation());
		batch.draw(texture, getPostition().x - getWitdh() / 2f,
				getPostition().y - getHeight() / 2f, getWitdh() / 2,
				getHeight() / 2, getWitdh(), getHeight(), 1, 1, d.floatValue(),
				0, 0, texture.getWidth(), texture.getHeight(), false, false);
	}

}
