package GameModel.Items;

import GameModel.Effects.Effect;

/**
 * Class Consumable.
 * Implements the abstract Item class for a type of item that applies a perk/status effect on the player.
 */

public class Consumable extends Item {

    /** A class of effect associated with this item */
    Effect effect;
    /**
     * Consumable class Constructor
     * __________________________
     * Initializes attributes
     * @param name The name of the item
     * @param item_description The description of the item
     * @param effect The effect of the item
     */
    public Consumable(String name, String item_description, Effect effect) {
        super(name,item_description);
        this.effect = effect;
    }

    /**
     * use
     * __________________________
     * Uses the item
     * @return Returns a double of 0 if the effect is over, otherwise, return 1
     */
    public double use() {
        if (this.effect.useRound()){return 1.0;}else {return 0.0;}
    }
    /**
     * getEffect
     * __________________________
     * Getter for the current item effect
     * @return Returns the item effect
     */
    public Effect getEffect(){return this.effect;}

}
