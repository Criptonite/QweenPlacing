package objects;

import algorythm.Backtracking;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Daniil on 21.06.2017.
 * Extended by Dmitriy on 21.06.2017.
 * <p>
 * <p>
 * Class will redraw itself when it'is necessary.
 */
public class Desk implements Serializable{

    //Image owner needs to be implemented in separate class
    private int size;
    private double cellSize;
    private Cell[][] cellMatrix;
    private Backtracking backtracking;
    private ArrayList<Cell[][]> steps;
    private ArrayList<Cell[][]> combinations;

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

    public Desk(int size, double graphPanelSize) {
        this.size = size;
        this.cellSize = graphPanelSize / size;           //!!!!!!!!!!!!!!!!
        cellMatrix = new Cell[size][size];
        fillMatrix();
    }

    /**
     * Matrix getter for backtracking
     *
     * @return cellMatrix metrix of cells
     */
    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    public void searchSolutions() {
        backtracking = new Backtracking(this);
        backtracking.run();
    }

    public ArrayList<Cell[][]> getCombinations() {
        return combinations;
    }

    public ArrayList<Cell[][]> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Cell[][]> steps) {
        this.steps = steps;
    }

    public void setCombinations(ArrayList<Cell[][]> combinations) {
        this.combinations = combinations;
    }

    public void setCellMatrix(Cell[][] cellMatrix) {
        this.cellMatrix = cellMatrix;
    }

    public double getCellSize() {
        return cellSize;
    }
}
