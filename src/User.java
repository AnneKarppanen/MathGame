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

    // Saves points of the user to the corresponding game type and difficulty
    // results
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

    // Method for getting the results of specific operation
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

    // Method for getting the stars
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

        }
        return stars;

    }

    // Method for getting the points
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

        }

        return points;

    }

}
