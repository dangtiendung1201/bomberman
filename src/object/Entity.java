package object;

import core.Const.*;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    private boolean visible;
    private int x;
    private int y;

    public Entity() {
        visible = true;
        x = 0;
        y = 0;
    }

    public abstract void render(GraphicsContext gc);
}
