import java.util.ArrayList;

public class Highscore {
    ArrayList<User> additionHighScores = new ArrayList<>();



    public void addNewHighScore(User user, int score){
        //LisääTähän joku sortteri, joka sorttaa listan ja poistaa ylimääräisen, jos enemmän kuin 6 tulosta

    }

    public void checkHighScore(User user, int score){

        for(User u : additionHighScores){
            if(score > u.getScore()){
                addNewHighScore(user, score);
            }  
        }
    }
    
}
