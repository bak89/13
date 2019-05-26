package WriteReadScore;

public class Score {
    private String name;
    private Integer level;

    /**
     * Creates an empty Score instance.
     */
    public Score() {
        this.name = "";
        this.level = 0;
    }

    /**
     * Creates a Score instance.
     */
    public Score(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Score : name = " + name + ", level = " + level;
    }
}
