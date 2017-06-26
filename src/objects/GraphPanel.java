package objects;

import frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Daniil on 21.06.2017.
 * Extended by Dmitriy on 22.06.2017.
 * <p>
 * Class is accepted signal from frame and marshall it to desk
 */
public class GraphPanel extends JPanel {
    private MainFrame frame;

    private Graphics2D graphics2D;

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
        graphics2D = (Graphics2D) g;
        int size = frame.getSpinnerValue();
        if (desk == null) {
            desk = new Desk(size, this.getWidth());
        }

        paintDesk();
    }

    /**
     * Entry point to algorithm visualizing
     */
    public void searchSolutions(String mode) {
        this.mode = mode;
        desk.searchSolutions();
        combinationsArray = desk.getCombinations();
        stepsArray = desk.getSteps();
        frame.getNextButton().setEnabled(true);
    }

    public void drawCombination(int index){
        System.out.println(index);
        if("manual".equals(mode))
            desk.setCellMatrix(stepsArray.get(index));
        else{
            desk.setCellMatrix(combinationsArray.get(index));
        }
        this.updateUI();
    }

    public ArrayList<Cell[][]> getCombinationsArray() {
        return combinationsArray;
    }

    public ArrayList<Cell[][]> getStepsArray() {
        return stepsArray;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    /**
     * Method painting desk
     */
    private void paintDesk(){
        QueenImage img = new QueenImage();
        BufferedImage qweenImage = img.resize((int) desk.getCellSize(), (int) desk.getCellSize());
        int size = desk.getCellMatrix().length;
        Cell [][] cellMatrix = desk.getCellMatrix();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = cellMatrix[i][j];

                graphics2D.setColor(cell.getStandardColor());
                graphics2D.fill(cell);
                graphics2D.draw(cell);

                graphics2D.setColor(cell.getColor());
                graphics2D.fill(cell);
                graphics2D.draw(cell);

                if (qweenImage != null)
                    if (cell.isQueen())
                        graphics2D.drawImage(qweenImage, (int) cell.getX(), (int) cell.getY(), this);
            }
        }

    }
}
