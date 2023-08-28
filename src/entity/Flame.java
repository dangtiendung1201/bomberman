package entity;

import static core.Const.*;

import entity.enemy.Enemy;
import graphic.Sprite;

public class Flame extends Entity {
    private int step = 0;

    public Flame(double x, double y) {
        super(x, y);
    }

    public Flame(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public Flame(double x, double y, Sprite[] sprite, int cur) {
        super(x, y, sprite);
        this.cur = cur;
    }

    public boolean isBrick() {
        int actualX = (int) x;
        int actualY = (int) y;

        if (brickPos[actualX][actualY] != null)
            return true;

        return false;
    }

    public boolean isWall() {
        int actualX = (int) x;
        int actualY = (int) y;

        if (wallPos[actualX][actualY] != null)
            return true;

        return false;
    }

    public void isEnemy() {
        for (Enemy enemy : enemyPos) {
            if (checkIntersect(enemy)) {
                enemy.setDead();
            }
        }
    }

    public void isBomber() {
        if (checkIntersect(bomberPos) && !bomberPos.getFlamePass()) {
            bomberPos.setCur(12);
            bomberPos.reset();
        }
    }

    public void update() {
        step++;
        if (step > 3) {
            step = 0;
            cur -= 3;
        } else {
            cur++;
            step++;
        }
        System.out.println(cur);
    }
}
