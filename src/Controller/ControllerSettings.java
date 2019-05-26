package Controller;

import Model.Settings;
import Model.UserState;
import animatefx.animation.FadeIn;
import io.Serializer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

import javax.xml.bind.JAXBException;
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
        try {
            new Serializer().save(userState, Settings.CONFIG_FILE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    public void init(UserState userState) {
        this.userState = userState;
        changeRow.setValue(userState.getSettings().HEIGHT);
        changeRow.valueProperty().addListener((observable, oldValue, newValue) ->
                userState.getSettings().HEIGHT = newValue.intValue());
        changeColumn.setValue(userState.getSettings().WIDTH);
        changeColumn.valueProperty().addListener((observable, oldValue, newValue) ->
                userState.getSettings().WIDTH = newValue.intValue());
        changeTile.setValue(userState.getSettings().TILE_SIZE);
        changeTile.valueProperty().addListener((observable, oldValue, newValue) ->
                userState.getSettings().TILE_SIZE = newValue.intValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
    }
}

