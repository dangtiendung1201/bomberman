package graphic;

import static core.Const.*;

import audio.Audio;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserInterfere {
    public static final ImageView menuImage = new ImageView(new Image("file:res/menu.png"));
    public static final Button buttonPlayer = new Button("");
    public static final Button buttonComputer = new Button("");
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

    public static void loadGame() {
        menuSound.stop();
    }
}
