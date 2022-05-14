import javax.swing.*;
import javax.swing.text.StyleConstants.FontConstants;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private JPanel middlePane = null;
    private JPanel southPane = null;
    private JPanel southArea = null;
    private JPanel northArea = null;   
    private JPanel pointsPanel = null;
    private JPanel timePanel = null;
    private JPanel mainQuizPanel = null;
    private JPanel quizPanel = null;
    private JPanel mainTimeout = null;

    private JLabel coinImageLabel = null;
    private JLabel timeImageLabel = null;
    private JLabel questionNumberLabel = null;
    private JLabel thumbsLabel = null;
    private JLabel question = null;
    private JLabel timeCounter = null;
    private JLabel pointsTitle = null;
    private JLabel helperLabel = null;
    private JLabel correctLabel = null;
    private JLabel incorrectLabel = null;

    private JButton checkButton = null;
    private JButton continueButton = null;
    private JButton continueButtonTimeout = null;

    private ImageIcon coinPicture = null;
    private ImageIcon timePicture = null;
    private ImageIcon timePictureBig = null;
    private ImageIcon thumbsDownPicture = null;
    private ImageIcon thumbsUpPicture = null;

    private Font questionFont = new Font("Arial", Font.BOLD, 50);
    private Font pointsAndTimeFont = new Font("Arial", Font.BOLD, 20);
    private Font buttonFont = new Font("Arial", Font.BOLD, 20);
    private Color pointsAndTimeFontColor = new Color(255, 255, 255);
    private Color buttonBackgroud = new Color(255, 164, 58);
    private Color mainBackground = new Color(237, 243, 249);
   
    private GameController gameController;
    private JTextField answerField = null;
    private ArrayList questions = null;
    private int questionCalculator = 1;
    private GridBagConstraints cConstraints;
    private Timer timer;
    private int second = 3;
    private int minute = 0;
    private String formattedSecond, formattedMinute;
    private DecimalFormat counterFormat = new DecimalFormat("00");
    

    public GamePanel() {

        this.gameController = gameController.getInstance();
        this.setBackground(mainBackground);
        this.setLayout(new BorderLayout());
        questions = gameController.askQuestion();

        setPanes();
        setPanels();
        setPictures();
        setLabels();
        setButtons();
        gameQuestionCalculator();
        setQuestion();
        setFields();
        getPoints();

        pointsPanel.add(coinImageLabel);
        pointsPanel.add(helperLabel);
        pointsPanel.add(pointsTitle);

        timePanel.add(timeImageLabel);
        timePanel.add(timeCounter);

        southArea.add(checkButton);
        southArea.add(continueButton);
        
        quizPanel.add(question);
        quizPanel.add(answerField);
        quizPanel.add(helperLabel);

        middlePane.add(northArea, BorderLayout.NORTH);
        middlePane.add(southArea, BorderLayout.SOUTH);
        middlePane.add(mainQuizPanel, BorderLayout.CENTER);
        southPane.add(pointsPanel, BorderLayout.EAST);
        southPane.add(timePanel, BorderLayout.WEST);

        setUpButtonListeners();
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());   
        
        countdownTimer();
        timer.start();
    }

    public void countdownTimer(){
        this.timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              
                second--;
                formattedSecond = counterFormat.format(second);
                formattedMinute = counterFormat.format(minute);
                
             timeCounter.setText(formattedMinute + ":" + formattedSecond);
             if(second == 0){
                 timerIsZero();
             }
             
            }
        });
    }

    public void timerIsZero(){
        timer.stop();
        JButton helperButton = new JButton();
       JPanel helperPanel = new JPanel();
       southArea.add(helperPanel);

        helperButton.setBorder(BorderFactory.createEmptyBorder());
        helperButton.setBackground(mainBackground);
        helperButton.setFont(buttonFont);
        helperButton.setText(" ");
        helperPanel.add(helperButton);
        southArea.add(helperPanel);
        checkButton.setVisible(false);
   
        answerField.getCaret().setVisible(false);
        mainTimeout = new JPanel(new GridBagLayout());
      mainTimeout.setBackground(mainBackground);

      JPanel subTimeout = new JPanel();
       subTimeout.setLayout(new BoxLayout(subTimeout, BoxLayout.Y_AXIS));
       
        subTimeout.setBackground(Color.WHITE);
        subTimeout.setPreferredSize(new Dimension(600, 500));
        subTimeout.setBorder(BorderFactory.createLineBorder(Color.BLACK));

   
      
     mainTimeout.add(subTimeout);
     middlePane.add(mainTimeout, BorderLayout.CENTER);
   
    JPanel timeoutMessagePanel = new JPanel();
    timeoutMessagePanel.setLayout(new GridBagLayout());
    JPanel buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(200, 200));
    buttonPanel.setLayout(new GridBagLayout());
    buttonPanel.setBackground(Color.WHITE);
    timeoutMessagePanel.setBackground(Color.WHITE);
    timeoutMessagePanel.setPreferredSize(new Dimension(200, 150));
 JPanel timeoutInfoPicPanel = new JPanel();
 timeoutInfoPicPanel.setLayout(new GridBagLayout());
    timeoutInfoPicPanel.setPreferredSize(new Dimension(200, 150));
    timeoutInfoPicPanel.setBackground(Color.WHITE);
    JLabel timeImageLabel2 = new JLabel();
    
