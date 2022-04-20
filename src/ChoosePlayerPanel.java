import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChoosePlayerPanel extends JPanel{
    
    private JLabel instruction = null;
    private JButton okButton = null;
    private JButton createPlayerButton = null;
    private JComboBox<String> comboBox = null;
    private UserList userList = null;

    public ChoosePlayerPanel(UserList users) {
        this.userList = users;
        intializeUserList();
        this.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(30, 30, 30, 30);
        // constraints.fill = GridBagConstraints.VERTICAL;

        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        // constraints.weightx = 1;
        // constraints.weighty = 1;
        // constraints.gridheight = 1;
        // constraints.fill = GridBagConstraints.BOTH;
        this.instruction = new JLabel("VALITSE PELAAJA");
        this.instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(instruction, cConstraints);
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;

        ArrayList<User> usersAsArrayList = userList.getUserList();
        int userArraySize = usersAsArrayList.size();
        System.out.println("ArraySize: " + userArraySize);
        String[] userArray = new String[userArraySize + 20];
        int i = 0;

        for (User user: usersAsArrayList) {
            userArray[i] = user.getUsername();
            i++;
        }

        this.comboBox = new JComboBox<>(userArray);
        this.comboBox.setFont(new Font("Arial", Font.BOLD, 20));
        cConstraints.gridx = 0;
        cConstraints.gridy = 2;
        this.add(comboBox, cConstraints);
        
        this.createPlayerButton = new JButton("UUSI PELAAJA");
        this.createPlayerButton.setBackground(new Color(109, 177, 240));
        this.createPlayerButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 3;
        // constraints.weightx = 1;
        // constraints.weighty = 20;
        // constraints.gridheight = 6;
        // constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        this.add(createPlayerButton, cConstraints);

        this.okButton = new JButton("OK");
        this.okButton.setBackground(new Color(255, 164, 58));
        this.okButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 4;
        // constraints.weightx = 1;
        // constraints.weighty = 20;
        // constraints.gridheight = 6;
        // constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        this.add(okButton, cConstraints);

       
    }

    public void intializeUserList() {
        User user1 = new User("KARI");
        User user2 = new User("LAURA");
        User user3 = new User("LIISA");
        this.userList.addUser(user1);
        this.userList.addUser(user2);
        this.userList.addUser(user3);
        
    }


    
}
