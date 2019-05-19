package Controller;

import Model.Bank;
import Model.GameBoard;
import Model.Settings;
import Model.UserState;
import Views.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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
    private ToggleButton bomb;
    @FXML
    private Button undo;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label moneyField;
    @FXML
    private Label levelField;

    private UserState userState;
    private GameBoard gameBoard;
    private Bank bank;

    public void init(UserState userState, GameBoard gameBoard, Bank bank) {
        this.userState = userState;
        this.gameBoard = gameBoard;
        this.bank = bank;
        moneyField.setText(String.valueOf(bank.getMoney()));
        bomb.setDisable(bank.getMoney() < bank.getBombCost());
        levelField.setText(String.valueOf(gameBoard.getLevel()));

        bank.addPropertyChangeListener(this);
        gameBoard.addPropertyChangeListener(this);

        undo.setDisable(bank.getMoney() < bank.getUndoCost());
        undo.setOnAction(event -> {
            if (gameBoard.canUndo()) {
                gameBoard.doUndo();
                bank.useUndo();
            } else {
                undo.setText("Sei povero");

            }
        });

        for (int x = 0; x < Settings.HEIGHT; x++) {
            for (int y = 0; y < Settings.WIDTH; y++) {
                Tile tile = new Tile();
                tile.setNumber(gameBoard.getValueTile(x, y));
                final int x1 = x;
                final int y1 = y;
                tile.setOnAction(event -> {
                    if (bomb.isSelected()) {
                        gameBoard.bombTile(x1, y1);
                        bank.useBomb();
                        bomb.setSelected(false);
                    } else {
                        if (!gameBoard.isClickable(x1, y1)) {
                            return;
                        }
                        bank.addMove();
                        gameBoard.playTile(x1, y1);
                    }
                });

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
        ViewChanger.changeToPause(anchorPane, userState, gameBoard, bank);
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
            case "Level":
                if ((Integer) evt.getNewValue() > (Integer) evt.getOldValue()) {
                    bank.addInterest();
                }
                //      userState.updateRecord((Integer) evt.getNewValue(), bank.getMoney());
                levelField.setText(String.valueOf(evt.getNewValue()));
                break;

            case "Money Change":
                moneyField.setText(String.valueOf(evt.getNewValue()));
                bomb.setDisable((Integer) evt.getNewValue() < bank.getBombCost());
                undo.setDisable((Integer) evt.getNewValue() < bank.getUndoCost());
                break;
            case "Bomb Cost Change":
                bomb.setDisable(bank.getMoney() < (Integer) evt.getNewValue());
                break;

            case "Undo Cost Change":
                undo.setDisable(bank.getMoney() < (Integer) evt.getNewValue());
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
