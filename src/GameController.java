import javax.swing.JPanel;

public class GameController {

    private User user;
    private Game gameData;
    private QuestionGenerator questionGenerator;
    private MathGame currentGame;

    public void intializeGame() {
        this.currentGame = new MathGame();
        currentGame.setVisible(true);
        //Jää odottamaan napin painallusta. Kun saa tiedon aloita-napin painalluksesta. kutsutaan gameController.chooseUser()
    }

    public void chooseUser() {
        JPanel choosePlayerPanel = new ChoosePlayerPanel();
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

    



}