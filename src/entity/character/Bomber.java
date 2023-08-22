package entity.character;

import static core.Const.*;

import core.Const.DIRECTION;
import entity.tile.Wall;
import graphic.Sprite;
import input.KeyListener;
import javafx.scene.input.KeyCode;

public class Bomber extends Character {
    public Bomber(int x, int y) {
        super(x, y);
        speed = 60;
        isMoving = false;
    }

    public Bomber(int x, int y, Sprite[] sprite, KeyListener keyListener) {
        super(x, y, sprite, keyListener);
        speed = 60;
        isMoving = false;
    }

    private boolean isWall(int x, int y) {
        if (x < 0 || y < 0 || x > row || y > col)
            return false;

        if (wallPos[x][y] != null)
            return true;
        // System.out.println(x + " " + y);

        return false;
    }

    public void update() {
        if (keyListener.isPressed(KeyCode.UP)) {
            if (!isWall(x - 1, y)) {
                isMoving = true;
                x--;
                setDirection(DIRECTION.UP);
                cur = (cur + 1) % 3;
            }
        } else if (keyListener.isPressed(KeyCode.DOWN)) {
            if (!isWall(x + 1, y)) {
                isMoving = true;
                x++;
                setDirection(DIRECTION.DOWN);
                cur = (cur + 1) % 3 + 3;
            }
        } else if (keyListener.isPressed(KeyCode.LEFT)) {
            if (!isWall(x, y - 1)) {
                isMoving = true;
                y--;
                setDirection(DIRECTION.LEFT);
                cur = (cur + 1) % 3 + 6;
            }
        } else if (keyListener.isPressed(KeyCode.RIGHT)) {
            if (!isWall(x, y + 1)) {
                isMoving = true;
                y++;
                setDirection(DIRECTION.RIGHT);
                cur = (cur + 1) % 3 + 9;
            }
        } else {
            isMoving = false;
            cur = 12;
        }
        System.out.println(x + " " + y);
    }
}
