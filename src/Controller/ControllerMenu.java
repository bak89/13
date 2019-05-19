package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;


public class ControllerMenu implements PropertyChangeListener {
    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button newGame;
    @FXML
    private Button settings;
    @FXML
    private Button score;

    @FXML
    private Button language;

    private UserState userState;
    private GameBoard gameBoard;

    public void init(UserState userState) {
        this.userState = userState;
    }

    public void newGame() {
        ViewChanger.changeToNewGame(anchorPane, userState);
    }

   /* public void continueGame() {
        ViewChanger.changeToContinue(anchorPane, userState, gameBoard);
    }*/

   public void settings(){
       ViewChanger.changeToSettings(anchorPane,userState);
   }



    public void score() {
        ViewChanger.changeToScore(anchorPane, userState);
    }

    public void language(ActionEvent event) throws IOException {

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}






