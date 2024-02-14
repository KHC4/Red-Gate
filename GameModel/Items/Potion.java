package GameModel.Items;

import GameModel.Player;
/**
 * Class Potion.
 * Implements the abstract Item class for a big pot item that adds health of 30 to the player.
 */
public class Potion extends Item {
    /** The health double bonus of this potion */
    double healthBoost = 0.0;

    /**
     * Potion class Constructor
     * __________________________
     * Initializes attributes
     * @param name The name of the item
     * @param healthBoost The health boost bonus of the item
     * @param item_description The description of the item
     */
    public Potion(String name, String item_description, Double healthBoost) {
        super(name, item_description); this.healthBoost = healthBoost;
    }
    /**
     * use
     * __________________________
     * Uses the weapon
     * @return Returns a boolean representing the health boost of this potion.
     */
    public double use() {
        return this.healthBoost;
    }

}
