package hangman;

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

public class LoginController implements Initializable {

    private UserService userService = new UserService();

    @FXML
    private Button btnBack;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblLoginError;

    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    protected void openStartPage() throws IOException {
        Stage currentStage = (Stage) btnBack.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
//        stage.setResizable(false);
    }

    @FXML
    protected void login() throws IOException {
        UserInfo userInfo = userService.login(txtUsername.getText());
        HangmanController.player = userInfo;
        if (userInfo.getUsername() != null) {
            if (Objects.equals(userInfo.getPassword(), txtPassword.getText())) {
                Stage currentStage = (Stage) btnBack.getScene().getWindow();
                currentStage.close();
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hangman-view.fxml")));
                stage.setTitle("Hangman");
                stage.setScene(new Scene(root));
                stage.show();
//              stage.setResizable(false);
            }
            lblLoginError.setText("The password doesn't match the username");
        } else {
            lblLoginError.setText("The username is not found");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoginError.setText("");
    }
}





