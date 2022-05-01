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
    //private GameController gameController = null;

    public ChoosePlayerPanel(UserList users) {
        this.userList = users;
        //this.gameController = gameController.getInstance();
        //this.gameController = gameController;
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        JPanel middlePane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setLayout(new GridBagLayout());
        middlePane.setBackground(new Color(237, 243, 249));
        //middlePane.setMaximumSize(new Dimension(800, 600));
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
        //cConstraints.fill = GridBagConstraints.VERTICAL;

        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        // constraints.weightx = 1;
        // constraints.weighty = 1;
        // constraints.gridheight = 1;
        //cConstraints.gridwidth = GridBagConstraints.REMAINDER; 
        // constraints.fill = GridBagConstraints.BOTH;
        
        /*JPanel filler = new JPanel();
        filler.setBackground(Color.RED);
        cConstraints.fill = GridBagConstraints.BOTH;
        //cConstraints.weightx = 0.25;
        //cConstraints.gridx = 0;
        //cConstraints.gridy = 0;
        //cConstraints.weighty = 1;
        this.add(filler, cConstraints);*/
        
        this.instruction = new JLabel("VALITSE PELAAJA");
        this.instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        cConstraints.fill = GridBagConstraints.NONE;
        cConstraints.weightx = 0.5;
        middlePane.add(instruction, cConstraints);
       

        /*JPanel filler2 = new JPanel();
        filler2.setBackground(Color.BLUE);
        filler2.setPreferredSize(new Dimension(250, 60));
        cConstraints.gridx = 2;
        cConstraints.gridy = 0;
        cConstraints.weightx = 0;
        cConstraints.fill = GridBagConstraints.BOTH;
        
        //cConstraints.weightx = 0;
        this.add(filler2, cConstraints);*/

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
        User currentUser = GameController.getInstance().getUser();
        if (currentUser != null) {
            comboBox.setSelectedItem(currentUser.getUsername());
        } else {
            comboBox.setSelectedIndex(0);
        }

        cConstraints.gridx = 1;
        cConstraints.gridy = 1;
        cConstraints.fill = GridBagConstraints.NONE;
        //cConstraints.weightx = 0;
        middlePane.add(comboBox, cConstraints);

        JPanel filler3 = new JPanel();
        //filler3.setBackground(Color.ORANGE);
        cConstraints.gridx = 1;
        cConstraints.gridy = 2;
        //cConstraints.weightx = 0;
        middlePane.add(filler3, cConstraints);

        JPanel filler4 = new JPanel();
        //filler4.setBackground(Color.GREEN);
        cConstraints.gridx = 1;
        cConstraints.gridy = 3;
        //cConstraints.weighty = 0;
        middlePane.add(filler4, cConstraints);
        
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
        middlePane.add(createPlayerButton, cConstraints);

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
       

   /* public ChoosePlayerPanel(UserList users) {
        this.userList = users;
        //this.gameController = gameController.getInstance();
        //this.gameController = gameController;
        intializeUserList();
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());
       

        JPanel filler = new JPanel();
        filler.setBackground(Color.RED);
        this.add(filler);
        JPanel middlePane = new JPanel();
        middlePane.setBackground(Color.GREEN);
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        middlePane.setLayout(new BoxLayout(middlePane, BoxLayout.Y_AXIS));
        middlePane.setAlignmentY(CENTER_ALIGNMENT);
        this.instruction = new JLabel("VALITSE PELAAJA");
        instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        instruction.setPreferredSize(new Dimension(50, 20));
        middlePane.add(instruction);
       

        JPanel filler2 = new JPanel();
        filler2.setBackground(Color.BLUE);
        filler2.setPreferredSize(new Dimension(250, 60));
        this.add(filler2);

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
        comboBox.setFont(new Font("Arial", Font.BOLD, 20));
        comboBox.setMaximumRowCount(5);
        comboBox.setPreferredSize(new Dimension(50, 60));
        comboBox.setMaximumSize(new Dimension(50, 60));
        middlePane.add(comboBox);

        JPanel filler3 = new JPanel();
        filler3.setBackground(Color.ORANGE);
        this.add(filler3);

        JPanel filler4 = new JPanel();
        filler4.setBackground(Color.GREEN);
        this.add(filler4);
        
        this.createPlayerButton = new JButton("UUSI PELAAJA");
        createPlayerButton.setBackground(new Color(109, 177, 240));
        createPlayerButton.setFont((new Font("Arial", Font.BOLD, 20)));
        createPlayerButton.setPreferredSize(new Dimension(50, 20));
        middlePane.add(createPlayerButton);

        this.okButton = new JButton("OK");
        okButton.setBackground(new Color(255, 164, 58));
        okButton.setFont((new Font("Arial", Font.BOLD, 20)));
        okButton.setPreferredSize(new Dimension(50, 20));
        middlePane.add(okButton);

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
        System.out.println(backText.getPreferredSize());
        //backButton.setBackground(new Color(237, 243, 249));
        backButton.setLayout(new BoxLayout(backButton, BoxLayout.LINE_AXIS));
        backButton.add(arrowImage);
        backButton.add(backText);
    
      
        //backButton.setPreferredSize(new Dimension(50, 50));
        //backButton.setMaximumSize(new Dimension(50,50));
        this.add(middlePane, BorderLayout.CENTER);
        this.add(backButton, BorderLayout.PAGE_END);
        setUpButtonListeners();
       
        


    }*/

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                    
                if (source == comboBox || source == okButton) {
                    String username = String.valueOf(comboBox.getSelectedItem());
                    System.out.println("Valittiin käyttäjä: " + username); 
                    GameController.getInstance().selectExistingUser(username);
                    GameController.getInstance().showChooseOperationPanel(); 
                                
                } else if (source == createPlayerButton) {
                    GameController.getInstance().showAddUserPanel();
                } else if (source == backButton) {
                    GameController.getInstance().showNewGameWindow();
                    System.out.println("Klikattiin backButtonia");
                }
                    /*
                     * }else if(source == starButton){
                     * System.out.println("Starbutton clicked");
                     * }else if(source == helpButton){
                     * System.out.println("Helpbutton clicked");
                     * }
                     */

                
            }
        };

        okButton.addActionListener(buttonListener);
        backButton.addActionListener(buttonListener);
        createPlayerButton.addActionListener(buttonListener);
        comboBox.addActionListener(buttonListener);
        //helpButton.addActionListener(buttonListener);

    }

}
