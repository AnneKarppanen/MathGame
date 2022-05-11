public class HighscoreLine {

    private String username;
    private int points;
    
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
