package de.techlogic.heroes.view;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import de.techlogic.heroes.model.Ship;

public class InputHandler implements InputProcessor {

	private Ship ship;

	public InputHandler(Space world) {
		ship = world.getShip();

	}

	@Override
	public boolean keyDown(int keycode) {

		switch (keycode) {
		case Keys.W:
			ship.setVelocity(ship.getVelocity().add(0, 1));
			break;

		case Keys.S:
			ship.setVelocity(ship.getVelocity().add(0, -1));
			break;

		case Keys.A:
			ship.setVelocity(ship.getVelocity().add(-1, 0));
			break;

		case Keys.D:
			ship.setVelocity(ship.getVelocity().add(1, 0));
			break;

		case Keys.UP:
			ship.startRotationClockWhise();
			break;
		case Keys.DOWN:
			ship.startRotationNotClockWhise();
			break;

		case Keys.LEFT:
			ship.startRotationClockWhise();
			break;

		case Keys.RIGHT:
			ship.startRotationNotClockWhise();
			break;
		case Keys.SPACE:
			ship.startShooting();
			break;
		}
		return true;

	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			ship.getVelocity().y = 0;
			break;

		case Keys.S:
			ship.getVelocity().y = 0;
			break;

		case Keys.A:
			ship.getVelocity().x = 0;
			break;

		case Keys.D:
			ship.getVelocity().x = 0;
			break;

		case Keys.UP:
			ship.stopRotationClockWhise();
			break;
		case Keys.DOWN:
			ship.stopRotationNotClockWhise();
			break;

		case Keys.LEFT:
			ship.stopRotationClockWhise();
			break;

		case Keys.RIGHT:
			ship.stopRotationNotClockWhise();
			break;
		case Keys.SPACE:
			ship.stopShooting();
			break;

		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
