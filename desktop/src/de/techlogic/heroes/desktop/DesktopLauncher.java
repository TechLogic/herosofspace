package de.techlogic.heroes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.techlogic.heroes.HeroesOfSpace;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 720;
		config.width = 1024;
		config.title = "Heroes of Space";
		config.useGL30 = false;
		config.resizable = false;
		new LwjglApplication(new HeroesOfSpace(), config);
	}
}
