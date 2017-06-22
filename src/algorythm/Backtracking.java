package algorythm;

import objects.Cell;
import objects.Desk;
import objects.GraphPanel;

/**
 * Created by Daniil on 22.06.2017.
 */
public class Backtracking implements Runnable {

    private static final long RENDER_DELAY = 800;

    private Desk desk;
    private Cell[][] matrix;
    private int range;
    private GraphPanel panel;


    public Backtracking(Desk desk, GraphPanel panel) {
        this.desk = desk;
        this.panel = panel;
        this.matrix = desk.getCellMatrix();
        range = 0;
    }

    public void setQween(int row, int col) {
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

    public void removeQween(int row, int col) {
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

    public void tryQween(int row, int col, int size, int num) {
        if (num == (size)) range++;
        if (row == size || col == size) return;
        for (int i = 0; i < size; i++) {
            if (matrix[row][i].isFree()) {
                delay(RENDER_DELAY);
                setQween(row, i);
                System.out.println("Set qween " + num);
                num++;
                int nextRow = row + 1;
                tryQween(nextRow, col, size, num);
                delay(RENDER_DELAY);
                removeQween(row, i);
                num--;
                System.out.println("Remove qween " + num);
            }
        }

    }

    private void delay(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        tryQween(0, 0, matrix.length, 0);
    }

//    public boolean checkQween(int row, int col, int size){
//        for(int i = 0; i < row; i++){
//            if(matrix[i][col].getEmployment() == -1){
//                return false;
//            }
//        }
//
//        for(int i = 1; i <= row && col-i >= 0; ++i)
//        {
//            if(matrix[row-i][col-i].getEmployment() == -1)
//            {
//                return false;
//            }
//        }
//
//        for(int i = 1; i <= row && col+i < size; i++)
//        {
//            if(matrix[row-i][col+i].getEmployment() == -1)
//            {
//                return false;
//            }
//        }
//        return true;
//    }
}
