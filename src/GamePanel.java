import javax.swing.*;
import javax.swing.text.StyleConstants.FontConstants;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private JPanel southArea = null;
    private JPanel northArea = null;
    private JButton checkButton = null;
    private JButton continueButton = null;
    private ImageIcon coinPicture = null;
    private ImageIcon timePicture = null;
    private JLabel coinImageLabel = null;
    private JLabel answerImageLabel = null;
    private JLabel timeImageLabel = null;
    private JLabel pointsLabel = null;
    private JLabel timeLabel = null;
    private GameController gameController;
    private JTextField answerField = null;
    private ImageIcon thumbsDownPicture = null;
    private ImageIcon thumbsUpPicture = null;
    private JPanel mainQuizPanel = null;
    private ArrayList questions = null;
    private int questionCalculator = 1;
    private JLabel question = null;
    private JPanel quizPanel = null;
    private Font questionFont = new Font("Arial", Font.BOLD, 50);
    private JLabel questionNumberLabel = null;
    private JLabel thumbsLabel = null;
    private GridBagConstraints cConstraints;
    private JPanel middlePane = null;

    public GamePanel() {

        answerImageLabel = new JLabel();
        questionNumberLabel = new JLabel();
        this.gameController = gameController.getInstance();
        questions = gameController.askQuestion();
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());
        middlePane = new JPanel();
        JPanel southPane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setBackground(new Color(237, 243, 249));
        middlePane.setLayout(new BorderLayout());
        this.add(middlePane, BorderLayout.CENTER);
        southPane.setBackground(new Color(50, 34, 151));
        southPane.setLayout(new BorderLayout());
        // southPane2.setLayout(new FlowLayout(FlowLayout.LEFT));
        // southPane2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        coinPicture = new ImageIcon("src/images/coin_small.png");
        timePicture = new ImageIcon("src/images/time_small.png");
        thumbsDownPicture = new ImageIcon("src/images/thumbsDown.png");
        thumbsUpPicture = new ImageIcon("src/images/thumbsUp.png");
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
        thumbsLabel = new JLabel();
        thumbsLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        coinImageLabel.setIcon(coinPicture);
        timeImageLabel.setIcon(timePicture);

        pointsPanel.add(coinImageLabel);
        pointsPanel.add(pointsTitle);
        timePanel.add(timeImageLabel);
        timePanel.add(timeTitle);
        // southPane2.setOpaque(false);
        // southPane2.add(timePanel);

        this.add(southPane, BorderLayout.SOUTH);
        // this.add(southPane2, BorderLayout.SOUTH);

        // Create buttons Check and Continue
        checkButton = new JButton("TARKISTA");
        checkButton.setBackground(new Color(255, 164, 58));
        checkButton.setFont((new Font("Arial", Font.BOLD, 20)));
        checkButton.setHorizontalAlignment(SwingConstants.CENTER);

        continueButton = new JButton("JATKA");
        continueButton.setBackground(new Color(255, 164, 58));
        continueButton.setFont((new Font("Arial", Font.BOLD, 20)));
        continueButton.setHorizontalAlignment(SwingConstants.CENTER);

        // Create Panel to the south of middlePanel for buttons
        southArea = new JPanel();
        southArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        southArea.add(checkButton);
        southArea.add(continueButton);
        continueButton.setVisible(false);

        // Create Panel to the north of middlePanel for question Calculator
        northArea = new JPanel();
        northArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        northArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        gameQuestionCalculator();

        mainQuizPanel = new JPanel(new GridBagLayout());
        cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        quizPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        question = new JLabel(questions.get(0).toString() + " " + "+ " + questions.get(1).toString());
        question.setFont((new Font("Arial", Font.BOLD, 50)));
        mainQuizPanel.add(quizPanel, cConstraints);
        quizPanel.add(question);

        // Set up JTextField answerField
        answerField = new JTextField(2);
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setFont(questionFont);
        answerField.setForeground(Color.BLACK);
        answerField.setBackground(Color.WHITE);
        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        mainQuizPanel.add(answerField, cConstraints);

        middlePane.add(northArea, BorderLayout.NORTH);
        middlePane.add(southArea, BorderLayout.SOUTH);

        middlePane.add(mainQuizPanel, BorderLayout.CENTER);
        southPane.add(pointsPanel, BorderLayout.EAST);
        southPane.add(timePanel, BorderLayout.WEST);

        setUpButtonListeners();
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());

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

    public void continueToNextQuestion() {
        questionCalculator = gameController.getQuestionCalculator();
        thumbsLabel.setVisible(false);
        answerField.setEditable(true);
        answerField.setBackground(Color.WHITE);
        answerField.setText("");
        continueButton.setVisible(false);
        checkButton.setVisible(true);

        questions = gameController.askQuestion();

        quizPanel.remove(question);
        question = new JLabel(questions.get(0).toString() + " " + "+ " + questions.get(1).toString() + " =");
        question.setFont(questionFont);
        gameQuestionCalculator();
        quizPanel.add(question);
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());

    }


    public void setThumbsConstraints() {
        cConstraints.gridx = 0;
        cConstraints.gridy = 3;
        cConstraints.gridwidth = 2;
        mainQuizPanel.add(thumbsLabel, cConstraints);
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