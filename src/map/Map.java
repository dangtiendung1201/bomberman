package map;

import static core.Const.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;

public class Map {
    private int lvl;
    private int row;
    private int col;
    private char[][] map;

    public void read(String path) throws FileNotFoundException {
        // read map from file Level1.txt
        File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            lvl = sc.nextInt();
            row = sc.nextInt();
            col = sc.nextInt();

            map = new char[row][col];

            for (int i = 0; i < row; i++) {
                String line = sc.nextLine();

                if (line.isEmpty()) {
                    i--;
                    continue;
                }

                for (int j = 0; j < col; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
        }
    }

    public void print() {
        System.out.println("Level: " + lvl);
        System.out.println("Row: " + row);
        System.out.println("Col: " + col);

        for (int i = 0; i < row; i++) {
            System.out.println(map[i]);
        }
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                // switch (map[i][j]) {
                // case '#':
                // gc.drawImage(wallImage, j * OBJECT_SIZE, OFFSET_STATUS_BAR + i * OBJECT_SIZE,
                // OBJECT_SIZE, OBJECT_SIZE);
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
            }
        }
    }
}