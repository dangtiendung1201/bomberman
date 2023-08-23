package entity.enemy;

import core.Const.DIRECTION;
import entity.Entity;
import graphic.Sprite;

public abstract class Enemy extends Entity {
    protected boolean wallPass;
    protected DIRECTION direction;
    protected int cur;
    protected boolean moving;

    public Enemy(int x, int y) {
        super(x, y);
        cur = 0;
    }

    public Enemy(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
        cur = 0;
    }

    public abstract void update();
}
