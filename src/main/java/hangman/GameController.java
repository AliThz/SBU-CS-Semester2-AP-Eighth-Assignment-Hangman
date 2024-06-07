package hangman;

import hangman.Model.DTOs.GameInfo;
import hangman.Model.DTOs.UserInfo;
import hangman.Model.Services.GameService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {
    private GameService gameService = new GameService();
    public static UserInfo player;
    private GameInfo gameInfo;
    private String word;
    private Timer timer;
    @FXML
    private Label lblTimer;

    @FXML
    private Button btnBack;
    @FXML
    private Button btnPlay;

    @FXML
    private Label lblUserName;
    @FXML
    private VBox vbxGameBoard;
    @FXML
    private FlowPane flwbxAlphabet;
    private ArrayList<Shape> man = new ArrayList<>();
    @FXML
    private Line shpBottomLine;
    @FXML
    private Line shpSideLine;
    @FXML
    private Line shpTopLine;
    @FXML
    private Line shpNeckLine;
    @FXML
    private Ellipse shpHead;
    @FXML
    private Line shpBody;
    @FXML
    private Line shpLeftLeg;
    @FXML
    private Line shpLeftHand;
    @FXML
    private Line shpRightHand;
    @FXML
    private Line shpRightLeg;
    @FXML
    private HBox hbxWord;
    @FXML
    private HBox hbxWordPlaceHolder;

    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameInfo = new GameInfo();
        getWord();
        System.out.println(word);

        lblUserName.setText(player.getUsername());

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Update the label text on the JavaFX Application Thread
                Platform.runLater(() -> {
                    gameInfo.setTime(gameInfo.getTime() + 1);
                    lblTimer.setText("Time: " + gameInfo.getTime() + " seconds");
                });
            }
        }, 1000, 1000);

        man.add(shpBottomLine);
        shpBottomLine.setVisible(false);
        man.add(shpSideLine);
        shpSideLine.setVisible(false);
        man.add(shpTopLine);
        shpTopLine.setVisible(false);
        man.add(shpNeckLine);
        shpNeckLine.setVisible(false);
        man.add(shpHead);
        shpHead.setVisible(false);
        man.add(shpBody);
        shpBody.setVisible(false);
        man.add(shpRightHand);
        shpRightHand.setVisible(false);
        man.add(shpLeftHand);
        shpLeftHand.setVisible(false);
        man.add(shpRightLeg);
        shpRightLeg.setVisible(false);
        man.add(shpLeftLeg);
        shpLeftLeg.setVisible(false);

        displayWord();
        displayAlphabet();
    }

    @FXML
    public void displayWord() {
        hbxWord.getChildren().clear();
        for (int i = 0; i < word.length(); i++) {
            Label label = new Label();
            label.getStyleClass().add("lblWord");
            label.setVisible(false);
//            label.setVisible(true);
            label.setPrefSize(100, 100);
            label.setText(String.valueOf(word.charAt(i)));
            label.setId("lblWord" + i);
            label.autosize();
            hbxWord.getChildren().add(label);

            Label letterPlaceholder = new Label();
            letterPlaceholder.setPrefSize(100, 5);
            letterPlaceholder.setVisible(true);
            letterPlaceholder.getStyleClass().add("letterPlaceHolder");
            letterPlaceholder.autosize();

            hbxWordPlaceHolder.getChildren().add(letterPlaceholder);
        }
    }

    @FXML
    public void displayAlphabet() {
        flwbxAlphabet.getChildren().clear();
        for (int i = 0; i < 26; i++) {
            Button button = new Button();
            button.setText(Character.toString('A' + i));
            button.setVisible(true);
            button.setStyle("-fx-background-color: #ffffff");
            button.setStyle("-fx-background-radius: 50");
            button.setPrefSize(50, 50);
            button.setOnAction(actionEvent -> {
                completeWord(button.getText().charAt(0));
                button.setVisible(false);
            });
            button.autosize();
            flwbxAlphabet.getChildren().add(button);
        }
    }

    public void completeWord(char character) {
        gameInfo.setWin(true);
        if (word.contains(String.valueOf(character))) {
            for (var c : hbxWord.getChildren()) {
                if (((Label) c).getText().charAt(0) == character) {
                    c.setVisible(true);
                }
            }
        } else {
            gameInfo.setWrongGuesses(gameInfo.getWrongGuesses() + 1);
            man.get(gameInfo.getWrongGuesses() - 1).setVisible(true);
            if (gameInfo.getWrongGuesses() == 10) {
                showGameResult(false);
            }
        }

        for (var c : hbxWord.getChildren()) {
            if (!c.isVisible()) {
                gameInfo.setWin(false);
            }
        }

        if (gameInfo.isWin()) {
            showGameResult(true);
        }
    }

    public void showGameResult(boolean hasWin) {
        timer.cancel();
        btnPlay.setDisable(false);

        vbxGameBoard.getChildren().remove(hbxWordPlaceHolder);

        Label winStatus = new Label();
        winStatus.setVisible(true);
        winStatus.getStyleClass().add("winStatus");

        if (hasWin) {
            winStatus.setText("You Won");
            flwbxAlphabet.getChildren().clear();
            flwbxAlphabet.getChildren().add(winStatus);
        } else {
            gameInfo.setWin(false);

            for (var c : hbxWord.getChildren()) {
                if (c instanceof Label) {
                    if (!c.isVisible()) {
                        c.setVisible(true);
                        c.getStyleClass().add("missingCharacters");
                    }
                }
            }
            for (var s : man) {
                s.getStyleClass().add("deadMan");
            }

            winStatus.setText("You Lost");
            flwbxAlphabet.getChildren().clear();
            flwbxAlphabet.getChildren().add(winStatus);
        }

        gameInfo.setUsername(player.getUsername());
        gameInfo.setWord(word);
        gameInfo.setWin(hasWin);
        gameService.createGame(gameInfo);
    }

    public void getWord() {
        word = gameService.getWord().toUpperCase(Locale.ROOT);
        if (word.length() > 8) {
            getWord();
        }
    }

    public void backToTheMenu() throws IOException {
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/hangman-view.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void playAgain() throws IOException {
        Stage currentStage = (Stage) btnPlay.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        stage.setTitle("Hangman");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
