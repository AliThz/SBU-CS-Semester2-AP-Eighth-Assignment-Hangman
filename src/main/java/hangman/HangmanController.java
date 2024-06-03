package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HangmanController {
    public static UserInfo player;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLeaderBoard;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnPreviousGames;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnLogOut;


    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    protected void play() throws IOException {
        GameController.player = player;
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void openLoginPage() throws IOException {
        Stage currentStage = (Stage) btnSignUp.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("Hangman - Login");
        stage.setScene(new Scene(root));
        stage.show();
//        stage.setResizable(false);
    }

    @FXML
    protected void openSignUpPage() throws IOException {
        Stage currentStage = (Stage) btnSignUp.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp.fxml")));
        stage.setTitle("Hangman - Sign Up");
        stage.setScene(new Scene(root));
        stage.show();
//        stage.setResizable(false);
    }

    @FXML
    protected void showLeaderBoard() throws IOException {
        Stage currentStage = (Stage) btnPreviousGames.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("leaderBoard.fxml")));
        stage.setTitle("Hangman - leader Board");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void showPreviousGames() throws IOException {
        PreviousGamesController.player = player;

        Stage currentStage = (Stage) btnPreviousGames.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("previousGames.fxml")));
        stage.setTitle("Hangman - Previous Games");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void logOut() throws IOException {
        Stage currentStage = (Stage) btnPreviousGames.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void exit() {
        Stage currentStage = (Stage) btnExit.getScene().getWindow();
        currentStage.close();
        System.exit(0);
    }
}