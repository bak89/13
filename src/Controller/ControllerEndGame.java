package Controller;


import Model.Bank;
import Model.GameBoard;
import Model.Settings;
import Model.UserState;
import Views.Tile;
import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEndGame implements PropertyChangeListener, Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button score;
    @FXML
    private Button restart;
    @FXML
    private Button mainMenu;
    @FXML
    private Label labelScore;
    @FXML
    private GridPane gridPane;

    private UserState userState;
    private GameBoard gameBoard;
    private Bank bank;

    public void init(UserState userState, GameBoard gameBoard, Bank bank) {
        this.userState = userState;
        this.gameBoard = gameBoard;
        labelScore.setText(String.valueOf(gameBoard.getLevel()));
        userState.updateRecord(gameBoard.getLevel(), bank.getMoney());

        for (int x = 0; x < Settings.HEIGHT; x++) {
            for (int y = 0; y < Settings.WIDTH; y++) {
                Tile tile = new Tile();
                tile.setNumber(gameBoard.getValueTile(x, y));
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
        ViewChanger.changeToScore(anchorPane, userState);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
    }
}

