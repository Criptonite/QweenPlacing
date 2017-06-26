package frames;

import objects.Desk;
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

    private JButton generateButton;

    private JButton startButton;

    private JButton nextButton;

    private JButton preButton;

    private JPanel rightPanel;

    private JPanel leftPanel;

    private JSpinner sizeSpinner;

    private GraphPanel graphPanel;

    private JMenuBar menuBar;

    private JMenu openMenu;

    private JMenu saveMenu;

    private JMenu infoMenu;

    private JRadioButton autoMode;

    private JRadioButton manualMode;

    private int index = 0;

    private int factor = 0;

    private boolean isManual;


    /**
     * Constructor
     */
    public MainFrame() {
        super();
        init();

    }


    //METHODS FOR FRAME CONSTRUCT

    /**
     * Initializing of frame
     */
    private void init() {
        factor = 1;
        setTitle("Расстановка ферзей на доске");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 540));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        initMenuBar();

        initLeftPanel();

        initRightPanel();

        getContentPane().add(leftPanel);
        getContentPane().add(rightPanel);
        setJMenuBar(menuBar);
    }

    /**
     * Initializing of menu bar and menu items
     */
    private void initMenuBar() {
        menuBar = new JMenuBar();
        MenuListener listener = new MenuBarListener();
        saveMenu = new JMenu("Сохранить");
        saveMenu.addMenuListener(listener);
        openMenu = new JMenu("Открыть");
        openMenu.addMenuListener(listener);
        infoMenu = new JMenu("О программе");
        infoMenu.addMenuListener(listener);

        menuBar.add(saveMenu);
        menuBar.add(openMenu);
        menuBar.add(infoMenu);
    }

    /**
     * Initializing of left panel with graphic panel
     */
    private void initLeftPanel() {
        //Left panel dimension
        Dimension leftPanelMinDimension = new Dimension(400, getHeight());
        Dimension leftPanelMaxDimension = new Dimension(600, getHeight());
        Dimension leftPanelPreferredDimension = new Dimension(500, getHeight());
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        //Create left panel
        graphPanel = new GraphPanel(this);
        graphPanel.setMinimumSize(leftPanelMinDimension);
        graphPanel.setMaximumSize(leftPanelMaxDimension);
        graphPanel.setPreferredSize(leftPanelPreferredDimension);
        graphPanel.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1),
                new BevelBorder(BevelBorder.LOWERED)));
        leftPanel.add(graphPanel, BorderLayout.CENTER);
    }

    /**
     * Initializing of right panel with spinner and buttons
     */
    private void initRightPanel() {
        //Buttons dimension
        Dimension buttonMinDimension = new Dimension(250, 45);
        Dimension buttonMaxDimension = new Dimension(350, 45);
        Dimension buttonPreferredDimension = new Dimension(250, 45);


        //Create right panel
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1),
                new BevelBorder(BevelBorder.LOWERED)));


        //Create right sub panel for sise
        JPanel sizeSubPanel = new JPanel();
        BoxLayout sizeSubLayout = new BoxLayout(sizeSubPanel, BoxLayout.X_AXIS);
        sizeSubPanel.setLayout(sizeSubLayout);

        //Create label
        sizeLabel = new JLabel("Размер доски: ");
        sizeLabel.setAlignmentX(JLabel.CENTER);
        sizeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));


        //Create spinner
        sizeSpinner = new JSpinner(new SpinnerNumberModel(4, 4, 64, 1));
        sizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                startButton.setEnabled(false);
                toStartCondition();
                graphPanel.updateUI();
            }
        });


        //Create generate button
        generateButton = new JButton("Сгенерировать перебор");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                graphPanel.searchSolutions();

            }
        });


        //Add to sub panel
        sizeSubPanel.add(sizeLabel);
        sizeSubPanel.add(sizeSpinner);
        sizeSubPanel.add(generateButton);
        sizeSubPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Create sub panel for buttons
        JPanel buttonsSubPanel = new JPanel();
        BoxLayout buttonsSubLayout = new BoxLayout(buttonsSubPanel, BoxLayout.Y_AXIS);
        buttonsSubPanel.setLayout(buttonsSubLayout);

        //Create buttons
        startButton = new JButton("Автоматический перебор");
        startButton.setEnabled(false);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setMinimumSize(buttonMinDimension);
        startButton.setMaximumSize(buttonMaxDimension);
        startButton.setPreferredSize(buttonPreferredDimension);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toStartCondition();
                if (isManual) {
                    graphPanel.drawCombination(index++);
                } else {
                    sizeSpinner.setEnabled(false);
                    startButton.setEnabled(false);

                    graphPanel.drawCombinations();

                    graphPanel.drawCombination(0);
                }
                factor = 1;
            }
        });

        nextButton = new JButton("Следующая расстановка");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.setEnabled(false);
        nextButton.setMinimumSize(buttonMinDimension);
        nextButton.setMaximumSize(buttonMaxDimension);
        nextButton.setPreferredSize(buttonPreferredDimension);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (factor == -1) index += 2;
                System.out.println(index);
                graphPanel.drawCombination(index++);
                int arrSize;
                if (isManual)
                    arrSize = graphPanel.getStepsArray().size();
                else {
                    arrSize = graphPanel.getCombinationsArray().size();
                }
                if (index > arrSize - 1) {
                    nextButton.setEnabled(false);
                }


                preButton.setEnabled(true);

                factor = 1;
            }
        });

        preButton = new JButton("Предыдущая расстановка");
        preButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        preButton.setEnabled(false);
        preButton.setMinimumSize(buttonMinDimension);
        preButton.setMaximumSize(buttonMaxDimension);
        preButton.setPreferredSize(buttonPreferredDimension);
        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (factor == 1) index -= 2;
                System.out.println(index);
                graphPanel.drawCombination(index--);

                nextButton.setEnabled(true);
                if (index < 0) {
                    preButton.setEnabled(false);
                }
                factor = -1;
            }
        });

        //Add to sub panel
        buttonsSubPanel.add(startButton);
        buttonsSubPanel.add(Box.createVerticalStrut(5));
        buttonsSubPanel.add(nextButton);
        buttonsSubPanel.add(Box.createVerticalStrut(5));
        buttonsSubPanel.add(preButton);

        //Create button group panel
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        ButtonGroup modeGroup = new ButtonGroup();
        autoMode = new JRadioButton("Автоматический перебор");
        autoMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factor = 1;
                index = 0;
                graphPanel.setMode("auto");
                startButton.setText("Начать автоматический перебор");
                nextButton.setEnabled(false);
                preButton.setEnabled(false);
                nextButton.setText("Следующая расстановка");
                preButton.setText("Предыдущая расстановка");
                isManual = false;
                toStartCondition();
            }
        });
        manualMode = new JRadioButton("Ручной перебор");
        manualMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factor = 1;
                index = 0;
                graphPanel.setMode("manual");
                startButton.setText("Начать ручной перебор");
                nextButton.setEnabled(false);
                preButton.setEnabled(false);
                nextButton.setText("Следующий шаг");
                preButton.setText("Предыдущий шаг");
                isManual = true;
                toStartCondition();
            }
        });
        modeGroup.add(autoMode);
        modeGroup.add(manualMode);
        autoMode.setSelected(true);
        modePanel.add(autoMode);
        modePanel.add(manualMode);
        rightPanel.add(sizeSubPanel, BorderLayout.NORTH);
        rightPanel.add(modePanel, BorderLayout.CENTER);
        rightPanel.add(buttonsSubPanel, BorderLayout.SOUTH);

    }


    /**
     * Method for setting buttons behavior in runtime
     */
    private void toStartCondition() {
        Desk desk = new Desk((int) sizeSpinner.getValue(), graphPanel.getWidth());
        graphPanel.setDesk(desk);
        factor = 1;
        index = 0;
        nextButton.setEnabled(false);
        preButton.setEnabled(false);
        graphPanel.updateUI();
    }


    //NESTED CLASS MENU LISTENER
    private class MenuBarListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            Object obj = e.getSource();
            JMenu temp = (JMenu) obj;
            if (temp == null) return;
            String menuName = temp.getText();
            if (menuName.equals("Сохранить")) {
                saveAction();
            } else if (menuName.equals("Открыть")) {
                openAction();

            } else if (menuName.equals("Информация")) {
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

    //Will be use for implementing menu behavior. Will be implemented later
    private void saveAction() {
    }

    private void openAction() {
    }


    private void infoAction() {
    }


    //GETTERS AND SETTERS BELOW

    /**
     * Returns desk size
     *
     * @return value from size spinner
     */
    public int getSpinnerValue() {
        return (int) sizeSpinner.getValue();
    }

    /**
     * Provides next button to callback from outer class
     *
     * @return next button
     */
    public JButton getStartButton() {
        return startButton;
    }

    public JSpinner getSizeSpinner() {
        return sizeSpinner;
    }

    public JButton getNextButton() {
        return nextButton;
    }
}

