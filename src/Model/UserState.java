package Model;

public class UserState {

    private int record;

    private int money;

    public int getMoney() {
        return money;
    }

    public int getRecord() {
        return record;
    }


    public void updateRecordMoney(Level level) {
        record = Math.max(record, level.getLevel());
        money = money * 3;
    }

    public UserState(int record, int money) {
        this.record = record;
        this.money = money;
    }
}
