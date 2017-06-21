package objects;

import frames.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniil on 21.06.2017.
 */
public class GraphPanel extends JPanel {
    private MainFrame frame;

    public GraphPanel(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Desk desk = new Desk(frame.getSpinnerValue(), this);
        desk.paintDesk(g2);
    }


}
