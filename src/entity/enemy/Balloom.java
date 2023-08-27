package entity.enemy;

import static core.Const.*;

import core.Const.DIRECTION;
import entity.weapon.Bomb;
import graphic.Sprite;

public class Balloom extends Enemy {
    public Balloom(double x, double y) {
        super(x, y);
        speed = 0.0625;
        wallPass = false;
        direction = DIRECTION.RIGHT;
        isDead = false;
    }

    public Balloom(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
        speed = 0.0625;
        wallPass = false;
        direction = DIRECTION.RIGHT;
        isDead = false;
    }

    private boolean isValid(double x, double y) {
        if (x < 0 || y < 0 || x > row || y > col)
            return false;
        return true;
    }

    private boolean isWall(double x, double y) {
        Balloom tmpBalloom = new Balloom(x, y);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (wallPos[i][j] != null && tmpBalloom.checkIntersect(wallPos[i][j]))
                    return true;
            }
        }

        return false;
    }

    private boolean isBrick(double x, double y) {
        Balloom tmpBalloom = new Balloom(x, y);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (brickPos[i][j] != null && tmpBalloom.checkIntersect(brickPos[i][j]))
                    return true;
            }
        }

        return false;
    }

    private boolean isBomber() {
        if (this.checkIntersect(bomberPos)) {
            isDead = true;
            return true;
        }

        return false;
    }

    private boolean checkInt(double num) {
        return (num - (int) num) == 0;
    }

    private boolean isBoom(double x, double y) {
        if (bombPass)
            return false;

        if (checkInt(x) && checkInt(y)) {
            int actualX = (int) x;
            int actualY = (int) y;

            for (Bomb bomb : bombPos) {
                if ((int) bomb.getX() == actualX && (int) bomb.getY() == actualY)
                    return true;
            }

        }

        return false;
    }

    @Override
    public void update() {
        if (!isValid(x, y - speed) || isBrick(x, y - speed) || isWall(x, y - speed) || isBoom(x, y - speed)) {
            direction = DIRECTION.RIGHT;
        } else if (!isValid(x, y + speed) || isBrick(x, y + speed) || isWall(x, y + speed) || isBoom(x, y + speed)) {
            direction = DIRECTION.LEFT;
        }

        if (direction == DIRECTION.LEFT) {
            moving = true;
            cur = (cur + 1) % 3;
            y -= speed;
        } else if (direction == DIRECTION.RIGHT) {
            moving = true;
            cur = (cur + 1) % 3 + 3;
            y += speed;
        } else if (direction == DIRECTION.STAND) {
            moving = false;
        }

        if (isBomber()) {
            bomberPos.setDead(true);
        }

        if (isDead) {
            cur = 6;
        }
    }
}
