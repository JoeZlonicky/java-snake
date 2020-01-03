package io.github.cybervoid.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.cybervoid.snake.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Game.TILES_WIDE * Game.TILE_SIZE;
		config.height = Game.TILES_HIGH * Game.TILE_SIZE;
		config.title = "Snake";
		new LwjglApplication(new Game(), config);
	}
}
