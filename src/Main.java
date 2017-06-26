import frames.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new MainFrame();

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}
