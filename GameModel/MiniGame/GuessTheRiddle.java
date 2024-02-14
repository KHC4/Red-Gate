package GameModel.MiniGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GuessTheRiddle implements MiniGame{
    /** List of riddles */
    private String[] riddles = {"I am taken from a mine, and shut up in a wooden case, from which I am " +
            "never released, and yet I am used by almost every person. What am I?", "What has keys but can't open " +
            "locks?", "I can fly without wings. I can cry without eyes. Wherever I go, darkness follows me. What am I?",
            "What has a head, a tail, is brown, and has no legs?" ,"What comes once in a minute, twice in a moment, but " +
            "never in a thousand years?"};
    /** List of answers */
    private String[] answerToRiddles = {"d", "e", "a", "b", "a"};
    /** List of options for answer for each riddle */
    private HashMap<Integer, ArrayList<String>> choicesForEachRiddle;
    /** Selected Riddle index */
    private int currentRiddleIndex;
    /**
     * GuessTheRiddle Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public GuessTheRiddle(){
        Random random = new Random();
        this.currentRiddleIndex = random.nextInt(riddles.length);
            ArrayList<String> temp = new ArrayList<>();
            if (currentRiddleIndex==0){
                temp.add("Gold");
                temp.add("Silver");
                temp.add("Diamond");
                temp.add("Pencil lead"); // Correct Answer
                temp.add("Coal");
            } else if (currentRiddleIndex==1) {
                temp.add("Piano");
                temp.add("Treasure chest");
                temp.add("Computer");
                temp.add("Calendar");
                temp.add("Typewriter"); // Correct Answer
            } else if (currentRiddleIndex == 2) {
                temp.add("Moon"); // Correct Answer
                temp.add("Bat");
                temp.add("Cloud");
                temp.add("Wind");
                temp.add("Night");
            } else if (currentRiddleIndex == 3){
                temp.add("Snake");
                temp.add("Penny");    // Correct Answer
                temp.add("Stick");
                temp.add("Horse");
                temp.add("Elephant");
            } else if (currentRiddleIndex==4){
                temp.add("The letter 'M'"); // Correct Answer
                temp.add("The number '1'");
                temp.add("The letter 'E'");
                temp.add("The letter 'O'");
                temp.add("The number '2'");
            } choicesForEachRiddle.put(currentRiddleIndex, temp);
    }

    /** This method will return true if user chooses the letter with correct answer from the multiple choice.
     * Otherwise, it will return false.
     * @param choiceLetter the letter from multiple choice, e.g. "a", "b", "c", "d", "e"
     * @return boolean
     */
    public boolean checkIfWin(String choiceLetter){
        if (choiceLetter.toUpperCase().equals(answerToRiddles[currentRiddleIndex].toUpperCase())){
            return true;
        } else {
            return false;
        }
    }

    /** Getter method for the current riddle.
     */
    public String getCurrentRiddle(){
        return riddles[currentRiddleIndex];
    }

    /** Getter method for the choices to be given with the current riddle.
     */
    public ArrayList<String> getChoicesForCurrentRiddle(){
        return choicesForEachRiddle.get(currentRiddleIndex);
    }

}
