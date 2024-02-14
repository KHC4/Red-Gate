package GameModel.Scores;
import java.util.HashMap;
/**
 * Class SingletonScore.
 *__________________________
 * This is the Class that uses a singleton design patter to keep track of stats.
 *
 */
public class SingletonScore {
    /** The instance of the singleton score */
    private static SingletonScore instance;
    /** The value of the statistic */
    private Double score;
    /** the name of the statistic */
    private String name;
    /** The hashmap containing all statistic names and values */
    HashMap<String, Double> statistics;
    /**
     * Class SingletonScore.
     *__________________________
     * Constructor class for SingletonScore. Initializes the attributes
     * @param score: the value of a given statistic
     * @param name: the name of a given statistic
     *
     */
    private SingletonScore(Double score, String name) {
        this.score = score;
        this.name = name;
        this.statistics = new HashMap<String, Double>();
    }

    /**
     * getInstance
     * __________________________
     * Getter Method for the instance attribute
     * @param name: the name of the statistic
     * @param score: the value of the statistic
     */
    public static SingletonScore getInstance(Double score, String name) {
        if (instance == null) {
            instance = new SingletonScore(score, name);
        }

        return instance;
    }

    /**
     * resetScore
     * __________________________
     * Reset the score of the given instance
     * @param name: the name of the statistic
     * @param score: the value of the statistic
     */
    public void resetScore(double score, String name) {
        instance.score = 0.0;  instance.name = name;
    }

    /**
     * addScore
     * __________________________
     * Add a score value to a given existing instance
     *
     * @param score: the value to add to the instance
     */
    public void addScore(double score) {
        instance.score = instance.score + score;
    }

    /**
     * getScore
     * __________________________
     * Getter Method for the score attribute
     *
     * @retun score: the value of the current instance
     */
    public double getScore() {
        return instance.score;
    }
    /**
     * getInstance
     * __________________________
     * Getter Method for the instance attribute
     * @return Hashmap<String, Double>: the statistics attribute
     */

    public HashMap<String, Double> getStats() {
        return instance.statistics;
    }

    /**
     * addStat
     * __________________________
     * Adds the score to the given statistic if it exists, otherwise creates a new stat and adds the score
     * @param stat: the name of the statistic
     * @param score: the value of the statistic
     */
    public void addStat(String stat, double score) {
        if (instance.statistics.containsKey(stat)) {
            instance.statistics.put(stat, instance.statistics.get(stat) + score);
        } else {
            instance.statistics.put(stat, score);
        }
    }


}

