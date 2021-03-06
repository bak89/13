package Controller;

import Model.UserState;
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerMenu implements Initializable {
    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button newGame;
    @FXML
    private Button settings;
    @FXML
    private Button score;
    @FXML
    private Label moneyField;
    @FXML
    private Label levelField;

    private UserState userState;

    /* private GameBoard gameBoard;
    public void continueGame() {
         ViewChanger.changeToContinue(anchorPane, userState, gameBoard);
     }*/
    public void init(UserState userState) {
        this.userState = userState;
        levelField.setText(String.valueOf(userState.getRecord().getLevel()));
        moneyField.setText(String.valueOf(userState.getRecord().getMoves()));
    }

    public void newGame() {
        ViewChanger.changeToNewGame(anchorPane, userState);
    }

    public void settings() {
        ViewChanger.changeToSettings(anchorPane, userState);
    }

    public void score() {
        ViewChanger.changeToScore(anchorPane, userState, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
        newGame.setOnMouseEntered(event -> new Pulse(newGame).setSpeed(1.5).play());
        settings.setOnMouseEntered(event -> new Pulse(settings).setSpeed(1.5).play());
        score.setOnMouseEntered(event -> new Pulse(score).setSpeed(1.5).play());
    }
}






