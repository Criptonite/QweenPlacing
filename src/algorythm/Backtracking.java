package algorythm;

import objects.Cell;
import objects.Desk;
import objects.GraphPanel;

import java.util.ArrayList;

/**
 * Created by Dmitriy on 22.06.2017.
 * Extended by Ruslan on 24.07.2017.
 */
public class Backtracking implements Runnable, Algorithm {

    private static final long RENDER_DELAY = 50;

    private Desk desk;
    private ArrayList<Cell[][]> combinations;
    private Cell[][] matrix;
    private GraphPanel panel;


    public Backtracking(Desk desk, GraphPanel panel) {
        this.desk = desk;
        this.combinations = new ArrayList<>();
        this.matrix = desk.getCellMatrix();
        this.panel = panel;
    }

    @Override
    public void setQueen(int row, int col) {
        int descSize = matrix.length;
        //horisont
        for (int i = 0; i < descSize; i++) {
            matrix[row][i].occupied();
        }

        //vertical
        for (int i = 0; i < descSize; i++) {
            matrix[i][col].occupied();
        }
        int diagY = row;
        int diagX = col;

        //north-west
        while (diagX >= 0 && diagY >= 0) {
            matrix[diagY][diagX].occupied();
            diagX--;
            diagY--;
        }
        //south-west
        diagY = row;
        diagX = col;
        while (diagX >= 0 && diagY < descSize) {
            matrix[diagY][diagX].occupied();
            diagX--;
            diagY++;
        }

        //north-east
        diagY = row;
        diagX = col;
        while (diagX < descSize && diagY >= 0) {
            matrix[diagY][diagX].occupied();
            diagX++;
            diagY--;
        }


        //south-east
        diagY = row;
        diagX = col;
        while (diagX < descSize && diagY < descSize) {
            matrix[diagY][diagX].occupied();
            diagX++;
            diagY++;
        }
        matrix[row][col].makeQueen();
        //desk.updateDesk();
        panel.updateUI();
    }

    @Override
    public void removeQueen(int row, int col) {
        int descSize = matrix.length;
        //horisont
        for (int i = 0; i < descSize; i++) {
            matrix[row][i].free();
        }

        //vertical
        for (int i = 0; i < descSize; i++) {
            matrix[i][col].free();
        }
        int diagY = row;
        int diagX = col;

        //north-west
        while (diagX >= 0 && diagY >= 0) {
            matrix[diagY][diagX].free();
            diagX--;
            diagY--;
        }
        //south-west
        diagY = row;
        diagX = col;
        while (diagX >= 0 && diagY < descSize) {
            matrix[diagY][diagX].free();
            diagX--;
            diagY++;
        }

        //north-east
        diagY = row;
        diagX = col;
        while (diagX < descSize && diagY >= 0) {
            matrix[diagY][diagX].free();
            diagX++;
            diagY--;
        }


        //south-east
        diagY = row;
        diagX = col;
        while (diagX < descSize && diagY < descSize) {
            matrix[diagY][diagX].free();
            diagX++;
            diagY++;
        }
        matrix[row][col].unMakeQueen();
        //desk.updateDesk();
        panel.updateUI();
    }

    @Override
    public void tryQueen(int row, int col, int size, int num) {
        if (num == (size)) {
            addCombination();
        }
        if (row == size || col == size) return;
        for (int i = 0; i < size; i++) {
            if (matrix[row][i].isFree()) {
                delay(RENDER_DELAY);
                setQueen(row, i);
                System.out.println("Set qween " + num);
                num++;
                int nextRow = row + 1;
                tryQueen(nextRow, col, size, num);
                delay(RENDER_DELAY);
                removeQueen(row, i);
                num--;
                System.out.println("Remove qween " + num);
            }
        }

    }

    public ArrayList<Cell[][]> getCombinations() {
        return combinations;
    }

    private void addCombination() {
        int size = matrix.length;
        Cell[][] newCombination = new Cell[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                newCombination[i][j] = (Cell) matrix[i][j].clone();
            }
        }

        combinations.add(newCombination);
    }

    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        combinations.clear();

        tryQueen(0, 0, matrix.length, 0);
        desk.notifyPanel();
    }

}
