package entity;

import static core.Const.*;
import graphic.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int cur;
    protected Sprite[] sprite;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        cur = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Entity(int x, int y, Sprite[] sprite) {
        this.x = x;
        this.y = y;
        cur = 0;
        this.sprite = sprite;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(sprite[cur].FxImage, y * OBJECT_SIZE, OFFSET_STATUS_BAR + x * OBJECT_SIZE);
    }
}
