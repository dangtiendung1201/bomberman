package entity.enemy;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;

public abstract class Enemy extends Entity {
    private int health;

    public Enemy() {
        super();
        health = 0;
    }

    public Enemy(double x, double y) {
        super(x, y);
        health = 0;
    }

    public Enemy(double x, double y, boolean visible) {
        super(x, y, visible);
        health = 0;
    }

    public boolean checkCollision() {
        return false;
    }

    public abstract void render(GraphicsContext gc);
    public abstract void move();

}
