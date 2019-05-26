import Controller.ControllerMenu;
import Model.Bank;
import Model.Settings;
import Model.UserState;
import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //aggiunta lingua
        // ResourceBundle bundle = ResourceBundle.getBundle("easter.example", new Locale("de", "DE"));

        UserState userState = new UserState(Settings.DEFAULT_LEVEL, 1000);

        // Parent root = FXMLLoader.load(getClass().getResource("Views/ly1.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/ly1.fxml"));

        Parent root = loader.load();
        loader.<ControllerMenu>getController().init(userState);

        primaryStage.setTitle("Main Menu");
        Scene scene = new Scene(root, 386, 653);
        //primaryStage.setScene(new Scene(root, 386, 653));
        scene.getStylesheets().add("Design.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        //animate the stage
        new FadeIn(root).play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
