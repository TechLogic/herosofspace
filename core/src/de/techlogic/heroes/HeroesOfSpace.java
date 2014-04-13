package de.techlogic.heroes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class HeroesOfSpace extends Game {
	public static final String VERSION = "0.1 - ALPHA";
	public static final String LOG = "Heros of space";

	public HeroesOfSpace() {
	}

	@Override
	public void create() {
		Screen splash = new de.techlogic.heroes.screens.SplashScreen(this);
		setScreen(splash);

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
		}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
