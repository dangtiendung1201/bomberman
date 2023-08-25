package entity.weapon;

import static core.Const.*;
import static graphic.Sprite.*;
import graphic.Sprite;

import entity.Flame;

public class Bomb extends Weapon {

    public Bomb(double x, double y) {
        super(x, y);
    }

    public Bomb(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public void update() {
        int size = bomberPos.getFlameSize();
        size = 2;
        int centerX = (int) x;
        int centerY = (int) y;

        // UP
        for (int i = 1; i <= size; i++)
        {
            Flame flame = new Flame(centerX - i, centerY, explosionImage, (i < size) ? 0 : 12);
            if (flame.isWall())
                break;

            if (flame.isBrick())
            {
                flamePos.add(flame);
                break;
            }

            flamePos.add(flame);
        }

        // DOWN
        for (int i = 1; i <= size; i++)
        {
            Flame flame = new Flame(centerX + i, centerY, explosionImage, (i < size) ? 0 : 15);
            if (flame.isWall())
                break;

            if (flame.isBrick())
            {
                flamePos.add(flame);
                break;
            }

            flamePos.add(flame);
        }

        // LEFT
        for (int i = 1; i <= size; i++)
        {
            Flame flame = new Flame(centerX, centerY - i, explosionImage, (i < size) ? 3 : 6);
            if (flame.isWall())
                break;

            if (flame.isBrick())
            {
                flamePos.add(flame);
                break;
            }

            flamePos.add(flame);
        }

        // RIGHT
        for (int i = 1; i <= size; i++)
        {
            Flame flame = new Flame(centerX, centerY + i, explosionImage, (i < size) ? 3 : 9);
            if (flame.isWall())
                break;

            if (flame.isBrick())
            {
                flamePos.add(flame);
                break;
            }

            flamePos.add(flame);
        }
    }
}
