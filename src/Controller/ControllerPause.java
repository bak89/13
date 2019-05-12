package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ControllerPause {

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

    public void init(UserState userState, GameBoard gameBoard) {
        this.userState = userState;
        this.gameBoard = gameBoard;
    }

    public void restart() {
        ViewChanger.changeToNewGame(anchorPane, userState);
    }

    public void mainMenu() {

        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    public void continueB() {
        ViewChanger.changeToContinue(anchorPane, userState, gameBoard);
    }

}

