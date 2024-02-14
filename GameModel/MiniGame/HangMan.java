package GameModel.MiniGame;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class HangMan implements MiniGame, GameGUI{
    /** Selected Word */
    private String currentWord;
    /** Array list of strings each representing  */
    private ArrayList<String> letters;
    /** List of selectable words */
    private static String[] words= {"SHARK", "LOUD", "HAPPY", "CAT", "DOG", "CAR", "BUTTERFLY"};
    /** List to hold all letters of the alphabet */
    private char[] allLetters;
    /** How many times the player has guessed wrong */
    private int failedAttempts = 0;
    /** The main grid pane */
    private GridPane mainGridPane;
    /** The letters that were guessed correctly */
    String[] revealed;
    /** The label to display the word so far */
    Label wordLabel;
    /** The hangman picture */
    ImageView graphic;
    /** Return value to return if player won or lost */
    boolean returnValue;
    /** The stage where everything is rendered */
    Stage stage;
    /**
     * playGame
     * __________________________
     * Sets up the game UI, and returns if the player won
     * @return boolean: true if the player won, false otherwise
     */
    public boolean playGame(){
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        this.currentWord = words[randomIndex];
        this.letters = new ArrayList<>();
        char[] charArray = currentWord.toCharArray();
        for (char i: charArray){
            letters.add(String.valueOf(i).toUpperCase());
        }
        allLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


        stage = new Stage(); //dialogue box
        mainGridPane = new GridPane();
        mainGridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#000000"),
                new CornerRadii(0),
                new Insets(0)
        )));

        RowConstraints row1 = new RowConstraints(350);
        RowConstraints row2 = new RowConstraints(100);

        ColumnConstraints col1 = new ColumnConstraints(75);
        ColumnConstraints col2 = new ColumnConstraints(50);

        mainGridPane.getRowConstraints().addAll(row1, row2, row2, row2);
        mainGridPane.getColumnConstraints().addAll(col1, col2, col2, col2, col2, col2, col2, col2, col2, col2, col2, col2, col2, col2, col1);


        graphic = new ImageView(new Image("src/images/hangman" +failedAttempts + ".png"));
        graphic.setPreserveRatio(true);
        graphic.setFitWidth(350);

        mainGridPane.add(graphic, 1, 0, 13, 1);
        GridPane.setHalignment(graphic, HPos.CENTER);

        String blanks = "";
        revealed = new String[getWordLength()];
        for (int i = 0; i < getWordLength(); i++){
            revealed[i] = "_";
            blanks += "_";
        }
        wordLabel = new Label(blanks);
        wordLabel.setAlignment(Pos.CENTER);
        wordLabel.setFont(new Font("Times New Roman", 16));
        wordLabel.setTextAlignment(TextAlignment.CENTER);
        wordLabel.setStyle("-fx-text-fill: white;");

        mainGridPane.add(wordLabel, 1, 1, 13, 1);
        GridPane.setHalignment(wordLabel, HPos.CENTER);

        for (int i = 0 ;i < 13; i++){
            Button letterButton = new Button(String.valueOf(allLetters[i]));
            letterButton.setId(String.valueOf(allLetters[i]));
            customizeButton(letterButton, 45, 40);
            makeButtonAccessible(letterButton, String.valueOf(allLetters[i]), "Guess the letter " + String.valueOf(allLetters[i]), "Click this button to guess the letter " + String.valueOf(allLetters[i]));
            letterButton.setOnAction(e -> {
                guess(letterButton.getId().charAt(0));
                letterButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
            });
            mainGridPane.add(letterButton, i+1, 2, 1, 1);
        }
        int j = 1;
        for (int i = 13 ;i < 26; i++){
            Button letterButton = new Button(String.valueOf(allLetters[i]));
            letterButton.setId(String.valueOf(allLetters[i]));
            customizeButton(letterButton, 45, 40);
            makeButtonAccessible(letterButton, String.valueOf(allLetters[i]), "Guess the letter " + String.valueOf(allLetters[i]), "Click this button to guess the letter " + String.valueOf(allLetters[i]));
            letterButton.setOnAction(e -> {
                guess(letterButton.getId().charAt(0));
                letterButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
            });
            mainGridPane.add(letterButton, j, 3, 1, 1);
            j++;
        }

        mainGridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainGridPane, 800, 720);
        stage.setScene(scene);
        stage.showAndWait();
        return returnValue;

    }
    /**
     * getWordLength
     * __________________________
     * Get the length of the selected word
     * @return int length of the selected word
     */
    public int getWordLength(){
        return currentWord.length();
    }
    /**
     * guess
     * __________________________
     * Checks if the player's guess was correct and updates the GUI accordingly
     * @param guessed the letter the player guessed
     */
   public void guess(char guessed){
        if (!currentWord.contains(String.valueOf(guessed))){
            failedAttempts += 1;
            if (failedAttempts == 7){
                stage.close();
            }else {
                graphic = new ImageView(new Image("src/images/hangman" + failedAttempts + ".png"));
                graphic.setPreserveRatio(true);
                graphic.setFitWidth(350);
                GridPane.setHalignment(graphic, HPos.CENTER);

                mainGridPane.add(graphic, 1, 0, 13, 1);
            }
        } else {
            String blanks ="";
            for (int i = 0; i < getWordLength(); i++){
                if (currentWord.charAt(i) == guessed){
                    revealed[i] = String.valueOf(currentWord.charAt(i));
                    blanks += revealed[i];
                }else{
                    blanks += revealed[i];
                }
            }
            wordLabel.setText(blanks);
            if (!blanks.contains("_")){
                returnValue = true;
                stage.close();
            }
        }
   }

    /**
     * makeButtonAccessible
     * __________________________
     * For information about ARIA standards, see
     * https://www.w3.org/WAI/standards-guidelines/aria/
     *
     * @param inputButton the button to add screenreader hooks to
     * @param name ARIA name
     * @param shortString ARIA accessible text
     * @param longString ARIA accessible help text
     */
    public static void makeButtonAccessible(Button inputButton, String name, String shortString, String longString) {
        inputButton.setAccessibleRole(AccessibleRole.BUTTON);
        inputButton.setAccessibleRoleDescription(name);
        inputButton.setAccessibleText(shortString);
        inputButton.setAccessibleHelp(longString);
        inputButton.setFocusTraversable(true);
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
        inputButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
    }
}