timeImageLabel2.setIcon(timePictureBig);
    JButton continueButtonTimeout = new JButton("JATKA");
    continueButtonTimeout.setFont((new Font("Arial", Font.BOLD, 30)));
    continueButtonTimeout.setBackground(buttonBackgroud);
    JLabel timeout = new JLabel("AIKA LOPPUI");
    timeout.setFont(new Font("Arial", Font.PLAIN, 60));
    cConstraints.gridx = 0;
    cConstraints.gridy = 0;
    timeoutInfoPicPanel.add(timeImageLabel2, cConstraints);
    JLabel time = new JLabel("00:00");
    time.setFont((new Font("Arial", Font.PLAIN, 60)));
    cConstraints.gridx = 1;
    cConstraints.gridy = 0;
    timeoutInfoPicPanel.add(time, cConstraints);
     timeoutMessagePanel.add(timeout);
    subTimeout.add(timeoutMessagePanel);
    subTimeout.add(timeoutInfoPicPanel);
    subTimeout.add(buttonPanel);
    cConstraints.ipadx = 90;
    buttonPanel.add(continueButtonTimeout, cConstraints);
    cConstraints.ipadx = 0;
    
  //  JButton helperButton = new JButton();
    //helperButton.setBackground(mainBackground);
    //helperButton.setForeground(mainBackground);
    //southArea.add(helperButton);
    //checkButton.setVisible(false);
  //  southArea.setVisible(false);
    
