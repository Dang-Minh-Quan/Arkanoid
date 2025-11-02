package LogicGamePlay;

import java.io.*;
import java.util.Scanner;

import static LogicGamePlay.Specifications.*;

public class Map {
    public int[][] builderMap(int Level) {
        int[][] a = new int[ROW][COL];
        String path = new String();
        switch (Level) {
            case 1:
                path = "/Interface/Map/Level1.txt";
                break;
            case 2:
                path = "/Interface/Map/Level2.txt";
                break;
            case 3:
                path = "/Interface/Map/Level3.txt";
                break;
        }
        InputStream input = getClass().getResourceAsStream(path);
        try (Scanner sc = new Scanner(input)) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    a[i][j] = sc.nextInt();
                }
            }
        }
        return a;
    }
}
