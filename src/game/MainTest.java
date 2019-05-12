package game;

public class MainTest {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(5, 5, new BinomialGenerator(new Level(6), 5));

        gameBoard.playTile(2, 2);

    }
}
