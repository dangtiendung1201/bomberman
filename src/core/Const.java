package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Const {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Bomberman";

    public enum STATE {
        MENU,
        PLAYER,
        COMPUTER,
        EXIT
    }

    public static STATE gameState = STATE.MENU;

    public static final int FPS = 60;

    public static final ImageView menuImage = new ImageView(new Image("file:res/menu.png"));

    // Menu buttons
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
    }

}
