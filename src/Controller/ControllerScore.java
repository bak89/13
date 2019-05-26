package Controller;

import Model.Score;
import Model.UserState;
import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerScore implements Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private ListView<Score> textArea;

    private UserState userState;

    public void init(UserState userState, Integer currentRecord) {
        this.userState = userState;

        textArea.setItems(FXCollections.observableArrayList(userState.getRecords()));
        if (currentRecord != null) {
            textArea.getSelectionModel().select(currentRecord);
        }
    }

    public void mainMenu() {
        ViewChanger.changeToMainMenu(anchorPane, userState);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
    }
}

