import Controller.ControllerMenu;
import Model.Score;
import Model.Settings;
import Model.UserState;
import animatefx.animation.FadeIn;
import io.Deserializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

      /*  //aggiunta lingua
        Locale current = Locale.getDefault();
        ResourceBundle languages = ResourceBundle.getBundle("languages", new Locale("en", "EN"));
        languages.getLocale();*/

        UserState userState;
        try {
            userState = new Deserializer().read("config.xml");
        } catch (FileNotFoundException e) {
            userState = new UserState(new Score(Settings.DEFAULT_LEVEL, 1000));
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/ly1.fxml"));

        Parent root = loader.load();
        loader.<ControllerMenu>getController().init(userState);

        primaryStage.setTitle("Main Menu");
        Scene scene = new Scene(root, 386, 653);
        scene.getStylesheets().add("Design.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        //animate the stage
        new FadeIn(root).play();

    }
}
