package entity.enemy;

import graphic.Sprite;

public class Balloom extends Enemy {
    public Balloom(int x, int y) {
        super(x, y);
    }

    public Balloom(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
    }
}
