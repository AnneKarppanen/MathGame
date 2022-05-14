
import java.util.HashMap;

public class User {
    private String username;
    private HashMap<Integer, HashMap<Integer, Integer>> additionResults;
    private HashMap<Integer, HashMap<Integer, Integer>> substractionResults;
    private HashMap<Integer, HashMap<Integer, Integer>> multiplicationResults;

    public User(String username) {
        this.username = username;
        this.additionResults = new HashMap<>();
        this.substractionResults = new HashMap<>();
        this.multiplicationResults = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * public void saveNewPoints(String operation, int points, int maximum, int
     * level) {
     * if (operation.equals("+")) {
     * if (additionResults.containsKey(maximum)) {
     * HashMap<Integer, Integer> results = additionResults.get(maximum);
     * if (results.containsKey(level)) {
     * int newPoints = results.get(level) + points;
     * results.put(level, newPoints);
     * additionResults.put(maximum,results);
     * } else {
     * results.put(level, points);
     * }
     * } else {
     * HashMap<Integer, Integer> results = new HashMap<>();
     * results.put(level, points);
     * additionResults.put(maximum, results);
     * }
     * 
     * 
     * }
     * }
     */

    public void saveNewPoints(String operation, int points, int maximum, int level) {

        HashMap<Integer, HashMap<Integer, Integer>> results = null;

        if (operation.equals("+")) {
            results = this.additionResults;

        } else if (operation.equals("-")) {
            results = this.substractionResults;

        } else if (operation.equals("*")) {
            results = this.multiplicationResults;
        }

        if (results.containsKey(maximum)) {
            HashMap<Integer, Integer> resultsByMaximum = results.get(maximum);
            if (resultsByMaximum.containsKey(level)) {
                int newPoints = resultsByMaximum.get(level) + points;
                resultsByMaximum.put(level, newPoints);
                results.put(maximum, resultsByMaximum);
            } else {
                resultsByMaximum.put(level, points);
            }
        } else {
            HashMap<Integer, Integer> resultsByMaximum = new HashMap<>();
            resultsByMaximum.put(level, points);
            results.put(maximum, resultsByMaximum);
        }

        if (operation.equals("+")) {
            this.additionResults = results;

        } else if (operation.equals("-")) {
            this.substractionResults = results;

        } else if (operation.equals("*")) {
            this.multiplicationResults = results;
        }

    }

    public HashMap<Integer, HashMap<Integer, Integer>> getResults(String operation) {

        HashMap<Integer, HashMap<Integer, Integer>> results = null;

        if (operation.equals("+")) {
            results = this.additionResults;
        } else if (operation.equals("-")) {
            results = this.substractionResults;
        } else if (operation.equals("*")) {
            results = this.multiplicationResults;
        }
            
        return results;
    }

    public int getStars(String operation, int maximum, int level) {

        HashMap<Integer, HashMap<Integer, Integer>> results = null;
        int stars = 0;

        if (operation.equals("+")) {
            results = this.additionResults;

        } else if (operation.equals("-")) {
            results = this.substractionResults;

        } else if (operation.equals("*")) {
            results = this.multiplicationResults;
        }

        try {
            stars = results.get(maximum).get(level) / 10000;

        } catch (NullPointerException e) {
            System.out.println("Could not find points.");
        }
        System.out.println("returning " + stars + " stars");
        return stars;

    }

    public int getPoints(String operation, int maximum, int level) {

        HashMap<Integer, HashMap<Integer, Integer>> results = null;
        int points = 0;

        if (operation.equals("+")) {
            results = this.additionResults;

        } else if (operation.equals("-")) {
            results = this.substractionResults;

        } else if (operation.equals("*")) {
            results = this.multiplicationResults;
        }

        try {
            points = results.get(maximum).get(level);

        } catch (NullPointerException e) {
            System.out.println("Could not find points.");
        }

        return points;

    }

}
