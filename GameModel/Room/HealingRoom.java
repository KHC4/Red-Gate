package GameModel.Room;

import GameModel.*;
import GameModel.Enemy.Enemy;
import GameModel.Items.Item;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Class AdventureGameView.
 *__________________________
 * This is the Class that represents the healing room.
 *
 */
public class HealingRoom implements Room{
    /** An attribute to store the current Room number. */
    int number;
    /** An attribute to store the Items in the Room. */
    ArrayList<Item> itemsInRoom;
    /** An attribute to store the Enemies in the Room. */
    ArrayList<Enemy> enemiesInRoom = new ArrayList<Enemy>();
    /**
     * HealingRoom Constructor
     * __________________________
     * Initializes attributes
     *
     * @param num the room number
     */
    public HealingRoom(int num) {
        this.number = num;
        this.itemsInRoom = new ArrayList<Item>();

    }

    /**
     * getRoomNum
     * __________________________
     * Getter method for Room Number
     *
     */
    @Override
    public int getRoomNum() {
        return this.number;
    }

    /**
     * getItems
     * __________________________
     * Returns a list of all items in the room.
     *
     */
    @Override
    public ArrayList<Item> getItems() {
        return this.itemsInRoom;
    }

    /**
     * addItem
     * __________________________
     * Adds an Item to the room.
     *
     */
    @Override
    public void addItem(Item object) {
        itemsInRoom.add(object);
    }

    /**
     * removeItem
     * __________________________
     * Removes an Item from the room.
     *
     */
    @Override
    public boolean removeItem(Item object) {
        if (itemsInRoom.contains(object)){
            itemsInRoom.remove(object);
            return true;
        }
        return false;
    }

    /**
     * getEnemiesInRoom
     * __________________________
     * Returns a list of all enemies in the room.
     *
     */
    public ArrayList<Enemy> getEnemiesInRoom() {
        return enemiesInRoom;
    }
}
