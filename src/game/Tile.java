package game;

public class Tile {

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int tileX;
    private int tileY;
    private int value;

    public Tile(int tileX, int tileY, int value) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.value = value;
    }

    public void increaseTile(){
        value++;
    }
}
