package entity.character;

import entity.*;
import input.KeyListener;
import javafx.scene.canvas.GraphicsContext;

public abstract class Character extends Entity {
    public Character() {
        super();
    }

    public Character(double x, double y) {
        super(x, y);
    }

    public Character(double x, double y, boolean visible) {
        super(x, y, visible);
    }

    public abstract void init(double x, double y);
    public abstract void move(KeyListener keyListener);
    public abstract void render(GraphicsContext gc);
}
