package io.github.cybervoid.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

class Fruit {
    private static final Color color = new Color(1, 0, 0, 1);
    private Random randomGenerator;
    private int x;
    private int y;

    Fruit(Block[] blocks) {
        randomGenerator = new Random();
        spawn(blocks);
    }

    void spawn(Block[] blocks) {
        x = randomGenerator.nextInt(Game.TILES_WIDE);
        y = randomGenerator.nextInt(Game.TILES_HIGH);
        for (Block block : blocks) {
            if (block != null) {
                if (block.getX() == x && block.getY() == y) {
                    spawn(blocks);
                    return;
                }
            }
        }
    }

    void draw(ShapeRenderer renderer) {
        renderer.setColor(color);
        renderer.rect(x * Game.TILE_SIZE, y * Game.TILE_SIZE,
                Game.TILE_SIZE, Game.TILE_SIZE);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
