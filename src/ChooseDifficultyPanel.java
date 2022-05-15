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
    private Font radioButtonFont = new Font("Arial", Font.BOLD, 18);

    ButtonGroup groupLukualue;
    ButtonGroup groupVaikeustaso;

    /*
     * IMPORTANT NOTE: It has been agreed with the teachers that the it´s enough
     * to implement the techically easiest form of game in the MVP version.
     * Therefore changing difficulty and number range has no effect, the game is the
     * same regardless what is chosen.
     */

    /*
     * Shows a view where the user can choose number range and difficulty level for
     * the game.
     */
    public ChooseDifficultyPanel() {

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

        Border blackline = BorderFactory.createLineBorder(Color.black);
        JPanel radioButtonMainPanel = new JPanel(new FlowLayout(0, 20, 20));
        radioButtonMainPanel.setBackground(new Color(237, 243, 249));

        JPanel radioButtonSubPanel1 = new JPanel();
        radioButtonSubPanel1.setBorder(blackline);
        radioButtonSubPanel1.setPreferredSize(new Dimension(180, 180));
        radioButtonSubPanel1.setLayout(new BoxLayout(radioButtonSubPanel1, BoxLayout.PAGE_AXIS));
        JPanel radioButtonSubPanel2 = new JPanel();
        radioButtonSubPanel2.setBorder(blackline);
        radioButtonSubPanel2.setPreferredSize(new Dimension(180, 180));
        radioButtonSubPanel2.setLayout(new BoxLayout(radioButtonSubPanel2, BoxLayout.PAGE_AXIS));

        jRadioLukuAlue_10 = new JRadioButton("0-10");
        jRadioLukuAlue_10.setFont(radioButtonFont);
        jRadioLukualue_20 = new JRadioButton("0-20");
        jRadioLukualue_20.setFont(radioButtonFont);
        jRadioLukualue_100 = new JRadioButton("0-100");
        jRadioLukualue_100.setFont(radioButtonFont);

        jRadioHelppo = new JRadioButton("Helppo");
        jRadioHelppo.setFont(radioButtonFont);
        jRadioKeskivaikea = new JRadioButton("Keskivaikea");
        jRadioKeskivaikea.setFont(radioButtonFont);
        jRadioVaikea = new JRadioButton("Vaikea");
        jRadioVaikea.setFont(radioButtonFont);

        JLabel lukualueText = new JLabel("LUKUALUE:");
        lukualueText.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel pelinVaikeusText = new JLabel("PELIN VAIKEUS:");
        pelinVaikeusText.setFont(new Font("Arial", Font.BOLD, 20));

        groupLukualue = new ButtonGroup();
        groupLukualue.add(jRadioLukuAlue_10);
        groupLukualue.add(jRadioLukualue_20);
        groupLukualue.add(jRadioLukualue_100);

        groupVaikeustaso = new ButtonGroup();
        groupVaikeustaso.add(jRadioHelppo);
        groupVaikeustaso.add(jRadioKeskivaikea);
        groupVaikeustaso.add(jRadioVaikea);

        radioButtonSubPanel1.add(Box.createVerticalStrut(20));
        radioButtonSubPanel1.add(lukualueText);
        radioButtonSubPanel1.add(Box.createVerticalStrut(10));
        radioButtonSubPanel1.add(jRadioLukuAlue_10);
        radioButtonSubPanel1.add(jRadioLukualue_20);
        radioButtonSubPanel1.add(jRadioLukualue_100);
        radioButtonSubPanel1.add(Box.createHorizontalStrut(10));

        radioButtonSubPanel2.add(Box.createVerticalStrut(20));
        radioButtonSubPanel2.add(pelinVaikeusText);
        radioButtonSubPanel2.add(Box.createVerticalStrut(10));
        radioButtonSubPanel2.add(jRadioHelppo);
        radioButtonSubPanel2.add(jRadioKeskivaikea);
        radioButtonSubPanel2.add(jRadioVaikea);
        radioButtonSubPanel2.add(Box.createHorizontalStrut(10));

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
        cConstraints.ipadx = 60;
        cConstraints.ipady = 25;

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
                    GameController.getInstance().setDifficulty(lukualue, vaikeustaso);
                    GameController.getInstance().startHardGame();
                    /*
                     * The else ifs for "lukualue" and "vaikeustaso" purely written ready for the
                     * next versions. Then the numeric range attribute and difficulty level
                     * would start different games. Agreed with the teachers that can be left out
                     * from MVP version.
                     * 
                     * Not implemented yet, so changing difficulty has no effect on the game.
                     */
                } else if (source == jRadioLukuAlue_10) {
                    lukualue = 10;
                } else if (source == jRadioLukualue_20) {
                    lukualue = 20;
                } else if (source == jRadioLukualue_100) {
                    lukualue = 100;
                } else if (source == jRadioHelppo) {
                    vaikeustaso = 1;
                } else if (source == jRadioKeskivaikea) {
                    vaikeustaso = 2;
                } else if (source == jRadioVaikea) {
                    vaikeustaso = 3;
                } else if (source == backButton) {
                    GameController.getInstance().showChooseOperationPanel();
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
