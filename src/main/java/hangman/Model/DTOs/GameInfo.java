package hangman.Model.DTOs;

import java.util.UUID;

public class GameInfo {
    private UUID gameID;
    private String username;
    private UserInfo userInfo;
    private String word;
    private int wrongGuesses;
    private int time;
    private boolean win;

    public GameInfo() {
        this.gameID = UUID.randomUUID();
    }

    public GameInfo(String username, String word, int wrongGuesses, int time, boolean win) {
        this.gameID = UUID.randomUUID();
        this.username = username;
        this.word = word;
        this.wrongGuesses = wrongGuesses;
        this.time = time;
        this.win = win;
    }

    public GameInfo(UUID gameID, String username, String word, int wrongGuesses, int time, boolean win) {
        this.gameID = gameID;
        this.username = username;
        this.word = word;
        this.wrongGuesses = wrongGuesses;
        this.time = time;
        this.win = win;
    }

    public UUID getGameID() {
        return gameID;
    }

    public void setGameID(UUID gameID) {
        this.gameID = gameID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
