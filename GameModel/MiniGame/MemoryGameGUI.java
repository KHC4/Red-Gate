package GameModel.MiniGame;

import GameModel.Items.Item;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class MemoryGameGUI.
 *__________________________
 * This is the Class that will visualize the memorygame.
 *
 */
public  class MemoryGameGUI implements GameGUI{
    /** Value to return */
    boolean returnVal = false;
    /** To set if game started */
    boolean alreadyStarted = false;
    /** Selected Coordinates*/
    ArrayList<int[]> coordinatesPicked = new ArrayList<int[]>();

    /**
     * removeDuplicates
     * __________________________
     * removes duplicates from coordinates picked
     */
    public static ArrayList<int[]> removeDuplicates(ArrayList<int[]> coordinatesPicked)
    {
        ArrayList<int[]> returnList = new ArrayList<int[]>();

        for(int i = 0; i< coordinatesPicked.size(); i++)
        {
            boolean hasDuplicate = false;
            for(int j = 0; j< coordinatesPicked.size(); j++)
            {
                if (i!=j && coordinatesPicked.get(i)[0]==coordinatesPicked.get(j)[0] && coordinatesPicked.get(i)[1]==coordinatesPicked.get(j)[1])
                {

                    hasDuplicate = true;
                }
            }
            if (!hasDuplicate)
            {
                returnList.add(coordinatesPicked.get(i));
            }


        }
        return returnList;
    }
    /**
     * playGame
     * __________________________
     * Sets up and plays the actual game
     * @return boolean: Returns true if the player won, false otherwise.
     */
    public boolean playGame() {


        GridPane memoryGridPane = new GridPane();
        RowConstraints row3 = new RowConstraints(150);
        ColumnConstraints col3 = new ColumnConstraints(150);
        memoryGridPane.setPadding(new Insets(25));
        memoryGridPane.getRowConstraints().addAll(row3, row3, row3, row3);
        memoryGridPane.getColumnConstraints().addAll(col3, col3, col3, col3);
        memoryGridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#202020"),
                new CornerRadii(0),
                new Insets(0)
        )));

        Label howToPlay = new Label("Rules");
        howToPlay.setAlignment(Pos.CENTER);
        howToPlay.setFont(new Font("Times New Roman", 16));
        howToPlay.setText("How to Play: \nClick on the start button and watch certain boxes light up on the grid.\nWhen they change back to white, then select the buttons that lit up by clicking on them (Do your best to remember which ones).\n When you get all of the, hit the done button.");
        howToPlay.setWrapText(true);
        howToPlay.setStyle("-fx-text-fill: white;");

        this.returnVal = false;
        Group root = new Group();
        boolean returnBool = false;
        Scene scene = new Scene(memoryGridPane,1280,720);
        final Stage gui = new Stage(); //dialogue box
        gui.setWidth(800);
        gui.setHeight(800);

        memoryGridPane.add(howToPlay, 3, 0, 1, 3);


        Button[][] memoryButtons = new Button[3][3];

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {

                Button tempButton = new Button();
                tempButton.setAlignment(Pos.CENTER);
                customizeButton(tempButton, 50, 50);
                int[] finalList = {i,j};
                tempButton.setOnAction(e -> {
                    this.coordinatesPicked.add(finalList);
                });
                tempButton.setStyle("-fx-background-color: #FFFFFF; ");
                memoryGridPane.add(tempButton, i, j, 1, 1);
                GridPane.setHalignment(tempButton, HPos.CENTER);
                GridPane.setValignment(tempButton, VPos.CENTER);
                memoryButtons[i][j] = tempButton;


            }
        }
        MemoryGame game = new MemoryGame();
        ArrayList<int[]> coordinates = game.getListOfCoordinates();

        Button doneButton = new Button("Done");
        customizeButton(doneButton, 100, 50);
        doneButton.setAlignment(Pos.CENTER);
        doneButton.setOnAction(e -> {


            this.coordinatesPicked = removeDuplicates(this.coordinatesPicked);


            this.returnVal = game.checkIfWin(this.coordinatesPicked);

            gui.close();
        });
        memoryGridPane.add(doneButton, 2, 4, 1, 1);


        Button finishedButton = new Button("Start");
        finishedButton.setAlignment(Pos.CENTER);
        customizeButton(finishedButton, 100, 50);
        finishedButton.setOnAction(e -> {

            if (!this.alreadyStarted)
            {
                for (int i=0; i<coordinates.size();i++)
                {
                    int[] tempCoord = coordinates.get(i);
                    Button lightButton = memoryButtons[tempCoord[0]][tempCoord[1]];
                    lightButton.setStyle("-fx-background-color: #ff0000; ");

                }
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {


                    for (int i=0; i<coordinates.size();i++)
                    {
                        int[] tempCoord = coordinates.get(i);
                        Button lightButton = memoryButtons[tempCoord[0]][tempCoord[1]];
                        lightButton.setStyle("-fx-background-color: #ff0000; ");
                        lightButton.setStyle("-fx-background-color: #FFFFFF; ");
                    }

                });
                pause.play();
                this.alreadyStarted = true;

            }

        });
        memoryGridPane.add(finishedButton, 1, 4, 1, 1);


        gui.setScene(scene);
        gui.showAndWait();
        return returnVal;
    }


    /**
     * customizeButton
     * __________________________
     *
     * @param inputButton the button to make stylish :)
     * @param w width
     * @param h height
     */
    private void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Times New Roman", 20));
        inputButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: Black;");

    }

}
