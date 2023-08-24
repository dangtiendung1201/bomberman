package entity.enemy;

import static core.Const.*;
import entity.Entity;
import graphic.Sprite;

public abstract class Enemy extends Entity {
    protected boolean wallPass;
    protected DIRECTION direction;
    protected double speed;
    protected int cur;
    protected boolean moving;
    protected boolean isDead;

    public Enemy(double x, double y) {
        super(x, y);
        cur = 0;
    }

    public Enemy(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
        cur = 0;
    }

    public abstract void update();
}
