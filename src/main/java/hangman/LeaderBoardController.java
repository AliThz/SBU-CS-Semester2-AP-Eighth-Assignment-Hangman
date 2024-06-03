package hangman;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LeaderBoardController implements Initializable {
    private GameService gameService = new GameService();
    private ArrayList<GameInfo> previousGames;
    @FXML
    private VBox vbxLeaderBoardContainer;
    @FXML
    private Button btnBack;

    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();


    @FXML
    public void back() throws IOException {
        Stage currentStage = (Stage) btnBack.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hangman-view.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<LeaderBoard> leaderBoards = gameService.getLeaderboard();
        Collections.reverse(leaderBoards);

        TableView<LeaderBoard> tableView = new TableView<>();
        TableColumn<LeaderBoard, String> usernameColumn = new TableColumn<>("Username");
        TableColumn<LeaderBoard, String> scoreColumn = new TableColumn<>("Score");
        TableColumn<LeaderBoard, String> playTimeColumn = new TableColumn<>("Play Time");

        // Set up cell value factories
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        scoreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getScore())));
        playTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPlayTime())));

        // Create an ObservableList and populate it with data from the database
        ObservableList<LeaderBoard> leaderboard = FXCollections.observableArrayList();
        leaderboard.addAll(leaderBoards);
        // Add more data from your database here

        // Bind the data to the TableView
        tableView.setItems(leaderboard);
        tableView.getColumns().addAll(usernameColumn, scoreColumn, playTimeColumn);

        tableView.getStyleClass().add("table-view");
        vbxLeaderBoardContainer.getChildren().add(1, tableView);
    }
}
