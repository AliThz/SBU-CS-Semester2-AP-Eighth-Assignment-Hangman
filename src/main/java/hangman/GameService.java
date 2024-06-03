package hangman;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameService {
    private DatabaseManager databaseManager;


    public String getWord() {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/randomword");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", "uvpIFPXS7SEX6mroJq8uxPyUf5q9vyWhLxfv4D6U");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            String word =root.path("word").asText();
            return word;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<GameInfo> getPreviousGames(String username) {
        return databaseManager.selectGameInfos().stream().filter(g -> Objects.equals(g.getUsername(), username)).collect(Collectors.toCollection(ArrayList<GameInfo> :: new));
    }

    public GameService() {
        this.databaseManager = new DatabaseManager();
    }

    public void createGame(GameInfo gameInfo) {
        databaseManager.insertGameInfo(gameInfo);
    }

    public GameInfo getGame(UUID gameID) {
        return databaseManager.selectGameInfo(gameID);
    }

    public ArrayList<GameInfo> getGames() {
        return databaseManager.selectGameInfos();
    }

    public void editGame(GameInfo gameInfo) {
        databaseManager.updateGameInfo(gameInfo);
    }

    public void removeGame(UUID gameID) {
        databaseManager.deleteGameInfo(gameID);
    }

    public ArrayList<LeaderBoard> getLeaderboard() {
        return databaseManager.selectLeaderBoard();
    }
}
