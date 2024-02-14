package GameModel.Scores;

import GameModel.Player;

import java.util.ArrayList;
/**
 * Class ScoreList.
 *__________________________
 * This is the Class that organizes the scores in the leaderboard.
 *
 */
public class ScoreList {
    /** List of player scores */
    ArrayList<Double> scores;
    /** List of player names */
    ArrayList<String> names;
    public ScoreList(ArrayList<Double> scores, ArrayList<String> names) {
        this.scores = scores;
        this.names = names;
    }

    /**
     * getScores
     * __________________________
     * Getter method for scores attribute
     *
     * @return ArrayList<Double> the list of player scores
     */
    public ArrayList<Double> getScores(){return this.scores;}
    /**
     * getNames
     * __________________________
     * Getter method for names attribute
     *
     * @return ArrayList<String> the list of player names
     */
    public ArrayList<String> getNames(){return this.names;}


}
