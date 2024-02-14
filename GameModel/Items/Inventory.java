package GameModel.Items;
import GameModel.Player;



/**
 * Class Inventory.
 * Manages the usage and modifications of items in the player's inventory alongside equipped items
 */



public class Inventory {
//    Item armour = null;
    /** The current equipped item*/
    Item weapon = null;
    /** The width of the inventory */
    int width = 4;
    /** The height of the inventory */
    int length = 3;
    /** The current player with this inventory */
    Player player;
    /** An array representing the player's inventory in association with the GUI*/
    Item[][] inventory = new Item[width][length];

    /**
     * Inventory class Constructor
     * __________________________
     * Initializes attributes
     * @param player The player associated with this inventory
     */


    public Inventory(Player player)
    {
        this.player= player;
    }



    /**
     * useItem
     * __________________________
     * Uses the item in the inventory specified at coordinates x, y
     * @param x The x coordinate of the item in the inventory
     * @param y the y coordinate of the item in the inventory
     * @return Returns a boolean representing if this item was successfully used
     */
    public boolean useItem(int x, int y)
    {
        try
        {
            Item selectedItem = this.inventory[x][y];
            if (selectedItem instanceof Weapon)
            {
                Item currentWeapon = this.weapon;
                this.weapon = selectedItem;
                this.inventory[x][y] = currentWeapon;
            }
            if (selectedItem instanceof Potion)
            {
                this.player.addHealth((int)selectedItem.use());
                this.inventory[x][y] = null;
            }

            if (selectedItem instanceof Consumable)
            {
                player.addEffect(((Consumable) selectedItem).getEffect());
                this.inventory[x][y] = null;
            }
            else{selectedItem.use(); return true;}
        }
        catch (Exception e){return false;}
        return false;
    }

    /**
     * removeItem
     * __________________________
     * Removes the item and returns it for the upper player class to put it in the room
     * @param x The x coordinate of the item in the inventory
     * @param y the y coordinate of the item in the inventory
     * @return Returns the item removed
     */
    public Item removeItem(int x, int y){
        try {
            Item tempItem = this.inventory[x][y];
            this.inventory[x][y] = null;
            return tempItem;

        }
        catch (Exception e){return null;}
    }

    /**
     * removeWeapon
     * __________________________
     * Removes the currently equipped weapon
     * @return Returns the Weapon removed
     */
    public Item removeWeapon(){Item tempItem = this.weapon; this.weapon = null; return tempItem;}
    /**
     * addItem
     * __________________________
     * Add the item to any free space in the inventory, return true if there is space and placed
     *
     * @return Returns true if the item is placed, false otherwise
     */
    public boolean addItem(Item item)
    {
        for (int i = 0; i < width; i++) {

            for (int j = 0; j < length; j++) {
                if (this.inventory[i][j]==null)
                    {
                        this.inventory[i][j] = item;
                        return true;
                    }

            }
        }
        return false;
    }
    /**
     * getWeapon
     * __________________________
     * Getter Method for currently equipped weapon attribute
     *
     * @return Item: Returns the currently equipped weapon
     */
    public Item getWeapon() {
        return weapon;
    }

    /**
     * getItem
     * __________________________
     * Gets the itme at position (i, j)
     * @param i The x coordinate of the item in the inventory
     * @param j the y coordinate of the item in the inventory
     * @return Returns the item removed
     */
    public Item getItem(int i, int j){
        Item item = null;
        try{
            item = inventory[i][j];
            return item;
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }
    /**
     * getWeapon
     * __________________________
     * Getter Method for inventory itself
     *
     * @return Item: Returns the inventory instance
     */
    public Item[][] getInventory() {
        return inventory;
    }
}
