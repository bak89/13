package Views;

import Model.Settings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Tile extends Button {

    public Tile() {
        setPrefSize(Settings.TILE_SIZE, Settings.TILE_SIZE);
        setNumber(0, false);
    }

    public void setNumber(int number, boolean isHighest) {
        setText(Integer.toString(number));
        Color color;
        if (isHighest) {
            color = Color.WHITE;
        } else {
            color = Settings.BLOCK_COLORS.get(number);
        }
        setBackground(new Background(new BackgroundFill(color, new CornerRadii(5), Insets.EMPTY)));
    }
}
