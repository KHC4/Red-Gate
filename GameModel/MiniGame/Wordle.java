package GameModel.MiniGame;

import java.util.*;
/**
 * Class Wordle.
 *__________________________
 * This is the Class that contains the wordle mini-game.
 *
 */
public class Wordle implements MiniGame{
    /** List of words */
    private static String[] listOfWords = {
            "Beach", "Jelly", "Toast", "Laugh", "Glint", "Grape", "Forge", "Slide", "Arrow", "Prize",
            "Maple", "Pilot", "Ocean", "Plank", "Horse", "Flame", "Water", "Bliss", "Tiger", "Chair",
            "Blaze", "Mirth", "South", "Crown", "Quiet", "Lemon", "Stone", "Earth", "Money", "Pearl",
            "Coral", "Sauna", "Grown", "Pixel", "Rhyme", "Scent", "Blend", "Sweep", "Watch", "Giant",
            "Amber", "Forge", "Crane", "Blaze", "Giant", "Angel", "Flock", "Cider", "Swarm", "Snail",
            "Chair", "Forge", "Pearl", "Stone", "Joust", "Pilot", "Toast", "Bliss", "Quiet", "Sauna",
            "Amber", "Flock", "Horse", "Water", "Plank", "Tiger", "Bliss", "Slide", "Earth", "Prize",
            "Flame", "Jelly", "Maple", "South", "Blaze", "Rhyme", "Chair", "Mirth", "Angel", "Lemon",
            "Money", "Ocean", "Tiger", "Prize", "Blaze", "Slide", "Giant", "Chair", "Bliss", "Earth",
            "Crown", "Pearl", "Horse", "Tiger", "Flame", "Bliss", "Chair", "Earth", "Blaze", "Prize"
    };
    /** Selected Word */
    private String currentWord;
    /** The current number of tries */
    private int tries;
    /** Check if player got the word correct */
    private boolean gotTheWordCorrect;
    /** Hashmap storing if letters are in the word but in the wrong position */
    private HashMap<String, ArrayList<Integer>> rightLetterWrongPosition;
    /** Hashmap storing if letters are in the word and right position */
    private HashMap<String, ArrayList<Integer>> rightLetterRightPosition;
    /**
     * Wordle Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public Wordle(){
        Random random = new Random();
        int randomIndex = random.nextInt(listOfWords.length);

        // Retrieve the random word
        this.currentWord = listOfWords[randomIndex];
        this.tries = 0;
        this.gotTheWordCorrect = true;
        this.rightLetterWrongPosition = new HashMap<>();
        this.rightLetterRightPosition = new HashMap<>();
    }

    // Modify the rightLetterRightPosition and rightLetterWrongPosition attributes.
    // If the letter is right but in the wrong position, then the righLetterWrongPosition will contain the letter as
    // key and the indexes of the letter in the userInput in list as the value.
    // If the letter is right and in the right position, then the righLetterRightPosition will contain the letter as
    // key and the indexes of the letter in the userInput in list as the value.
    public void checkWord(String userInput){
        char[] charArrayCurrWord = splitWordToList(currentWord.toUpperCase());
        char[] charArrayUserInput= splitWordToList(userInput.toUpperCase());

        int idx = 0;
        for (char i: charArrayUserInput){
            if (String.valueOf(i) == String.valueOf(charArrayCurrWord[idx])){
                if (rightLetterRightPosition.containsKey(String.valueOf(i))){
                    rightLetterRightPosition.get(String.valueOf(i)).add(idx);
                } else{
                    ArrayList<Integer> tempIdx = new ArrayList<>();
                    tempIdx.add(idx);
                    rightLetterRightPosition.put(String.valueOf(i), tempIdx);
                }
            } else {
                for (char j: charArrayCurrWord){
                    if (j == i){
                        if (rightLetterWrongPosition.containsKey(String.valueOf(i))){
                            rightLetterWrongPosition.get(String.valueOf(i)).add(idx);
                        } else{
                            ArrayList<Integer> tempIdx = new ArrayList<>();
                            tempIdx.add(idx);
                            rightLetterWrongPosition.put(String.valueOf(i), tempIdx);
                        }
                    }
                } gotTheWordCorrect = false;
            } idx += 1;
        }
        tries += 1;
    }

    // Returns true if player wins. Otherwise, returns false.
    public boolean checkIfWin(){
        if (tries < 5){
            if (gotTheWordCorrect){
                return true;
            }
        } return false;
    }
    public char[] splitWordToList(String userInput) {
        char[] charArray = userInput.toCharArray();
        return charArray;
    }

    public HashMap<String, ArrayList<Integer>> getRightLetterWrongPosition(){
        return rightLetterWrongPosition;
    }

    public HashMap<String, ArrayList<Integer>> getRightLetterRightPosition(){
        return rightLetterRightPosition;
    }

}
