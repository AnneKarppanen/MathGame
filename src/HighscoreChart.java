import java.util.LinkedList;

public class HighscoreChart {
    LinkedList<HighscoreLine> additionHighScores;
    LinkedList<HighscoreLine> substractionHighScores;
    LinkedList<HighscoreLine> multiplicationHighScores;

    int lowestHighScore;

    public HighscoreChart() {
        this.additionHighScores = new LinkedList<>();
        this.substractionHighScores = new LinkedList<>();
        this.multiplicationHighScores = new LinkedList<>();
        this.lowestHighScore = 0;
    }

    public int isNewHighScore(int points) {
        int rank;

        if (additionHighScores.size() == 0) {
            rank = 1;
            // } else if (points < lowestHighScore) {
            // rank = 0;
        } else {
            int index = 0;
            for (HighscoreLine record : additionHighScores) {
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

    public void addNewHighScore(int rank, String userName, int points) {

        HighscoreLine newRecord = new HighscoreLine(userName, points);
        additionHighScores.add(rank - 1, newRecord);
        if (additionHighScores.size() > 5) {
            additionHighScores.removeLast();
        }

    }

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
