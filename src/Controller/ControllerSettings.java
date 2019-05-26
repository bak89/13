package Controller;

import Model.Settings;
import Model.UserState;
import animatefx.animation.FadeIn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSettings implements Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Slider changeRow;
    @FXML
    private Slider changeColumn;
    @FXML
    private Slider changeTile;

    private UserState userState;

    public void mainMenu() {
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    public void init(UserState userState) {
        this.userState = userState;
        changeRow.setValue(Settings.HEIGHT);
        changeRow.valueProperty().addListener((observable, oldValue, newValue) ->
                Settings.HEIGHT = newValue.intValue());
        changeColumn.setValue(Settings.WIDTH);
        changeColumn.valueProperty().addListener((observable, oldValue, newValue) ->
                Settings.WIDTH = newValue.intValue());
        changeTile.setValue(Settings.TILE_SIZE);
        changeTile.valueProperty().addListener((observable, oldValue, newValue) ->
                Settings.TILE_SIZE = newValue.intValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
    }
}

