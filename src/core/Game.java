package core;

import static core.Const.*;
import static media.UserInterfere.*;

import map.*;

import java.io.FileNotFoundException;
import java.util.List;

import core.Const.STATE;
import entity.Flame;
import entity.weapon.Bomb;
import entity.enemy.Enemy;
import input.KeyListener;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

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

    private void gameOver(Stage stage) {
        stage.setTitle("Game Over");
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        Group root = new Group();
        Scene scene = new Scene(root);

        // black background
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        stage.setScene(scene);
        stage.show();
    }

    private void update() {
        bomberPos.update();
        portalPos.update();

        if (!bombPos.isEmpty()) {
            for (Bomb bomb : bombPos) {
                bomb.update();
            }
        }

        if (!enemyPos.isEmpty()) {
            for (Enemy enemy : enemyPos) {
                enemy.update();
            }
        }
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        renderInfo();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grassPos[i][j] != null) {
                    grassPos[i][j].render(gc);
                }
            }
        }

        portalPos.render(gc);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (wallPos[i][j] != null) {
                    wallPos[i][j].render(gc);
                }

                if (itemPos[i][j] != null) {
                    itemPos[i][j].render(gc);
                }

                if (brickPos[i][j] != null) {
                    brickPos[i][j].render(gc);
                }

            }
        }

        for (Bomb bomb : bombPos) {
            bomb.render(gc);

            List<Flame> flamePos = bomb.getFlamePos();
            if (!flamePos.isEmpty()) {
                for (Flame flame : flamePos) {
                    flame.render(gc);
                }
            }
        }

        if (!enemyPos.isEmpty()) {
            for (Enemy enemy : enemyPos) {
                enemy.render(gc);
            }
        }
        bomberPos.render(gc);
    }

    private void renderInfo() {
        levelText.setText("Level: " + lvl);
        scoreText.setText("Score: " + score);

    }

    private void player(Stage stage) {
        loadGame();
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

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

        backgroundImage.setX(0);
        backgroundImage.setY(0);
        backgroundImage.setFitWidth(WIDTH);
        backgroundImage.setFitHeight(HEIGHT);

        root.getChildren().add(backgroundImage);
        root.getChildren().add(canvas);
        root.getChildren().add(levelText);
        root.getChildren().add(scoreText);

        stage.setScene(scene);

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long frameStart = System.nanoTime();
                // System.out.println(frameStart);

                update();
                render(gc);

                if (gameState == STATE.GAMEOVER) {
                    this.stop();
                    gameOver(stage);
                }

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
            case GAMEOVER:
                gameOver(stage);
                break;
            case EXIT:
                Platform.exit();
                System.exit(0);
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
                    System.exit(0);
                }
            }
        };
        timer.start();
    }
}
