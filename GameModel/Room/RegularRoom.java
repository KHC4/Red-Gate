package GameModel.Room;


import GameModel.*;
import GameModel.Enemy.Enemy;
import GameModel.Enemy.EnemyFactory;
import GameModel.Items.Item;

import java.util.ArrayList;

/**
 * Class RegularRoom.
 *__________________________
 * This is the Class that represents a regular room.
 *
 */
public class RegularRoom implements MonsterRoom{
    /** An attribute to store the current Room number. */
    int number;
    /** An attribute to store the Items in the Room. */
    ArrayList<Item> itemsInRoom;
    /** An attribute to store the Enemies in the Room. */
    ArrayList<Enemy> enemiesInRoom;

    /**
     * RegularRoom Constructor
     * __________________________
     * Initializes attributes
     *
     * @param num the room number
     */
    public RegularRoom(int num) {
        this.number = num;
        this.enemiesInRoom = new ArrayList<Enemy>();
        this.itemsInRoom = new ArrayList<Item>();

        EnemyFactory enemyFactory = new EnemyFactory();
        enemiesInRoom.add(enemyFactory.createEnemy());
    }

    /**
     * getRoomNum
     * __________________________
     * Getter method for Room Number
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
     * addMonster
     * __________________________
     * Adds a Monster to the room.
     *
     */
    @Override
    public void addMonster(Enemy monster) {
        enemiesInRoom.add(monster);
    }

    /**
     * removeMonster
     * __________________________
     * Removes a Monster from the room.
     *
     */
    @Override
    public void removeMonster(Enemy monster) {
        enemiesInRoom.remove(monster);
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
