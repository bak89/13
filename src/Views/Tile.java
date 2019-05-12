package Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class Tile extends Button {

    public Tile() {
        setPrefSize(Model.Settings.TILE_SIZE, Model.Settings.TILE_SIZE);
        setNumber(0);
    }

    public void setNumber(int number) {
        setBackground(new Background(new BackgroundFill(Model.Settings.BLOCK_COLORS.get(number), new CornerRadii(5), Insets.EMPTY)));

        setText(Integer.toString(number));
    }
}
