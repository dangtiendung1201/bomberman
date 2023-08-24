package entity.enemy;

import static core.Const.*;

import core.Const.DIRECTION;
import graphic.Sprite;
import javafx.application.Platform;

public class Balloom extends Enemy {
    public Balloom(double x, double y) {
        super(x, y);
        speed = 0.25;
        wallPass = false;
        direction = DIRECTION.RIGHT;
        isDead = false;
    }

    public Balloom(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
        speed = 0.25;
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

    @Override
    public void update() {
        if (!isValid(x, y - speed) || isBrick(x, y - speed) || isWall(x, y - speed)) {
            direction = DIRECTION.RIGHT;
        } else if (!isValid(x, y + speed) || isBrick(x, y + speed) || isWall(x, y + speed)) {
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
            // Platform.runLater(() -> {
            //     enemyPos.remove(this);
            // });
        }

        if (isDead) {
            cur = 6;
        }
    }
}
