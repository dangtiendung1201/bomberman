package core;

import static core.Const.*;
import map.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import core.Const.STATE;
import input.KeyListener;
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

    public void render(GraphicsContext gc, KeyListener keyListener) {
        for (int i = 0; i < stiilEntities.length; i++) {
            for (int j = 0; j < stiilEntities[i].length; j++) {
                if (stiilEntities[i][j] != null) {
                    stiilEntities[i][j].render(gc);
                }
            }
        }

        bomber.move(keyListener);
        bomber.render(gc);

    }

    private void player(Stage stage) {
        stage.setTitle("Player");
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        KeyListener keyListener = new KeyListener(scene);

        try {
            map = new Map();
            map.read("res/levels/level1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        map.setup();

        root.getChildren().add(canvas);
        stage.setScene(scene);

        // lock fps to 60

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                // show FPS
                // System.out.println(1000000000 / (now - lastUpdate));
                render(gc, keyListener);

                long frameTime = (now - lastUpdate) / 1000000;
                stage.setTitle("FPS: " + 1000 / frameTime);
                if (frameTime < FPS) {
                    try {
                        Thread.sleep(FPS - frameTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lastUpdate = System.nanoTime();
            }
        };
        timer.start();
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
