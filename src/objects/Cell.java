package objects;

import java.awt.*;

/**
 * Created by Daniil on 21.06.2017.
 * Extend by Ruslan on 22.06.2017.
 */
public class Cell extends Rectangle implements Cloneable{

    private Color color;
    private Color standardColor;
    private boolean queen;

    private int occupiedDegree;


    public Cell(double x, double y, double size, int color) {
        super(x, y, size, size);
        if (color == -1) {                                          //this IF isn't using, need it?
            this.color = new Color(0, 0, 255, 150);
            this.standardColor = new Color(0, 0, 255, 150);
            queen = true;
        } else if (color % 2 == 0) {
            this.color = new Color(160, 84, 49);
            this.standardColor = new Color(160, 84, 49);
            queen = false;

        } else {
            this.color = new Color(239, 224, 184);
            this.standardColor = new Color(239, 224, 184);
            queen = false;
        }
        occupiedDegree = 0;
    }

    public Color getStandardColor() {
        return standardColor;
    }
    /**
     * Change cell color if occupied
     */
    private void makeOccupied(){
        color = new Color(0, 0, 255, 150);
    }

    /**
     * Change back standard color
     */
    private void makeFree(){
        color = standardColor;
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
        makeOccupied();
    }

    /**
     * Decrement field each time cell free
     */
    public void free() {
        if (occupiedDegree > 0) occupiedDegree--;
        if (occupiedDegree == 0) makeFree();
    }

    public void makeQueen(){
        queen = true;
        occupied();
    }

    public void unMakeQueen(){
        queen = false;
        free();
    }

    public boolean isQueen(){
        return queen;
    }

    @Override
    public Object clone() {
        Cell newCell = new Cell(this.getX(), this.getY(), this.getWidth() , 0);
        newCell.color = this.color;
        newCell.standardColor = this.standardColor;
        newCell.queen = this.queen;
        newCell.occupiedDegree = this.occupiedDegree;
        return newCell;
    }
}
