package frames;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Daniil on 26.06.2017.
 */
public class StatusPanel extends JPanel {

    private JLabel statusLabel;

    private Component frame;

    public StatusPanel(Component mainFrame) {
        this.frame = mainFrame;
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setPreferredSize(new Dimension(frame.getWidth(), 16));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(statusLabel);
    }

    public void setText(String text){
        statusLabel.setText(text);
    }

}
