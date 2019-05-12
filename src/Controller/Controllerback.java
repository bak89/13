package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Controllerback implements PropertyChangeListener {

    @FXML
    public AnchorPane anchorPane;


    @FXML
    private Button back;


    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ly1.fxml"));
        AnchorPane pane = loader.load();
        anchorPane.getScene().setRoot(pane);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

