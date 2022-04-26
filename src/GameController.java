import javax.swing.JPanel;

public class GameController {

    private User user;
    private Game gameData;
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
    }

    public void chooseUser() {
        UserList users = new UserList();
        JPanel choosePlayerPanel = new ChoosePlayerPanel(users);
        currentGame.changePanel(choosePlayerPanel);
        // Tämän pitää saada tieto valitusta käyttäjästä, että voi lisätä sen attribuutiksi.
        // Luoko tämä myös uuden käyttäjän tarvittaessa? Jos painetaan uusi pelaaja nappia?
    }

    public void chooseGameType() {

    }

    public void startHardGame() {
        this.gameData = new Game();
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

}