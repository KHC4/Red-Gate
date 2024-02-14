package views;

import GameModel.Scores.ScoreBoard;
import GameModel.Scores.ScoreList;
import GameModel.Scores.SingletonScore;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * Class LeaderboardView.
 *__________________________
 * This is the Class that will visualize the Leaderboard.
 *
 */
public class LeaderboardView {
    /** Light Mode toggle switch */
    private final boolean lightMode;
    /**
     * InventoryView Constructor
     * __________________________
     * Initializes attributes
     * @param name the current player's name
     * @param lightMode the lightmode toggled boolean
     */
    public LeaderboardView(String name, boolean lightMode) throws FileNotFoundException {
        ScoreBoard scoresBoard = new ScoreBoard(name);
        ScoreList scores = scoresBoard.getScores();
        ArrayList<Double> scoresList = scores.getScores();
        ArrayList<String> nameList = scores.getNames();
        this.lightMode = lightMode;

        GridPane leaderboardGridPane = new GridPane();
        leaderboardGridPane.setPadding(new Insets(20));
        if (!lightMode) {
            leaderboardGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }else {
            leaderboardGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        RowConstraints row1 = new RowConstraints(50);
        RowConstraints row2 = new RowConstraints(40);
        ColumnConstraints col1 = new ColumnConstraints(200);

        leaderboardGridPane.getRowConstraints().addAll(row1, row2, row2, row2, row2, row2, row2, row2, row2, row2, row2);
        leaderboardGridPane.getColumnConstraints().addAll(col1, col1);

        Label nameTitle = new Label("Name");
        customizeLabel(nameTitle, 20);
        nameTitle.setText("Name");

        Label scoreTitle = new Label("Score");
        customizeLabel(scoreTitle, 20);
        scoreTitle.setText("Score");

        String helpText = "";
        int j = 1;
        for (int i = 9; i >= 0; i--){
            helpText = helpText + nameList.get(i).toString() + ":" + scoresList.get(i).toString() + "\n";

            Label playerName = new Label(nameList.get(i).toString());
            customizeLabel(playerName, 14);
            playerName.setText(nameList.get(i));

            Label playerScore = new Label(scoresList.get(i).toString());
            customizeLabel(playerScore, 14);
            playerScore.setText(scoresList.get(i).toString());

            leaderboardGridPane.add(playerName, 0, j, 1, 1);
            leaderboardGridPane.add(playerScore, 1, j, 1, 1);

            GridPane.setHalignment(playerName, HPos.CENTER);
            GridPane.setHalignment(playerScore, HPos.CENTER);

            j++;
        }

        leaderboardGridPane.add(nameTitle, 0,0, 1, 1);
        leaderboardGridPane.add(scoreTitle, 1, 0, 1, 1);

        GridPane.setHalignment(nameTitle,HPos.CENTER);
        GridPane.setHalignment(scoreTitle,HPos.CENTER);

        leaderboardGridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(leaderboardGridPane,400,400);
        final Stage gui = new Stage(); //dialogue box
        gui.setWidth(400.0);
        gui.setHeight(500.0);

        gui.setScene(scene);
        gui.show();

    }

    /**
     * customizeLabel
     * __________________________
     *
     * @param inputLabel the button to make stylish :)
     * @param size font size
     */
    private void customizeLabel(Label inputLabel, int size){
        inputLabel.setAlignment(Pos.CENTER);
        inputLabel.setFont(new Font("Times New Roman", size));
        if (!lightMode){
            inputLabel.setStyle("-fx-text-fill: white;");
        }else {
            inputLabel.setStyle("-fx-text-fill: black;");
        }
        inputLabel.setTextAlignment(TextAlignment.CENTER);
    }
}