package entity;

import static core.Const.*;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected boolean visible;
    protected double x;
    protected double y;

    public Entity() {
        visible = true;
        x = 0;
        y = 0;
    }

    public Entity(double x, double y) {
        visible = true;
        this.x = x;
        this.y = y;
    }

    public Entity(double x, double y, boolean visible) {
        this.visible = visible;
        this.x = x;
        this.y = y;
    }

    public boolean isVisible() {
        return visible;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean checkCollision(Entity other) {
        return (x < other.x + OBJECT_SIZE && x + OBJECT_SIZE > other.x && y < other.y + OBJECT_SIZE
                && y + OBJECT_SIZE > other.y);
    }

    public abstract void render(GraphicsContext gc);
}
