package entity.tile;

import graphic.Sprite;

public class Grass extends Tile {

    public Grass(double x, double y) {
        super(x, y);
    }
    
    public Grass(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }
}
