import java.util.ArrayList;

import javax.swing.JPanel;

public class GameController {

    private User user = null;
    private UserList users;
    private GameData gameData;
    private QuestionGenerator questionGenerator;
    private MathGame currentGame;
    private static GameController gameControllerInstance = null;

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
        intializeUserList();
        this.gameData = new GameData(); //Tämä tulee oikeasti siihen missä uusi peli alkaa
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
        this.gameData = new GameData();
        this.questionGenerator = new QuestionGenerator();
     //   int i = 1;
    /*    while (i < 16) {
            askQuestion(questionGenerator);
        }
        showGameEndPanel(); */
    }

    public ArrayList askQuestion() {
        ArrayList nextQuestion = null; // gameData.getNextQuestion();
        //retrieves next question from questionGenerator
        return nextQuestion;
       
    }

    public boolean checkQuestion(String answer){
         //check answer

        // if format wrong aka cannot be converted to int or answer is incorrect 
        //No points
        //if answer incorrect return false
        //false triggers the gamepanel to add ThumbsDown and change to "Jatka" button
        
        //if answer is correct
        //check timer, add points accordingly
        //return true
       // true triggers the gamepanel to add ThumbsUp and change to "Jatka" button
       //also points must be updated to gamepanel-view

        return true;
    }

    public void showGameEndPanel() {
        JPanel gameEndedPanel = new GameEndedPanel();
        currentGame.changePanel(gameEndedPanel);
        //Hakee loppupisteet ja tarkistaa tähtien ja ennätysten tilanteen 
        //ja piirtää niiden pohjalta oikeanlaisen paneelin.

    }

    public void showNewGameWindow(){
        JPanel newGamePanel = new StartPanel();
        currentGame.changePanel(newGamePanel);
        startHardGame();
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
        startHardGame();
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

    

}