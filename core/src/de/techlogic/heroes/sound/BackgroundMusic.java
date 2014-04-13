package de.techlogic.heroes.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {

	private Music music;

	public BackgroundMusic() {
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
	}

	public void play() {
		music.setLooping(true);
		music.play();
	}

}
