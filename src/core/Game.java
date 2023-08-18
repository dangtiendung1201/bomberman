package core;

import static core.Const.*;

import core.Const.STATE;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
    private boolean loop = true;

    private void menu(Stage stage) {
        stage.setTitle("Menu");

        buttonPlayer.setOnAction(e -> {
            gameState = STATE.PLAYER;
            gameLoop(stage);
        });

        buttonComputer.setOnAction(e -> {
            gameState = STATE.COMPUTER;
            gameLoop(stage);
        });

        buttonExit.setOnAction(e -> {
            gameState = STATE.EXIT;
            gameLoop(stage);
        });

        Group root = new Group(menuImage);
        root.getChildren().add(buttonPlayer);
        root.getChildren().add(buttonComputer);
        root.getChildren().add(buttonExit);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void player(Stage stage) {
        stage.setTitle("Player");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        Group root = new Group(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void computer(Stage stage) {
        stage.setTitle("Computer");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        Button buttonBack = new Button("Back");
        buttonBack.setLayoutX(500);
        buttonBack.setLayoutY(500);
        buttonBack.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonBack.setStyle("-fx-background-color: black;");
        buttonBack.setOnAction(e -> {
            gameState = STATE.MENU;
            gameLoop(stage);
        });

        Group root = new Group(canvas);
        root.getChildren().add(buttonBack);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gameLoop(Stage stage) {
        switch (gameState) {
            case MENU:
                menu(stage);
                break;
            case PLAYER:
                player(stage);
                break;
            case COMPUTER:
                computer(stage);
                break;
            case EXIT:
                Platform.exit();
                break;
        }
    }

    private void loadAssets() {
        loadMenu();
    }

    @Override
    public void start(Stage stage) {
        loadAssets();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (loop) {
                    gameLoop(stage);
                    loop = false;
                }
                if (gameState == STATE.EXIT) {
                    Platform.exit();
                }
            }
        };
        timer.start();
    }
}
