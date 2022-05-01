import java.util.ArrayList;

public class GameData {
    private User user;
    private int questionNumber;
    private int wrongAnswers;
    private ArrayList<String> questionsToAskAgain;
    private int points;

    public GameData(){
        this.points = 1000;
    }

    public int getPoints() {
        return this.points;
    }

}
