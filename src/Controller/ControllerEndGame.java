package Controller;


import Model.*;
import Views.Tile;
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import io.Serializer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.xml.bind.JAXBException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEndGame implements Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Label labelScore;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button mainMenu;
    @FXML
    private Button score;
    @FXML
    private Button restart;

    private UserState userState;
    private Integer currentRecord;


    void init(UserState userState, GameBoard gameBoard, Bank bank) {
        this.userState = userState;

        labelScore.setText(String.valueOf(gameBoard.getLevel()));
        currentRecord = userState.updateRecord(new Score(gameBoard.getLevel(), bank.getMoney()));

        try {
            new Serializer().save(userState, Settings.CONFIG_FILE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < userState.getSettings().HEIGHT; x++) {
            for (int y = 0; y < userState.getSettings().WIDTH; y++) {
                Tile tile = new Tile(userState.getSettings().TILE_SIZE);
                tile.setNumber(gameBoard.getValueTile(x, y), false);
                gridPane.add(tile, y, x);
            }
        }
    }

    public void restart() {
        ViewChanger.changeToNewGame(anchorPane, userState);
    }

    public void mainMenu() {
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    public void score() {
        ViewChanger.changeToScore(anchorPane, userState, currentRecord);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
        mainMenu.setOnMouseEntered(event -> new Pulse(mainMenu).play());
        restart.setOnMouseEntered(event -> new Pulse(restart).play());
        score.setOnMouseEntered(event -> new Pulse(score).play());
    }
}

