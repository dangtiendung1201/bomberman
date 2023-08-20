package map;

import static core.Const.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entity.Entity;
import entity.Grass;
import entity.Wall;
import entity.character.Bomber;
import input.KeyListener;
import javafx.scene.Scene;
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

    public void setup() {
        stiilEntities = new Entity[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == '#') {
                    stiilEntities[i][j] = new Wall(j * OBJECT_SIZE, OFFSET_STATUS_BAR + i * OBJECT_SIZE);
                } else if (map[i][j] == 'p') {
                    bomber = new Bomber(j * OBJECT_SIZE, OFFSET_STATUS_BAR + i * OBJECT_SIZE);
                } else {
                    stiilEntities[i][j] = new Grass(j * OBJECT_SIZE, OFFSET_STATUS_BAR + i * OBJECT_SIZE);
                }
            }
        }
    }
}