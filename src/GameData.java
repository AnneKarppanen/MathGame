import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JSpinner.NumberEditor;

public class GameData {
    private User user;
    private String operation;
    private int level;
    private int maximum;
    private int questionNumber;
    private int newQuestionIndex;
    private int questionCalculator = 0;
    // private int wrongAnswers;
    private ArrayList<ArrayList<Integer>> questionList;
    private ArrayList<ArrayList<Integer>> questionsToAskAgain;
    private int points;
    private ArrayList<Integer> lastQuestionAsked;
    private boolean newStarAchieved;
    private boolean newHighScore;
    private int numberOfStarsAtTheEnd = 0;
    private int rankAtTheEnd;

    public GameData(ArrayList questionList, User user, String operation, int maximum, int level) {
        this.points = 0;
        this.questionNumber = 1;
        this.newQuestionIndex = 0;
        this.questionList = questionList;
        this.level = 1;
        this.maximum = 10;
        this.newStarAchieved = false;
        this.newHighScore = false;
        this.operation = "+";
        this.user = user;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getNewStarAchieved() {
        return newStarAchieved;
    }

    public boolean getNewHighScore() {
        return newHighScore;
    }

    
    public String getOperation() {
        return operation;
    }

    public int getLevel() {
        return level;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getNumberOfStarsAtTheEnd() {
        return numberOfStarsAtTheEnd;
    }

    public int getRankAtTheEnd() {
        return rankAtTheEnd;
    }

    public ArrayList<Integer> getNextQuestion() {
        questionCalculator++;
        if (questionCalculator > 3) {
        //if (questionCalculator > 15) {
            // tästä kutsuttaisiin sitten gamControllerin kautta lopetuspaneelia?
            return null;
        } else {

            ArrayList<Integer> questionToAsk = new ArrayList<>();
            questionsToAskAgain = new ArrayList<>();

            if (questionsToAskAgain.size() > 0) {
                questionToAsk = questionsToAskAgain.get(0);
                if (questionToAsk.equals(lastQuestionAsked) && questionsToAskAgain.size() > 1) {
                    questionToAsk = questionsToAskAgain.get(1);
                }
                if (questionToAsk.equals(lastQuestionAsked) && questionsToAskAgain.size() == 1) {
                    questionToAsk = questionList.get(newQuestionIndex);
                    newQuestionIndex++;
                }
            } else {
                questionToAsk = questionList.get(newQuestionIndex);
                newQuestionIndex++;
            }

            lastQuestionAsked = questionToAsk;
            return questionToAsk;
        }
    }

    public int getQuestionCalculator() {
        return questionCalculator;
    }

    public void countGameResults(HighscoreChart highscores) {

        if (user.getResults(this.operation) != null) {
            HashMap<Integer, HashMap<Integer, Integer>> additionResults = user.getResults(this.operation);
           
            if ( additionResults.get(maximum) != null  && additionResults.get(maximum).get(level) != null) {
                int oldPoints = additionResults.get(maximum).get(level);
                if (oldPoints < 10000) {

                    if (oldPoints + points >= 10000) {
                        newStarAchieved = true;
                        numberOfStarsAtTheEnd = 1;
                    }

                } else if (oldPoints >= 10000 && oldPoints < 20000) {
                    if (oldPoints - 10000 + points >= 10000) {
                        newStarAchieved = true;
                        numberOfStarsAtTheEnd = 2;
                    }

                } else if (oldPoints >= 20000 && oldPoints < 30000) {
                    if (oldPoints - 20000 + points >= 10000) {
                        newStarAchieved = true;
                        numberOfStarsAtTheEnd = 3;
                    }

                }

            }
        }

        rankAtTheEnd = highscores.isNewHighScore(this.points);

        if (rankAtTheEnd > 0) {
            newHighScore = true;
            highscores.addNewHighScore(rankAtTheEnd, user.getUsername(), points);
        }

    }

}
