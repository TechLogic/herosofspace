package de.techlogic.heroes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import de.techlogic.heroes.HeroesOfSpace;
import de.techlogic.heroes.sound.BackgroundMusic;
import de.techlogic.heroes.view.LightRenderer;
import de.techlogic.heroes.view.Space;
import de.techlogic.heroes.view.UserInterface;
import de.techlogic.heroes.view.WorldRenderer;

public class Game implements Screen {

	private Space world;
	private WorldRenderer renderer;
	private UserInterface ui;
	private BackgroundMusic music;
	private LightRenderer lights;
	private OrthographicCamera camera;
	private static int gold;

	public static boolean debugging = true;

	public Game(HeroesOfSpace game) {
		// this.game = game;
		
		camera = new OrthographicCamera();
		world = new Space(game);
		lights = new LightRenderer(world, camera);
		renderer = new WorldRenderer(world,camera);
		ui = new UserInterface(world,camera);

		if (debugging == false) {
			music = new BackgroundMusic();
			music.play();
		}

		gold = 0;
	}

	public static void addGold(int gold) {
		Game.gold += gold;
	}

	public static int getGold() {
		return gold;
	}

	@Override
	public void render(float delta) {

		world.update();
		

		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();
		lights.render();
		ui.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		renderer.dispose();
		world.dispose();
	}

}
