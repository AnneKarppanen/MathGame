
import java.util.HashMap;

public class User {
    private String username;
    private HashMap<Integer, HashMap<Integer, Integer>> additionResults;
    

    public User(String username) {
        this.username = username;
        this.additionResults = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void saveNewPoints(String operation, int points, int maximum, int level) {
        if (operation.equals("+")) {
            if (additionResults.containsKey(maximum)) {
                HashMap<Integer, Integer> results = additionResults.get(maximum);
                if (results.containsKey(level)) {
                    int newPoints = results.get(level) + points;
                    results.put(level, newPoints);
                    additionResults.put(maximum,results);
                } else {
                    results.put(level, points);
                }
            } else {
                HashMap<Integer, Integer> results = new HashMap<>();
                results.put(level, points);
                additionResults.put(maximum, results);
            }
            
           
        }
    }

    public HashMap<Integer, HashMap<Integer, Integer>> getResults(String operation) {
        
        if(operation.equals("+"));
            return additionResults;
    }


    

}
