package entity.tile;

import entity.Entity;
import graphic.Sprite;

public abstract class Tile extends Entity {
    public Tile(int x, int y) {
        super(x, y);
    }

    public Tile(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
