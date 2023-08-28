package entity.item;

import static media.UserInterfere.*;

import entity.Entity;
import media.Sprite;

public abstract class Item extends Entity {
    public Item(double x, double y) {
        super(x, y);
    }

    public Item(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public Item(double x, double y, Sprite[] sprite, int cur) {
        super(x, y, sprite);
        this.cur = cur;
    }

    protected void playSound() {
        itemSound.play();
    }
}
