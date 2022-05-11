import javax.swing.*;
import javax.swing.text.StyleConstants.FontConstants;

import java.awt.*;
import java.awt.event.*;
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

    private JLabel coinImageLabel = null;
    private JLabel timeImageLabel = null;
    private JLabel questionNumberLabel = null;
    private JLabel thumbsLabel = null;
    private JLabel question = null;
    private JLabel timeTitle = null;
    private JLabel pointsTitle = null;

    private JButton checkButton = null;
    private JButton continueButton = null;

    private ImageIcon coinPicture = null;
    private ImageIcon timePicture = null;
    private ImageIcon thumbsDownPicture = null;
    private ImageIcon thumbsUpPicture = null;

    private Font questionFont = new Font("Arial", Font.BOLD, 50);
    private Font pointsAndTimeFont = new Font("Arial", Font.BOLD, 15);
    private Font buttonFont = new Font("Arial", Font.BOLD, 20);
    private Color pointsAndTimeFontColor = new Color(255, 255, 255);
    private Color buttonBackgroud = new Color(255, 164, 58);
   
    private GameController gameController;
    private JTextField answerField = null;
    private ArrayList questions = null;
    private int questionCalculator = 1;
    private GridBagConstraints cConstraints;
    
    // private JLabel pointsLabel = null;
    // private JLabel timeLabel = null;
    // private JLabel answerImageLabel = null;

    public GamePanel() {

        this.gameController = gameController.getInstance();
        this.setBackground(new Color(237, 243, 249));
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

        pointsPanel.add(coinImageLabel);
        pointsPanel.add(pointsTitle);

        timePanel.add(timeImageLabel);
        timePanel.add(timeTitle);

        southArea.add(checkButton);
        southArea.add(continueButton);
        
        quizPanel.add(question);

        middlePane.add(northArea, BorderLayout.NORTH);
        middlePane.add(southArea, BorderLayout.SOUTH);
        middlePane.add(mainQuizPanel, BorderLayout.CENTER);
        southPane.add(pointsPanel, BorderLayout.EAST);
        southPane.add(timePanel, BorderLayout.WEST);

        

        setUpButtonListeners();
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());
    }

    public void setPanes() {
        middlePane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setBackground(new Color(237, 243, 249));
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

        timePanel = new JPanel();
        timePanel.setBackground(new Color(50, 34, 151));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Create Panel to the north area of middlePanel for question Calculator
        northArea = new JPanel();
        northArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        northArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Create Panel to the south area of middlePanel for Check and Continue buttons
        southArea = new JPanel();
        southArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Create container Panel for question and answer
        mainQuizPanel = new JPanel(new GridBagLayout());
        cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;

        // Create Panel for question and add it to mainQuizPanel
        quizPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainQuizPanel.add(quizPanel, cConstraints);
    }

    public void setLabels() {
        // answerImageLabel = new JLabel();
        questionNumberLabel = new JLabel();

        coinImageLabel = new JLabel();
        coinImageLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        timeImageLabel = new JLabel();

        thumbsLabel = new JLabel();
        thumbsLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        timeTitle = new JLabel("00:12", JLabel.CENTER);
        timeTitle.setForeground(pointsAndTimeFontColor);
        timeTitle.setFont(pointsAndTimeFont);

        pointsTitle = new JLabel("5000", JLabel.CENTER);
        pointsTitle.setForeground(pointsAndTimeFontColor);
        pointsTitle.setFont(pointsAndTimeFont);

        coinImageLabel.setIcon(coinPicture);
        timeImageLabel.setIcon(timePicture);

    }

    public void setPictures() {
        coinPicture = new ImageIcon("src/images/coin_small.png");
        timePicture = new ImageIcon("src/images/time_small.png");
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
        answerField.setFont(questionFont);
        answerField.setForeground(Color.BLACK);
        answerField.setBackground(Color.WHITE);
        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        mainQuizPanel.add(answerField, cConstraints);
    }

    public void gameQuestionCalculator() {
        questionNumberLabel.setText("");
        questionNumberLabel.setText(questionCalculator + " / 15");
        questionNumberLabel.setFont((new Font("Arial", Font.BOLD, 20)));
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
        question = new JLabel(questions.get(0).toString() + " " + "+ " + questions.get(1).toString() + " =");
        question.setFont(questionFont);
    }

    public void continueToNextQuestion() {
        
        thumbsLabel.setVisible(false);
        answerField.setEditable(true);
        answerField.setBackground(Color.WHITE);
        answerField.setText("");
        continueButton.setVisible(false);
        checkButton.setVisible(true);

        questions = gameController.askQuestion();
        questionCalculator = gameController.getQuestionCalculator();

        quizPanel.remove(question);
        // question = new JLabel(questions.get(0).toString() + " " + "+ " +
        // questions.get(1).toString() + " =");
        // question.setFont(questionFont);
        setQuestion();
        gameQuestionCalculator();
        quizPanel.add(question);
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());

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

    public void correctAnswer() {
        answerField.setEditable(false);
        answerField.setBackground(Color.LIGHT_GRAY);
        thumbsLabel.setIcon(thumbsUpPicture);
        setThumbsConstraints();

        // päivitä pisteet
        checkButton.setVisible(false);
        continueButton.setVisible(true);
    }

    public void incorrectAnswer() {
        answerField.setEditable(false);
        answerField.setBackground(Color.LIGHT_GRAY);

        thumbsLabel.setIcon(thumbsDownPicture);
        setThumbsConstraints();
        // päivitä pisteet
        checkButton.setVisible(false);
        continueButton.setVisible(true);
    }

    public void setUpButtonListeners() {

        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                checkAnswer();
            }
        });

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerField.setEditable(false);
                answerField.setBackground(Color.LIGHT_GRAY);
                Object source = e.getSource();

                if (source == checkButton) {
                    checkAnswer();

                } else if (source == continueButton) {
                    if (questionCalculator < 15) {
                        continueToNextQuestion();
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