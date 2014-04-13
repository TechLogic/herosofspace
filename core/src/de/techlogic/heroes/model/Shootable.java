package de.techlogic.heroes.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import de.techlogic.heroes.view.LightRenderer;
import de.techlogic.heroes.view.Space;

public class Shootable extends MoveableEntity {

	protected Vector2 direction;
	private Double oldRotation;
	protected boolean shooting;
	protected float time;
	protected Sound shot;

	public Shootable(Space space, float witdh, float height, float speed) {
		super(space, witdh, height, speed);
		direction = new Vector2(1, 1);

		oldRotation = new Double(0);
		time = 2.5f;
		shooting = false;
		shot = Gdx.audio.newSound(Gdx.files.internal("data/shoot.mp3"));

	}

	public Vector2 getDirection() {
		return direction;
	}

	@Override
	public void update() {
		super.update();
		Double d = Math.toDegrees(getRotation());
		direction.rotate(d.floatValue() - oldRotation.floatValue());
		oldRotation = d;
		time += Gdx.graphics.getDeltaTime();
		shoot();
	}

	public void startShooting() {
		shooting = true;

	}

	public void stopShooting() {
		shooting = false;

	}

	protected void shoot() {

		if (shooting) {
			if (time >= 2.5) {
				Bullet b = new Bullet(space, this, 1, 1.5f, 1000);
				b.setBody(space.getWorld().createBody(b.getBodyDef()));
				b.createLights(LightRenderer.rayHandler);
				time = 0;
				shot.play();
			}

		}

	}

}
