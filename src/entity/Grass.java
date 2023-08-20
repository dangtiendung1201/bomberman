package entity;

import static core.Const.*;
import javafx.scene.canvas.GraphicsContext;

public class Grass extends Entity {
    public Grass() {
        super();
    }

    public Grass(double x, double y) {
        super(x, y);
    }

    public Grass(double x, double y, boolean visible) {
        super(x, y, visible);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(grassImage, x, y, OBJECT_SIZE, OBJECT_SIZE);
    }
    
}
