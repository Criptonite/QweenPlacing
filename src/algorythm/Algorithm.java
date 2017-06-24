package algorythm;

import objects.Cell;

import java.util.ArrayList;

//marker interface for hierarchy creating ability
interface Algorithm {

    void setQueen(int row, int col);

    void removeQueen(int row, int col);

    void tryQueen(int row, int col, int size, int num);

    ArrayList<Cell[][]> getCombinations();
}
