package algorythm;

import objects.Cell;
import objects.Desk;
import objects.GraphPanel;

import java.util.ArrayList;

/**
 * Created by Daniil on 24.06.2017.
 */
public class ManualBacktracking {

    private Desk desk;
    private ArrayList<Cell[][]> steps;
    private ArrayList<Cell[][]> combinations;
    private Cell[][] matrix;


    public ManualBacktracking(Desk desk) {
        this.desk = desk;
        this.steps = new ArrayList<>();
        this.combinations = new ArrayList<>();
        this.matrix = desk.getCellMatrix();
    }

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
        addStep();
    }

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
        addStep();
    }

    public void tryQueen(int row, int col, int size, int num) {
        if (num == (size)) {
            addCombination();
        }
        if (row == size || col == size) return;
        for (int i = 0; i < size; i++) {
            if (matrix[row][i].isFree()) {
                setQueen(row, i);
                System.out.println("Set qween " + num);
                num++;
                int nextRow = row + 1;
                tryQueen(nextRow, col, size, num);
                removeQueen(row, i);
                num--;
                System.out.println("Remove qween " + num);
            }
        }
    }

    public ArrayList<Cell[][]> getCombinations() {
        return combinations;
    }

    public ArrayList<Cell[][]> getSteps() {
        return steps;
    }


    private void addStep() {
        int size = matrix.length;
        Cell[][] newStep = new Cell[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                newStep[i][j] = (Cell) matrix[i][j].clone();
            }
        }
        steps.add(newStep);
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

    public void run() {
                combinations.clear();
                steps.clear();
        tryQueen(0, 0, matrix.length, 0);
        desk.setCombinations(combinations);
        desk.setSteps(steps);
        desk.notifyPanel();
    }
}
