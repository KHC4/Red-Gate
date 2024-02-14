package GameModel.Enemy;

import GameModel.Player;

/**
 * Class Zombie.
 *__________________________
 * This is the Zombie enemy type.
 *
 */
public class Zombie extends Enemy{
    /** Zombie Health */
    private static final int zombieHealth = 60;
    /** Zombie Damage */
    private static final int zombieDamage = 15;
    /** Zombie Worth */
    private static final int zombieWorth = 13;
    /** Zombie Name */
    private static final String name = "Zombie";
    /**
     * Skeleton Constructor
     * __________________________
     * Initializes attributes
     */
    public Zombie(){
        super(zombieDamage, zombieHealth, zombieWorth);
    }
    /**
     * getName
     * __________________________
     * Getter method for Zombie name
     * @return name: the zombie's name
     */
    @Override
    public String getName() {
        return name;
    }
}

