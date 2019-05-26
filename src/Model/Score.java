package Model;


public class Score implements Comparable<Score> {

    private int level;
    private long moves;

    /**
     * Creates an empty Score instance.
     */
    public Score() {

        this.level = 0;
    }

    /**
     * Creates a Score instance.
     */
    public Score(int level, long moves) {
        this.moves = moves;
        this.level = level;
    }

    public long getMoves() {
        return moves;
    }

    public void setMoves(long moves) {
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
        return "Level = " + level + ", moves = "+ moves;
    }

    @Override
    public int compareTo(Score o) {
        if (level == o.level) {
            if (moves == o.moves) return 0;
            return moves < o.moves ? -1 : 1;
        }
        return level - o.level;
    }

}
