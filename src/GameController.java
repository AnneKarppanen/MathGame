import java.util.ArrayList;

import javax.swing.JPanel;

public class GameController {
    //new try
    private User user = null;
    private UserList users;
    private HighscoreChart highscores; 
    private GameData gameData;
    private QuestionGenerator questionGenerator;
    private MathGame currentGame;
    private static GameController gameControllerInstance = null;
    private ArrayList questionList;
    private ArrayList nextQuestion = null;

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
      //  this.gameData = new GameData(); //Tämä tulee oikeasti siihen missä uusi peli alkaa
    }

    public void chooseUser() {
        if (users.getSize() < 1) {
            showAddUserPanel();
        } else {
            JPanel choosePlayerPanel = new ChoosePlayerPanel(users);
            //JPanel backButtonPanel = new BackButtonPanel();
            currentGame.changePanel(choosePlayerPanel);
            //currentGame.changeSouthPanel(backButtonPanel);
            //currentGame.pack();
            // Tämän pitää saada tieto valitusta käyttäjästä, että voi lisätä sen attribuutiksi.
            // Luoko tämä myös uuden käyttäjän tarvittaessa? Jos painetaan uusi pelaaja nappia?
        }
        
    }

    public void showAddUserPanel() {
        JPanel addNewPlayer = new AddNewPlayerPanel();
        currentGame.changePanel(addNewPlayer);
    }

    // checks if user exists and only creates a new user if cannot find an existing user with the same name
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

    public int getQuestionCalculator(){
        return gameData.getQuestionCalculator();
    }

    public ArrayList askQuestion() {
        nextQuestion = gameData.getNextQuestion();
        //retrieves next question from questionGenerator
    //    gameData.updateQuestionAmount();
        return nextQuestion;
       
    }

    public boolean checkQuestion(int answer){
         //check answer, comes here only if answers is in ok format.
        System.out.println(answer);
        String string1 = nextQuestion.get(0).toString();
        String string2 = nextQuestion.get(1).toString();
        int number1 = Integer.parseInt(string1);
        int number2 = Integer.parseInt(string2);

        if(number1 + number2 == answer){
            int newPoints = 2000;
            int pointTotal = newPoints + gameData.getPoints();
            gameData.setPoints(pointTotal);
            System.out.println(number1 + " + " + number2 + " = " + answer);

            return true;
        }else{        
            System.out.println("Meni väärin!");
        return false;
        }
    }


    public void showGameEndPanel() {
        gameData.countGameResults(highscores);
        user.saveNewPoints(gameData.getOperation(), gameData.getPoints(), gameData.getMaximum(), gameData.getLevel());
        JPanel gameEndedPanel = new GameEndedPanel(gameData.getNewStarAchieved(), gameData.getNewHighScore(), gameData.getPoints());
        currentGame.changePanel(gameEndedPanel);
        //Hakee loppupisteet ja tarkistaa tähtien ja ennätysten tilanteen 
        //ja piirtää niiden pohjalta oikeanlaisen paneelin.

    }

    public void showNewGameWindow(){
        JPanel newGamePanel = new StartPanel();
        currentGame.changePanel(newGamePanel);
        //startHardGame();
    }

    public void showChooseOperationPanel(){
        
        System.out.println("Pelaaja on " + this.user.getUsername());
        JPanel chooseOperationPanel = new ChooseOperationPanel();
        currentGame.changePanel(chooseOperationPanel);
    }

    public void showChooseDifficultyPanel(){
        JPanel chooseDifficultyPanel = new ChooseDifficultyPanel();
        currentGame.changePanel(chooseDifficultyPanel);
    }

    public void showGamePanel(){
        JPanel gamePanel = new GamePanel();
        currentGame.changePanel(gamePanel);
      //  startHardGame();
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

    

}