package entity.item;

import static core.Const.*;
import graphic.Sprite;

public class BombsItem extends Item {

    public BombsItem(int x, int y) {
        super(x, y);
        this.cur = 0;
    }

    public BombsItem(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
        this.cur = 0;
    }

    public int update(int x, int y, int maxBomb) {
        maxBomb++;
        itemPos[x][y] = null;

        return maxBomb;
    }
}
