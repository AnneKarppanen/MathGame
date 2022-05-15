import java.util.LinkedList;

public class HighscoreChart {
    LinkedList<HighscoreLine> additionHighScores;
    LinkedList<HighscoreLine> substractionHighScores;
    LinkedList<HighscoreLine> multiplicationHighScores;

    int lowestHighScore;

    // Creates a class that contains top 5 highscores for different operations.
    public HighscoreChart() {
        this.additionHighScores = new LinkedList<>();
        this.substractionHighScores = new LinkedList<>();
        this.multiplicationHighScores = new LinkedList<>();
        this.lowestHighScore = 0;
    }

    /*
     * Takes a number of points (int) and an operation (+, -, *) as parameters and
     * returns the rank that is achieved with these points in the top higscores list
     * of the given operation.
     */
    public int isNewHighScore(int points, String operation) {
        int rank;

        LinkedList<HighscoreLine> highscores = null;

        if (operation.equals("+")) {
            highscores = additionHighScores;

        } else if (operation.equals("-")) {
            highscores = substractionHighScores;

        } else if (operation.equals("*")) {
            highscores = substractionHighScores;
        }

        if (highscores.size() == 0) {
            rank = 1;
        } else {
            int index = 0;
            for (HighscoreLine record : highscores) {
                int scoreToCompare = record.getPoints();
                if (points >= scoreToCompare) {
                    break;
                }
                index++;
            }
            rank = index + 1;
        }

        return rank;

    }

    /*
     * Takes a rank (int), userName (String), points (int) and operation (String) as
     * parameters and adds the new highscore to the correct LinkedList that contains
     * the top 5 scores for the given operation.
     */
    public void addNewHighScore(int rank, String userName, int points, String operation) {

        LinkedList<HighscoreLine> highscores = null;

        if (operation.equals("+")) {
            highscores = additionHighScores;

        } else if (operation.equals("-")) {
            highscores = substractionHighScores;

        } else if (operation.equals("*")) {
            highscores = substractionHighScores;
        }

        HighscoreLine newRecord = new HighscoreLine(userName, points);
        highscores.add(rank - 1, newRecord);
        if (highscores.size() > 5) {
            highscores.removeLast();
        }

    }

    // Returns the LinkedList that contains the top 5 highscores for the given
    // operation.
    public LinkedList<HighscoreLine> getHighScoresByOperation(String operation) {

        LinkedList<HighscoreLine> highscores = null;

        if (operation.equals("+")) {
            highscores = this.additionHighScores;

        } else if (operation.equals("-")) {
            highscores = this.substractionHighScores;

        } else if (operation.equals("*")) {
            highscores = this.multiplicationHighScores;
        }

        return highscores;
    }

    public LinkedList<HighscoreLine> getAdditionHighScores() {
        return additionHighScores;
    }

    public LinkedList<HighscoreLine> getSubstractionHighScores() {
        return substractionHighScores;
    }

    public LinkedList<HighscoreLine> getMultiplicationHighScores() {
        return multiplicationHighScores;
    }

}
