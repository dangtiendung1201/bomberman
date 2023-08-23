package entity.enemy;

import entity.Entity;
import graphic.Sprite;

public class Enemy extends Entity {
    public Enemy(int x, int y) {
        super(x, y);
        cur = 0;
    }

    public Enemy(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
        cur = 0;
    }
}
