package core;

import input.*;
import audio.*;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Const {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Bomberman";

    public static KeyListener keyListener;

    public enum STATE {
        MENU,
        PLAYER,
        COMPUTER,
        EXIT
    }

    public static STATE gameState = STATE.MENU;

    public static final int FPS = 60;

    public static final ImageView blankImage = new ImageView(new Image("file:res/blank.png"));

    public static final ImageView menuImage = new ImageView(new Image("file:res/menu.png"));

    // Menusun.swing.table
    public static final int BUTTON_WIDTH = 228;
    public static final int BUTTON_HEIGHT = 70;

    public static final int BUTTON_PLAYER_START_X = 486;
    public static final int BUTTON_PLAYER_START_Y = 340;
    public static final Button buttonPlayer = new Button("");

    public static final int BUTTON_COMPUTER_START_X = 486;
    public static final int BUTTON_COMPUTER_START_Y = 432;
    public static final Button buttonComputer = new Button("");

    public static final int BUTTON_EXIT_START_X = 486;
    public static final int BUTTON_EXIT_START_Y = 524;
    public static final Button buttonExit = new Button("");

    public static Audio menuSound = new Audio();

    public static void loadMenu() {
        buttonPlayer.setLayoutX(BUTTON_PLAYER_START_X);
        buttonPlayer.setLayoutY(BUTTON_PLAYER_START_Y);
        buttonPlayer.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonPlayer.setStyle("-fx-background-color: transparent;");
        buttonPlayer.setOnMouseEntered(e -> {
            buttonPlayer.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 2px;");
        });
        buttonPlayer.setOnMouseExited(e -> {
            buttonPlayer.setStyle("-fx-background-color: transparent;");
        });

        buttonComputer.setLayoutX(BUTTON_COMPUTER_START_X);
        buttonComputer.setLayoutY(BUTTON_COMPUTER_START_Y);
        buttonComputer.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonComputer.setStyle("-fx-background-color: transparent;");
        buttonComputer.setOnMouseEntered(e -> {
            buttonComputer
                    .setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 2px;");
        });
        buttonComputer.setOnMouseExited(e -> {
            buttonComputer.setStyle("-fx-background-color: transparent;");
        });

        buttonExit.setLayoutX(BUTTON_EXIT_START_X);
        buttonExit.setLayoutY(BUTTON_EXIT_START_Y);
        buttonExit.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonExit.setStyle("-fx-background-color: transparent;");
        buttonExit.setOnMouseEntered(e -> {
            buttonExit
                    .setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 2px;");
        });
        buttonExit.setOnMouseExited(e -> {
            buttonExit.setStyle("-fx-background-color: transparent;");
        });

        menuSound.setPath("res/music/menu.wav");
        menuSound.setLoop(true);
    }

    public static final int OBJECT_SIZE = 35;
    public static final int OFFSET_STATUS_BAR = 60;
    public static final int OFFSET_MAP = 660;

    public static final Image[] onealImage = new Image[7];
    public static final Image[] balloomImage = new Image[7];
    public static final Image[] dollImage = new Image[7];
    public static final Image[] minvoImage = new Image[7];
    public static final Image[] kondoriaImage = new Image[7];
    public static final Image[] ovapiImage = new Image[7];

    public static final Image grassImage = new Image("file:res/sprites/grass.png");
    public static final Image portalImage = new Image("file:res/sprites/portal.png");
    public static final Image wallImage = new Image("file:res/sprites/wall.png");

    public static void loadEnemy() {
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                onealImage[i] = new Image("file:res/sprites/oneal" + "dead" + ".png");
                balloomImage[i] = new Image("file:res/sprites/balloom" + "dead" + ".png");
                dollImage[i] = new Image("file:res/sprites/doll" + "dead" + ".png");
                minvoImage[i] = new Image("file:res/sprites/minvo" + "dead" + ".png");
                kondoriaImage[i] = new Image("file:res/sprites/kondoria" + "dead" + ".png");
                ovapiImage[i] = new Image("file:res/sprites/ovapi" + "dead" + ".png");
            } else if (1 <= i && i <= 3) {
                onealImage[i] = new Image("file:res/sprites/oneal" + "left" + Integer.toString(i) + ".png");
                balloomImage[i] = new Image("file:res/sprites/balloom" + "left" + Integer.toString(i) + ".png");
                dollImage[i] = new Image("file:res/sprites/doll" + "left" + Integer.toString(i) + ".png");
                minvoImage[i] = new Image("file:res/sprites/minvo" + "left" + Integer.toString(i) + ".png");
                kondoriaImage[i] = 
                        new Image("file:res/sprites/kondoria" + "left" + Integer.toString(i) + ".png");
                ovapiImage[i] = new Image("file:res/sprites/ovapi" + "left" + Integer.toString(i) + ".png");
            } else {
                onealImage[i] = new Image("file:res/sprites/oneal" + "right" + Integer.toString(i - 3) + ".png");
                balloomImage[i] = 
                        new Image("file:res/sprites/balloom" + "right" + Integer.toString(i - 3) + ".png");
                dollImage[i] = new Image("file:res/sprites/doll" + "right" + Integer.toString(i - 3) + ".png");
                minvoImage[i] = new Image("file:res/sprites/minvo" + "right" + Integer.toString(i - 3) + ".png");
                kondoriaImage[i] = 
                        new Image("file:res/sprites/kondoria" + "right" + Integer.toString(i - 3) + ".png");
                ovapiImage[i] = new Image("file:res/sprites/ovapi" + "right" + Integer.toString(i - 3) + ".png");
            }
        }
    }
}
