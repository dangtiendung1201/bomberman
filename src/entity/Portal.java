package entity;

import static core.Const.*;

import media.Sprite;

public class Portal extends Entity {
    private boolean isOpen = false;

    public Portal(double x, double y) {
        super(x, y);
    }

    public Portal(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public void setVisible(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void update() {
        if (brickPos[(int) x][(int) y] == null && enemyPos.isEmpty()) {
            isOpen = true;
        }
    }
}
