package objects;

import java.awt.geom.Point2D;

/**
 * Created by Daniil on 21.06.2017.
 */
public class Point extends Point2D {

    private double x;
    private double y;


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public double getX() {
        return x;
    }


    @Override
    public double getY() {
        return y;
    }


    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }


}
