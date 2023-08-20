package entity;

import static core.Const.*;
import javafx.scene.canvas.GraphicsContext;

public class Wall extends Entity {
    public Wall() {
        super();
    }

    public Wall(double x, double y) {
        super(x, y);
    }

    public Wall(double x, double y, boolean visible) {
        super(x, y, visible);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(wallImage, x, y, OBJECT_SIZE, OBJECT_SIZE);
    }
    
}
