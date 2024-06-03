package hangman;

public class LeaderBoard {
    private String username;
    private int score;
    private int playTime;


    public LeaderBoard() {
    }

    public LeaderBoard(String username, int score, int playTime) {
        this.username = username;
        this.score = score;
        this.playTime = playTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
}
