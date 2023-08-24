package entity;

import static core.Const.*;
import graphic.Sprite;

public class Portal extends Entity {
    private boolean isVisible = false;

    public Portal(double x, double y) {
        super(x, y);
    }

    public Portal(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void update() {
        if (brickPos[(int) x][(int) y] == null) {
            isVisible = true;
        }
    }
}
