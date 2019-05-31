package Controller;

import Model.Bank;
import Model.GameBoard;
import Model.UserState;
import Views.Tile;
import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGame implements PropertyChangeListener, Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private ToggleButton bomb;
    @FXML
    private Button undo;
    @FXML
    private Button pause;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label moneyField;
    @FXML
    private Label levelField;

    private UserState userState;
    private GameBoard gameBoard;
    private Bank bank;

    void init(UserState userState, GameBoard gameBoard, Bank bank) {
        this.userState = userState;
        this.gameBoard = gameBoard;
        this.bank = bank;
        moneyField.setText(String.valueOf(bank.getMoney()));
        bomb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            double scale = newValue ? 1.1 : 1.0;
            bomb.setScaleX(scale);
            bomb.setScaleY(scale);
        });
        bomb.setDisable(bank.getMoney() < bank.getBombCost());
        levelField.setText(String.valueOf(gameBoard.getLevel()));
        bomb.setText(String.valueOf(bank.getBombCost()));
        undo.setText(String.valueOf(bank.getUndoCost()));

        bank.addPropertyChangeListener(this);
        gameBoard.addPropertyChangeListener(this);

        undo.setDisable(bank.getMoney() < bank.getUndoCost());
        undo.setOnAction(event -> {
            if (gameBoard.canUndo()) {
                gameBoard.doUndo();
                bank.useUndo();
                undo.setText(String.valueOf(bank.getUndoCost()));
            } else {
                undo.setText("You are poor");
            }
        });

        for (int x = 0; x < userState.getSettings().HEIGHT; x++) {
            for (int y = 0; y < userState.getSettings().WIDTH; y++) {
                Tile tile = new Tile(userState.getSettings().TILE_SIZE);
                updateTile(tile, x, y);
                final int x1 = x;
                final int y1 = y;
                tile.setOnAction(event -> {
                    if (bomb.isSelected()) {
                        String musicFile = "nani.mp3";
                        Media naniSound = new Media(new File(musicFile).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(naniSound);
                        mediaPlayer.play();
                        AnimationFX a = new BounceOut(tile);
                        a.setOnFinished(e -> {
                            gameBoard.bombTile(x1, y1);
                            bank.useBomb();
                            bomb.setText(String.valueOf(bank.getBombCost()));
                        });
                        a.setResetOnFinished(true).setSpeed(2.5).play();

                        bomb.setSelected(false);
                    } else {
                        if (!gameBoard.isClickable(x1, y1)) {
                            new Wobble(tile).setSpeed(2).play();
                            // new Pulse(tile).setSpeed(4).setCycleCount(2).play();
                            return;
                        }
                        bank.addMove();
                        gameBoard.playTile(x1, y1);

                    }
                });

                gameBoard.addPropertyChangeListener(evt -> {
                            if ("Fall".equals(evt.getPropertyName())) {
                                updateTile(tile, x1, y1);
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

    private void updateTile(Tile tile, int x, int y) {
        int value = gameBoard.getValueTile(x, y);
        tile.setNumber(value, value == gameBoard.getLevel());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "Fall":
                //tutto gestito da tile
                break;
            case "Level":
                if ((Integer) evt.getNewValue() > (Integer) evt.getOldValue()) {
                    bank.addInterest();
                }
                levelField.setText(String.valueOf(evt.getNewValue()));
                new GlowText(moneyField, Paint.valueOf("white"), Paint.valueOf("green")).play();//money green
                new GlowText(levelField, Paint.valueOf("white"), Paint.valueOf("green")).play();//level green
                break;
            case "Money Change":
                moneyField.setText(String.valueOf(evt.getNewValue()));
                bomb.setDisable((long) evt.getNewValue() < bank.getBombCost());
                undo.setDisable((long) evt.getNewValue() < bank.getUndoCost());
                break;
            case "Bomb Cost Change":
                bomb.setDisable(bank.getMoney() < (Integer) evt.getNewValue());
                new GlowText(moneyField, Paint.valueOf("white"), Paint.valueOf("red")).play();//money red
                break;

            case "Undo Cost Change":
                undo.setDisable(bank.getMoney() < (Integer) evt.getNewValue());
                new GlowText(moneyField, Paint.valueOf("white"), Paint.valueOf("red")).play();//money red
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
        pause.setOnMouseEntered(event -> new Pulse(pause).play());
        bomb.setOnMouseEntered(event -> new Pulse(bomb).play());
       /* bomb.setOnAction(event -> {
            String musicFile = "shindeiru.mp3";
            Media shindeiruSound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(shindeiruSound);
            mediaPlayer.play();
        });*/
        undo.setOnMouseEntered(event -> new Pulse(undo).play());
    }
}
