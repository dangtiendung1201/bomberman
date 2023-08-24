package entity.character;

import static core.Const.*;
import static graphic.Sprite.*;

import core.Const.DIRECTION;
import entity.enemy.Enemy;
import entity.item.BombsItem;
import entity.weapon.Bomb;
import graphic.Sprite;
import input.KeyListener;
import javafx.scene.input.KeyCode;

public class Bomber extends Character {
    private boolean isDead = false;
    private double speed = 0.25;
    private boolean bombPass = false;

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

    private boolean checkInt(double num) {
        return (num - (int) num) == 0;
    }

    private boolean isBoom(double x, double y) {
        if (bombPass)
            return false;

        if (checkInt(x) && checkInt(y)) {
            int actualX = (int) x;
            int actualY = (int) y;

            for (int i = 0; i < maxBomb; i++) {
                if (bomb[i] != null && (int) bomb[i].getX() == actualX && (int) bomb[i].getY() == actualY)
                    return true;
            }

        }

        return false;
    }

    private void placeBomb() {
        if (cntBomb < maxBomb) {
            int actualX = (int) x;
            int actualY = (int) y;

            for (int i = 0; i < maxBomb; i++) {
                if (bomb[i] != null && (int) bomb[i].getX() == actualX && (int) bomb[i].getY() == actualY) {
                    return;
                }
            }

            bomb[cntBomb] = new Bomb((double) actualX, (double) actualY, bombImage);
            cntBomb++;
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
        if (checkInt(x) && checkInt(y)) {
            int actualX = (int) x;
            int actualY = (int) y;

            if (itemPos[actualX][actualY] instanceof BombsItem) {
                maxBomb = ((BombsItem) itemPos[actualX][actualY]).update(maxBomb);

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
        } else if (keyListener.isPressed(KeyCode.LEFT)) {
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
            placeBomb();
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
        System.out.println("x: " + x + " y: " + y);
        // System.out.println("maxBomb: " + maxBomb);
    }
}
