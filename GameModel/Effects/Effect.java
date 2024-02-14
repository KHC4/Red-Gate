package GameModel.Effects;

import GameModel.Player;

/**
 * Class Effect.
 *__________________________
 * This is the Class that handles all effects on players.
 *
 */
public class Effect {
    /** player reference */
    private Player player;
    /** Name of the effect */
    private String name;
    /** bonus stats given by the effect */
    private Integer bonus;
    /** Rounds left until the effect runs out */
    private Integer roundsLeft;

    /**
     * Effect class Constructor
     * __________________________
     * Initializes attributes
     * @param name The name of this Effect
     * @param bonus How much can player gain from this Effect.
     * @param roundsLeft Number of rounds left for this Effect.
     */
    public Effect(String name, Integer bonus, Integer roundsLeft) {
        this.name = name; this.bonus = bonus;  this.roundsLeft = roundsLeft;
    }

    /**
     * Use one round of this effect.
     *
     * @return true if this effect is still available; false otherwise.
     */
    public boolean useRound(){this.roundsLeft = this.roundsLeft-1; return (roundsLeft>0);}

    /**
     * Get the string representation of this effect, including name and number of rounds left.
     *
     * @return String representation of this effect.
     */
    public String getInfo(){
        String info = name + ": " + roundsLeft.toString();
        return info;
    }

    /**
     * getName
     * __________________________
     * Getter of effect name
     *
     * @return Effect name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * getRoundsLeft
     * __________________________
     * Getter of number of rounds left
     *
     * @return Number of rounds left
     */
    public Integer getRoundsLeft() {
        return roundsLeft;
    }
}
