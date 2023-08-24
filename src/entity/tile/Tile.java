package entity.tile;

import entity.Entity;
import graphic.Sprite;

public abstract class Tile extends Entity {
    public Tile(double x, double y) {
        super(x, y);
    }

    public Tile(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
