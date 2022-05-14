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
    private ArrayList questionList;
    private ArrayList nextQuestion = null;
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

    public void intializeGame(MathGame currentGame) {
        this.currentGame = currentGame;
        this.users = new UserList();
        this.highscores = new HighscoreChart();
        intializeUserList();
    }

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

    public void showAddUserPanel() {
        JPanel addNewPlayer = new AddNewPlayerPanel();
        previousPanel = currentPanel;
        currentPanel = addNewPlayer;
        currentGame.changePanel(addNewPlayer);
    }

    // checks if user exists and only creates a new user if cannot find an existing
    // user with the same name
    public void addNewUser(String username) {
        boolean userExists = users.checkIfUserExists(username);

        if (!userExists) {
            this.user = new User(username);
            users.addUser(user);
        } else {
            selectExistingUser(username);
        }

    }

    public void selectExistingUser(String username) {

        this.user = users.getExistingUser(username);
    }

    public void chooseGameType() {

    }

    public void startHardGame() {
        this.questionGenerator = new QuestionGenerator();
        questionGenerator.createAdditionQuestions(10);
        questionList = questionGenerator.giveQuestionList();
        this.gameData = new GameData(questionList, user, "+", 10, 3);
        showGamePanel();

    }

    public void showResultPanel() {
        JPanel resultPanel = new ResultPanel(user, highscores);
        previousPanel = currentPanel;
        currentPanel = resultPanel;
        currentGame.changePanel(resultPanel);
    }

    public void showPreviousPanel() {
        currentPanel.setVisible(false);
        currentPanel = previousPanel;
        currentGame.changePanel(previousPanel);
    }

    public int getQuestionCalculator() {
        return gameData.getQuestionCalculator();
    }

    public ArrayList askQuestion() {
        nextQuestion = gameData.getNextQuestion();
        // retrieves next question from questionGenerator
        return nextQuestion;

    }

    public boolean checkQuestion(int answer, int second) {
        // check answer, enters this method only if answers is in ok format.
        String string1 = nextQuestion.get(0).toString();
        String string2 = nextQuestion.get(1).toString();
        int number1 = Integer.parseInt(string1);
        int number2 = Integer.parseInt(string2);

        if (number1 + number2 == answer) {
            int correctAnswerPoints = 1000;
            int remainingTime = second;
            int timePoints = remainingTime * 100;
            int pointTotal = timePoints + correctAnswerPoints + gameData.getPoints();
            gameData.setPoints(pointTotal);

            return true;
        } else {
            gameData.addQuestionToAskAgainList(nextQuestion);
            return false;
        }
    }

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
        // Hakee loppupisteet ja tarkistaa tähtien ja ennätysten tilanteen
        // ja piirtää niiden pohjalta oikeanlaisen paneelin.

    }

    public void showNewGameWindow() {
        JPanel newGamePanel = new StartPanel();
        previousPanel = currentPanel;
        currentPanel = newGamePanel;
        currentGame.changePanel(newGamePanel);
    }

    public void showChooseOperationPanel() {

        JPanel chooseOperationPanel = new ChooseOperationPanel();
        previousPanel = currentPanel;
        currentPanel = chooseOperationPanel;
        currentGame.changePanel(chooseOperationPanel);
    }

    public void showChooseDifficultyPanel() {
        JPanel chooseDifficultyPanel = new ChooseDifficultyPanel();
        previousPanel = currentPanel;
        currentPanel = chooseDifficultyPanel;
        currentGame.changePanel(chooseDifficultyPanel);
    }

    public void showGamePanel() {
        JPanel gamePanel = new GamePanel();
        previousPanel = currentPanel;
        currentPanel = gamePanel;
        currentGame.changePanel(gamePanel);
    }

    public void intializeUserList() {
        User user1 = new User("KARI");
        User user2 = new User("LAURA");
        User user3 = new User("LIISA");
        this.users.addUser(user1);
        this.users.addUser(user2);
        this.users.addUser(user3);

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

}