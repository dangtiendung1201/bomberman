package entity.character;

import core.Const.DIRECTION;
import entity.Entity;
import graphic.Sprite;
import input.KeyListener;

public abstract class Character extends Entity {
    protected KeyListener keyListener;
    protected DIRECTION direction;
    protected double speed = 0.25;
    protected int life = 5;

    public Character(double x, double y) {
        super(x, y);
    }

    public Character(double x, double y, Sprite[] sprite, KeyListener keyListener) {
        super(x, y, sprite);
        this.keyListener = keyListener;
    }

    protected void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public int getLife() {
        return life;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    protected void setLife(int life) {
        this.life = life;
    }

    public abstract void update();
}
