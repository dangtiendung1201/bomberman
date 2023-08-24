package entity.enemy;

import static core.Const.*;

import core.Const.DIRECTION;
import graphic.Sprite;

public class Balloom extends Enemy {
    public Balloom(double x, double y) {
        super(x, y);
        wallPass = false;
        direction = DIRECTION.RIGHT;
    }

    public Balloom(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
        wallPass = false;
        direction = DIRECTION.RIGHT;
    }

    private boolean isValid(double x, double y) {
        if (x < 0 || y < 0 || x > row || y > col)
            return false;
        return true;
    }

    private boolean isWall(double x, double y) {

        // if (wallPos[x][y] != null)
        //     return true;

        return false;
    }

    private boolean isBrick(double x, double y) {

        // if (brickPos[x][y] != null)
        //     return true;

        return false;
    }

    @Override
    public void update() {
        if (!isValid(x, y - 1) || isBrick(x, y - 1) || isWall(x, y - 1)) {
            direction = DIRECTION.RIGHT;
        } else if (!isValid(x, y + 1) || isBrick(x, y + 1) || isWall(x, y + 1)) {
            direction = DIRECTION.LEFT;
        }

        if (direction == DIRECTION.LEFT) {
            moving = true;
            cur = (cur + 1) % 3;
            y--;
        } else if (direction == DIRECTION.RIGHT) {
            moving = true;
            cur = (cur + 1) % 3 + 3;
            y++;
        }

        if (isBomber()) {
            bomberPos.setDead(true);
        }
    }
}

// Test branch
