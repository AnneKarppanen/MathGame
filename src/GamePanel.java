import javax.swing.*;
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
    private JPanel subTimeout = null;
    private JPanel helperPanel = null;
    private JPanel thumbsPanel = null;

    private JLabel coinImageLabel = null;
    private JLabel timeImageLabel = null;
    private JLabel questionNumberLabel = null;
    private JLabel thumbsLabel = null;
    private JLabel question = null;
    private JLabel timeCounterLabel = null;
    private JLabel pointsLabel = null;
    private JLabel helperLabel = null;

    private JButton checkButton = null;
    private JButton continueButton = null;
    private JButton continueButtonTimeout = null;
    private JButton helperButton = null;

    private ImageIcon coinPicture = null;
    private ImageIcon timePicture = null;
    private ImageIcon timePictureBig = null;
    private ImageIcon thumbsDownPicture = null;
    private ImageIcon thumbsUpPicture = null;

    private Font questionFont = new Font("Arial", Font.BOLD, 50);
    private Font pointsAndTimeFont = new Font("Arial", Font.BOLD, 20);
    private Font buttonFont = new Font("Arial", Font.BOLD, 30);
    private Color pointsAndTimeFontColor = new Color(255, 255, 255);
    private Color buttonBackgroud = new Color(255, 164, 58);
    private Color mainBackground = new Color(237, 243, 249);

    private GameController gameController;
    private JTextField answerField = null;
    private ArrayList<Integer> questions = null;
    private int questionCalculator = 1;
    private GridBagConstraints cConstraints;
    private Timer timer;
    private int second = 8;
    private int minute = 0;
    private String formattedSecond, formattedMinute;
    private DecimalFormat counterFormat = new DecimalFormat("00");

    // Takes care of the different views during the started game
    public GamePanel() {

        this.gameController = GameController.getInstance();
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
        createGameWindow();

        setUpButtonListeners();
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());

        countdownTimer();
        timer.start();

    }

    // Creates the main game window by building all the Panels to be used
    public void createGameWindow() {

        pointsPanel.add(coinImageLabel);
        pointsPanel.add(helperLabel);
        pointsPanel.add(pointsLabel);

        timePanel.add(timeImageLabel);
        timePanel.add(timeCounterLabel);

        southArea.add(checkButton);
        southArea.add(continueButton);
        southArea.add(helperPanel);
        helperPanel.add(helperButton);
        southArea.add(helperPanel);

        quizPanel.add(question);
        quizPanel.add(answerField);
        quizPanel.add(helperLabel);

        middlePane.add(northArea, BorderLayout.NORTH);
        middlePane.add(southArea, BorderLayout.SOUTH);
        middlePane.add(mainQuizPanel, BorderLayout.CENTER);
        southPane.add(pointsPanel, BorderLayout.EAST);
        southPane.add(timePanel, BorderLayout.WEST);

    }

    // Creates countdown timer for the game questions 
    public void countdownTimer() {

        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                second--;
                formattedSecond = counterFormat.format(second);
                formattedMinute = counterFormat.format(minute);
                timeCounterLabel.setText(formattedMinute + ":" + formattedSecond);
                if (second == 0) {
                    timerIsZero();
                }
            }
        });
    }

    /*
     * If time runs out, this method is called to hide some elements and to add a
     * new panelthat informs the user that time has run out
     */
    public void timerIsZero() {

        timer.stop();

        helperButton.setVisible(true);
        checkButton.setVisible(false);
        answerField.getCaret().setVisible(false);

        mainTimeout = new JPanel(new GridBagLayout());
        mainTimeout.setBackground(mainBackground);

        subTimeout = new JPanel();
        subTimeout.setLayout(new BoxLayout(subTimeout, BoxLayout.Y_AXIS));
        subTimeout.setBackground(Color.WHITE);
        subTimeout.setPreferredSize(new Dimension(600, 500));
        subTimeout.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        mainTimeout.add(subTimeout);
        middlePane.add(mainTimeout, BorderLayout.CENTER);

        JPanel timeoutMessagePanel = new JPanel();
        timeoutMessagePanel.setLayout(new GridBagLayout());
        timeoutMessagePanel.setBackground(Color.WHITE);
        timeoutMessagePanel.setPreferredSize(new Dimension(200, 150));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 200));
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);

        JPanel timeoutInfoPicPanel = new JPanel();
        timeoutInfoPicPanel.setLayout(new GridBagLayout());
        timeoutInfoPicPanel.setPreferredSize(new Dimension(200, 150));
        timeoutInfoPicPanel.setBackground(Color.WHITE);

        JPanel timeImagePanel = new JPanel();
        timeImagePanel.setBackground(Color.WHITE);
        timeoutInfoPicPanel.add(timeImagePanel);

        JLabel timeImageLabel2 = new JLabel();
        timeImageLabel2.setIcon(timePictureBig);
        timeImagePanel.add(timeImageLabel2);

        continueButtonTimeout = new JButton("JATKA");
        continueButtonTimeout.setFont((new Font("Arial", Font.BOLD, 30)));
        continueButtonTimeout.setBackground(buttonBackgroud);

        JLabel timeout = new JLabel("AIKA LOPPUI");
        timeout.setFont(new Font("Arial", Font.PLAIN, 60));

        JLabel timeText = new JLabel("00:00");
        timeText.setFont((new Font("Arial", Font.PLAIN, 60)));

        cConstraints.gridx = 1;
        cConstraints.gridy = 0;
        timeoutInfoPicPanel.add(timeImagePanel);
        timeoutInfoPicPanel.add(timeText, cConstraints);
        timeoutMessagePanel.add(timeout);

        subTimeout.add(timeoutMessagePanel);
        subTimeout.add(timeoutInfoPicPanel);
        subTimeout.add(buttonPanel);
        cConstraints.ipadx = 90;
        buttonPanel.add(continueButtonTimeout, cConstraints);
        cConstraints.ipadx = 0;

        // Listener to timeout info's Continue-button
        continueButtonTimeout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (questionCalculator < 3) {

                    mainTimeout.setVisible(false);
                    gameController.checkQuestion(-1, 0);

                    continueToNextQuestion();
                } else {
                    gameController.showGameEndPanel();

                }
            }
        });
    }

    // Sets the main panes to main JPanel
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

    // Instantiates and prepares most of the GamePanel-view's Panels 
    public void setPanels() {

        // Create Points and Time Panels to southBorder
        pointsPanel = new JPanel();
        pointsPanel.setBackground(new Color(50, 34, 151));
        pointsPanel.setPreferredSize(new Dimension(200, 10));
        timePanel = new JPanel();
        timePanel.setBackground(new Color(50, 34, 151));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Create Panel to north area of middlePanel for question Calculator
        northArea = new JPanel();
        northArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        northArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        northArea.setBackground(mainBackground);

        // Create Panel to south area of middlePanel for Check and Continue buttons
        southArea = new JPanel();
        southArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        southArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        southArea.setBackground(mainBackground);

        // Create helperPanel to keep southArea size in tact when hiding buttons
        helperPanel = new JPanel();
        helperPanel.setBackground(mainBackground);

        // Create container Panel for question and answer
        mainQuizPanel = new JPanel(new GridBagLayout());
        mainQuizPanel.setBackground(mainBackground);
        cConstraints = new GridBagConstraints();

        // Create Panel for question and add it to mainQuizPanel
        quizPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quizPanel.setPreferredSize(new Dimension(350, 75));
        quizPanel.setBackground(new Color(156, 204, 249));
        mainQuizPanel.add(quizPanel, cConstraints);

        // Create Panel for thumbs and add it to mainQuizPanel
        thumbsPanel = new JPanel();
        thumbsPanel.setPreferredSize(new Dimension(200, 100));
        thumbsPanel.setBackground(mainBackground);
        cConstraints.insets = new Insets(20, 20, 20, 20);
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        mainQuizPanel.add(thumbsPanel, cConstraints);

    }

    // Instantiates and prepares most of the GamePanel-view's Labels
    public void setLabels() {

        helperLabel = new JLabel();
        helperLabel.setPreferredSize(new Dimension(10, 10));

        questionNumberLabel = new JLabel();

        coinImageLabel = new JLabel();
        coinImageLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        timeImageLabel = new JLabel();

        thumbsLabel = new JLabel();
        thumbsLabel.setLayout(new FlowLayout(FlowLayout.LEFT));

        timeCounterLabel = new JLabel();
        timeCounterLabel.setHorizontalAlignment(JLabel.LEFT);
        timeCounterLabel.setForeground(pointsAndTimeFontColor);
        timeCounterLabel.setFont(pointsAndTimeFont);

        pointsLabel = new JLabel();
        pointsLabel.setHorizontalAlignment(JLabel.RIGHT);
        pointsLabel.setForeground(pointsAndTimeFontColor);
        pointsLabel.setFont(pointsAndTimeFont);

        coinImageLabel.setIcon(coinPicture);
        timeImageLabel.setIcon(timePicture);

    }

    // Sets the paths to GamePanel's images
    public void setPictures() {
        coinPicture = new ImageIcon("src/images/coin_small.png");
        timePicture = new ImageIcon("src/images/time_small.png");
        timePictureBig = new ImageIcon("src/images/time.png");
        thumbsDownPicture = new ImageIcon("src/images/thumbsDown.png");
        thumbsUpPicture = new ImageIcon("src/images/thumbsUp.png");
    }

    // Instantiates and prepares most of the GamePanel's buttons
    public void setButtons() {
        checkButton = new JButton("TARKISTA");
        checkButton.setBackground(buttonBackgroud);
        checkButton.setFont(buttonFont);
        checkButton.setHorizontalAlignment(SwingConstants.CENTER);

        continueButton = new JButton("   JATKA   ");
        continueButton.setBackground(buttonBackgroud);
        continueButton.setFont(buttonFont);
        continueButton.setHorizontalAlignment(SwingConstants.CENTER);
        continueButton.setVisible(false);

        helperButton = new JButton();
        helperButton.setBorder(BorderFactory.createEmptyBorder());
        helperButton.setBackground(mainBackground);
        helperButton.setFont(buttonFont);
        helperButton.setText(" ");
        helperButton.setVisible(false);
    }

    // Instantiates answerField for users answers
    public void setFields() {
        answerField = new JTextField(2);
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        answerField.setFont(questionFont);
        answerField.setBackground(Color.WHITE);
    }

    // Adds question number to northArea of the gameview
    public void gameQuestionCalculator() {
        questionNumberLabel.setText("");
        questionNumberLabel.setText(questionCalculator + " / 15");
        questionNumberLabel.setFont((new Font("Arial", Font.BOLD, 30)));
        northArea.add(questionNumberLabel);
    }

    // Tests if the parameter is int, returns true if it is, false if not
    public boolean parseInt(String testable) {
        try {
            Integer.parseInt(testable);
            return true;

        } catch (Exception e) {
            return false;

        }
    }

    // Asks the questions from the user
    public void setQuestion() {
        question = new JLabel(" " + questions.get(0).toString() + " " + "+ " + questions.get(1).toString() + " =  ");
        question.setFont(questionFont);
    }

    /*
     * After checking the correctness of the answer, sets everything up for the next
     * question and gets a new question from the GameController
     */
    public void continueToNextQuestion() {
        second = 8;
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
        setQuestion();
        gameQuestionCalculator();
        quizPanel.add(question);
        quizPanel.add(answerField);
        quizPanel.add(helperLabel);
        EventQueue.invokeLater(() -> answerField.requestFocusInWindow());
    }

    // Prepares thumbs to be shown on thumbsLabel
    public void setThumbs() {
        thumbsPanel.add(thumbsLabel);
        thumbsLabel.setVisible(true);
    }

    // Checks the correctness of the user's answer
    public void checkAnswer() {
        boolean correctFormat = parseInt(answerField.getText());
        if (correctFormat) {
            int answer = Integer.parseInt(answerField.getText());
            boolean answerCorrect = gameController.checkQuestion(answer, second);
            if (answerCorrect) {
                correctAnswer();
            } else {
                incorrectAnswer();
            }
        } else {
            incorrectAnswer();
        }
    }

    // Gets points status from GameData
    public void getPoints() {
        int points = gameController.getGameData().getPoints();
        pointsLabel.setText(Integer.toString(points));
    }

    // If user's answer is correct, this shows Thumbsup and updates points
    public void correctAnswer() {
        answerField.setEditable(false);
        answerField.setBackground(new Color(156, 204, 249));
        answerField.setBorder(null);
        thumbsLabel.setIcon(thumbsUpPicture);
        setThumbs();
        thumbsLabel.setText(" OIKEIN");
        thumbsLabel.setFont(pointsAndTimeFont);
        thumbsLabel.setForeground(new Color(15, 170, 40));
        getPoints();
        checkButton.setVisible(false);
        continueButton.setVisible(true);
    }

    // If user's answer is incorrect, this shows ThumbsDown
    public void incorrectAnswer() {
        answerField.setEditable(false);
        answerField.setBackground(new Color(156, 204, 249));
        answerField.setBorder(null);
        thumbsLabel.setIcon(thumbsDownPicture);
        setThumbs();
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
                answerField.getCaret().setVisible(false);
                checkAnswer();
            }
        });

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                answerField.setEditable(false);
                Object source = e.getSource();

                if (source == checkButton) {
                    timer.stop();
                    answerField.getCaret().setVisible(false);
                    checkAnswer();

                } else if (source == continueButton) {
                    if (questionCalculator < 15) {
                        second = 8;
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