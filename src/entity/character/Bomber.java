package entity.character;

import static core.Const.*;
import static media.Sprite.*;
import static media.UserInterfere.*;

import core.Const.DIRECTION;
import core.Const.STATE;
import entity.enemy.Enemy;
import entity.item.BombsItem;
import entity.weapon.Bomb;
import input.KeyListener;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import media.Sprite;

public class Bomber extends Character {
    private boolean protectedState = true;

    private boolean bombPass = false;
    private boolean flamePass = false;

    private int maxBomb = 1;
    private int cntBomb = 0;

    private int flameSize = 1;

    public Bomber(double x, double y) {
        super(x, y);
    }

    public Bomber(double x, double y, Sprite[] sprite, KeyListener keyListener) {
        super(x, y, sprite, keyListener);
    }

    public boolean getProtectedState() {
        return protectedState;
    }

    public int getCntBomb() {
        return cntBomb;
    }

    public int getMaxBomb() {
        return maxBomb;
    }

    public int getFlameSize() {
        return flameSize;
    }

    public boolean getFlamePass() {
        return flamePass;
    }

    public void setFlameSize(int flameSize) {
        this.flameSize = flameSize;
    }

    public void reduceBomb() {
        cntBomb--;
    }

    public void reset() {
        life--;
        bomberDieSound.play();

        if (life == 0) {
            gameState = STATE.GAMEOVER;
        } else {
            x = 1;
            y = 1;

            protectedState = true;

        }
    }

    private boolean isValid(double x, double y) {
        if (x < 0 || y < 0 || x > row || y > col)
            return false;
        return true;
    }

    private boolean isWall(double x, double y) {
        Bomber tmpBomber = new Bomber(x, y);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (wallPos[i][j] != null && tmpBomber.checkIntersect(wallPos[i][j]))
                    return true;
            }
        }

        return false;
    }

    private boolean isBrick(double x, double y) {
        Bomber tmpBomber = new Bomber(x, y);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (brickPos[i][j] != null && tmpBomber.checkIntersect(brickPos[i][j]))
                    return true;
            }
        }

        return false;
    }

    private boolean isBoom(double x, double y) {
        if (bombPass)
            return false;

        if (x - (int)x == 0 && y - (int)y == 0) {
            int actualX = (int) x;
            int actualY = (int) y;

            for (Bomb bomb : bombPos) {
                if ((int) bomb.getX() == actualX && (int) bomb.getY() == actualY)
                    return true;
            }

        }

        return false;
    }

    private void placeBomb() {
        if (cntBomb < maxBomb) {
            int actualX;
            int actualY;

            if (direction == DIRECTION.UP) {
                actualX = (int) x + 1;
                actualY = (int) y;
            } else if (direction == DIRECTION.DOWN) {
                actualX = (int) x;
                actualY = (int) y;
            } else if (direction == DIRECTION.LEFT) {
                actualX = (int) x;
                actualY = (int) y + 1;
            } else if (direction == DIRECTION.RIGHT) {
                actualX = (int) x;
                actualY = (int) y;
            } else {
                actualX = (int) Math.round(x);
                actualY = (int) Math.round(y);
            }

            Bomb tmpBomb = new Bomb(actualX, actualY, bombImage);

            for (Bomb bomb : bombPos) {
                if (tmpBomb.checkIntersect(bomb))
                    return;
            }

            placeBombSound.play();

            Platform.runLater(() -> {
                bombPos.add(tmpBomb);
                cntBomb++;
            });
        }
    }

    private boolean isEnemy() {
        for (Enemy enemy : enemyPos) {
            if (this.checkIntersect(enemy))
                return true;
        }

        return false;
    }

    private void checkItem() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (itemPos[i][j] != null && this.checkIntersect(itemPos[i][j])) {
                    if (itemPos[i][j] instanceof BombsItem) {
                        maxBomb = ((BombsItem) itemPos[i][j]).update(maxBomb);
                    }
                    itemPos[i][j] = null;
                }
            }
        }
    }

    public void update() {
        if (keyListener.isPressed(KeyCode.UP)) {
            protectedState = false;
            setDirection(DIRECTION.UP);
            cur = (cur + 1) % 3;

            if (isValid(x - speed, y) && !isWall(x - speed, y) && !isBrick(x - speed, y) && !isBoom(x - speed, y)) {
                x -= speed;
            }
        }
        if (keyListener.isPressed(KeyCode.DOWN)) {
            protectedState = false;
            setDirection(DIRECTION.DOWN);
            cur = (cur + 1) % 3 + 3;

            if (isValid(x + speed, y) && !isWall(x + speed, y) && !isBrick(x + speed, y) && !isBoom(x + speed, y)) {
                x += speed;
            }
        }
        if (keyListener.isPressed(KeyCode.LEFT)) {
            protectedState = false;
            setDirection(DIRECTION.LEFT);
            cur = (cur + 1) % 3 + 6;

            if (isValid(x, y - speed) && !isWall(x, y - speed) && !isBrick(x, y - speed) && !isBoom(x, y - speed)) {
                y -= speed;
            }
        }
        if (keyListener.isPressed(KeyCode.RIGHT)) {
            protectedState = false;
            setDirection(DIRECTION.RIGHT);
            cur = (cur + 1) % 3 + 9;

            if (isValid(x, y + speed) && !isWall(x, y + speed) && !isBrick(x, y + speed) && !isBoom(x, y + speed)) {
                y += speed;
            }
        }

        if (keyListener.isPressed(KeyCode.SPACE)) {
            placeBomb();
        }

        if (keyListener.isReleased()) {
            direction = DIRECTION.STAND;
            cur = 0;
        }

        checkItem();
        if (!protectedState && isEnemy()) {
            cur = 12;
            reset();
        }

        // System.out.println("x: " + x + " y: " + y);
        System.out.println("life: " + life);
    }
}
