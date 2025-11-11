package Score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
  private static final String fileHighScores = "highscores.txt";
  private List<HighScoreList> HighScoreList;
  private final int NumOfHighScore = 9;

    public ScoreManager() {
        HighScoreList = LoadScores();
    }

  /**
   * Đọc từ file dữ liệu bảng điểm trả về danh sách bảng điểm.
   * @return danh sách HighScoreList.
   * Nếu file không tồn tại hoặc lỗi, trả về danh sách rỗng.
   */
  private List<HighScoreList> LoadScores() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileHighScores))) {
            Object obj = input.readObject();
            if (obj instanceof List) {
                return (List<HighScoreList>) obj;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

  /**
   * Ghi danh sách điểm cao hiện tại vào file.
   */
  private void SaveScores() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileHighScores))) {
            output.writeObject(HighScoreList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  /**
   * Thêm một điểm mới vào bảng xếp hạng.
   *
   * @param name
   * @param score
   */
  public void AddScore(String name, int score) {
        HighScoreList.add(new HighScoreList(name, score));
        Collections.sort(HighScoreList);
        while (HighScoreList.size() > NumOfHighScore) {
            HighScoreList.remove(HighScoreList.size() - 1);
        }
        SaveScores();
    }

  /**
   * Kiểm tra xem điểm hiện tại có đủ cao để lọt vào bảng xếp hạng hay không.
   *
   * @param currentScore
   * @return true nếu điểm đủ cao để lọt vào bảng xếp hạng,
   * ngược lại trả về false
   */
  public boolean CheckHighScore(int currentScore) {
        if (HighScoreList.size() < NumOfHighScore) {
            return true;
        }
        return currentScore > HighScoreList.get(HighScoreList.size() - 1).getScore();
    }

  public List<HighScoreList> getHighScoreList() {
        return HighScoreList;
    }

  /**
   * Xóa toàn bộ bảng xếp hạng.
   */
  public void resetScores() {
        HighScoreList.clear();
        SaveScores();
    }
}