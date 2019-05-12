package game;

public class UserState {

    private int record;
    private int level;
    private int money;

    public int getMoney() {
        return money;
    }

    public int getRecord() {
        return record;
    }

    public int getLevel() {
        return level;
    }

    public void updateLevel(int level) {
        this.level = level;

        record = Math.max(record, level);
        money = money * 3;
    }

    public UserState(int record, int level, int money) {
        this.record = record;
        this.level = level;
        this.money = money;
    }
}
