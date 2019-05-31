package Controller;

import Model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

class ViewChanger {

    static void changeToNewGame(AnchorPane root, UserState userState) {
        GameBoard gameBoard = new GameBoard(userState.getSettings().HEIGHT, userState.getSettings().WIDTH,
                new BinomialGenerator(Settings.LEVEL_RANGE), Settings.DEFAULT_LEVEL);
        changeToContinue(root, userState, gameBoard, new Bank(Settings.DEFAULT_MONEY));

    }

    static void changeToMainMenu(AnchorPane root, UserState userState) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly1.fxml"));
            Parent mainMenu = loader.load();
            root.getScene().setRoot(mainMenu);
            loader.<ControllerMenu>getController().init(userState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void changeToContinue(AnchorPane root, UserState userState, GameBoard gameBoard, Bank bank) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly2.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerGame>getController().init(userState, gameBoard, bank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void changeToPause(AnchorPane root, UserState userState, GameBoard gameBoard, Bank bank) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly3.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerPause>getController().init(userState, gameBoard, bank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void changeToScore(AnchorPane root, UserState userState, Integer currentRecord) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly5.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerScore>getController().init(userState, currentRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void changeToSettings(AnchorPane root, UserState userState) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly6.fxml"));
            Parent pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerSettings>getController().init(userState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void changeToGameOver(AnchorPane root, UserState userState, GameBoard gameBoard, Bank bank) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly4.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerEndGame>getController().init(userState, gameBoard, bank);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
