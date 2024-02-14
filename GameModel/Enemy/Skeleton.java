package GameModel.Enemy;

import GameModel.Player;

/**
 * Class Skeleton.
 *__________________________
 * This is the Skeleton Enemy type.
 *
 */
public class Skeleton extends Enemy{
    /** Giant Skeleton Health */
    private static final int skeletonHealth = 20;
    /** Giant Skeleton Damage */
    private static final int skeletonDamage = 20;
    /** Skeleton Worth */
    private static final int skeletonWorth = 5;
    /** Skeleton Name */
    private static final String name = "Skeleton";
    /**
     * Skeleton Constructor
     * __________________________
     * Initializes attributes
     */
    public Skeleton(){
        super(skeletonDamage, skeletonHealth, skeletonWorth);
    }
    /**
     * getName
     * __________________________
     * Getter method for skeleton name
     * @return name: the skeleton's name
     */
    @Override
    public String getName() {
        return name;
    }
}
