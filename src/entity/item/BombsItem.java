package entity.item;

import static core.Const.*;
import graphic.Sprite;

public class BombsItem extends Item {

    public BombsItem(double x, double y) {
        super(x, y);
        this.cur = 0;
    }

    public BombsItem(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
        this.cur = 0;
    }

    public int update(int maxBomb) {
        maxBomb++;
        itemPos[(int) x][(int) y] = null;

        return maxBomb;
    }
}
