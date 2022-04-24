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
    private GameController gameController = null;

    public ChoosePlayerPanel(UserList users, GameController gameController ) {
        this.userList = users;
        this.gameController = gameController;
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
        //cConstraints.gridwidth = GridBagConstraints.REMAINDER; 
        // constraints.fill = GridBagConstraints.BOTH;
        
        JPanel filler = new JPanel();
        filler.setBackground(Color.RED);
        cConstraints.fill = GridBagConstraints.BOTH;
        //cConstraints.weightx = 0.25;
        //cConstraints.gridx = 0;
        //cConstraints.gridy = 0;
        //cConstraints.weighty = 1;
        this.add(filler, cConstraints);
        
        this.instruction = new JLabel("VALITSE PELAAJA");
        this.instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        cConstraints.fill = GridBagConstraints.NONE;
        //cConstraints.weightx = 0.5;
        this.add(instruction, cConstraints);
       

        JPanel filler2 = new JPanel();
        filler2.setBackground(Color.BLUE);
        cConstraints.gridx = 2;
        cConstraints.gridy = 0;
        cConstraints.fill = GridBagConstraints.BOTH;
        
        //cConstraints.weightx = 0;
        this.add(filler2, cConstraints);

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
        this.comboBox.setMaximumRowCount(5);
        cConstraints.gridx = 1;
        cConstraints.gridy = 1;
        cConstraints.fill = GridBagConstraints.NONE;
        //cConstraints.weightx = 0;
        this.add(comboBox, cConstraints);

        JPanel filler3 = new JPanel();
        filler3.setBackground(Color.ORANGE);
        cConstraints.gridx = 1;
        cConstraints.gridy = 2;
        //cConstraints.weightx = 0;
        this.add(filler3, cConstraints);

        JPanel filler4 = new JPanel();
        filler4.setBackground(Color.GREEN);
        cConstraints.gridx = 1;
        cConstraints.gridy = 3;
        //cConstraints.weighty = 0;
        this.add(filler4, cConstraints);
        
        this.createPlayerButton = new JButton("UUSI PELAAJA");
        this.createPlayerButton.setBackground(new Color(109, 177, 240));
        this.createPlayerButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 1;
        cConstraints.gridy = 4;
        //cConstraints.weightx = 0;
        // constraints.weightx = 1;
        // constraints.weighty = 20;
        // constraints.gridheight = 6;
        // constraints.anchor = GridBagConstraints.SOUTH;
        //cConstraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        this.add(createPlayerButton, cConstraints);

        this.okButton = new JButton("OK");
        this.okButton.setBackground(new Color(255, 164, 58));
        this.okButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 1;
        cConstraints.gridy = 5;
        //cConstraints.weightx = 0;
        // constraints.weighty = 20;
        // constraints.gridheight = 6;
        // constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        this.add(okButton, cConstraints);

        ImageIcon backArrow = new ImageIcon("src/images/backArrow_small.png");
        //System.out.println("width: " + backArrow.getIconWidth());
        //System.out.println("height: " + backArrow.getIconHeight());
        //backArrow.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        JLabel arrowImage = new JLabel(backArrow);
        arrowImage.setIcon(backArrow);
        JLabel backText = new JLabel("TAKAISIN");
        backText.setFont(new Font("Arial", Font.BOLD, 25));
        backText.setForeground(new Color(31, 29, 114));
        JPanel backButton = new JPanel();
        backButton.setBackground(new Color(237, 243, 249));
        backButton.setLayout(new FlowLayout());
        backButton.add(arrowImage);
        backButton.add(backText);
        cConstraints.gridx = 0;
        cConstraints.gridy = 6;
        //cConstraints.weightx = 0;
        //backButton.setPreferredSize(new Dimension(50, 50));
        //backButton.setMaximumSize(new Dimension(50,50));
        this.add(backButton, cConstraints);
       
        


    }

    public void intializeUserList() {
        User user1 = new User("KARI");
        User user2 = new User("LAURA");
        User user3 = new User("LIISA");
        this.userList.addUser(user1);
        this.userList.addUser(user2);
        this.userList.addUser(user3);

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == okButton) {
                    System.out.println("Klikattiin okButtonia");
                    gameController.showChooseOperationPanel();
                    /*
                     * }else if(source == starButton){
                     * System.out.println("Starbutton clicked");
                     * }else if(source == helpButton){
                     * System.out.println("Helpbutton clicked");
                     * }
                     */

                }
            }
        };

        okButton.addActionListener(buttonListener);
        //starButton.addActionListener(buttonListener);
        //helpButton.addActionListener(buttonListener);

    }

}
