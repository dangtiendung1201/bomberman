package entity.weapon;

import entity.Entity;
import media.Sprite;

public abstract class Weapon extends Entity {

    public Weapon(double x, double y) {
        super(x, y);
    }

    public Weapon(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }
    
}
