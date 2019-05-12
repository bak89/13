package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;



import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ControllerPause implements PropertyChangeListener {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    private Button continueB;

    @FXML
    private Button restart;

    @FXML
    private Button mainMenu;


    public void restart() throws IOException {
        //GameBoard gameBoard = new GameBoard();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ly2.fxml"));
        AnchorPane pane = loader.load();
        anchorPane.getScene().setRoot(pane);
        //loader.<ControllerGame>getController().init(gameBoard);
    }

    public void mainMenu() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ly1.fxml"));
        AnchorPane pane = loader.load();
        anchorPane.getScene().setRoot(pane);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

