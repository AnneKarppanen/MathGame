public class HighscoreLine {

    private String username;
    private int points;

    // Contains the data for a new high score in a top 5 list.

    public HighscoreLine(String username, int points) {
        this.username = username;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

}
