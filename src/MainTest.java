import Model.BinomialGenerator;
import Model.GameBoard;

public class MainTest {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(5, 5, new BinomialGenerator(5),6);

        gameBoard.playTile(2, 2);

    }
}
