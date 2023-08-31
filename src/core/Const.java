package core;

import java.util.ArrayList;
import java.util.List;

import entity.Portal;
import entity.character.Bomber;
import entity.enemy.Enemy;
import entity.item.Item;
import entity.tile.Brick;
import entity.tile.Grass;
import entity.tile.Wall;
import entity.weapon.Bomb;

public class Const {
    public static int WIDTH;
    public static int HEIGHT;
    public static final String TITLE = "Bomberman";

    public enum STATE {
        MENU,
        PLAYER,
        COMPUTER,
        GAMEOVER,
        EXIT
    }

    public enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        STAND
    }

    public static STATE gameState = STATE.MENU;

    public static final int FPS = 25;
    public static final int DELAY_TIME = 1000 / FPS;

    public static final int BUTTON_WIDTH = 228;
    public static final int BUTTON_HEIGHT = 70;

    public static final int BUTTON_PLAYER_START_X = 486;
    public static final int BUTTON_PLAYER_START_Y = 340;

    public static final int BUTTON_COMPUTER_START_X = 486;
    public static final int BUTTON_COMPUTER_START_Y = 432;

    public static final int BUTTON_EXIT_START_X = 486;
    public static final int BUTTON_EXIT_START_Y = 524;

    public static final int OBJECT_SIZE = 32;
    public static final int OFFSET_STATUS_BAR = 60;
    public static final int STATUS_BAR_SIZE = 60;
    public static final int OFFSET_MAP = 660;

    public static int lvl;
    public static int row;
    public static int col;
    public static int numItem;

    public static int score = 0;

    public static Wall[][] wallPos;
    public static Portal portalPos;
    public static Bomber bomberPos;
    public static Grass[][] grassPos;
    public static Brick[][] brickPos;
    public static List<Enemy> enemyPos = new ArrayList<>();
    public static List<Bomb> bombPos = new ArrayList<>();

    public static Item[][] itemPos;
    public static int ITEM_MAX;
    public static int BOMBSITEM_MAX = 1;
}
