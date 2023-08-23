package entity.enemy;

import static core.Const.*;
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

    protected boolean isBomber() {
        return (x == bomberPos.getX() && y == bomberPos.getY());
    }

    public abstract void update();
}
