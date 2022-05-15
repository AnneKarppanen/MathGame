import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddNewPlayerPanel extends JPanel {

    private JTextField textField = null;
    private JButton okButton = null;
    private BackButton backButton = null;

    /* Creates a view where user can add a new playername to the game */
    public AddNewPlayerPanel() {

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

        JLabel instruction = new JLabel("UUDEN PELAAJAN NIMI");
        instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        cConstraints.fill = GridBagConstraints.NONE;
        cConstraints.weightx = 0.5;
        middlePane.add(instruction, cConstraints);

        this.textField = new JTextField("NIMI", 12);
        textField.setFont(new Font("Arial", Font.PLAIN, 25));
        cConstraints.gridx = 1;
        cConstraints.gridy = 2;
        cConstraints.fill = GridBagConstraints.NONE;
        middlePane.add(textField, cConstraints);

        this.okButton = new JButton("OK");
        this.okButton.setBackground(new Color(255, 164, 58));
        this.okButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 1;
        cConstraints.gridy = 3;
        cConstraints.ipadx = 40;
        cConstraints.ipady = 20;
        middlePane.add(okButton, cConstraints);
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
                if (source == textField || source == okButton) {
                    String text = textField.getText();
                    GameController gameController = GameController.getInstance();
                    gameController.addNewUser(text);
                    gameController.showChooseOperationPanel();

                } else if (source == backButton) {
                    GameController.getInstance().chooseUser();
                }

            }
        };

        textField.addActionListener(buttonListener);
        okButton.addActionListener(buttonListener);
        backButton.addActionListener(buttonListener);
    }

}
