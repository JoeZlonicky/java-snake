package io.github.cybervoid.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

class Snake {
    private static final Color color = new Color(1, 1, 1, 1);

    private ArrayList<int[]> pieces;
    private Direction direction;

    Snake() {
        pieces = new ArrayList<int[]>();
        direction = Direction.RIGHT;
        pieces.add(new int[] {(int) (Game.TILES_WIDE / 2), (int) (Game.TILES_HIGH / 2)});
        increaseSize();
        increaseSize();
    }

    void update() {
        handleInput();
        updateBody();
        updateHead();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && direction != Direction.DOWN) {
            direction = Direction.UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && direction != Direction.LEFT) {
            direction = Direction.RIGHT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && direction != Direction.UP) {
            direction = Direction.DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && direction != Direction.RIGHT) {
            direction = Direction.LEFT;
        }
    }

    private void updateHead() {
        pieces.get(0)[0] += direction.x;
        pieces.get(0)[1] += direction.y;
        handleMovementPastEdge();
    }

    private void updateBody() {
        for (int i = pieces.size() - 1; i > 0; --i) {
            pieces.get(i)[0] = pieces.get(i-1)[0];
            pieces.get(i)[1] = pieces.get(i-1)[1];
        }
    }

    private void handleMovementPastEdge() {
        if (pieces.get(0)[0] > Game.TILES_WIDE - 1) {
            pieces.get(0)[0] = 0;
        }
        if (pieces.get(0)[0] < 0) {
            pieces.get(0)[0] = Game.TILES_WIDE - 1;
        }
        if (pieces.get(0)[1] > Game.TILES_HIGH - 1) {
            pieces.get(0)[1] = 0;
        }
        if (pieces.get(0)[1] < 0) {
            pieces.get(0)[1] = Game.TILES_HIGH - 1;
        }
    }

    boolean fruitEaten(Fruit fruit) {
        if (pieces.get(0)[0] == fruit.getX() && pieces.get(0)[1] == fruit.getY()) {
            increaseSize();
            return true;
        }
        return false;
    }

    private void increaseSize() {
        pieces.add(new int[] {
                pieces.get(pieces.size() - 1)[0],
                pieces.get(pieces.size() - 1)[1]
        });
    }

    boolean collisionOccured(Block[] blocks) {
        for (int[] piece : pieces) {
            if (pieces.get(0) != piece && pieces.get(0)[0] == piece[0] && pieces.get(0)[1] == piece[1]) {
                return true;
            }
        }
        for (Block block : blocks) {
            if (block != null) {
                if (block.getX() == pieces.get(0)[0] && block.getY() == pieces.get(0)[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    void draw(ShapeRenderer renderer) {
        renderer.setColor(color);
        for (int[] piece : pieces) {
            renderer.rect(piece[0] * Game.TILE_SIZE, piece[1] * Game.TILE_SIZE,
                    Game.TILE_SIZE, Game.TILE_SIZE);
        }
    }

}

enum Direction {
    UP (0, 1),
    RIGHT (1, 0),
    DOWN (0, -1),
    LEFT (-1, 0);

    final int x;
    final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
