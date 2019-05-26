import AI.Ai;
import Model.BinomialGenerator;
import Model.GameBoard;
import Model.Location;
import Model.Settings;

public class MainTest {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(5, 5, new BinomialGenerator(5), 6);
        Settings settings = new Settings();
        Ai ai = new Ai(gameBoard, settings);
        while (gameBoard.isClickable()) {
            Location move = ai.getNextMove();
            gameBoard.playTile(move.getX(), move.getY());
        }

    }
}
