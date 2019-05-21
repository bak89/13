package Model;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static final int TILE_SIZE = 60;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;

    public static final int DEFAULT_LEVEL = 6;
    public static final int LEVEL_RANGE = 7;

    public static final double PROBABILITY = 0.4;


    public static final Map<Integer, Color> BLOCK_COLORS = new HashMap<>() {{
        put(0, Color.rgb(252, 252, 252));
        put(1, Color.rgb(230, 194, 74));
        put(2, Color.rgb(181, 207, 97));
        put(3, Color.rgb(198, 132, 84));
        put(4, Color.rgb(192, 83, 78));
        put(5, Color.rgb(196, 83, 147));
        put(6, Color.rgb(147, 73, 198));
        put(7, Color.rgb(96, 61, 165));
        put(8, Color.rgb(54, 65, 181));
        put(9, Color.rgb(54, 129, 197));
        put(10, Color.rgb(6, 161, 189));
        put(11, Color.rgb(82, 227, 220));
        put(12, Color.rgb(197, 238, 240));
        put(13, Color.rgb(200, 200, 200));
        put(14, Color.rgb(134, 134, 134));
        put(15, Color.rgb(79, 79, 79));
        put(16,Color.rgb(90,79,98));
        put(17,Color.rgb(110,79,98));
        put(18,Color.rgb(120,79,98));
        put(19,Color.rgb(150,79,98));
        put(20,Color.rgb(190,79,98));
        put(21,Color.rgb(156,56,98));

    }};
}
