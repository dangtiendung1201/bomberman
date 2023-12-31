package entity.enemy;

import static core.Const.*;
import entity.Entity;
import javafx.application.Platform;
import media.Sprite;

public abstract class Enemy extends Entity {
    protected boolean wallPass;
    protected DIRECTION direction;
    protected double speed;

    public Enemy(double x, double y) {
        super(x, y);
    }

    public Enemy(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public void setDead() {
        cur = 6;
        Platform.runLater(() -> {
            enemyPos.remove(this);
        });
    }

    public abstract void update();
}
