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
                path= "/Interface/Map/Level1.txt";
                break;
            case 2:
                path= "/Interface/Map/Level2.txt";
                break;
            case 3:
                path= "/Interface/Map/Level3.txt";
                break;
        }
        InputStream input = getClass().getResourceAsStream(path);
        try (Scanner sc = new Scanner(input)) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    int k = sc.nextInt();
                    switch (k){
                        case 0:
                            a[i][j] = "non";
                            break;
                        case 1:
                            a[i][j] = "basic";
                            break;
                        case 2:
                            a[i][j] = "solid";
                            break;
                        case 3:
                            a[i][j] = "broken";
                            break;
                        case 4:
                            a[i][j] = "boom";
                            break;
                        case 5:
                            a[i][j] = "blind";
                            break;
                        case 6:
                            a[i][j] = "hard";
                            break;
                    }
                }
            }
        }
        return a;
    }
}
