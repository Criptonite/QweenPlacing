package objects;

import java.awt.*;

/**
 * Created by Daniil on 21.06.2017.
 */
public class Cell extends Rectangle {

    private Color color;

    public Color getColor() {
        return color;
    }

    public Cell(double x, double y, double size, int color) {
        super(x, y, size, size);
        if (color == -1) {
            this.color = new Color(0, 0, 255, 150);
        } else if (color % 2 == 0) {
            this.color = new Color(160, 84, 49);
        } else {
            this.color = new Color(239, 224, 184);
        }
    }
}
