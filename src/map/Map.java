package map;

import static core.Const.*;
import static graphic.Sprite.*;

import entity.Portal;
import entity.character.*;
import entity.enemy.*;
import entity.item.*;
import entity.tile.*;

import input.KeyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    private char[][] map;
    private char[][] itemMap;

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
            itemMap = new char[row][col];

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

            sc.nextLine();
            numItem = sc.nextInt();

            for (int i = 1; i <= numItem; i++) {
                char type = sc.next().charAt(0);
                int x = sc.nextInt();
                int y = sc.nextInt();

                itemMap[x][y] = type;
            }
        }
    }

    public void init(KeyListener keyListener) {
        grassPos = new Grass[row][col];
        wallPos = new Wall[row][col];
        brickPos = new Brick[row][col];
        itemPos = new Item[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (itemMap[i][j] == 'b') {
                    itemPos[i][j] = new BombsItem(i, j, itemImage);
                    BOMBSITEM_MAX++;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grassPos[i][j] = new Grass(i, j, grassImage);

                if (map[i][j] == 'x') {
                    brickPos[i][j] = new Brick(i, j, brickImage);
                    portalPos = new Portal(i, j, portalImage);
                } else if (map[i][j] == '#') {
                    wallPos[i][j] = new Wall(i, j, wallImage);
                } else if (map[i][j] == 'p') {
                    bomberPos = new Bomber(i, j, bomberImage, keyListener);
                } else if (map[i][j] == '*') {
                    brickPos[i][j] = new Brick(i, j, brickImage);
                } else if (map[i][j] == '1') {
                    enemyPos.add(new Balloom(i, j, balloomImage));
                } else if (map[i][j] == '2') {
                    enemyPos.add(new Oneal(i, j, onealImage));
                }
                // else if (map[i][j] == '3') {
                // enemyPos[i][j] = new Doll(i, j, dollImage);
                // }
                // else if (map[i][j] == '4') {
                // enemyPos[i][j] = new Minvo(i, j, minvoImage);
                // }
                // else if (map[i][j] == '5') {
                // enemyPos[i][j] = new Kondoria(i, j, kondoriaImage);
                // }
                // else if (map[i][j] == '6') {
                // enemyPos[i][j] = new Ovapi(i, j, ovapiImage);
                // }
                // else if (map[i][j] == '7') {
                // enemyPos[i][j] = new Pass(i, j, passImage);
                // }
                // else if (map[i][j] == '8') {
                // enemyPos[i][j] = new Pontan(i, j, pontanImage);
                // }

            }
        }
    }

    public void print() {
        System.out.println("Level: " + lvl);
        System.out.println("Row: " + row);
        System.out.println("Col: " + col);

        // System.out.println(wallPos);

        // for (int i = 0; i < row; i++) {
        // System.out.println(map[i]);
        // }

        // for (int i = 0; i < row; i++) {
        // System.out.println(itemMap[i]);
        // }
    }
}