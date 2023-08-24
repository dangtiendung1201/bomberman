package entity;

import static core.Const.*;
import graphic.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected double x;
    protected double y;
    protected int cur;
    protected Sprite[] sprite;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
        cur = 0;
    }

    public Entity(double x, double y, Sprite[] sprite) {
        this.x = x;
        this.y = y;
        cur = 0;
        this.sprite = sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(sprite[cur].FxImage, y * OBJECT_SIZE, OFFSET_STATUS_BAR + x * OBJECT_SIZE);
    }

    public boolean checkIntersect(Entity entity) {
        double topA = OFFSET_STATUS_BAR + x * 150;
        double leftA = y * 150;
        double bottomA = OFFSET_STATUS_BAR + (x + 1) * 150;
        double rightA = (y + 1) * 150;

        double topB = OFFSET_STATUS_BAR + entity.getX() * 150;
        double leftB = entity.getY() * 150;
        double bottomB = OFFSET_STATUS_BAR + (entity.getX() + 1) * 150;
        double rightB = (entity.getY() + 1) * 150;

        System.out.println("A" + x + " " + y + " " + topA + " " + leftA + " " +
        bottomA + " " + rightA);
        // System.out.println("B" + entity.getX() + " " + entity.getY() + " " + topB + "
        // " + leftB + " " + bottomB + " " + rightB);

        if (bottomA <= topB) {
            return false;
        }

        if (topA >= bottomB) {
            return false;
        }

        if (rightA <= leftB) {
            return false;
        }

        if (leftA >= rightB) {
            return false;
        }

        return true;
    }
}
