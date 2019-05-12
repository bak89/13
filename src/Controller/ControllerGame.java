package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;



import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerGame implements PropertyChangeListener {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private Button pause;
    @FXML
    private Button bomb;
    @FXML
    private Button undo;
    @FXML
    private StackPane stackPane;

    private Group group = new Group();






    public void pause(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ly3.fxml"));
        AnchorPane pane = loader.load();
        anchorPane.getScene().setRoot(pane);
    }


    public void gameOver(PropertyChangeEvent evt) throws IOException {

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "New Game":
               // addAllTiles((ArrayList<Tile>) evt.getNewValue());
                break;
            case "Continue":
                stackPane.getChildren().addAll((Group) evt.getOldValue());
                break;
            case "Remove":
                group.getChildren().remove(evt.getNewValue());
                break;
            case "Fall":

                break;
            case "Bomb":

                break;

            case "Undo":
                break;

            case "Game Over":
               // gameOver(evt);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }

    }

}
