import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChoosePlayerPanel extends JPanel {

    private JLabel instruction = null;
    private JButton okButton = null;
    private JButton createPlayerButton = null;
    private JComboBox<String> comboBox = null;
    private UserList userList = null;
    private JButton backButton = null;

    /*
     * Creates and shows a view where the user can select player name 
     * from the dropdown menu or create a new player
     */
    public ChoosePlayerPanel(UserList users) {
        this.userList = users;
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

        this.instruction = new JLabel("VALITSE PELAAJA");
        this.instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        cConstraints.fill = GridBagConstraints.NONE;
        cConstraints.weightx = 0.5;
        middlePane.add(instruction, cConstraints);

        ArrayList<User> usersAsArrayList = userList.getUserList();
        int userArraySize = usersAsArrayList.size();
        System.out.println("ArraySize: " + userArraySize);
        String[] userArray = new String[userArraySize + 20];
        int i = 0;

        for (User user : usersAsArrayList) {
            userArray[i] = user.getUsername();
            i++;
        }

        this.comboBox = new JComboBox<>(userArray);
        this.comboBox.setFont(new Font("Arial", Font.BOLD, 20));
        this.comboBox.setMaximumRowCount(5);
        User currentUser = GameController.getInstance().getUser();
        if (currentUser != null) {
            comboBox.setSelectedItem(currentUser.getUsername());
        } else {
            comboBox.setSelectedIndex(0);
        }

        cConstraints.gridx = 1;
        cConstraints.gridy = 1;
        cConstraints.fill = GridBagConstraints.NONE;
        middlePane.add(comboBox, cConstraints);

        JPanel filler3 = new JPanel();
        cConstraints.gridx = 1;
        cConstraints.gridy = 2;
        middlePane.add(filler3, cConstraints);

        JPanel filler4 = new JPanel();
        cConstraints.gridx = 1;
        cConstraints.gridy = 3;
        middlePane.add(filler4, cConstraints);

        this.createPlayerButton = new JButton("UUSI PELAAJA");
        this.createPlayerButton.setBackground(new Color(109, 177, 240));
        this.createPlayerButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 1;
        cConstraints.gridy = 4;
        cConstraints.ipadx = 60;
        cConstraints.ipady = 25;
        middlePane.add(createPlayerButton, cConstraints);

        this.okButton = new JButton("OK");
        this.okButton.setBackground(new Color(255, 164, 58));
        this.okButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 1;
        cConstraints.gridy = 5;
        cConstraints.ipadx = 60;
        cConstraints.ipady = 25;
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

                if (source == comboBox || source == okButton) {
                    String username = String.valueOf(comboBox.getSelectedItem());
                    GameController.getInstance().selectExistingUser(username);
                    GameController.getInstance().showChooseOperationPanel();

                } else if (source == createPlayerButton) {
                    GameController.getInstance().showAddUserPanel();
                } else if (source == backButton) {
                    GameController.getInstance().showNewGameWindow();
                }
            }
        };

        okButton.addActionListener(buttonListener);
        backButton.addActionListener(buttonListener);
        createPlayerButton.addActionListener(buttonListener);
        comboBox.addActionListener(buttonListener);
    }
}
