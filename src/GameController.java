import java.util.ArrayList;
import javax.swing.JPanel;

public class GameController {
    private User user = null;
    private UserList users;
    private HighscoreChart highscores;
    private GameData gameData;
    private QuestionGenerator questionGenerator;
    private MathGame currentGame;
    private static GameController gameControllerInstance = null;
    private ArrayList<ArrayList<Integer>> questionList;
    private ArrayList<Integer> nextQuestion = null;
    private JPanel previousPanel;
    private JPanel currentPanel;
   
  

    public static GameController getInstance() {
        if (null == gameControllerInstance) {
            gameControllerInstance = new GameController();
        }

        return gameControllerInstance;
    }

    private GameController() {

    }

    public User getUser() {
        return this.user;
    }

    public int getCurrentPoints() {
        return gameData.getPoints();
    }

    public GameData getGameData() {
        return this.gameData;
    }

    public void setCurrentPanel(JPanel panel) {
        this.currentPanel = panel;
    }

    // Initializes Mathgame class
    public void intializeGame(MathGame currentGame) {
        this.currentGame = currentGame;
        this.users = new UserList();
        this.highscores = new HighscoreChart();
        intializeUserList();
    }

    /*
     * Initializes UserList. This method is done purely for demonstration purposes
     * when testing the game by teachers and would not be included in the real game
     * version.
     */
    public void intializeUserList() {
        User user1 = new User("KARI");
        User user2 = new User("LAURA");
        User user3 = new User("LIISA");
        this.users.addUser(user1);
        this.users.addUser(user2);
        this.users.addUser(user3);

    }

    // Shows StartPanel
    public void showNewGameWindow() {
        JPanel newGamePanel = new StartPanel();
        previousPanel = currentPanel;
        currentPanel = newGamePanel;
        currentGame.changePanel(newGamePanel);
    }

    // Controls switching to choosePlayer view
    public void chooseUser() {
        if (users.getSize() < 1) {
            showAddUserPanel();
        } else {
            JPanel choosePlayerPanel = new ChoosePlayerPanel(users);
            previousPanel = currentPanel;
            currentPanel = choosePlayerPanel;
            currentGame.changePanel(choosePlayerPanel);
        }

    }

    // Controls switching to addNewPlayer view
    public void showAddUserPanel() {
        JPanel addNewPlayer = new AddNewPlayerPanel();
        previousPanel = currentPanel;
        currentPanel = addNewPlayer;
        currentGame.changePanel(addNewPlayer);
    }

    /*
     * Checks if user exists and only creates a new user if cannot find an existing
     * user with the same name.
     */
    public void addNewUser(String username) {
        boolean userExists = users.checkIfUserExists(username);

        if (!userExists) {
            this.user = new User(username);
            users.addUser(user);
        } else {
            selectExistingUser(username);
        }
    }

    // Selects existing user and switches to ChooseOperation view
    public void selectExistingUser(String username) {
        this.user = users.getExistingUser(username);
    }

    // Shows ChooseOperationPanel
    public void showChooseOperationPanel() {
        JPanel chooseOperationPanel = new ChooseOperationPanel();
        previousPanel = currentPanel;
        currentPanel = chooseOperationPanel;
        currentGame.changePanel(chooseOperationPanel);
    }

    // Shows ChooseDifficultyPanel
    public void showChooseDifficultyPanel() {
        JPanel chooseDifficultyPanel = new ChooseDifficultyPanel();
        previousPanel = currentPanel;
        currentPanel = chooseDifficultyPanel;
        currentGame.changePanel(chooseDifficultyPanel);
    }

    // Starts the Hard gametype for Addition-mode with number range of 0-10
    public void startHardGame() {
        this.questionGenerator = new QuestionGenerator();
        questionGenerator.createAdditionQuestions(10);
        questionList = questionGenerator.giveQuestionList();
        this.gameData = new GameData(questionList, user, "+", 10, 3);
        showGamePanel();
    }

    // Shows GamePanel
    public void showGamePanel() {
        JPanel gamePanel = new GamePanel();
        previousPanel = currentPanel;
        currentPanel = gamePanel;
        currentGame.changePanel(gamePanel);
    }

    // Gets question number from GameData-class
    public int getQuestionCalculator() {
        return gameData.getQuestionCalculator();
    }

    // Retrieves next question from QuestionGenerator and returns it to GamePanel
    public ArrayList askQuestion() {
        nextQuestion = gameData.getNextQuestion();
        return nextQuestion;

    }

    /*
     * Asnwers are checked for correctness in this method. Also points are
     * calculated if answer is correct. Points are awarded also for remaining time,
     * which this method also gets as a parameter from GamePanel, with the answer
     * from the user. If answer is wrong, the question is added on a list to be
     * asked again later.
     */
    public boolean checkQuestion(int answer, int second) {
        String string1 = nextQuestion.get(0).toString();
        String string2 = nextQuestion.get(1).toString();
        int number1 = Integer.parseInt(string1);
        int number2 = Integer.parseInt(string2);

        if (number1 + number2 == answer) {
            int correctAnswerPoints = 200;
            int remainingTime = second;
            int timePoints = remainingTime * 30;
            int pointTotal = timePoints + correctAnswerPoints + gameData.getPoints();
            gameData.setPoints(pointTotal);
            return true;
        } else {
            gameData.addQuestionToAskAgainList(nextQuestion);
            return false;
        }
    }

    /*
     * Retrieves the points when the game ends and checks if they are enough for
     * highscores or stars. Shows suitable Panel based on this information.
     */
    public void showGameEndPanel() {
        gameData.countGameResults(highscores);
        user.saveNewPoints(gameData.getOperation(), gameData.getPoints(), gameData.getMaximum(), gameData.getLevel());
        boolean isNewStarAchieved = gameData.getNewStarAchieved();
        boolean isNewHighScore = gameData.getNewHighScore();
        int points = gameData.getPoints();
        JPanel gameEndedPanel = new GameEndedPanel(isNewStarAchieved, isNewHighScore, points);
        previousPanel = currentPanel;
        currentPanel = gameEndedPanel;
        currentGame.changePanel(gameEndedPanel);
    }

    // Shows ResultPanel
    public void showResultPanel() {
        JPanel resultPanel = new ResultPanel(user, highscores);
        previousPanel = currentPanel;
        currentPanel = resultPanel;
        currentGame.changePanel(resultPanel);
    }

    // Shows previous Panel when exiting from ResultPanel
    public void showPreviousPanel() {
        currentPanel.setVisible(false);
        currentPanel = previousPanel;
        currentGame.changePanel(previousPanel);
    }

}