package objects;

import frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniil on 21.06.2017.
 * Extended by Dmitriy on 22.06.2017.
 * <p>
 * Class is accepted signal from frame and marshall it to desk
 */
public class GraphPanel extends JPanel {
    private MainFrame frame;

    private Graphics2D graphics;

    private Desk desk;

    private ArrayList<Cell[][]> combinationsArray;

    private ArrayList<Cell[][]> stepsArray;

    private String mode;

    public GraphPanel(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        int size = frame.getSpinnerValue();
        if (desk == null) {
            desk = new Desk(size, this, graphics);
        }
        desk.setGraphics2D(graphics);

        desk.paintDesk();
    }

    /**
     * Entry point to algorithm visualizing
     */
    public void searchSolutions(String mode) {
        this.mode = mode;
        desk.searchSolutions();
        combinationsArray = desk.getCombinations();
        stepsArray = desk.getSteps();
    }

    public void drawCombination(int index){
        System.out.println(index);
        if("manual".equals(mode))
        //combinationsArray = desk.getCombinations();
            desk.setCellMatrix(stepsArray.get(index));
        else{
            desk.setCellMatrix(combinationsArray.get(index));
        }
        this.updateUI();
    }

    public ArrayList<Cell[][]> getCombinationsArray() {
        return combinationsArray;
    }

    public void setCombinationsArray(ArrayList<Cell[][]> combinationsArray) {
        this.combinationsArray = combinationsArray;
    }

    public ArrayList<Cell[][]> getStepsArray() {
        return stepsArray;
    }

    public void setStepsArray(ArrayList<Cell[][]> stepsArray) {
        this.stepsArray = stepsArray;
    }

    public MainFrame getFrame() {
        return frame;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }
}
