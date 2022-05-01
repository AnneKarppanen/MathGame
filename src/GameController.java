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
        int i = 1;
        while (i < 16) {
            askQuestion();
        }
        showGameEndPanel();
    }

    public void askQuestion() {
        //generate question
        //check answer
        //show is answer was correct
        //add points
        //Timer!
    }

    public void showGameEndPanel() {
        //Hakee loppupisteet ja tarkistaa tähtien ja ennätysten tilanteen 
        //ja piirtää niiden pohjalta oikeanlaisen paneelin.

    }

    public void showNewGameWindow(){
        JPanel newGamePanel = new StartPanel();
        currentGame.changePanel(newGamePanel);
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