package de.techlogic.heroes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import de.techlogic.heroes.HeroesOfSpace;

public class MainMenu implements Screen {
	private HeroesOfSpace game;
	private Stage stage;
	private BitmapFont font;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private TextButton button;

	private Texture logoTexture;
	private Image logo;
	private Texture backgroundTexture;
	private Image background;

	public MainMenu(HeroesOfSpace game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		stage.act();

		batch.begin();
		stage.draw();

		batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {

		logo.setSize(0.75f * Gdx.graphics.getWidth(),
				0.25f * Gdx.graphics.getHeight());
		logo.setX(Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2);
		logo.setY(0.8f * Gdx.graphics.getHeight() - logo.getHeight());

		button.setWidth(150);
		button.setHeight(100);
		button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		button.setY(logo.getY() - button.getHeight() -  Gdx.graphics.getHeight()*0.1f);

		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setPosition(0, 0);
		stage = new Stage();
		stage.clear();
		stage.addActor(background);
		stage.addActor(logo);
		stage.addActor(button);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		backgroundTexture = new Texture("data/space.jpg");
		background = new Image(backgroundTexture);

		logoTexture = new Texture("data/splashscreen.png");
		logo = new Image(logoTexture);

		batch = new SpriteBatch();
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("data/font.ttf"));

		font = gen.generateFont(25);
		font.setColor(0, 0, 0, 1);
		gen.dispose();
		atlas = new TextureAtlas("data/button.atlas");
		skin = new Skin(atlas);

		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("Button");
		style.down = skin.getDrawable("Button_Pressed");
		style.font = font;
		style.fontColor = Color.BLACK;
		button = new TextButton("PLAY", style);

		button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			};

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new Game(game));
			}
		});
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		stage.dispose();
	}

}
