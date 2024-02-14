package GameModel.Enemy;

import GameModel.Player;
/**
 * Class Giant.
 *__________________________
 * This is the Giant Enemy type.
 *
 */
public class Giant extends Enemy {
    /** Giant Health */
    private static final int giantHealth = 100;
    /** Giant Damage */
    private static final int giantDamage = 10;
    /** Giant Worth */
    private static final int giantWorth = 20;
    /** Giant Name */
    private static final String name = "Giant";
    /**
     * Giant Constructor
     * __________________________
     * Initializes attributes
     */
    public Giant(){
        super(giantDamage, giantHealth, giantWorth);
    }
    /**
     * getName
     * __________________________
     * Getter method for giant name
     * @return name: the giant's name
     */
    @Override
    public String getName() {
        return name;
    }

}
