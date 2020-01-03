package io.github.cybervoid.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Timer;

public class Game extends ApplicationAdapter {
	public static final int TILE_SIZE = 32;
	public static final int TILES_WIDE = 28;
	public static final int TILES_HIGH = 28;
	private static final float UPDATE_RATE = 0.05f;
	private static final float DEATH_TIME = 0.5f;

	private ShapeRenderer shapeRenderer;
	private Snake snake;
	private Fruit fruit;
	private Block[] blocks;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		snake = new Snake();
		blocks = new Block[] {
				new Block(7, 8),
				new Block(8,7),
				new Block(2, 2),
				new Block(1, 1),
				new Block(7, TILES_HIGH - 9),
				new Block(8, TILES_WIDE - 8),
				new Block(1, TILES_HIGH - 2),
				new Block(2, TILES_HIGH - 3),
				new Block(TILES_WIDE - 2, 1),
				new Block(TILES_WIDE - 3, 2),
				new Block(TILES_WIDE - 9, 7),
				new Block(TILES_WIDE - 8, 8),
				new Block(TILES_WIDE - 2, TILES_HIGH - 2),
				new Block(TILES_WIDE - 3, TILES_HIGH - 3),
				new Block(TILES_WIDE - 9, TILES_HIGH - 8),
				new Block(TILES_WIDE - 8, TILES_WIDE - 9)

		};
		fruit = new Fruit(blocks);
		scheduleUpdateLoop();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);
		fruit.draw(shapeRenderer);
		snake.draw(shapeRenderer);
		for (Block block : blocks) {
			if (block != null) {
				block.draw(shapeRenderer);
			}
		}
		shapeRenderer.end();
	}

	private void update() {
		snake.update();
		if (snake.fruitEaten(fruit)) {
			fruit.spawn(blocks);
		}
		checkForGameOver();
	}

	private void checkForGameOver() {
		if (snake.collisionOccured(blocks)) {
			Timer.instance().clear();
			Timer.schedule(new Timer.Task() {
				@Override
				public void run() {
					restart();
				}
			}, 1.0f, 0, 1);
		}
	}

	private void restart() {
		snake = new Snake();
		fruit.spawn(blocks);
		scheduleUpdateLoop();
	}

	private void scheduleUpdateLoop() {
		Timer.instance().clear();
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				update();
			}
		}, DEATH_TIME, UPDATE_RATE);
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}