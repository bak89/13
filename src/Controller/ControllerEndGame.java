package Controller;


import Model.GameBoard;
import Model.UserState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ControllerEndGame implements PropertyChangeListener {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button score;
    @FXML
    private Button restart;
    @FXML
    private Button mainMenu;

    private UserState userState;
    private GameBoard gameBoard;

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
}

