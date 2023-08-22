package entity.character;

import core.Const.DIRECTION;
import entity.Entity;
import graphic.Sprite;
import input.KeyListener;

public abstract class Character extends Entity {
    protected KeyListener keyListener;
    protected DIRECTION direction;
    protected int speed;
    protected boolean isMoving = false;
    public Character(int x, int y) {
        super(x, y);
    }
    public Character(int x, int y, Sprite[] sprite, KeyListener keyListener) {
        super(x, y, sprite);
        this.keyListener = keyListener;
    }

    protected void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public abstract void update();
}
