package GameModel.Items;


/**
 * Class Weapon.
 * Implements the abstract Item class for a type of item that can be used as a weapon against other enemies.
 */

public class Weapon extends Item{
    /** The damage double of this weapon */
    double damageBonus = 0; //how much extra damage we do
    /** The chance of this damage bonus being applied(deprecated feature) */
    double percentBonus = 0; //chance of the effect being applied

    /**
     * Weapon class Constructor
     * __________________________
     * Initializes attributes
     * @param name The name of the item
     * @param damageBonus The damage bonus of the item
     * @param item_description The description of the item
     */
    public Weapon(String name, String item_description, double damageBonus) {
        super(name, item_description);
        this.damageBonus = damageBonus;

    }
    /**
     * use
     * __________________________
     * Uses the weapon
     * @return Returns a boolean representing the damage of this weapon.
     */
    @Override
    public double use() {
        return this.damageBonus;
    }

}
