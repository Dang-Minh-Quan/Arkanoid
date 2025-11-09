package LogicGamePlay;

import java.io.*;
import java.util.Scanner;

import static LogicGamePlay.Specifications.*;

public class Map {
    public String[][] builderMap(int Level) {
        String[][] a = new String[ROW][COL];
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
            case 4:
              path= "/Interface/Map/Level4.txt";
              break;
        }
        InputStream input = getClass().getResourceAsStream(path);
        try (Scanner sc = new Scanner(input)) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    int k = sc.nextInt();
                    switch (k) {
                        case 0:
                            a[i][j] = "null";
                            break;
                        case 1:
                            a[i][j] = "normal";
                            break;
                        case 2, 3:
                            a[i][j] = "strong";
                            break;
                        case 4:
                            a[i][j] = "explosive";
                            break;
                        case 5:
                            a[i][j] = "blind";
                            break;
                        case 6:
                            a[i][j] = "unbreakable";
                            break;
                    }
                }
            }
        }
        return a;
    }
}
