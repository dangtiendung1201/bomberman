package entity.character;

import static core.Const.*;
import static graphic.Sprite.*;

import core.Const.DIRECTION;
import entity.item.BombsItem;
import entity.weapon.Bomb;
import graphic.Sprite;
import input.KeyListener;
import javafx.scene.input.KeyCode;

public class Bomber extends Character {
    private boolean isDead = false;
    private double speed = 0.05;

    private int maxBomb = 1;
    private int cntBomb = 0;
    private Bomb[] bomb = new Bomb[BOMBSITEM_MAX + 1];

    public Bomber(double x, double y) {
        super(x, y);
        isMoving = false;
    }

    public Bomber(double x, double y, Sprite[] sprite, KeyListener keyListener) {
        super(x, y, sprite, keyListener);
        isMoving = false;
    }

    public int getCntBomb() {
        return cntBomb;
    }

    public Bomb[] getBomb() {
        return bomb;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
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
        Bomber tmpBomber = new Bomber(x, y);

        for (int i = 0; i < maxBomb; i++) {
            if (bomb[i] != null && tmpBomber.checkIntersect(bomb[i]))
                return true;
        }

        return false;
    }

    private boolean isEnemy() {
        // if (enemyPos[x][y] != null)
        // return true;

        return false;
    }

    private void checkItem() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (itemPos[i][j] != null && this.checkIntersect(itemPos[i][j])) {
                    if (itemPos[i][j] instanceof BombsItem) {
                        maxBomb = ((BombsItem) itemPos[i][j]).update(i, j, maxBomb);
                    }
                }
            }
        }
    }

    public void update() {
        if (keyListener.isPressed(KeyCode.UP)) {
            setDirection(DIRECTION.UP);
            cur = (cur + 1) % 3;

            if (isValid(x - speed, y) && !isWall(x - speed, y) && !isBrick(x - speed, y) && !isBoom(x - speed, y)) {
                isMoving = true;
                x -= speed;
            }
        } else if (keyListener.isPressed(KeyCode.DOWN)) {
            setDirection(DIRECTION.DOWN);
            cur = (cur + 1) % 3 + 3;

            if (isValid(x + speed, y) && !isWall(x + speed, y) && !isBrick(x + speed, y) && !isBoom(x + speed, y)) {
                isMoving = true;
                x += speed;
            }
        } else if (keyListener.isPressed(KeyCode.LEFT) && !isBoom(x, y - speed)) {
            setDirection(DIRECTION.LEFT);
            cur = (cur + 1) % 3 + 6;

            if (isValid(x, y - speed) && !isWall(x, y - speed) && !isBrick(x, y - speed) && !isBoom(x, y - speed)) {
                isMoving = true;
                y -= speed;
            }
        } else if (keyListener.isPressed(KeyCode.RIGHT)) {
            setDirection(DIRECTION.RIGHT);
            cur = (cur + 1) % 3 + 9;

            if (isValid(x, y + speed) && !isWall(x, y + speed) && !isBrick(x, y + speed) && !isBoom(x, y + speed)) {
                isMoving = true;
                y += speed;
            }
        } else if (keyListener.isPressed(KeyCode.SPACE)) {
            if (cntBomb < maxBomb && !isBoom(x, y)) {
                bomb[cntBomb] = new Bomb(x, y, bombImage);
                cntBomb++;
            }

        } else if (keyListener.isReleased()) {
            isMoving = false;
            cur = 0;
        }

        checkItem();
        if (isEnemy()) {
            isDead = true;
        }

        if (isDead) {
            cur = 12;
        }
        // System.out.println("x: " + x + " y: " + y);
        // System.out.println("maxBomb: " + maxBomb);
    }
}
