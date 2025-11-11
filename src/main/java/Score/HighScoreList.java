package Score;

import java.io.Serializable;

public class HighScoreList implements Serializable, Comparable<HighScoreList> {
    private String name;
    private int score;

    /**
     * Constructor.
     *
     * @param name
     * @param score
     */
    public HighScoreList(String name, int score) {
        this.name = name;
        this.score = score;
    }

  public String getName() {
        return name;
    }

  public int getScore() {
        return score;
    }

  /**
   * So sánh hai người chơi theo điểm số để sắp xếp bảng xếp hạng.
   *
   * @param other
   * @return giá trị so sánh
   */
  @Override
    public int compareTo(HighScoreList other) {
        return Integer.compare(other.score, this.score);
    }
}
