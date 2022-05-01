import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

    private JButton checkButton = null;
    private ImageIcon coinPicture = null;
    private JLabel coinImageLabel = null;
    private JLabel pointsLabel = null;
    //private GameController gameController;


    public GamePanel(){
        //this.gameController = gameController;
        //this.gameController = gameController.getInstance();

     //   this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());
        JPanel middlePane = new JPanel();
        JPanel southPane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setBackground(new Color(237, 243, 249));
        middlePane.setLayout(new BorderLayout());
        this.add(middlePane, BorderLayout.CENTER);
      //  southPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPane.setBackground(new Color(50, 34, 151));
        southPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        coinPicture = new ImageIcon("src/images/coin_small.png");
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(new Color(50, 34, 151));
        //Tähän pointLabeliin pitää nyt sitten saada tuotua peliscore...
        //Gamedata.getPOints?
        pointsLabel = new JLabel("test");
        pointsLabel.setBackground(new Color(255, 255, 255));
        pointsLabel.setPreferredSize(new Dimension(50, 10));
        
        coinImageLabel = new JLabel();
        coinImageLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        coinImageLabel.setIcon(coinPicture);
        
        pointsPanel.add(coinImageLabel);
        pointsPanel.add(pointsLabel);

        
        this.add(southPane, BorderLayout.SOUTH);

        
       
   //     GridBagConstraints cConstraints = new GridBagConstraints();
    //    cConstraints.insets = new Insets(20, 20, 20, 20);
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
        
        
        southPane.add(pointsPanel);
       // southPane.add(pointsLabel);
        
       
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
                    //ja jos oli oikein, niin pitää päivittää pisteet!
                           
              }
       

            } 
        };

        checkButton.addActionListener(buttonListener);
    }  
    
}

