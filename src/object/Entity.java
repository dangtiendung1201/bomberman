package object;

import core.Const.*;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    private boolean visible;
    private double x;
    private double y;

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

    public abstract void render(GraphicsContext gc);
}
