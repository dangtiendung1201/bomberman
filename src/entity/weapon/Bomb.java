package entity.weapon;

import static core.Const.*;
import static graphic.Sprite.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import graphic.Sprite;
import javafx.application.Platform;
import entity.Flame;

public class Bomb extends Weapon {
    private int timer = 0;
    private List<Flame> flamePos = new ArrayList<>();
    private Timer bombTimer = new Timer();

    public Bomb(double x, double y) {
        super(x, y);
    }

    public Bomb(double x, double y, Sprite[] sprite) {
        super(x, y, sprite);
    }

    public List<Flame> getFlamePos() {
        return flamePos;
    }

    public void update() {
        timer++;
        // System.out.println(timer);
        if (timer == 50) {
            Platform.runLater(() -> {
                int size = bomberPos.getFlameSize();

                int centerX = (int) x;
                int centerY = (int) y;

                // UP
                for (int i = 1; i <= size; i++) {
                    Flame flame = new Flame(centerX - i, centerY, explosionImage, (i < size) ? 0 : 12);
                    if (flame.isWall())
                        break;

                    if (flame.isBrick()) {
                        flamePos.add(flame);
                        break;
                    }

                    flamePos.add(flame);
                }

                // DOWN
                for (int i = 1; i <= size; i++) {
                    Flame flame = new Flame(centerX + i, centerY, explosionImage, (i < size) ? 0 : 15);
                    if (flame.isWall())
                        break;

                    if (flame.isBrick()) {
                        flamePos.add(flame);
                        break;
                    }

                    flamePos.add(flame);
                }

                // LEFT
                for (int i = 1; i <= size; i++) {
                    Flame flame = new Flame(centerX, centerY - i, explosionImage, (i < size) ? 3 : 6);
                    if (flame.isWall())
                        break;

                    if (flame.isBrick()) {
                        flamePos.add(flame);
                        break;
                    }

                    flamePos.add(flame);
                }

                // RIGHT
                for (int i = 1; i <= size; i++) {
                    Flame flame = new Flame(centerX, centerY + i, explosionImage, (i < size) ? 3 : 9);
                    if (flame.isWall())
                        break;

                    if (flame.isBrick()) {
                        flamePos.add(flame);
                        break;
                    }

                    flamePos.add(flame);
                }
                bombTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        for (Flame flame : flamePos) {
                            flame.update();
                        }

                        for (Flame flame : flamePos) {
                            if (flame.isBrick()) {
                                brickPos[(int) flame.getX()][(int) flame.getY()] = null;
                            }
                            
                            flame.isEnemy();

                            flame.isBomber();
                        }
                    }
                }, 10);
            });
        }
        if (timer == 60) {
            Platform.runLater(() -> {
                bombTimer.cancel();
                flamePos.clear();
                bomberPos.reduceBomb();
                bombPos.remove(this);
            });
        }
    }
}
