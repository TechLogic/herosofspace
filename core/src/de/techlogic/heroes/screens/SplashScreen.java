package de.techlogic.heroes.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.techlogic.heroes.HeroesOfSpace;
import de.techlogic.heroes.tween.SpriteTween;

public class SplashScreen implements Screen {

	private Texture splashTexture;
	private Texture backgroundTexture;
	private Sprite splashSprite;
	private Sprite background;
	private SpriteBatch batch;
	private HeroesOfSpace game;
	private TweenManager manager;

	public SplashScreen(HeroesOfSpace game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		manager.update(delta);
		batch.begin();
		background.draw(batch);
		splashSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		splashTexture = new Texture("data/splashscreen.png");
		backgroundTexture = new Texture("data/space.jpg");
		background = new Sprite(backgroundTexture);
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setPosition(0, 0);
		splashSprite = new Sprite(splashTexture);
		splashSprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight() / 3);
		splashSprite.setPosition(
				Gdx.graphics.getWidth() / 2 - splashSprite.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - splashSprite.getHeight() / 2);
		splashSprite.setColor(1, 1, 1, 0);

		batch = new SpriteBatch();

		Tween.registerAccessor(Sprite.class, new SpriteTween());
		manager = new TweenManager();

		TweenCallback tc = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};
		// normal value 2.5f and 3f
		Tween.to(splashSprite, SpriteTween.ALPHA, 2.5f).target(1)
				.ease(TweenEquations.easeInCirc).repeatYoyo(1, 3f)
				.setCallback(tc).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
		
	}

	private void tweenCompleted() {
		game.setScreen(new MainMenu(game));
		Gdx.app.log(HeroesOfSpace.LOG, "Main Menu created");

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
	}

}
