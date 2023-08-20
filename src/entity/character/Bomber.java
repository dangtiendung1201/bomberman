package entity.character;

import static core.Const.*;
import input.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class Bomber extends Character {
    public Bomber() {
        super();
    }

    public Bomber(double x, double y) {
        super(x, y);
    }

    public Bomber(double x, double y, boolean visible) {
        super(x, y, visible);
    }

    public void init(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(onealImage[1], x, y, OBJECT_SIZE, OBJECT_SIZE);
    }

    @Override
    public void move(KeyListener keyListener) {
        if (keyListener.isPressed(KeyCode.UP)) {
            y -= 1;
        }
    }
    
}
