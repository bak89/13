package Model;

public class UserState {

    private int recordLevel;
    private int money;
    private int recordMoney;

    public UserState(int recordLevel, int money) {
        this.recordLevel = recordLevel;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public int getRecordLevel() {
        return recordLevel;
    }

    public void updateRecordMoney(int level) {
        recordLevel = Math.max(recordLevel, level);
        money = money * 3;
    }
}
