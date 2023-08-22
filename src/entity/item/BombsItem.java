package entity.item;

import graphic.Sprite;

public class BombsItem extends Item {

    public BombsItem(int x, int y) {
        super(x, y);
    }

    public BombsItem(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
        this.cur = 0;
    }
    
}
