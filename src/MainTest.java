import Model.BinomialGenerator;
import Model.GameBoard;
import Model.UserState;

public class MainTest {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(5, 5, new BinomialGenerator(new UserState(6,6,1), 5));

        gameBoard.playTile(2, 2);

    }
}
