import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private JButton checkButton = null;
    private ImageIcon coinPicture = null;
    private ImageIcon timePicture = null;
    private JLabel coinImageLabel = null;
    private JLabel answerImageLabel = null;
    private JLabel timeImageLabel = null;
    private JLabel pointsLabel = null;
    private JLabel timeLabel = null;
    private GameController gameController;
    private JTextField answerField;
    private ImageIcon thumbsDownPicture = null;
    private JPanel mainQuizPanel = null;
    private ArrayList question = null;


    public GamePanel(){

        answerImageLabel = new JLabel();
        //this.gameController = gameController;
        this.gameController = gameController.getInstance();
        question = gameController.askQuestion();
        System.out.println(question);
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
        thumbsDownPicture = new ImageIcon("src/images/thumbs down.png");
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
     mainQuizPanel = new JPanel(new GridBagLayout());
     GridBagConstraints cConstraints = new GridBagConstraints();
     cConstraints.insets = new Insets(20, 20, 20, 20);
     cConstraints.gridx = 0;
     cConstraints.gridy = 0;
JPanel quizPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
JLabel question = new JLabel("5 + 5 = ");
question.setFont((new Font("Arial", Font.BOLD, 50)));
mainQuizPanel.add(quizPanel, cConstraints);
quizPanel.add(question);

answerField = new JTextField(2);


answerField.setHorizontalAlignment(JTextField.CENTER);

answerField.setFont(new java.awt.Font("Arial", Font.BOLD, 50));
answerField.setForeground(Color.BLACK);
answerField.setBackground(Color.WHITE);
cConstraints.gridx = 1;
cConstraints.gridy = 0;
mainQuizPanel.add(answerField, cConstraints);
//answerField.requestFocus();
//answerField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

answerField.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("The entered answer is: " + answerField.getText());
    }
});

        middlePane.add(southArea,BorderLayout.SOUTH);
        middlePane.add(mainQuizPanel, BorderLayout.CENTER);
        
        
        southPane.add(pointsPanel, BorderLayout.EAST);
        southPane.add(timePanel, BorderLayout.WEST);

        

        setUpButtonListeners();
      //  answerField.setVisible(true);
      //  answerField.requestFocus();
     //   answerField.grabFocus();
     //   answerField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
     EventQueue.invokeLater( () -> answerField.requestFocusInWindow() );
     

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source == checkButton){
                    System.out.println("The entered answer is: " + answerField.getText());
                   

              }
       

            } 
        };

        checkButton.addActionListener(buttonListener);
    }  

    public void checkAnswer(String text){
        try {
            int answer = Integer.parseInt(text);


          }
          catch(Exception e) {
          //  mainQuizPanel.add();
            
          }
    }

   
    
}

