package objects;

import java.awt.*;

/**
 * Created by Daniil on 21.06.2017.
 * Extend by Ruslan on 22.06.2017.
 */
public class Cell extends Rectangle {

    private Color color;
    private int occupiedDegree;

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

    /**
     * Change cell color if occupied
     */
    public void makeOccupied(){
        color = new Color(0, 0, 255, 150);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Occupied indicator
     * @return true if cell is free
     */
    public boolean isFree() {
        return occupiedDegree == 0;
    }

    /**
     * Increment field each time cell occupied
     */
    public void occupied() {
        occupiedDegree++;
    }

    /**
     * Decrement field each time cell free
     */
    public void free() {
        if (occupiedDegree > 0) occupiedDegree--;
    }


}
