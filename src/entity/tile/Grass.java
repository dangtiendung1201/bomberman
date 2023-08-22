package entity.tile;

import graphic.Sprite;

public class Grass extends Tile {

    public Grass(int x, int y) {
        super(x, y);
    }
    
    public Grass(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
    }
}
