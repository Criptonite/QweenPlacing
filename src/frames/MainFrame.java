package frames;

import objects.GraphPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Dmitriy on 21.06.2017.
 * Extended by Daniil on 21.06.2017.
 */
public class MainFrame extends JFrame {

    private JLabel sizeLabel;

    private JButton startButton;

    private JButton nextButton;

    private JButton preButton;

    private JPanel rightPanel;

    private JSpinner sizeSpinner;

    private GraphPanel graphPanel;

    private JMenuBar menuBar;

    private JMenu openMenu;

    private JMenu saveMenu;

    private JMenu infoMenu;


    public MainFrame() {
        super();
        setTitle("Queen Placing v 0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 450));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        initMenuBar();


        //Create standart dimensions
        //Left panel dimension
        Dimension leftPanelMinDimension = new Dimension(250, getHeight());
        Dimension leftPanelMaxDimension = new Dimension(450, getHeight());
        Dimension leftPanelPreferredDimension = new Dimension(350, getHeight());


        //Right panel dimensoin
        Dimension rightPanelMinDimension = new Dimension(150, getHeight());
        Dimension rightPanelMaxDimension = new Dimension(350, getHeight());
        Dimension rightPanelPreferredDimension = new Dimension(250, getHeight());

        //Spinner dimension
        Dimension spinnerMinDimension = new Dimension(25, 25);
        Dimension spinnerMaxDimension = new Dimension(100, 25);
        Dimension spinnerPreferredDimension = new Dimension(50, 25);

        //Buttons dimension
        Dimension buttonMinDimension = new Dimension(50, 45);
        Dimension buttonMaxDimension = new Dimension(150, 45);
        Dimension buttonPreferredDimension = new Dimension(50, 45);


        //Create left panel
        graphPanel = new GraphPanel(this);
        graphPanel.setMinimumSize(leftPanelMinDimension);
        graphPanel.setMaximumSize(leftPanelMaxDimension);
        graphPanel.setPreferredSize(leftPanelPreferredDimension);
        graphPanel.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1),
                new BevelBorder(BevelBorder.LOWERED)));

        //Create right panel
        rightPanel = new JPanel();
        rightPanel.setMinimumSize(rightPanelMinDimension);
        rightPanel.setMaximumSize(rightPanelMaxDimension);
        rightPanel.setPreferredSize(rightPanelPreferredDimension);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1),
                new BevelBorder(BevelBorder.LOWERED)));


        //Create right sub panel for sise
        JPanel sizeSubPanel = new JPanel();
        BoxLayout sizeSubLayout = new BoxLayout(sizeSubPanel, BoxLayout.X_AXIS);
        sizeSubPanel.setLayout(sizeSubLayout);

        //Create label
        sizeLabel = new JLabel("Size of desk: ");
        sizeLabel.setAlignmentX(JLabel.CENTER);
        sizeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));


        //Create spinner
        sizeSpinner = new JSpinner(new SpinnerNumberModel(4, 0, 64, 1));
        sizeSpinner.setMinimumSize(spinnerMinDimension);
        sizeSpinner.setMaximumSize(spinnerMaxDimension);
        sizeSpinner.setPreferredSize(spinnerPreferredDimension);
        sizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                graphPanel.updateUI();
            }
        });

        //Add to sub panel
        sizeSubPanel.add(sizeLabel);
        sizeSubPanel.add(sizeSpinner);
        sizeSubPanel.setMinimumSize(new Dimension(225, 50));
        sizeSubPanel.setMaximumSize(new Dimension(300, 50));
        sizeSubPanel.setPreferredSize(new Dimension(250, 50));
        sizeSubPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


        //Create sub panel for buttons
        JPanel buttonsSubPanel = new JPanel();
        BoxLayout buttonsSubLayout = new BoxLayout(buttonsSubPanel, BoxLayout.Y_AXIS);
        buttonsSubPanel.setLayout(buttonsSubLayout);

        //Create buttons
        startButton = new JButton("Start");
        startButton.setMinimumSize(buttonMinDimension);
        startButton.setMaximumSize(buttonMaxDimension);
        startButton.setPreferredSize(buttonPreferredDimension);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.searchSolutions();
            }
        });

        nextButton = new JButton("Next");
        nextButton.setEnabled(false);
        nextButton.setMinimumSize(buttonMinDimension);
        nextButton.setMaximumSize(buttonMaxDimension);
        nextButton.setPreferredSize(buttonPreferredDimension);

        preButton = new JButton("Prev");
        preButton.setEnabled(false);
        preButton.setMinimumSize(buttonMinDimension);
        preButton.setMaximumSize(buttonMaxDimension);
        preButton.setPreferredSize(buttonPreferredDimension);

        //Add to sub panel
        buttonsSubPanel.add(startButton);
        buttonsSubPanel.add(Box.createVerticalStrut(5));
        buttonsSubPanel.add(nextButton);
        buttonsSubPanel.add(Box.createVerticalStrut(5));
        buttonsSubPanel.add(preButton);
        buttonsSubPanel.setMinimumSize(new Dimension(255, 250));
        buttonsSubPanel.setMaximumSize(new Dimension(300, 250));
        buttonsSubPanel.setPreferredSize(new Dimension(350, 250));
        buttonsSubPanel.setBorder(new EmptyBorder(5, 5, 5, 55));


        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(sizeSubPanel);
        rightPanel.add(Box.createVerticalStrut(55));
        rightPanel.add(buttonsSubPanel);


        getContentPane().add(graphPanel);


        getContentPane().add(rightPanel);

        setJMenuBar(menuBar);
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        MenuListener listener = new MenuBarListener();
        saveMenu = new JMenu("Save");
        saveMenu.addMenuListener(listener);
        openMenu = new JMenu("Open");
        openMenu.addMenuListener(listener);
        infoMenu = new JMenu("Info");
        infoMenu.addMenuListener(listener);

        menuBar.add(saveMenu);
        menuBar.add(openMenu);
        menuBar.add(infoMenu);
    }


    //Throwing exceptions ont know why......
    private class MenuBarListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            JMenu temp = (JMenu) e.getSource();
            if(temp == null) return;
            String menuName = temp.getName();
            if (menuName.equals("Save")) {
                saveAction();
            } else if (menuName.equals("Open")) {
                openAction();

            } else if (menuName.equals("Info")) {
                infoAction();
            }
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            //DO NOTHING
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            //DO NOTHING
        }
    }

    public int getSpinnerValue() {
        return (int) sizeSpinner.getValue();
    }


    //Will be use for implementing menu behavior. Will be implemented later
    private void saveAction() {
    }

    private void openAction() {
    }

    private void infoAction() {
    }
}
