package GameModel.Items;

import GameModel.Effects.Effect;

import java.util.Random;
/**
 * Class ItemFactory.
 * Creates a random factory for the main game to use while generating rooms.
 */
public class ItemFactory {

    /**
     * Potion class Constructor
     * __________________________
     * Initializes the ItemFactory
     */
    public ItemFactory(){}
    /**
     * createItem
     * __________________________
     * Creates a random item to use
     * @return Returns a random item of type Potion, Weapon, Consumable or TinCan/IronCan.
     */
    public Item createItem(){
        Random rand = new Random();
        int selected = rand.nextInt(10);
        Item item = null;

        if (selected < 2){
           String[] names = {"Fire Sword", "Knife", "Regular Sword"};
           Double[] damages = {50.0,20.0,40.0};
           String[] descriptions = {"A sword that has flames! Does 50 damage, while looking cool at the same time!"
           , "A knife, pretty short, a basic weapon that does 20 damage!"
                   , " A regular sword, does 40 damage, does good in battles!"
           };
           Random rand2 = new Random();
           int selected2 = rand.nextInt(3);
           item = new Weapon(names[selected2], descriptions[selected2], damages[selected2]);
        }else if (selected < 4){
            item = new Potion("Big Pot","A big pot that heals 30 health!", 30.0);
        }
        else if (selected < 7){
            item = new IronCan();
        }
        else if (selected < 9){
            String[] names2 = {"Speed Potion","Strength Potion"};
            Integer[] bonuses = {1,25};
            Integer roundsLeft = 3;
            String[] descriptions = {"A speed potion that increases your chances of running away from battles",
                    "A strength potion that increases your damage output by 25% for 3 rounds!"
            };

            Random rand3 = new Random();
            int selected3 = rand.nextInt(2);
            Effect returnEffect = new Effect(names2[selected3], bonuses[selected3],3);
            item = new Consumable(names2[selected3], descriptions[selected3],returnEffect);
        }
        else
        {
            item = new TinCan();
        }
        return item;
    }
}
