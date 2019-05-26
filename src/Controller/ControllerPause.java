package Controller;

import Model.*;
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerPause implements Initializable {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    private Button continueB;

    @FXML
    private Button restart;

    @FXML
    private Button mainMenu;

    private UserState userState;
    private GameBoard gameBoard;
    private Bank bank;

    public void init(UserState userState, GameBoard gameBoard, Bank bank) {
        this.userState = userState;
        this.gameBoard = gameBoard;
        this.bank = bank;
    }

    public void restart() {
        ViewChanger.changeToNewGame(anchorPane, userState);
    }

    public void mainMenu() {
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    public void continueB() {
        ViewChanger.changeToContinue(anchorPane, userState, gameBoard, bank);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
        continueB.setOnMouseEntered(event -> new Pulse(continueB).play());
        restart.setOnMouseEntered(event -> new Pulse(restart).play());
        mainMenu.setOnMouseEntered(event -> new Pulse(mainMenu).play());
    }
}

