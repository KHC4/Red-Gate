package GameModel;

import GameModel.Effects.Effect;
import GameModel.Items.Weapon;
import GameModel.Enemy.Enemy;
import GameModel.Room.Room;
import GameModel.Items.Item;
import GameModel.Items.Inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class Player implements Serializable {
    /** Player's current health */
    private int health;
    /** Player's max health */
    private int maxHealth = 100;
    /** Player's current speed */
    private int speed = 10;
    /** Player's current damage */
    private int damage = 15;
    /** Player's current points */
    private int points;
    /** Player's name */
    private String name;
    /** Player's current effects */
    private ArrayList<Effect> currentEffects;

    /**
     * The current room that the player is located in.
     */
    private Room currentRoom;

    /** Game reference */
    private AdventureGame gameReference;
    /** Player's inventory */
    public Inventory inventory;

    /**
     * Adventure Game Player Constructor
     */
    public Player(Room currentRoom, AdventureGame gameReference) {
        this.gameReference = gameReference;
        this.inventory = new Inventory(this);
        this.currentRoom = currentRoom;
        this.health = maxHealth;
        this.points = 0;
        this.currentEffects = new ArrayList<Effect>();
    }

    /**
     * Attack Enemy.
     * __________________________
     * If encountered Enemy died, move player to next room.
     *
     * @return damage
     */
    public double attack(){
        double returnDamage = damage;
        if (inventory.getWeapon() != null){
            returnDamage = returnDamage + this.inventory.getWeapon().use();
        }
        for (int i=0; i< this.currentEffects.size(); i++)
        {
            this.currentEffects.get(i).useRound();
            if (this.currentEffects.get(i).getName()=="Strength Potion")
            {
                returnDamage = returnDamage + returnDamage *0.25;
            }
            if (this.currentEffects.get(i).getRoundsLeft() == 0){
                this.currentEffects.remove(i);
            }
        }
        return returnDamage;
    }

    /**
     * addEffect
     * __________________________
     * This method adds an Effect into currentEffects,
     * an array list that store the effects to be used.
     *
     * @param effect
     */
    public void addEffect(Effect effect){
        this.currentEffects.add(effect);
    }

    /**
     * setCurrentRoom
     * __________________________
     * Setter method for the current room attribute.
     *
     * @param currentRoom The location of the player in the game.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * addToInventory
     * __________________________
     * This method adds an object to the inventory of the player.
     *
     * @param object Prop or object to be added to the inventory.
     */
    public void addToInventory(Item object) {
        this.inventory.addItem(object);
    }

    /**
     * getStats
     * __________________________
     * Print current Player statistics.
     *
     * @return A String representation of current user statistics.
     */
    public String getStats() {
        String stats = "Health: " + health + " / " + maxHealth + "\n";
        stats += "Points: " + points + "\n";
        if (inventory.getWeapon() == null){
            stats += "Current Weapon: None \n";
        }else{
            stats += "Current Weapon: " + inventory.getWeapon().getName() + "\n";
        }
        stats += "Current Effects:\n";
        for (int i = 0; i < currentEffects.size(); i++){
            stats += currentEffects.get(i).getInfo() + "\n";
        }
        return stats;
    }

    /**
     * getCurrentRoom
     * __________________________
     * Getter method for the current room attribute.
     *
     * @return current room of the player.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * addPoints
     * __________________________
     * Add points to Player.
     * @param num Points to be added.
     */
    public void addPoints(int num) {
        this.points += num;
    }

    /**
     * flee
     * __________________________
     * Decides if the player is successful in running away from the enemy.
     * @return Returns boolean according to if the player is still alive
     */
    public boolean flee() {
        int bonusSpeed = 0;
        for (int i=0; i< this.currentEffects.size(); i++)
        {
            this.currentEffects.get(i).useRound();
            if (this.currentEffects.get(i).getName()=="Speed Potion"){bonusSpeed = 5;}
        }
        Random rand  = new Random();
        int required = rand.nextInt(20);

        if (required < speed+bonusSpeed){
            return true;
        }else{
            return false;
        }
    }

    /**
     * getHealth
     * __________________________
     * Getter method for the health attribute.
     *
     * @return current player's health.
     */
    public int getHealth() { return this.health; }
    /**
     * addHealth
     * __________________________
     * Method to add health to the player
     *
     * @param healthAdded health to be added.
     */
    public void addHealth(int healthAdded) { int tempHealth = this.health + healthAdded ; this.health = Math.min(maxHealth, tempHealth); }
    /**
     * setMaxHealth
     * __________________________
     * Setter method for the max Health attribute.
     *
     * @param newMaxHealth new value for max health.
     */
    public void setMaxHealth(int newMaxHealth){this.maxHealth = newMaxHealth;}
    /**
     * getCurrentRoom
     * __________________________
     * Getter method for the current room attribute.
     *
     * @return current player's max health.
     */
    public int getMaxHealth(){return maxHealth;}
    /**
     * setHealth
     * __________________________
     * Setter method for the health attribute.
     *
     * @param health the health to be set.
     */
    public void setHealth(int health){this.health = health;}
    /**
     * takeDamage
     * __________________________
     * Method to decrease player's health based on damage taken
     *
     * @param damage the amount of damage the player takes
     */
    public void takeDamage(int damage) { this.health = this.health - damage; }
    /**
     * setName
     * __________________________
     * Setter method for the name attribute.
     *
     * @param name the player name.
     */
    public void setName(String name){this.name = name;}
    /**
     * getCurrentRoom
     * __________________________
     * Getter method for the current room attribute.
     *
     * @return the player's name.
     */
    public String getName(){return this.name;}
    /**
     * getCurrentRoom
     * __________________________
     * Getter method for the inventory attribute.
     *
     * @return the player's inventory.
     */
    public Inventory getInventory(){return this.inventory;}
    /**
     * getCurrentRoom
     * __________________________
     * Getter method for the points attribute.
     *
     * @return current player points.
     */
    public int getPoints(){return this.points;}
    /**
     * setPoints
     * __________________________
     * Setter method for the points attribute.
     * @param points the new value of player points.
     */
    public void setPoints(int points){this.points = points;}
    /**
     * isAlive
     * __________________________
     * Method to check if the player is alive
     *
     * @return true if player is alive
     */
    public boolean isAlive(){return this.health>0;}
}
