package Model;


public class Score implements Comparable<Score> {

    private int level;
    private int moves;

    /**
     * Creates an empty Score instance.
     */
    public Score() {

        this.level = 0;
    }

    /**
     * Creates a Score instance.
     */
    public Score(int level, int moves) {
        this.moves = moves;
        this.level = level;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Score : level = " + level;
    }

    @Override
    public int compareTo(Score o) {
        if (level == o.level) {
            return moves - o.moves;
        }
        return level - o.level;
    }

}
