import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChooseOperationPanel extends JPanel {
    private JButton additionButton = null;
    private JButton subtractionButton = null;
    private JButton multiplicationButton = null;
    private JButton backButton = null;

    /*
     * Shows a new view, where the user can select which game type to play.
     * IMPORTANT NOTE: It is agreed with the teachers that only Addition-gametype
     * needs to be implemented.
     */
    public ChooseOperationPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(new Color(237, 243, 249));
        middlePanel.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);

        // Button for Addition
        additionButton = new JButton("YHTEENLASKU +");
        additionButton.setBackground(new Color(255, 164, 58));
        additionButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        cConstraints.ipadx = 100;
        cConstraints.ipady = 25;
        additionButton.setHorizontalAlignment(SwingConstants.LEFT);
        middlePanel.add(additionButton, cConstraints);

        // Button for subraction
        subtractionButton = new JButton("VÄHENNYSLASKU -");
        subtractionButton.setBackground(new Color(255, 164, 58));
        subtractionButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 2;
        cConstraints.ipadx = 70;
        cConstraints.ipady = 25;
        subtractionButton.setHorizontalAlignment(SwingConstants.LEFT);
        middlePanel.add(subtractionButton, cConstraints);

        // Button for multiplication
        multiplicationButton = new JButton("KERTOLASKU *");
        multiplicationButton.setBackground(new Color(255, 164, 58));
        multiplicationButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 3;
        cConstraints.ipadx = 110;
        cConstraints.ipady = 25;
        multiplicationButton.setHorizontalAlignment(SwingConstants.LEFT);
        middlePanel.add(multiplicationButton, cConstraints);

        this.add(middlePanel, BorderLayout.CENTER);

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
                if (source == additionButton) {
                    GameController.getInstance().showChooseDifficultyPanel();
                } else if (source == subtractionButton) {
                    /*
                     * IMPORTANT!! This subtraction functionality has been agreed
                     * with teachers to be optional, so no implementation.
                     * This will take user to the start window now.
                     */
                    GameController.getInstance().showNewGameWindow();
                } else if (source == multiplicationButton) {
                    /*
                     * IMPORTANT!! This multiplication functionality has been agreed
                     * with teachers to be optional, so no implementation.
                     * This will take user to the start window now.
                     */
                    GameController.getInstance().showNewGameWindow();
                } else if (source == backButton) {
                    GameController.getInstance().chooseUser();
                }

            }
        };

        additionButton.addActionListener(buttonListener);
        subtractionButton.addActionListener(buttonListener);
        multiplicationButton.addActionListener(buttonListener);
        backButton.addActionListener(buttonListener);
    }

}
