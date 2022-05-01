import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddNewPlayerPanel extends JPanel{

    private JTextField textField = null;
    private JButton okButton = null;
    private BackButton backButton = null;

    public AddNewPlayerPanel() {

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
        JLabel instruction = new JLabel("UUDEN PELAAJAN NIMI");
        instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        cConstraints.fill = GridBagConstraints.NONE;
        cConstraints.weightx = 0.5;
        middlePane.add(instruction, cConstraints);

        /*ArrayList<User> usersAsArrayList = userList.getUserList();
        int userArraySize = usersAsArrayList.size();
        System.out.println("ArraySize: " + userArraySize);
        String[] userArray = new String[userArraySize + 20];
        int i = 0;

        for (User user: usersAsArrayList) {
            userArray[i] = user.getUsername();
            i++;
        }*/

        this.textField = new JTextField("NIMI", 12);
        //textField.setSize();
        textField.setFont(new Font("Arial", Font.PLAIN, 25));
        cConstraints.gridx = 1;
        cConstraints.gridy = 2;
        cConstraints.fill = GridBagConstraints.NONE;
        //cConstraints.weightx = 0;
        middlePane.add(textField, cConstraints);

        this.okButton = new JButton("OK");
        this.okButton.setBackground(new Color(255, 164, 58));
        this.okButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 1;
        cConstraints.gridy = 3;
        //cConstraints.weightx = 0;
        // constraints.weighty = 20;
        // constraints.gridheight = 6;
        // constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 40; // internal padding x
        cConstraints.ipady = 20; // internal padding y
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
                if(source == textField || source == okButton){
                    String text = textField.getText();
                    GameController gameController = GameController.getInstance();
                    gameController.addNewUser(text);
                    gameController.showChooseOperationPanel();           
                    
                }else if(source == backButton){
                    GameController.getInstance().chooseUser();
                }

            }
        };

        textField.addActionListener(buttonListener);
        okButton.addActionListener(buttonListener);
        backButton.addActionListener(buttonListener);
    }
    
}
