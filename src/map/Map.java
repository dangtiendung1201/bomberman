package map;

import static core.Const.*;
import static graphic.Sprite.*;

import entity.character.Bomber;
import entity.item.BombsItem;
import entity.item.Item;
import entity.tile.Brick;
import entity.tile.Grass;
import entity.tile.Wall;
import input.KeyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    private char[][] map;

    public void read(String path) throws FileNotFoundException {
        // read map from file Level1.txt
        File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            lvl = sc.nextInt();
            row = sc.nextInt();
            col = sc.nextInt();

            WIDTH = col * OBJECT_SIZE;
            HEIGHT = row * OBJECT_SIZE + STATUS_BAR_SIZE * 2;

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

    public void init(KeyListener keyListener) {
        grassPos = new Grass[row][col];
        wallPos = new Wall[row][col];
        brickPos = new Brick[row][col];
        itemPos = new Item[row][col];

        itemPos[1][2] = new BombsItem(1, 2, itemImage);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grassPos[i][j] = new Grass(i, j, grassImage);

                if (map[i][j] == '#') {
                    wallPos[i][j] = new Wall(i, j, wallImage);
                } else if (map[i][j] == 'p') {
                    bomberPos = new Bomber(i, j, bomberImage, keyListener);
                }
                else if (map[i][j] == '*') {
                    brickPos[i][j] = new Brick(i, j, brickImage);
                }
            }
        }
    }

    public void print() {
        System.out.println("Level: " + lvl);
        System.out.println("Row: " + row);
        System.out.println("Col: " + col);

        // System.out.println(wallPos);

        for (int i = 0; i < row; i++) {
            System.out.println(map[i]);
        }
    }
}