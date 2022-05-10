import java.util.ArrayList;

public class GameData {
    private User user;
    private int questionNumber;
    private int newQuestionIndex;
    private int questionCalculator = 0;
    //private int wrongAnswers;
    private ArrayList<ArrayList<Integer>> questionList;
    private ArrayList<ArrayList<Integer>> questionsToAskAgain;
    private int points;
    private ArrayList<Integer> lastQuestionAsked;

    public GameData(ArrayList questionList){
        this.points = 1000;
        this.questionNumber = 1;
        this.newQuestionIndex = 0;
        this.questionList = questionList;
    }

    public int getPoints() {
        return this.points;
    }

    public ArrayList<Integer> getNextQuestion() {
        questionCalculator++;
        if(questionCalculator > 15){
            //tästä kutsuttaisiin sitten gamControllerin kautta lopetuspaneelia?
            return null;
        }else{

        
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

    public int getQuestionCalculator(){
        return questionCalculator;
    }
    

}
