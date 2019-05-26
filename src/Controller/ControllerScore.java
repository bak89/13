package Controller;

import Model.UserState;
import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerScore implements Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button mainMenu;
    private UserState userState;

    public void init(UserState userState){
        this.userState = userState;
    }
    public void mainMenu() {
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
    }
}

