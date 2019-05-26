package AI;

import Model.GameBoard;
import Model.Location;
import Model.Settings;

import java.util.Random;

public class Ai {
    private final Settings settings;
    private Random random = new Random();
    private GameBoard gameBoard;

    public Ai(GameBoard gameBoard, Settings settings) {
        this.gameBoard = gameBoard;
        this.settings = settings;
    }

    public Location getNextMove() {
        return new Location(random.nextInt(settings.HEIGHT), random.nextInt(settings.WIDTH));
    }
}
