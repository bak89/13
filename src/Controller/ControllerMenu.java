package Controller;

import Model.*;
import animatefx.animation.FadeIn;
import animatefx.animation.Flash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerMenu implements PropertyChangeListener, Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //new Flash().play();
    }
}






