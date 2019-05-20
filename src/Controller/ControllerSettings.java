package Controller;

import Model.Settings;
import Model.UserState;
import animatefx.animation.FadeIn;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSettings implements PropertyChangeListener, Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button mainMenu;
    @FXML
    private Label labelSettings;
    @FXML
    private MenuButton gridSize;
    @FXML
    private Button apply;
    @FXML
    private Slider changeRow;
    @FXML
    private Slider changeColumn;
    @FXML
    private Slider changeTile;

    private UserState userState;
    private Settings settings;

    public void mainMenu() {
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    public void changeSettings(){
        changeColumn.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                changeColumn.setValue(Settings.HEIGHT);
            }
        });
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
    }
}

