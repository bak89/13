package Controller;

import Model.UserState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControllerSettings implements PropertyChangeListener {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button mainMenu;
    private UserState userState;

    public void mainMenu() {
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

