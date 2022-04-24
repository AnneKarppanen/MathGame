import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

    private JButton checkButton = null;
    private GameController gameController;


    public GamePanel(GameController gameController){
        this.gameController = gameController;
        this.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
 
        
        checkButton = new JButton("TARKISTA");
        checkButton.setBackground(new Color(255, 164, 58));
        checkButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y
       // checkButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(checkButton, cConstraints);

        setUpButtonListeners();

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source == checkButton){
                    System.out.println("checkButton clicked");
                           
              }
       

            } 
        };

        checkButton.addActionListener(buttonListener);
    }  
}

