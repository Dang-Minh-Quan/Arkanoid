package LogicGamePlay;

import java.io.Serializable;

public class HighScoreList implements Serializable, Comparable<HighScoreList> {
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

  /**
   * Constructor.
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

    @Override
    public int compareTo(HighScoreList other) {
        return Integer.compare(other.score, this.score);
    }
}
