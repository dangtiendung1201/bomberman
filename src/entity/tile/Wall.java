package entity.tile;

import graphic.Sprite;

public class Wall extends Tile{

    public Wall(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public Wall(int x, int y) {
        super(x, y);
    }
}
