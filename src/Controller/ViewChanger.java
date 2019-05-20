package Controller;

import Model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Random;

public class ViewChanger {

    public static void changeToNewGame(AnchorPane root, UserState userState) {

        GameBoard gameBoard = new GameBoard(Settings.HEIGHT, Settings.WIDTH, /*new RandomGenerator() {
            Random random = new Random();

            @Override
            public int getRandomNumber(int level) {

                return random.nextInt(level-1) + 1;
            }
        }*/
                new BinomialGenerator(Settings.LEVEL_RANGE), Settings.DEFAULT_LEVEL);

        changeToContinue(root, userState, gameBoard, new Bank(1000));

    }

    public static void changeToMainMenu(AnchorPane root, UserState userState) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly1.fxml"));
            Parent mainMenu = loader.load();
            root.getScene().setRoot(mainMenu);
            loader.<ControllerMenu>getController().init(userState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeToContinue(AnchorPane root, UserState userState, GameBoard gameBoard, Bank bank) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly2.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerGame>getController().init(userState, gameBoard, bank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeToPause(AnchorPane root, UserState userState, GameBoard gameBoard, Bank bank) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly3.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerPause>getController().init(userState, gameBoard, bank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeToScore(AnchorPane root, UserState userState) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly5.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeToSettings(AnchorPane root, UserState userState) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly6.fxml"));
            Parent pane = loader.load();
            root.getScene().setRoot(pane);
            // loader.<ControllerSettings>getController().init(userState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
