package io.github.cybervoid.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    private static final Color color = new Color(0.5f, 0.5f, 0.5f, 1);
    private int x = 0;
    private int y = 0;

    Block(int x, int y) {
        this.x = x;
        this.y = y;
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
