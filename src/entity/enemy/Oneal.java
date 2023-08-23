package entity.enemy;

import graphic.Sprite;

public class Oneal extends Enemy {

    public Oneal(int x, int y) {
        super(x, y);
        wallPass = false;
    }

    public Oneal(int x, int y, Sprite[] sprite) {
        super(x, y, sprite);
        wallPass = false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
    
}
