import java.util.ArrayList;
import java.util.HashMap;

public class GameData {
    private User user;
    private String operation;
    private int level;
    private int maximum;
    private int newQuestionIndex = 0;
    private int questionCalculator = 0;
    private ArrayList<ArrayList<Integer>> questionList;
    private ArrayList<ArrayList<Integer>> questionsToAskAgain;
    private int points = 0;
    private ArrayList<Integer> lastQuestionAsked;
    private boolean newStarAchieved = false;
    private boolean newHighScore = false;
    private int numberOfStarsAtTheEnd = 0;
    private int rankAtTheEnd;

    /*
     * Creates a model for the game data needed in one game. A new game data class
     * is created whenever a user starts a new game. Gamedata takes a list of
     * questions, the current user, an operation (+, - or *), the biggest number of
     * the range (10, 20 or 100) and the level (1-3) as parameters.
     */
    public GameData(ArrayList<ArrayList<Integer>> questionList, User user, String operation, int maximum, int level) {
        this.questionList = questionList;
        this.level = level;
        this.maximum = 10;
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

    /*
     * Returns an ArrayList<Integer> that contains the numbers needed to form the
     * next question. The method takes the next question either from an ArrayList
     * that contains the new questions (questionsToAsk), or an ArrayList
     * (questionsToAskAgain) that contains the questions that the user has answered
     * incorrectly and the questions where the user has
     * ran out of the time.
     */
    public ArrayList<Integer> getNextQuestion() {
        questionCalculator++;
        if (questionCalculator > 3) {
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

    // Adds the question that is given as a parameter to the questionsToAskAgain
    // ArrayList.
    public void addQuestionToAskAgainList(ArrayList<Integer> askThisQuestionAgain) {
        questionsToAskAgain.add(askThisQuestionAgain);
    }

    public int getQuestionCalculator() {
        return questionCalculator;
    }

    /*
     * Counts the game's end results to define whether the player has gained a new
     * star an/or achieved a new highscore. Takes the current HighscoreChart as a
     * parameter.
     */
    public void countGameResults(HighscoreChart highscores) {

        if (user.getResults(this.operation) != null) {
            HashMap<Integer, HashMap<Integer, Integer>> additionResults = user.getResults(this.operation);

            if (additionResults.get(maximum) != null && additionResults.get(maximum).get(level) != null) {
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

        rankAtTheEnd = highscores.isNewHighScore(this.points, operation);

        if (rankAtTheEnd > 0) {
            newHighScore = true;
            highscores.addNewHighScore(rankAtTheEnd, user.getUsername(), points, operation);
        }

    }

}
