package entity.tile;

import media.Sprite;

public class Wall extends Tile{

    public Wall(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public Wall(double x, double y) {
        super(x, y);
    }
}
