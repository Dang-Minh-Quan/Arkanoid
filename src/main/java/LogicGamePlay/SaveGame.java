package LogicGamePlay;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveGame {

  private static final String SAVE_FILE = "savegame.txt";

  public static void saveProgress() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
      writer.write("Level:" + Specifications.Level.get());
      writer.newLine();
//      writer.write("Score:" + Specifications.score.get());
//      writer.newLine();

      System.out.println("Game progress saved successfully.");
    } catch (IOException e) {
      System.err.println("Error saving game progress: " + e.getMessage());
    }
  }

  public static boolean loadProgress() {
    File saveFile = new File(SAVE_FILE);
    if (!saveFile.exists()) {
      System.out.println("No saved game found.");
      return false;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(":");
        if (parts.length < 2) continue;

        String key = parts[0].trim();
        int value = Integer.parseInt(parts[1].trim());

        switch (key) {
          case "Level":
            Specifications.Level.set(value);
            break;
//          case "Score":
//            Specifications.score.set(value);
//            break;
        }
      }
      System.out.println("Game progress loaded. Current Level: " + Specifications.Level.get());
      return true;
    } catch (IOException | NumberFormatException e) {
      System.err.println("Error loading game progress: " + e.getMessage());
      return false;
    }
  }

    public static boolean hasSavedGame() {
      File saveFile = new File(SAVE_FILE);

      int savedLevel = 1;

      try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
          if (line.startsWith("Level:")) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
              savedLevel = Integer.parseInt(parts[1].trim());
              break;
            }
          }
        }
      } catch (IOException | NumberFormatException e) {
        System.err.println("Error reading saved level: " + e.getMessage());
        return false;
      }

      return savedLevel > 1;

    }

}
