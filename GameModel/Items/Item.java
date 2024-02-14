package GameModel.Items;

/**
 * Class Item.
 * Represents an abstract item class to be used inside the inventory for the game loop.
 */
public abstract class Item {
    /** A string representation of the name of the item being used */
    private String item_name = "";
    /** A string description of the name of the item being used */
    private String item_description = "";

    /**
     * Item class Constructor
     * __________________________
     * Initializes attributes
     * @param name The name of the item
     * @param item_description The description of the item
     */
    public Item(String name, String item_description){this.item_name = name;this.item_description = item_description;}
    /**
     * use
     * __________________________
     * Uses the item
     * @return Returns a boolean representing a characteristic about the use function.
     */

    public abstract double use();

    /**
     * getName
     * __________________________
     * Returns the name of the item
     * @return Returns the current name of the item
     */
    public String getName() {return this.item_name;}
    /**
     * getName
     * __________________________
     * Returns the description of the item
     * @return Returns the current description
     */
    public String getDescription() {return this.item_description;}
    /**
     * getImagePath
     * __________________________
     * Returns the path to the item image
     * @return Returns the current path to the item image
     */
    public String getImagePath(){
        String imagePath = "src/images/" + item_name + ".png";
        return imagePath;
    }
}
