import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

    private JButton checkButton = null;
    private JPanel centerPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    private JPanel southPanel;
    //private GameController gameController;


    public GamePanel(){
        //this.gameController = gameController;
        //this.gameController = gameController.getInstance();

     //   this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());
        JPanel middlePane = new JPanel();
        JPanel southPane = new JPanel();
     //   JPanel southPane = new JPanel();
     //   this.southPanel = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       // middlePane.setLayout(new GridBagLayout());
        middlePane.setBackground(new Color(237, 243, 249));
        middlePane.setLayout(new BorderLayout());
        this.add(middlePane, BorderLayout.CENTER);
        southPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPane.setBackground(new Color(50, 34, 151));
        southPane.setLayout(new FlowLayout());
        this.add(southPane, BorderLayout.SOUTH);
       
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
    //    cConstraints.gridx = 0;
    //    cConstraints.gridy = 0;

    
 
//textfield, on add playerissä...
        checkButton = new JButton("TARKISTA");
        checkButton.setBackground(new Color(255, 164, 58));
        checkButton.setFont((new Font("Arial", Font.BOLD, 20)));
     //   checkButton.setPreferredSize(new Dimension(20, 10));
     //   cConstraints.gridx = 0;
     //   cConstraints.gridy = 0;
     //   cConstraints.ipadx = 60; // internal padding x
     //   cConstraints.ipady = 25; // internal padding y
     //   checkButton.add(cConstraints);
        checkButton.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel southArea = new JPanel();
        southArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
      
southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        southArea.add(checkButton);
     //   southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        middlePane.add(southArea,BorderLayout.SOUTH);
        

      //  southPane.add();
        
       
    //   this.add(middlePane, BorderLayout.CENTER);
    //   middlePane.add(southPane, BorderLayout.SOUTH);
    //   southPanel.add(checkButton);

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

