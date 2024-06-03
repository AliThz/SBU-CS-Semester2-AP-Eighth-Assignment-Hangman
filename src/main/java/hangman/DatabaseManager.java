package hangman;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

// Use JDBC to connect to your database and run queries

public class DatabaseManager {

    //region [ - UserInfo CRUD - ]

    //region [ - insertUserInfo(UserInfo userInfo) - ]
    public void insertUserInfo(UserInfo userInfo) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertUserInfo)");

            stmt = c.prepareStatement("INSERT INTO public.userinfo(\"Name\", \"Username\", \"Password\") VALUES (?, ?, ?);");
            stmt.setString(1, userInfo.getName());
            stmt.setString(2, userInfo.getUsername());
            stmt.setString(3, userInfo.getPassword());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (insertUserInfo)");
    }
    //endregion

    //region [ - ArrayList<UserInfo> selectUserInfos() - ]
    public ArrayList<UserInfo> selectUserInfos() {
        Connection c;
        Statement stmt;
        ArrayList<UserInfo> userInfos = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserInfos)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserInfo;");
            userInfos = new ArrayList<>();
            while (rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setName(rs.getString("Name"));
                userInfo.setUsername(rs.getString("Username"));
                userInfo.setPassword(rs.getString("Password"));
                userInfos.add(userInfo);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (selectUserInfos)");
        return userInfos;
    }
    //endregion

    //region [ - UserInfo selectUserInfo(String username) - ]
    public UserInfo selectUserInfo(String username) {
        Connection c;
        PreparedStatement stmt;
        UserInfo userInfo = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserInfo (" + username + "))");

            stmt = c.prepareStatement("SELECT * FROM UserInfo WHERE \"Username\" = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            userInfo = new UserInfo();
            while (rs.next()) {
                userInfo.setName(rs.getString("Name"));
                userInfo.setUsername(rs.getString("Username"));
                userInfo.setPassword(rs.getString("Password"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (selectUserInfo (" + username + "))");
        return userInfo;
    }
    //endregion

    //region [ - updateUserInfo(UserInfo userInfo) - ]
    public void updateUserInfo(UserInfo userInfo) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateUserInfo)");

            stmt = c.prepareStatement("UPDATE public.userinfo SET \"Name\" = ?, \"Password\" = ? WHERE \"Username\" = ?;");
            stmt.setString(1, userInfo.getName());
            stmt.setString(2, userInfo.getPassword());
            stmt.setString(3, userInfo.getUsername());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (updateUserInfo)");
    }
    //endregion

    //region [ - deleteUserInfo(String username) - ]
    public void deleteUserInfo(String username) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteUserInfo)");

            stmt = c.prepareStatement("DELETE FROM public.userinfo WHERE \"Username\" = ?;");
            stmt.setString(1, username);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteUserInfo)");
    }
    //endregion

    //endregion

    //region [ - GameInfo CRUD - ]

    //region [ - insertGameInfo(GameInfo gameInfo) - ]
    public void insertGameInfo(GameInfo gameInfo) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertGameInfo)");

            stmt = c.prepareStatement("INSERT INTO public.gameinfo(\"GameID\", \"Username\", \"Word\", \"WrongGuesses\", \"Time\",\"Win\") VALUES (?, ?, ?, ?, ?, ?);");
            stmt.setObject(1, gameInfo.getGameID());
            stmt.setString(2, gameInfo.getUsername());
            stmt.setString(3, gameInfo.getWord());
            stmt.setInt(4, gameInfo.getWrongGuesses());
            stmt.setInt(5, gameInfo.getTime());
            stmt.setBoolean(6, gameInfo.isWin());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (insertGameInfo)");
    }
    //endregion

    //region [ - ArrayList<GameInfo> selectGameInfos() - ]
    public ArrayList<GameInfo> selectGameInfos() {
        Connection c;
        Statement stmt;
        ArrayList<GameInfo> gameInfos = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectGameInfos)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GameInfo;");
            gameInfos = new ArrayList<>();
            while (rs.next()) {
                GameInfo gameInfo = new GameInfo();
                gameInfo.setGameID(UUID.fromString(rs.getString("GameID")));
                gameInfo.setUsername(rs.getString("Username"));
                gameInfo.setUserInfo(selectUserInfo(gameInfo.getUsername()));
                gameInfo.setWord(rs.getString("Word"));
                gameInfo.setWrongGuesses(rs.getInt("WrongGuesses"));
                gameInfo.setTime(rs.getInt("Time"));
                gameInfo.setWin(rs.getBoolean("Win"));
                gameInfos.add(gameInfo);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (selectGameInfos)");
        return gameInfos;
    }
    //endregion

    //region [ - GameInfo selectGameInfo(UUID gameID) - ]
    public GameInfo selectGameInfo(UUID gameID) {
        Connection c;
        PreparedStatement stmt;
        GameInfo gameInfo = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectGameInfo)");

            stmt = c.prepareStatement("SELECT * FROM GameInfo WHERE \"GameID\" = ?");
            stmt.setObject(1, gameID);
            ResultSet rs = stmt.executeQuery();
            gameInfo = new GameInfo();
            while (rs.next()) {
                gameInfo.setGameID(UUID.fromString(rs.getString("GameID")));
                gameInfo.setUsername(rs.getString("Username"));
                gameInfo.setUserInfo(selectUserInfo(gameInfo.getUsername()));
                gameInfo.setWord(rs.getString("Word"));
                gameInfo.setWrongGuesses(rs.getInt("WrongGuesses"));
                gameInfo.setTime(rs.getInt("Time"));
                gameInfo.setWin(rs.getBoolean("Win"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (selectGameInfo)");
        return gameInfo;
    }
    //endregion

    //region [ - updateGameInfo(GameInfo gameInfo) - ]
    public void updateGameInfo(GameInfo gameInfo) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateGameInfo)");

            stmt = c.prepareStatement("UPDATE public.gameinfo SET \"Username\" = ?, \"Word\" = ?, \"WrongGuesses\" = ?, \"Time\" = ?, \"Win\" = ? WHERE \"GameID\" = ?;");
            stmt.setString(1, gameInfo.getUsername());
            stmt.setString(2, gameInfo.getWord());
            stmt.setInt(3, gameInfo.getWrongGuesses());
            stmt.setInt(4, gameInfo.getTime());
            stmt.setBoolean(5, gameInfo.isWin());
            stmt.setObject(6, gameInfo.getGameID());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (updateGameInfo)");
    }
    //endregion

    //region [ - deleteGameInfo(UUID gameID) - ]
    public void deleteGameInfo(UUID gameID) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteGameInfo)");

            stmt = c.prepareStatement("DELETE FROM public.gameinfo WHERE \"GameID\" = ?;");
            stmt.setObject(1, gameID);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteGameInfo)");
    }
    //endregion

    //region [ - ArrayList<LeaderBoard> selectLeaderBoard() - ]
    public ArrayList<LeaderBoard> selectLeaderBoard() {
        Connection c;
        Statement stmt;
        ArrayList<LeaderBoard> leaderBoards = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:4321/SBU-CS-Semester2-AP-Eighth-Assignment-Hangman", "postgres", "hmhat");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectLeaderboard)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT \"Username\", SUM((100 * LENGTH(\"Word\")) / \"Time\") as \"Score\", SUM(\"Time\") as \"Play Time\" FROM public.gameinfo GROUP BY \"Username\"  ORDER BY \"Score\" desc;");
            leaderBoards = new ArrayList<>();
            while (rs.next()) {
                LeaderBoard leaderBoard = new LeaderBoard();
                leaderBoard.setUsername(rs.getString("Username"));
                leaderBoard.setScore(rs.getInt("Score"));
                leaderBoard.setPlayTime(rs.getInt("Play Time"));
                leaderBoards.add(leaderBoard);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (selectLeaderboard)");
        return leaderBoards;
    }
    //endregion

    //endregion

}