//     checkButton.setBackground(mainBackground);
//     checkButton.setForeground(mainBackground);
//      checkButton.setBorder(BorderFactory.createLineBorder(mainBackground));



    continueButtonTimeout.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent event) {
        if (questionCalculator < 3) {
  
           mainTimeout.setVisible(false);
           helperButton.setVisible(false);
         
        //  subTimeout.removeAll();
        //   southArea.add(continueButton);
        //   middlePane.add(southArea);
        //   checkButton.setVisible(true);
       
          continueToNextQuestion();
        }else{
          gameController.showGameEndPanel();
           
       }
    }
   });
    }

    public void setPanes() {
        middlePane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setBackground(mainBackground);
        middlePane.setLayout(new BorderLayout());
        this.add(middlePane, BorderLayout.CENTER);

        southPane = new JPanel();
        southPane.setBackground(new Color(50, 34, 151));
        southPane.setLayout(new BorderLayout());
        this.add(southPane, BorderLayout.SOUTH);
    }

    public void setPanels() {

        // Create Points and Time Panels to southBorder
        pointsPanel = new JPanel();
        pointsPanel.setBackground(new Color(50, 34, 151));
        pointsPanel.setPreferredSize(new Dimension(200, 10));


    

        timePanel = new JPanel();
        timePanel.setBackground(new Color(50, 34, 151));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Create Panel to the north area of middlePanel for question Calculator
        northArea = new JPanel();
        northArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        northArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        northArea.setBackground(mainBackground);

        // Create Panel to the south area of middlePanel for Check and Continue buttons
        southArea = new JPanel();
        southArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        southArea.setBackground(mainBackground);
        

        // Create container Panel for question and answer
        mainQuizPanel = new JPanel(new GridBagLayout());
        mainQuizPanel.setBackground(mainBackground);
        cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;

        // Create Panel for question and add it to mainQuizPanel
        quizPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quizPanel.setPreferredSize(new Dimension(350, 75));
        quizPanel.setBackground(new Color(156, 204, 249));
        mainQuizPanel.add(quizPanel, cConstraints);
        System.out.println(gameController.getGameData().getPoints());
    }

    public void setLabels() {
        // answerImageLabel = new JLabel();

        helperLabel = new JLabel();
        helperLabel.setPreferredSize(new Dimension(10, 10));
        questionNumberLabel = new JLabel();

        coinImageLabel = new JLabel();
        coinImageLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        timeImageLabel = new JLabel();

        thumbsLabel = new JLabel();
        thumbsLabel.setLayout(new FlowLayout(FlowLayout.LEFT));

      //  timeCounter = new JLabel("00:12", JLabel.CENTER);
      timeCounter = new JLabel();
      timeCounter.setHorizontalAlignment(JLabel.LEFT);
        timeCounter.setForeground(pointsAndTimeFontColor);
        timeCounter.setFont(pointsAndTimeFont);

        pointsTitle = new JLabel();
        pointsTitle.setHorizontalAlignment(JLabel.RIGHT);
        pointsTitle.setForeground(pointsAndTimeFontColor);
        pointsTitle.setFont(pointsAndTimeFont);

        coinImageLabel.setIcon(coinPicture);
        timeImageLabel.setIcon(timePicture);

   

    }

    public void setPictures() {
        coinPicture = new ImageIcon("src/images/coin_small.png");
        timePicture = new ImageIcon("src/images/time_small.png");
        timePictureBig = new ImageIcon("src/images/time.png");
        thumbsDownPicture = new ImageIcon("src/images/thumbsDown.png");
        thumbsUpPicture = new ImageIcon("src/images/thumbsUp.png");
    }

    public void setButtons() {
        checkButton = new JButton("TARKISTA");
        checkButton.setBackground(buttonBackgroud);
        checkButton.setFont(buttonFont);
        checkButton.setHorizontalAlignment(SwingConstants.CENTER);

        continueButton = new JButton("JATKA");
        continueButton.setBackground(buttonBackgroud);
        continueButton.setFont(buttonFont);
        continueButton.setHorizontalAlignment(SwingConstants.CENTER);
        continueButton.setVisible(false);
    }

    public void setFields() {
        // Set JTextField answerField
        answerField = new JTextField(2);
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        answerField.setFont(questionFont);
    //    answerField.setForeground(Color.BLACK);
        answerField.setBackground(Color.WHITE);
     //   cConstraints.gridx = 1;
     //   cConstraints.gridy = 0;
     //   quizPanel.add(answerField, cConstraints);
    }

    public void gameQuestionCalculator() {
        questionNumberLabel.setText("");
        questionNumberLabel.setText(questionCalculator + " / 15");
        questionNumberLabel.setFont((new Font("Arial", Font.BOLD, 30)));
        northArea.add(questionNumberLabel);
    }

    public boolean parseInt(String testable) {
        try {
            Integer.parseInt(testable);
            return true;

        } catch (Exception e) {
            return false;

        }
    }

    public void setQuestion() {
        question = new JLabel(" " + questions.get(0).toString() + " " + "+ " + questions.get(1).toString() + " =  ");
        question.setFont(questionFont);
    }

    public void continueToNextQuestion() {
        second = 5;
        timer.start();
        thumbsLabel.setVisible(false);
        answerField.setEditable(true);
        answerField.setBackground(Color.WHITE);
        answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        answerField.setText("");
        checkButton.setVisible(true);
        continueButton.setVisible(false);
      

        questions = gameController.askQuestion();
        questionCalculator = gameController.getQuestionCalculator();

        quizPanel.remove(question);
        // question = new JLabel(questions.get(0).toString() + " " + "+ " +
        // questions.get(1).toString() + " =");
        // question.setFont(questionFont);
        
        setQuestion();
        gameQuestionCalculator();
        quizPanel.add(question);
        quizPanel.add(answerField);
        quizPanel.add(helperLabel);
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());

        
       // countdownTimer();
      

    }

    public void setThumbsConstraints() {
        cConstraints.gridx = 0;
        cConstraints.gridy = 3;
        cConstraints.gridwidth = 2;
        mainQuizPanel.add(thumbsLabel, cConstraints);
    // southArea.add(thumbsLabel, cConstraints);
        thumbsLabel.setVisible(true);
    }

    public void checkAnswer() {
        boolean correctFormat = parseInt(answerField.getText());
        if (correctFormat) {
            int answer = Integer.parseInt(answerField.getText());
            boolean answerCorrect = gameController.checkQuestion(answer);

            if (answerCorrect) {
                correctAnswer();
            } else {
                incorrectAnswer();
            }
        } else {
            incorrectAnswer();
        }
    }

    public void getPoints(){
        int points = gameController.getGameData().getPoints();
        pointsTitle.setText(Integer.toString(points));
    }

    public void correctAnswer() {
        answerField.setEditable(false);
        answerField.setBackground(new Color(156, 204, 249));
        answerField.setBorder(null);
        thumbsLabel.setIcon(thumbsUpPicture);
        setThumbsConstraints();
        thumbsLabel.setText(" OIKEIN");
        thumbsLabel.setFont(pointsAndTimeFont);
        thumbsLabel.setForeground(new Color(15, 170, 40));
        getPoints();
        
        checkButton.setVisible(false);
        continueButton.setVisible(true);
    }

    public void incorrectAnswer() {
        answerField.setEditable(false);
        answerField.setBackground(new Color(156, 204, 249));
        answerField.setBorder(null);

        thumbsLabel.setIcon(thumbsDownPicture);
        setThumbsConstraints();
        thumbsLabel.setText(" VÄÄRIN");
        thumbsLabel.setFont(pointsAndTimeFont);
        thumbsLabel.setForeground(new Color(203, 30, 0));
        checkButton.setVisible(false);
        continueButton.setVisible(true);
    }

    

    public void setUpButtonListeners() {

        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                timer.stop();
                checkAnswer();
            }
        });

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                answerField.setEditable(false);
             //   answerField.setBackground(Color.LIGHT_GRAY);
                Object source = e.getSource();

                if (source == checkButton) {
                    timer.stop();
                //    checkButton.setVisible(false);
                //    continueButton.setVisible(true);
                    checkAnswer();

                } else if (source == continueButton) {
                    if (questionCalculator < 3) {
                    //if (questionCalculator < 15) {
                     //   second = 16;
                    //    timer.start();
                        continueToNextQuestion();
               //     } else if (source == continueButtonTimeout) {
                       
                    } else {
                        gameController.showGameEndPanel();
                    }
                }
            }
        };

        checkButton.addActionListener(buttonListener);
        continueButton.addActionListener(buttonListener);
    }

}