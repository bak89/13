package Model;

public class UserState {

    private int recordLevel;
    private int recordMoney;

    public UserState(int recordLevel, int recordMoney) {
        this.recordLevel = recordLevel;
        this.recordMoney = recordMoney;
    }

    public int getRecordMoney() {
        return recordMoney;
    }

    public int getRecordLevel() {
        return recordLevel;
    }


    public void updateRecord(int level, int money) {
        recordLevel = Math.max(recordLevel, level);
        if(level == recordLevel){
            recordMoney = Math.max(recordMoney,money);//problema
        }
    }
}
