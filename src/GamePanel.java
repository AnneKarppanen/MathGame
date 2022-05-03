import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

    private JButton checkButton = null;
    private ImageIcon coinPicture = null;
    private ImageIcon timePicture = null;
    private JLabel coinImageLabel = null;
    private JLabel timeImageLabel = null;
    private JLabel pointsLabel = null;
    private JLabel timeLabel = null;
    private GameController gameController;


    public GamePanel(){
        //this.gameController = gameController;
        this.gameController = gameController.getInstance();
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());
        JPanel middlePane = new JPanel();
        JPanel southPane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setBackground(new Color(237, 243, 249));
        middlePane.setLayout(new BorderLayout());
        this.add(middlePane, BorderLayout.CENTER);
        southPane.setBackground(new Color(50, 34, 151));
     southPane.setLayout(new BorderLayout());
     //   southPane2.setLayout(new FlowLayout(FlowLayout.LEFT));
     //   southPane2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        coinPicture = new ImageIcon("src/images/coin_small.png");
        timePicture = new ImageIcon("src/images/time_small.png");
        JPanel pointsPanel = new JPanel();
        JPanel timePanel = new JPanel();
        pointsPanel.setBackground(new Color(50, 34, 151));
        timePanel.setBackground(new Color(50, 34, 151));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      
        
      
  
        JLabel timeTitle = new JLabel("00:12", JLabel.CENTER);
        JLabel pointsTitle = new JLabel("5000", JLabel.CENTER);
        timeTitle.setForeground(new Color(255, 255, 255));
        timeTitle.setFont(new Font("Arial", Font.BOLD, 15));
        pointsTitle.setForeground(new Color(255, 255, 255));
        pointsTitle.setFont(new Font("Arial", Font.BOLD, 15));

        
      //  JPanel emptyPanel = new JPanel();
      //  emptyPanel.setPreferredSize(new Dimension(1010, 10));
      //  emptyPanel.setBackground(new Color(50, 34, 151));
      //  emptyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //Tähän pointLabeliin pitää nyt sitten saada tuotua peliscore...
        //Gamedata.getPOints?
       // pointsLabel = new JLabel("test");
      //  pointsLabel.setBackground(new Color(255, 255, 255));
      //  pointsLabel.setPreferredSize(new Dimension(50, 10));
        
        coinImageLabel = new JLabel();
        timeImageLabel = new JLabel();
        coinImageLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        coinImageLabel.setIcon(coinPicture);
        timeImageLabel.setIcon(timePicture);
        
        pointsPanel.add(coinImageLabel);
        pointsPanel.add(pointsTitle);
        timePanel.add(timeImageLabel);
        timePanel.add(timeTitle);
     //   southPane2.setOpaque(false);
    //    southPane2.add(timePanel);

        
        this.add(southPane, BorderLayout.SOUTH);
     //   this.add(southPane2, BorderLayout.SOUTH);

    
 
//textfield, on add playerissä...
        checkButton = new JButton("TARKISTA");
        checkButton.setBackground(new Color(255, 164, 58));
        checkButton.setFont((new Font("Arial", Font.BOLD, 20)));
        checkButton.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel southArea = new JPanel();
        southArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
      
southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        southArea.add(checkButton);
     //   southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
     
JPanel quizPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
JLabel question = new JLabel("5 + 5 = ");
quizPanel.add(question);


        middlePane.add(southArea,BorderLayout.SOUTH);
        middlePane.add(quizPanel, BorderLayout.CENTER);
        
        
        southPane.add(pointsPanel, BorderLayout.EAST);
        southPane.add(timePanel, BorderLayout.WEST);

        

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

