package entity.weapon;

import graphic.Sprite;

public class Bomb extends Weapon {

    public Bomb(double x, double y) {
        super(x, y);
    }

    public Bomb(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }
}
