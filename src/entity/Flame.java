package entity;

import static core.Const.*;
import graphic.Sprite;

public class Flame extends Entity {
    public Flame(double x, double y) {
        super(x, y);
    }

    public Flame(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public Flame(double x, double y, Sprite[] sprite, int cur) {
        super(x, y, sprite);
        this.cur = cur;
    }

    public boolean isBrick() {
        int actualX = (int) x;
        int actualY = (int) y;

        if (brickPos[actualX][actualY] != null)
            return true;

        return false;
    }

    public boolean isWall() {
        int actualX = (int) x;
        int actualY = (int) y;

        if (wallPos[actualX][actualY] != null)
            return true;

        return false;
    }

    public boolean isEnemy() {
        // for (int)
        return false;
    }

    public void update() {
        // TODO Auto-generated method stub

    }
}
