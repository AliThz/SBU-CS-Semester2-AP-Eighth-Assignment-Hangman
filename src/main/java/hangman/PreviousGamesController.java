package hangman;

import hangman.Model.DTOs.GameInfo;
import hangman.Model.DTOs.UserInfo;
import hangman.Model.Services.GameService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class PreviousGamesController implements Initializable {
    public static UserInfo player;
    private GameService gameService = new GameService();
    private ArrayList<GameInfo> previousGames;

    @FXML
    private FlowPane flwboxGames;

    @FXML
    private Label lblUsername;
    @FXML
    private Button btnBack;

    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        previousGames = gameService.getPreviousGames(player.getUsername());
//        previousGames = gameService.getGames();
        if (previousGames.isEmpty()) {
            Label label = new Label("You haven't played any games yet!");
            label.setFont(new Font(35));
            label.setTextFill(Color.WHITE);
            label.autosize();
            flwboxGames.getChildren().add(label);
            return;
        }
        lblUsername.setText(player.getUsername());

        for (var g : previousGames) {
            VBox vBox = new VBox();
            vBox.setVisible(true);
            vBox.getStyleClass().add("game-information-container");

            Label userName = new Label(g.getUsername());
            userName.setVisible(true);
            userName.getStyleClass().add("game-information");
            Label word = new Label(g.getWord());
            word.setVisible(true);
            word.getStyleClass().add("game-information");
            Label wrongGuesses = new Label(String.valueOf(g.getWrongGuesses()));
            wrongGuesses.setVisible(true);
            wrongGuesses.getStyleClass().add("game-information");
            Label time = new Label(String.valueOf(g.getTime() + " s"));
            time.setVisible(true);
            time.getStyleClass().add("game-information");

            vBox.setPrefSize(120, 120);
            vBox.getChildren().add(userName);
            vBox.getChildren().add(word);
            vBox.getChildren().add(wrongGuesses);
            vBox.getChildren().add(time);

            flwboxGames.getChildren().add(vBox);
        }
    }

    @FXML
    public void back() throws IOException {
        Stage currentStage = (Stage) btnBack.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/hangman-view.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
