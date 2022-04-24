import javax.swing.JPanel;

public class GameController {

    private User user;
    private Game gameData;
    private QuestionGenerator questionGenerator;
    private MathGame currentGame;

    public void intializeGame() {
        this.currentGame = new MathGame(this);
    }

    public void chooseUser() {
        UserList users = new UserList();
        JPanel choosePlayerPanel = new ChoosePlayerPanel(users, this);
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
        JPanel newGamePanel = new StartPanel(this);
        currentGame.changePanel(newGamePanel);
    }

    public void showChooseOperationPanel(){
        JPanel chooseOperationPanel = new ChooseOperationPanel(this);
        currentGame.changePanel(chooseOperationPanel);
    }

    public void showChooseDifficultyPanel(){
        JPanel chooseDifficultyPanel = new ChooseDifficultyPanel(this);
        currentGame.changePanel(chooseDifficultyPanel);
    }

    public void showGamePanel(){
        JPanel gamePanel = new GamePanel(this);
        currentGame.changePanel(gamePanel);
    }

}