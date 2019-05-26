package Controller;

import Model.Bank;
import Model.GameBoard;
import Model.Settings;
import Model.UserState;
import Views.Tile;
import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerGame implements PropertyChangeListener, Initializable {

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
                        AnimationFX a = new BounceOutDown(tile);
                        a.setOnFinished(e -> {
                            gameBoard.bombTile(x1, y1);
                            bank.useBomb();
                            new BounceInUp(tile).play();
                        });
                        a.play();
                        bomb.setSelected(false);
                    } else {
                        if (!gameBoard.isClickable(x1, y1)) {
                            new Wobble(tile).play();
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
            case "Fall":
                //salvare copia board
                //in init inizializzare undo con stato board
                break;
            case "Level":
                if ((Integer) evt.getNewValue() > (Integer) evt.getOldValue()) {
                    bank.addInterest();
                }
                //
                levelField.setText(String.valueOf(evt.getNewValue()));
                new GlowText(moneyField, Paint.valueOf("white"), Paint.valueOf("green")).play();//soldi verde
                new GlowText(levelField, Paint.valueOf("white"), Paint.valueOf("green")).play();//livello verde
                break;
            case "Money Change":
                moneyField.setText(String.valueOf(evt.getNewValue()));
                bomb.setDisable((Integer) evt.getNewValue() < bank.getBombCost());
                undo.setDisable((Integer) evt.getNewValue() < bank.getUndoCost());
                break;
            case "Bomb Cost Change":
                bomb.setDisable(bank.getMoney() < (Integer) evt.getNewValue());
                new GlowText(moneyField, Paint.valueOf("white"), Paint.valueOf("red")).play();//animazione soldi rosso
                break;

            case "Undo Cost Change":
                undo.setDisable(bank.getMoney() < (Integer) evt.getNewValue());
                new GlowText(moneyField, Paint.valueOf("white"), Paint.valueOf("red")).play();//animazione soldi rosso
                break;

            case "Game Over":
                ViewChanger.changeToGameOver(anchorPane, userState, gameBoard, bank);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(anchorPane).play();
    }
}
