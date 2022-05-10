import java.util.ArrayList;
import java.util.Random;



public class QuestionGenerator {

    private ArrayList<ArrayList<Integer>> questionsToAsk;
    
    public QuestionGenerator() {
        this.questionsToAsk = new ArrayList<>();
    }

    public void createAdditionQuestions(int maximum) {
        Random random = new Random();

        int addend1;
        int addend2;
        for (int i = 0; i < 15; i++) {
            addend1 = random.nextInt(maximum + 1);
         //   System.out.println(addend1);
            addend2 = random.nextInt(maximum + 1 - addend1);
            ArrayList<Integer> addends = new ArrayList<>();
            addends.add(addend1);
            addends.add(addend2);
            questionsToAsk.add(addends); 
        }
        System.out.println("päästiin ulos");
    }

    public ArrayList<ArrayList<Integer>> giveQuestionList() {
        return questionsToAsk;
    } 
    
}
