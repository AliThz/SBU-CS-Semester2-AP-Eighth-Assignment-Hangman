package hangman;

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


    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    protected void play() {

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

    protected void showLeaderBoard() {

    }

    protected void showGameHistory() {

    }

    protected void exit() {

    }

}