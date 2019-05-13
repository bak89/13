package Controller;

import Model.GameBoard;
import Model.Settings;
import Model.UserState;
import Views.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;


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
    private GridPane gridPane;

    private UserState userState;
    private GameBoard gameBoard;

    public void init(UserState userState, GameBoard gameBoard) {
        this.userState = userState;
        this.gameBoard = gameBoard;
        gameBoard.addPropertyChangeListener(this);
        for (int x = 0; x < Settings.HEIGHT; x++) {
            for (int y = 0; y < Settings.WIDTH; y++) {
                Tile tile = new Tile();
                tile.setNumber(gameBoard.getValueTile(x, y));
                final int x1 = x;
                final int y1 = y;
                tile.setOnAction(event -> gameBoard.playTile(x1, y1));
                gameBoard.addPropertyChangeListener(evt -> {
                            switch (evt.getPropertyName()) {
                                case "Fall":
                                    tile.setNumber(gameBoard.getValueTile(x1, y1));
                                    break;
                            }
                        }
                );
                gridPane.add(tile, y, x);
            }
        }
    }

    public void pause() {
        ViewChanger.changeToPause(anchorPane, userState, gameBoard);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "Continue":
                gridPane.getChildren().addAll((Group) evt.getOldValue());
                break;

            case "Fall":
                //salvare copia board
                //in init inizializzare undo con stato board
                break;
            case "Bomb":
                break;

            case "Undo":
                break;

            case "Game Over":
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ly4.fxml"));
                    AnchorPane pane = loader.load();
                    anchorPane.getScene().setRoot(pane);
                } catch (IOException e) {
                    System.out.println("Error");
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }

    }

}
