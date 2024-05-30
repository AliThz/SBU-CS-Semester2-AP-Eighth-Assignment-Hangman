package hangman;

import java.sql.*;
import java.util.ArrayList;

// Use JDBC to connect to your database and run queries

public class DatabaseManager {
    public static void main(String[] args) {
        var user = new UserInfo();
        user.setName("Hamed Pouraghniaie");
        user.setUsername("hamed");
        user.setPassword("hamed12345");
    }

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
        System.out.println("Operation done successfully (selectUserInfos (insertUserInfo)");
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
                System.out.println("Name = " + userInfo.getName());
                System.out.println("Username = " + userInfo.getUsername());
                System.out.println("Password = " + userInfo.getPassword());
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (selectUserInfos (" + username + "))");
        return userInfo;
    }
    //endregion

    //endregion
}