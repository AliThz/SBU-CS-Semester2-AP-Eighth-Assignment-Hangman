package hangman.Model.Services;

import hangman.Model.DTOs.UserInfo;
import hangman.Model.Database.DatabaseManager;

import java.util.ArrayList;

public class UserService {
    private DatabaseManager databaseManager;

    public UserService() {
        this.databaseManager = new DatabaseManager();
    }

    public UserInfo login(String username) {
        UserInfo userInfo = getUser(username);
        if (userInfo != null) {
            return userInfo;
        }
        return null;
    }

    public void createUser(UserInfo userInfo) {
        databaseManager.insertUserInfo(userInfo);
    }

    public UserInfo getUser(String username) {
        return databaseManager.selectUserInfo(username);
    }

    public ArrayList<UserInfo> getUser() {
        return databaseManager.selectUserInfos();
    }

    public void editUser(UserInfo userInfo) {
        databaseManager.updateUserInfo(userInfo);
    }

    public void removeUser(String username) {
        databaseManager.deleteUserInfo(username);
    }
}
