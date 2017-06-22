package algorythm;

import objects.Cell;

/**
 * Created by Daniil on 22.06.2017.
 */
public class Backtracking {

    private Cell [][] matrix;
    private int range;


    Backtracking (Cell [][] matrix){
        this.matrix = matrix;
        range = 0;
    }

    public void setQween(int row, int col){
        int descSize = (int) Math.sqrt(matrix.length); //хз что здесь
        //horisont
        for(int i = 0; i < descSize; i++){
            matrix[row][i].setEmployment(matrix[row][i].getEmployment()+1);
        }

        //vertical
        for(int i = 0; i < descSize; i++){
            matrix[i][col].setEmployment(matrix[i][col].getEmployment()+1);
        }
        int diagY = row;
        int diagX = col;

        //north-west
        while(diagX >= 0 && diagY >= 0){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()+1);
            diagX--;
            diagY--;
        }
        //south-west
        diagY = row;
        diagX = col;
        while(diagX >= 0 && diagY < descSize){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()+1);
            diagX--;
            diagY++;
        }

        //north-east
        diagY = row;
        diagX = col;
        while(diagX < descSize && diagY >= 0){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()+1);
            diagX++;
            diagY--;
        }


        //south-east
        diagY = row;
        diagX = col;
        while(diagX < descSize && diagY < descSize){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()+1);
            diagX++;
            diagY++;
        }
        matrix[row][col].setEmployment(-1);
        //drawDesk(this->matrix);
    }

    public void removeQween(int row, int col){
        int descSize = (int) Math.sqrt(matrix.length);
        //horisont
        for(int i = 0; i < descSize; i++){
            matrix[row][i].setEmployment(matrix[row][i].getEmployment()-1);
        }

        //vertical
        for(int i = 0; i < descSize; i++){
            matrix[i][col].setEmployment(matrix[i][col].getEmployment()-1);
        }
        int diagY = row;
        int diagX = col;

        //north-west
        while(diagX >= 0 && diagY >= 0){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()-1);
            diagX--;
            diagY--;
        }
        //south-west
        diagY = row;
        diagX = col;
        while(diagX >= 0 && diagY < descSize){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()-1);
            diagX--;
            diagY++;
        }

        //north-east
        diagY = row;
        diagX = col;
        while(diagX < descSize && diagY >= 0){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()-1);
            diagX++;
            diagY--;
        }


        //south-east
        diagY = row;
        diagX = col;
        while(diagX < descSize && diagY < descSize){
            matrix[diagY][diagX].setEmployment(matrix[diagY][diagX].getEmployment()-1);
            diagX++;
            diagY++;
        }
        matrix[row][col].setEmployment(0);
        //drawDesk(this->matrix);
    }

    public void tryQween(int row, int col, int size, int num){
        if(num == (size)) range++;
        if(row == size || col == size) return;
        for(int i = 0; i < size; i++){
            if(matrix[row][i].getEmployment() == 0){
                //delay();
                setQween(row, i);
                num++;
                int nextRow = row + 1;
                tryQween(nextRow, col, size, num);
                //delay();
                removeQween(row, i);
                num--;
            }
        }

    }

    public boolean checkQween(int row, int col, int size){
        for(int i = 0; i < row; i++){
            if(matrix[i][col].getEmployment() == -1){
                return false;
            }
        }

        for(int i = 1; i <= row && col-i >= 0; ++i)
        {
            if(matrix[row-i][col-i].getEmployment() == -1)
            {
                return false;
            }
        }

        for(int i = 1; i <= row && col+i < size; i++)
        {
            if(matrix[row-i][col+i].getEmployment() == -1)
            {
                return false;
            }
        }
        return true;
    }
}
