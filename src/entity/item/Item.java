package entity.item;

import entity.Entity;
import graphic.Sprite;

public abstract class Item extends Entity {
    public Item(int x, int y) {
        super(x, y);
    }

    public Item(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public Item(int x, int y, Sprite[] sprite, int cur) {
        super(x, y, sprite);
        this.cur = cur;
    }

    public int getType() {
        return cur;
    }
}
