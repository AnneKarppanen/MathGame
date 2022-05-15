import java.util.ArrayList;
import java.util.Random;

public class QuestionGenerator {

    private ArrayList<ArrayList<Integer>> questionsToAsk;

    // Creates an ArrayList for the questions to be asked in a game.
    public QuestionGenerator() {
        this.questionsToAsk = new ArrayList<>();
    }

    /*
     * Fills the ArrayList created in a constructor with numbers that can be used to
     * make addition questions whose maximum result is the int that is taken as a
     * parameter.
     */
    public void createAdditionQuestions(int maximum) {
        Random random = new Random();

        int addend1;
        int addend2;
        for (int i = 0; i < 15; i++) {
            addend1 = random.nextInt(maximum + 1);
            addend2 = random.nextInt(maximum + 1 - addend1);
            ArrayList<Integer> addends = new ArrayList<>();
            addends.add(addend1);
            addends.add(addend2);
            questionsToAsk.add(addends);
        }
    }

    public ArrayList<ArrayList<Integer>> giveQuestionList() {
        return questionsToAsk;
    }

}
