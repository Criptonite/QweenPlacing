package objects;

import frames.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniil on 21.06.2017.
 * Extended by Dmitriy on 22.06.2017.
 */
public class GraphPanel extends JPanel {
    private MainFrame frame;
    Graphics2D graphics;
    Desk desk;

    public GraphPanel(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        desk = new Desk(frame.getSpinnerValue(), this);
        desk.paintDesk(graphics);
    }

    /**
     * Entry point to algorithm visualizing
     */
    public void searchSolutions() {
        desk.searchSolutions(graphics);
    }


}