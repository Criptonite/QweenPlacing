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
        //setMinimumSize(new Dimension(800, 450));
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
        Dimension leftPanelMinDimension = new Dimension(250, getHeight());
        Dimension leftPanelMaxDimension = new Dimension(450, getHeight());
        Dimension leftPanelPreferredDimension = new Dimension(350, getHeight());
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


//        //Create standart dimensions
//        //Right panel dimensoin
//        Dimension rightPanelMinDimension = new Dimension(250, getHeight());
//        Dimension rightPanelMaxDimension = new Dimension(450, getHeight());
//        Dimension rightPanelPreferredDimension = new Dimension(350, getHeight());
//
//        //Spinner dimension
//        Dimension spinnerMinDimension = new Dimension(25, 25);
//        Dimension spinnerMaxDimension = new Dimension(100, 25);
//        Dimension spinnerPreferredDimension = new Dimension(50, 25);
//
//        //Buttons dimension
//        Dimension buttonMinDimension = new Dimension(250, 45);
//        Dimension buttonMaxDimension = new Dimension(350, 45);
//        Dimension buttonPreferredDimension = new Dimension(250, 45);


        //Create right panel
        rightPanel = new JPanel();
//        rightPanel.setMinimumSize(rightPanelMinDimension);
//        rightPanel.setMaximumSize(rightPanelMaxDimension);
//        rightPanel.setPreferredSize(rightPanelPreferredDimension);
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
//        sizeSpinner.setMinimumSize(spinnerMinDimension);
//        sizeSpinner.setMaximumSize(spinnerMaxDimension);
//        sizeSpinner.setPreferredSize(spinnerPreferredDimension);
        sizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                toStartCondition();
                graphPanel.updateUI();
            }
        });

        //Add to sub panel
        sizeSubPanel.add(sizeLabel);
        sizeSubPanel.add(sizeSpinner);
//        sizeSubPanel.setMinimumSize(new Dimension(225, 50));
//        sizeSubPanel.setMaximumSize(new Dimension(300, 50));
//        sizeSubPanel.setPreferredSize(new Dimension(250, 50));
        sizeSubPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //sizeSubPanel.setAlignmentX(50);

        //Create sub panel for buttons
        JPanel buttonsSubPanel = new JPanel();
        BoxLayout buttonsSubLayout = new BoxLayout(buttonsSubPanel, BoxLayout.Y_AXIS);
        buttonsSubPanel.setLayout(buttonsSubLayout);

        //Create buttons
        startButton = new JButton("Запустить перебор");
//        startButton.setMinimumSize(buttonMinDimension);
//        startButton.setMaximumSize(buttonMaxDimension);
//        startButton.setPreferredSize(buttonPreferredDimension);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toStartCondition();
                if (isManual) {
                    graphPanel.searchSolutions("manual");
                } else {
                    graphPanel.searchSolutions("auto");
                }
                graphPanel.updateUI();
            }
        });

        nextButton = new JButton("Следующая расстановка");
        nextButton.setEnabled(false);
//        nextButton.setMinimumSize(buttonMinDimension);
//        nextButton.setMaximumSize(buttonMaxDimension);
//        nextButton.setPreferredSize(buttonPreferredDimension);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (factor == -1) index += 2;
                System.out.println(index);
                graphPanel.drawCombination(index++);
                int arrSize;
                if (isManual)
                    arrSize = graphPanel.getStepsArray().size();
                else{
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
        preButton.setEnabled(false);
//        preButton.setMinimumSize(buttonMinDimension);
//        preButton.setMaximumSize(buttonMaxDimension);
//        preButton.setPreferredSize(buttonPreferredDimension);
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
//        buttonsSubPanel.setMinimumSize(new Dimension(155, 250));
//        buttonsSubPanel.setMaximumSize(new Dimension(250, 250));
//        buttonsSubPanel.setPreferredSize(new Dimension(155, 250));
//        buttonsSubPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//        buttonsSubPanel.setAlignmentX(100);


        //Create button group panel
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
//        modePanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
//                new BevelBorder(BevelBorder.LOWERED)));
        //modePanel.setAlignmentX(100);
        ButtonGroup modeGroup = new ButtonGroup();
        autoMode = new JRadioButton("Автоматический перебор");
        autoMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setText("Запустить перебор");
                nextButton.setText("Следующая расстановка");
                preButton.setText("Предыдущая расстановка");
                isManual = false;
            }
        });
        manualMode = new JRadioButton("Ручной перебор");
        manualMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setText("Сгенерировать шаги алгоритма");
                nextButton.setText("Следующий шаг");
                preButton.setText("Предыдущий шаг");
                isManual = true;
            }
        });
        modeGroup.add(autoMode);
        modeGroup.add(manualMode);
        autoMode.setSelected(true);
        modePanel.add(autoMode);
        modePanel.add(manualMode);
//        rightPanel.add(Box.createVerticalStrut(10));
//        rightPanel.add(sizeSubPanel);
//        rightPanel.add(Box.createVerticalStrut(25));
//        rightPanel.add(modePanel);
//        rightPanel.add(Box.createVerticalStrut(55));
//        rightPanel.add(buttonsSubPanel);
        rightPanel.add(sizeSubPanel, BorderLayout.NORTH);
        rightPanel.add(modePanel, BorderLayout.CENTER);
        rightPanel.add(buttonsSubPanel, BorderLayout.SOUTH);

    }


    /**
     * Method for setting buttons behavior in runtime
     */
    private void toStartCondition() {
        Desk desk = new Desk((int) sizeSpinner.getValue(), graphPanel, (Graphics2D) graphPanel.getGraphics());
        graphPanel.setDesk(desk);
        factor = 1;
        index = 0;
        nextButton.setEnabled(false);
        preButton.setEnabled(false);
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
    public JButton getNextButton() {
        return nextButton;
    }
}
