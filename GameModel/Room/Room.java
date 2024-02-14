package GameModel.Room;


import GameModel.*;
import GameModel.Enemy.Enemy;
import GameModel.Items.Item;

import java.util.ArrayList;
/**
 * Interface Room.
 *__________________________
 * This is the Interface dictates the behaviour of room objects.
 *
 */

public interface Room {

    /**
     * getRoomNum
     * __________________________
     * Getter method for Room Number
     */
    abstract int getRoomNum();

    /**
     * addItem
     * __________________________
     * Adds an Item to the room.
     *
     */
    abstract void addItem(Item object);

    /**
     * removeItem
     * __________________________
     * Removes an Item from the room.
     *
     */
    abstract boolean removeItem(Item object);

    /**
     * getItems
     * __________________________
     * Returns a list of all items in the room.
     *
     */
    abstract ArrayList<Item> getItems();

    /**
     * getEnemiesInRoom
     * __________________________
     * Returns a list of all enemies in the room.
     *
     */
    abstract ArrayList<Enemy> getEnemiesInRoom();
}