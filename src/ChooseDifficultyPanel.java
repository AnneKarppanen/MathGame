import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class ChooseDifficultyPanel extends JPanel {
    private JButton playButton = null;
    private JButton backButton = null;
    private JRadioButton jRadioLukuAlue_10;
    private JRadioButton jRadioLukualue_20;
    private JRadioButton jRadioLukualue_100;
    private JRadioButton jRadioHelppo;
    private JRadioButton jRadioKeskivaikea;
    private JRadioButton jRadioVaikea;
    private int lukualue = 1;
    private int vaikeustaso = 1;

    ButtonGroup groupLukualue;
    ButtonGroup groupVaikeustaso;
    // private GameController gameController;

    public ChooseDifficultyPanel() {
        // this.gameController = gameController;
        // this.gameController = gameController.getInstance();

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());
        JPanel middlePane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setLayout(new GridBagLayout());
        middlePane.setBackground(new Color(237, 243, 249));
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;

        JPanel radioButtonMainPanel = new JPanel(new FlowLayout(0, 20, 20));
        radioButtonMainPanel.setBackground(new Color(237, 243, 249));
        JPanel radioButtonSubPanel1 = new JPanel();
        JPanel radioButtonSubPanel2 = new JPanel();
        radioButtonSubPanel1.setPreferredSize(new Dimension(140, 120));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        radioButtonSubPanel1.setBorder(blackline);
        radioButtonSubPanel2.setBorder(blackline);

        radioButtonSubPanel2.setPreferredSize(new Dimension(140, 120));
        radioButtonSubPanel1.setLayout(new BoxLayout(radioButtonSubPanel1, BoxLayout.PAGE_AXIS));
        radioButtonSubPanel2.setLayout(new BoxLayout(radioButtonSubPanel2, BoxLayout.PAGE_AXIS));

        jRadioLukuAlue_10 = new JRadioButton("0-10");
        jRadioLukualue_20 = new JRadioButton("0-20");
        jRadioLukualue_100 = new JRadioButton("0-100");
        jRadioHelppo = new JRadioButton("Helppo");
        jRadioKeskivaikea = new JRadioButton("Keskivaikea");
        jRadioVaikea = new JRadioButton("Vaikea");
        jRadioLukuAlue_10.setFont((new Font("Arial", Font.BOLD, 14)));
        jRadioLukuAlue_10.setPreferredSize(new Dimension(50, 50));
        jRadioLukualue_20.setFont((new Font("Arial", Font.BOLD, 14)));
        jRadioLukualue_20.setPreferredSize(new Dimension(50, 50));
        jRadioLukualue_100.setFont((new Font("Arial", Font.BOLD, 14)));
        jRadioLukualue_100.setPreferredSize(new Dimension(50, 50));
        jRadioHelppo.setFont((new Font("Arial", Font.BOLD, 14)));
        jRadioHelppo.setPreferredSize(new Dimension(50, 50));
        jRadioKeskivaikea.setFont((new Font("Arial", Font.BOLD, 14)));
        jRadioKeskivaikea.setPreferredSize(new Dimension(50, 50));
        jRadioVaikea.setFont((new Font("Arial", Font.BOLD, 14)));
        jRadioVaikea.setPreferredSize(new Dimension(50, 50));
        JLabel lukualueText = new JLabel("LUKUALUE:");
        lukualueText.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel pelinVaikeusText = new JLabel("PELIN VAIKEUS:");
        pelinVaikeusText.setFont(new Font("Arial", Font.BOLD, 16));

        groupLukualue = new ButtonGroup();
        groupVaikeustaso = new ButtonGroup();

        groupLukualue.add(jRadioLukuAlue_10);
        groupLukualue.add(jRadioLukualue_20);
        groupLukualue.add(jRadioLukualue_100);
        groupVaikeustaso.add(jRadioHelppo);
        groupVaikeustaso.add(jRadioKeskivaikea);
        groupVaikeustaso.add(jRadioVaikea);

        radioButtonSubPanel1.add(Box.createVerticalStrut(5));
        radioButtonSubPanel1.add(Box.createHorizontalStrut(15));
        radioButtonSubPanel1.add(lukualueText);
        radioButtonSubPanel1.add(Box.createVerticalStrut(15));
        radioButtonSubPanel2.add(Box.createVerticalStrut(5));
        radioButtonSubPanel2.add(Box.createHorizontalStrut(15));
        radioButtonSubPanel2.add(pelinVaikeusText);
        radioButtonSubPanel2.add(Box.createVerticalStrut(15));

        radioButtonSubPanel1.add(jRadioLukuAlue_10);
        radioButtonSubPanel1.add(jRadioLukualue_20);
        radioButtonSubPanel1.add(jRadioLukualue_100);
        radioButtonSubPanel2.add(jRadioHelppo);
        radioButtonSubPanel2.add(jRadioKeskivaikea);
        radioButtonSubPanel2.add(jRadioVaikea);
        middlePane.add(radioButtonMainPanel);
        radioButtonMainPanel.add(radioButtonSubPanel1);
        radioButtonMainPanel.add(radioButtonSubPanel2);
        jRadioLukuAlue_10.setSelected(true);
        jRadioHelppo.setSelected(true);

        playButton = new JButton("PELAA");
        playButton.setBackground(new Color(255, 164, 58));
        playButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y

        middlePane.add(playButton, cConstraints);
        this.add(middlePane, BorderLayout.CENTER);

        this.backButton = new BackButton();
        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(237, 243, 249));
        southPanel.add(backButton);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        this.add(southPanel, BorderLayout.SOUTH);

        setUpButtonListeners();

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == playButton) {
                    System.out.println("playButton clicked");
                    
                    // tästä lähtee sitten eka peli-ikkuna
                    GameController.getInstance().showGamePanel();
                } else if (source == jRadioLukuAlue_10) {
                    System.out.println("radioButton10 clicked");
                    lukualue = 1;
                }else if(source == jRadioLukualue_20){
                    System.out.println("radioButton20 clicked");
                    lukualue = 2;
                }else if (source == jRadioLukualue_100){
                    System.out.println("radioButton100 clicked");
                    lukualue = 3;
                }else if (source == jRadioHelppo){
                    System.out.println("radioButtonHelppo clicked");
                    vaikeustaso = 1;
                }else if (source == jRadioKeskivaikea){
                    System.out.println("radioButtonKeskivaikea clicked");
                    vaikeustaso = 2;
                } else if (source == jRadioVaikea) {
                    System.out.println("jRadioVaikea clicked");
                    vaikeustaso = 3;
                } else if (source == backButton) {
                    GameController.getInstance().showChooseOperationPanel();
                    System.out.println("Klikattiin backButtonia");
                }
            }
        };

        playButton.addActionListener(buttonListener);
        jRadioLukuAlue_10.addActionListener(buttonListener);
        jRadioLukualue_20.addActionListener(buttonListener);
        jRadioLukualue_100.addActionListener(buttonListener);
        jRadioHelppo.addActionListener(buttonListener);
        jRadioKeskivaikea.addActionListener(buttonListener);
        jRadioVaikea.addActionListener(buttonListener);
        backButton.addActionListener(buttonListener);
    }

}
