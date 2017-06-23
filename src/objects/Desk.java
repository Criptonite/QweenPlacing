package objects;

import algorythm.Backtracking;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniil on 21.06.2017.
 * Extended by Dmitriy on 21.06.2017.
 * <p>
 * <p>
 * Class will redraw itself when it'is necessary.
 */
public class Desk {

    //Image owner needs to be implemented in separate class
    private BufferedImage qweenImage;
    private int size;
    private double cellSize;
    Graphics2D graphics2D;


    private Cell[][] cellMatrix;
    private Backtracking backtracking;

    private GraphPanel graphPanel; //NEED

    /**
     * Method initializing cell matrix
     */
    private void fillMatrix() {
        int x = 0;
        int y = 0;
        int cellColor = 0;

        for (int i = 0; i < size; i++) {
            x = 0;
            for (int j = 0; j < size; j++) {
                cellMatrix[i][j] = new Cell(x, y, cellSize, cellColor);
                x += cellSize;
                cellColor++;
            }
            if (size % 2 == 0) cellColor++;
            y += cellSize;
        }
    }

    public Desk(int size, GraphPanel graphPanel, Graphics2D graphics2D) {
        this.size = size;
        this.graphPanel = graphPanel;
        this.graphics2D = graphics2D;
        this.cellSize = graphPanel.getWidth() / size;
        cellMatrix = new Cell[size][size];
        fillMatrix();
        QueenImage img = new QueenImage();
        qweenImage = img.resize((int) cellSize, (int) cellSize);
    }

    /**
     * Matrix getter for backtracking
     *
     * @return cellMatrix metrix of cells
     */
    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    /**
     * Method painting desk
     */
    public void paintDesk() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                drawElement(i, j);
            }
        }
    }


    /**
     * Method used for drawing cells in one string
     *
     * @param i index of raw
     * @param j index of coll
     */
    private void drawElement(int i, int j) {
        Cell cell = cellMatrix[i][j];

        graphics2D.setColor(cell.getStandardColor());
        graphics2D.fill(cell);
        graphics2D.draw(cell);

        graphics2D.setColor(cell.getColor());
        graphics2D.fill(cell);
        graphics2D.draw(cell);

        if (qweenImage != null)
            if (cell.isQueen())
                graphics2D.drawImage(qweenImage, (int) cell.getX(), (int) cell.getY(), graphPanel);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }


    public void searchSolutions() {
        backtracking = new Backtracking(this, graphPanel);
        Thread backtrackingTask = new Thread(backtracking);
        backtrackingTask.start();

    }

}