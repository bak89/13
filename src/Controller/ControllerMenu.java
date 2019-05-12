package Controller;

import Model.*;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.UnknownServiceException;


public class ControllerMenu implements PropertyChangeListener {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    private Button newGame;
    @FXML
    private Button continueGame;
    @FXML
    private Button score;

    @FXML
    private Button language;

    private UserState userState;

    public void init(UserState userState) {
        this.userState = userState;
    }

    public void newGame(ActionEvent event) throws IOException {

        GameBoard gameBoard = new GameBoard(Settings.HEIGHT, Settings.WIDTH,
                new BinomialGenerator(new Level(Settings.DEFAULT_LEVEL), Settings.LEVEL_RANGE));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ly2.fxml"));
        AnchorPane pane = loader.load();
        anchorPane.getScene().setRoot(pane);
        loader.<ControllerGame>getController().init(userState,gameBoard);
    }

    public void continueGame(ActionEvent event) {


    }


    public void score(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ly5.fxml"));
        AnchorPane pane = loader.load();
        anchorPane.getScene().setRoot(pane);
    }

    public void language(ActionEvent event) throws IOException {

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}






