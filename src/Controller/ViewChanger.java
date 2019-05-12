package Controller;

import Model.GameBoard;
import Model.RandomGenerator;
import Model.Settings;
import Model.UserState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Random;

public class ViewChanger {


    public static void changeToNewGame(AnchorPane root, UserState userState) {

        GameBoard gameBoard = new GameBoard(Settings.HEIGHT, Settings.WIDTH, new RandomGenerator() {
            Random random = new Random();

            @Override
            public int getRandomNumber() {

                return random.nextInt(5) + 1;
            }
        }); //new BinomialGenerator(new Level(Settings.DEFAULT_LEVEL), Settings.LEVEL_RANGE));

        changeToContinue(root, userState, gameBoard);

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

    public static void changeToContinue(AnchorPane root, UserState userState, GameBoard gameBoard) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly2.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerGame>getController().init(userState, gameBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeToPause(AnchorPane root, UserState userState, GameBoard gameBoard) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewChanger.class.getResource("../Views/ly3.fxml"));
            AnchorPane pane = loader.load();
            root.getScene().setRoot(pane);
            loader.<ControllerPause>getController().init(userState, gameBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
