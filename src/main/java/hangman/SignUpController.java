package hangman;

import hangman.Model.DTOs.UserInfo;
import hangman.Model.Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private UserService userService = new UserService();

    @FXML
    private Button btnBack;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtName;
    @FXML
    private Label lblLoginError;

    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    protected void openStartPage() throws IOException {
        Stage currentStage = (Stage) btnBack.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/start.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
//        stage.setResizable(false);
    }

    @FXML
    protected void signUp() throws IOException {
        UserInfo userInfo = userService.login(txtUsername.getText());
        if (userInfo.getUsername() == null) {
            userInfo = new UserInfo(txtName.getText(), txtUsername.getText(), txtPassword.getText());
            userService.createUser(new UserInfo(txtName.getText(), txtUsername.getText(), txtPassword.getText()));
            HangmanController.player = userInfo;
            lblLoginError.setText("");
            Stage currentStage = (Stage) btnBack.getScene().getWindow();
            currentStage.close();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/hangman-view.fxml")));
            stage.setTitle("Hangman");
            stage.setScene(new Scene(root));
            stage.show();
//          stage.setResizable(false);
        } else {
            lblLoginError.setText("The username is taken");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoginError.setText("");
    }
}
