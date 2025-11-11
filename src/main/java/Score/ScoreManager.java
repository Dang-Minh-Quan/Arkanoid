package Score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
  private static final String FILE_NAME = "highscores.txt";
  private List<HighScoreList> highScores;
  private final int NumOfHighScore = 9;

    public ScoreManager() {
        highScores = loadScores();
    }

  /**
   * Đọc từ file dữ liệu bảng điểm trả về danh sách bảng điểm.
   * @return
   */
  private List<HighScoreList> loadScores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                return (List<HighScoreList>) obj;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No high scores file found, creating new list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

  /**
   * Ghi bảng điểm được lưu vào file.
   */
  private void saveScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  /**
   * Thêm điểm cao vào bảng điểm.
   * @param name
   * @param score
   */
  public void addScore(String name, int score) {
        highScores.add(new HighScoreList(name, score));
        Collections.sort(highScores);
        while (highScores.size() > NumOfHighScore) {
            highScores.remove(highScores.size() - 1);
        }
        saveScores();
    }

  /**
   * Sắp xếp danh sách bảng điểm theo thứ tự.
   * @param currentScore
   * @return
   */
  public boolean isTopScore(int currentScore) {
        if (highScores.size() < NumOfHighScore) {
            return true;
        }
        return currentScore > highScores.get(highScores.size() - 1).getScore();
    }

  /**
   * Lấy điểm cao.
   * @return
   */
  public List<HighScoreList> getHighScores() {
        return highScores;
    }

  /**
   * Xóa bảng xếp hạng điểm.
   */
  public void resetScores() {
        highScores.clear();
        saveScores();
    }
}