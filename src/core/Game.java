package core;

import static core.Const.*;
import static graphic.UserInterfere.*;
import map.*;

import java.io.FileNotFoundException;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    private boolean loop = true;

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

    private void update() {
        bomberPos.update();
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grassPos[i][j] != null) {
                    grassPos[i][j].render(gc);
                }

                if (wallPos[i][j] != null) {
                    wallPos[i][j].render(gc);
                }
            }
        }

        bomberPos.render(gc);
    }

    private void player(Stage stage) {

        stage.setTitle("Player");
        Map map = null;

        try {
            map = new Map();
            map.read("res/levels/level1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        KeyListener keyListener = new KeyListener(scene);

        map.init(keyListener);
        map.print();

        // for (int i = 0; i < row; i++) {
        //     for (int j = 0; j < col; j++) {
        //         if (wallPos[i][j] != null) {
        //             System.out.print("W");
        //         }
        //         else {
        //             System.out.print(" ");
        //         }
        //     }
        //     System.out.println();
        // }

        root.getChildren().add(canvas);
        stage.setScene(scene);

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long frameStart = System.nanoTime();
                // System.out.println(frameStart);

                update();
                render(gc);

                long frameTime = System.nanoTime() - frameStart;
                // System.out.println(frameTime);
                if (frameTime / 1000000 < DELAY_TIME) {
                    try {
                        Thread.sleep(DELAY_TIME - frameTime / 1000000);

                        stage.setTitle(TITLE + " | FPS: " + 1000 / (DELAY_TIME - frameTime / 1000000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
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
        loadPlay();
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
