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

    private int maxBomb = 1;
    private int cntBomb = 0;
    private Bomb[] bomb = new Bomb[BOMBSITEM_MAX + 1];

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

    public int getCntBomb() {
        return cntBomb;
    }

    public Bomb[] getBomb() {
        return bomb;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    private boolean isWall(int x, int y) {
        if (x < 0 || y < 0 || x > row || y > col)
            return false;

        if (wallPos[x][y] != null)
            return true;

        return false;
    }

    private boolean isBrick(int x, int y) {
        if (x < 0 || y < 0 || x > row || y > col)
            return false;

        if (brickPos[x][y] != null)
            return true;

        return false;
    }

    private boolean isBoom(int x, int y) {
        for (int i = 0; i < maxBomb; i++) {
            if (bomb[i] != null && bomb[i].getX() == x && bomb[i].getY() == y)
                return true;
        }

        return false;
    }

    private boolean isEnemy() {
        if (enemyPos[x][y] != null)
            return true;

        return false;
    }

    private void checkItem() {
        if (itemPos[x][y] != null) {
            if (itemPos[x][y] instanceof BombsItem) {
                maxBomb = ((BombsItem) itemPos[x][y]).update(x, y, maxBomb);
            }
        }
    }

    public void update() {
        if (keyListener.isPressed(KeyCode.UP)) {
            setDirection(DIRECTION.UP);
            cur = (cur + 1) % 3;

            if (!isWall(x - 1, y) && !isBrick(x - 1, y) && !isBoom(x - 1, y)) {
                isMoving = true;
                x--;
            }
        } else if (keyListener.isPressed(KeyCode.DOWN)) {
            setDirection(DIRECTION.DOWN);
            cur = (cur + 1) % 3 + 3;

            if (!isWall(x + 1, y) && !isBrick(x + 1, y) && !isBoom(x + 1, y)) {
                isMoving = true;
                x++;
            }
        } else if (keyListener.isPressed(KeyCode.LEFT) && !isBoom(x, y - 1)) {
            setDirection(DIRECTION.LEFT);
            cur = (cur + 1) % 3 + 6;

            if (!isWall(x, y - 1) && !isBrick(x, y - 1) && !isBoom(x, y - 1)) {
                isMoving = true;
                y--;
            }
        } else if (keyListener.isPressed(KeyCode.RIGHT)) {
            setDirection(DIRECTION.RIGHT);
            cur = (cur + 1) % 3 + 9;

            if (!isWall(x, y + 1) && !isBrick(x, y + 1) && !isBoom(x, y + 1)) {
                isMoving = true;
                y++;
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
