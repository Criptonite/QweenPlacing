package objects;

import java.awt.geom.Rectangle2D;

/**
 * Created by Daniil on 21.06.2017.
 */
public class Rectangle extends Rectangle2D {

    private double x;
    private double y;
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(double x, double y, double width, double height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setRect(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }


    @Override
    public int outcode(double x, double y) {
        return 0;
    }


    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        return null;
    }


    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        return null;
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
    public double getWidth() {
        return width;
    }


    @Override
    public double getHeight() {
        return height;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }
}
