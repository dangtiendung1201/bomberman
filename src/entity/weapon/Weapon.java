package entity.weapon;

import entity.Entity;
import graphic.Sprite;

public abstract class Weapon extends Entity {

    public Weapon(int x, int y) {
        super(x, y);
    }

    public Weapon(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
    }
    
}
