package GameModel;

import GameModel.Effects.Effect;
import GameModel.Items.Consumable;
import GameModel.Items.Item;
import GameModel.Items.Potion;
import GameModel.Items.Weapon;

public class Shop {
    public Shop() {}

    /**
     * getPrice
     * __________________________
     * Return the price of item, according to input itemName
     * @param itemName the name of Item player want to purchase.
     * @return Returns requested item price.
     */
    public int getPrice(String itemName) {
        return switch (itemName) {
            case "Knife" -> 100;
            case "Regular Sword" -> 200;
            case "Fire Sword" -> 300;
            case "Potion" -> 250;
            case "SpeedEffect" -> 150;
            case "StrengthEffect" -> 350;
            default -> 0;
        };
    }

    /**
     * getItem
     * __________________________
     * Return an Item from shop, according to input itemName
     * @param itemName the name of Item player want to purchase.
     * @return Returns purchased Item.
     */
    public Item getItem(String itemName) {
        return switch (itemName) {
            case "Knife" -> new Weapon("Knife", "Knife", 20.0);
            case "Regular Sword" -> new Weapon("Regular Sword", "Regular Sword", 40.0);
            case "Fire Sword" -> new Weapon("Fire Sword", "Fire Sword", 50.0);
            case "Potion" -> new Potion("Potion", "Potion", 30.0);
            //case "SpeedEffect" -> new Consumable("Speed", "SpeedEffect", new SpeedEffect(10,3));
            //case "StrengthEffect" -> new Consumable("Strength", "StrengthEffect", new DamageEffect(10,3));
            default -> null;
        };
    }
}
