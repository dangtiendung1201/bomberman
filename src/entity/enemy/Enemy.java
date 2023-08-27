package entity.enemy;

import static core.Const.*;
import entity.Entity;
import graphic.Sprite;
import javafx.application.Platform;

public abstract class Enemy extends Entity {
    protected boolean wallPass;
    protected DIRECTION direction;
    protected double speed;
    protected boolean moving;
    protected boolean bombPass;
    protected boolean isDead;

    public Enemy(double x, double y) {
        super(x, y);
    }

    public Enemy(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public void setDead() {
        isDead = true;
        cur = 6;
        Platform.runLater(() -> {
            enemyPos.remove(this);
        });
    }

    public abstract void update();
}
