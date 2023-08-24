package entity.enemy;

import graphic.Sprite;

public class Oneal extends Enemy {

    public Oneal(double x, double y) {
        super(x, y);
        wallPass = false;
    }

    public Oneal(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
        wallPass = false;
    }

    @Override
    public void update() {
        

    }
    
}
