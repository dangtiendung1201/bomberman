package core;

import static core.Const.*;
import map.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import core.Const.STATE;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    private boolean loop = true;
    private Map map;

    private void menu(Stage stage) {
        stage.setTitle("Menu");

        Group root = new Group();
        Scene scene = new Scene(root);

        menuSound.play();

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

        root.getChildren().add(menuImage);
        root.getChildren().add(buttonPlayer);
        root.getChildren().add(buttonComputer);
        root.getChildren().add(buttonExit);

        stage.setScene(scene);
        stage.show();
    }

    private void player(Stage stage) {
        stage.setTitle("Player");
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        try {
            map = new Map();
            map.read("res/levels/level1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        map.print();

        // render balloom in screen
        // for (int i = 0; i < row; i++) {
        // for (int j = 0; j < col; j++) {
        // switch (map[i][j]) {
        // case '#':
        // gc.drawImage(wallImage, j * SIZE, i * SIZE);
        // break;
        // case '*':
        // gc.drawImage(brickImage, j * SIZE, i * SIZE);
        // break;
        // case 'x':
        // gc.drawImage(bombImage, j * SIZE, i * SIZE);
        // break;
        // case 'p':
        // gc.drawImage(playerImage, j * SIZE, i * SIZE);
        // break;
        // case '1':
        // gc.drawImage(balloomImage, j * SIZE, i * SIZE);
        // break;
        // case '2':
        // gc.drawImage(onealImage, j * SIZE, i * SIZE);
        // break;
        // case '3':
        // gc.drawImage(dollImage, j * SIZE, i * SIZE);
        // break;
        // case '4':
        // gc.drawImage(minvoImage, j * SIZE, i * SIZE);
        // break;
        // case '5':
        // gc.drawImage(kondoriaImage, j * SIZE, i * SIZE);
        // break;
        // case '6':
        // gc.drawImage(ovapiImage, j * SIZE, i * SIZE);
        // break;
        // case '7':
        // gc.drawImage(passImage, j * SIZE, i * SIZE);
        // break;
        // case '8':
        // gc.drawImage(pontanImage, j * SIZE, i * SIZE);
        // break;
        // case '9':
        // gc.drawImage(dahlImage, j * SIZE, i * SIZE);
        // break;
        // case '0':
        // gc.drawImage(dahlImage, j * SIZE, i * SIZE);
        // break;
        // default:
        // break;
        // }
        // }
        // }

        // render Image
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(portalImage, 50, 50, 100, 100);

        // display red screen
        // gc.setFill(Color.RED);
        // gc.fillRect(0, 0, 100, 100);
        root.getChildren().add(blankImage);
        root.getChildren().add(canvas);
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
        loadEnemy();

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
