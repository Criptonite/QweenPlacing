package objects;

import frames.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniil on 21.06.2017.
 * Extended by Dmitriy on 22.06.2017.
 * <p>
 * Class is accepted signal from frame and marshall it to desk
 */
public class GraphPanel extends JPanel {
    private MainFrame frame;
    Graphics2D graphics;
    Desk desk;
    int prevSize;

    public GraphPanel(MainFrame frame) {
        this.frame = frame;
        prevSize = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        int size = frame.getSpinnerValue();
        if (desk == null || prevSize != size) {
            desk = new Desk(size, this, graphics);
        }
        desk.setGraphics2D(graphics);

        desk.paintDesk();
        prevSize = size;
    }

    /**
     * Entry point to algorithm visualizing
     */
    public void searchSolutions() {
        desk.searchSolutions();
    }
}