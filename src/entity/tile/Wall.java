package entity.tile;

import entity.Entity;
import graphic.Sprite;

public class Wall extends Entity{

    public Wall(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public Wall(int x, int y) {
        super(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
