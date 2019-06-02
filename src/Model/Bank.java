package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Bank {

    private long money;
    private int bombCost = 50;
    private int undoCost = 20;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Bank(long money) {
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

    private void setMoney(long money) {
        if (money == this.money) return;

        long oldMoney = this.money;
        this.money = money;
        support.firePropertyChange("Money Change", oldMoney, money);
    }

    public int getBombCost() {
        return bombCost;
    }

    private void setBombCost(int bombCost) {
        if (bombCost == this.bombCost) return;

        int oldBombCost = this.bombCost;
        this.bombCost = bombCost;
        support.firePropertyChange("Bomb Cost Change", oldBombCost, bombCost);
    }

    public int getUndoCost() {
        return undoCost;
    }

    private void setUndoCost(int undoCost) {
        if (undoCost == this.undoCost) return;

        int oldUndoCost = this.undoCost;
        this.undoCost = undoCost;
        support.firePropertyChange("Undo Cost Change", oldUndoCost, undoCost);
    }

    public void addMove() {
        setMoney(getMoney() + 1);
    }

    public void addInterest() {
        setMoney(getMoney() * 2);
    }

    /**
     * Take Money for Bomb cost
     */
    public void useBomb() {
        setMoney(getMoney() - getBombCost());
        setBombCost(getBombCost() * 2);
    }

    /**
     * Take Money for Undo cost
     */
    public void useUndo() {
        setMoney(getMoney() - getUndoCost());
        setUndoCost(getUndoCost() * 2);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
