package GameModel.Enemy;
import GameModel.*;

import GameModel.MiniGame.*;
import jdk.jshell.spi.ExecutionControl;
import java.util.Random;

import java.util.ArrayList;

import static java.lang.Math.max;

/**
 * Class Enemy.
 *__________________________
 * This is the Class that the enemies are based on.
 *
 */
public class Enemy {
    /** Enemy Damage */
    private int damage;
    /** List of Minigames the enemy plays */
    private ArrayList<MiniGame> listGames;
    /** Enemy Health */
    private int health;
    /** Enemy Worth */
    private int worth;
    /** Enemy Name */
    private String name;
    /**
     * Enemy Constructor
     * __________________________
     * Initializes attributes
     * @param health enemy health
     * @param damage enemy damage
     * @param worth enemy worth
     */
    public Enemy(int damage, int health, int worth) {
        this.damage = damage;
//        this.listGames.add(new MemoryGame());
//        this.listGames.add(new TicTacToe());
//        this.listGames.add(new Wordle());
//        this.listGames.add(new HangMan());
//        this.listGames.add(new GuessTheRiddle());
        this.health = health;
        this. worth = worth;

    }
    /**
     * attackPlayer
     * __________________________
     * attack the player
     */
    private boolean attackPlayer() {
        Random random = new Random();
//        return this.listGames.get(random.nextInt(this.listGames.size())).playGame();
        return true;
    }
    /**
     * getAttacked
     * __________________________
     * Post damage on Enemy(triggered by Player).
     *
     * @param damageByPlayer: the damage Enemy will receive.
     * @return true if enemy died, false if enemy is still alive
     */
    public boolean getAttacked(double damageByPlayer){
        this.health -= damageByPlayer;
        return this.health > 0;
    }
    /**
     * getDamage
     * __________________________
     * Getter method for enemy damage
     *
     * @return damage: the damage dealt by the enemy
     */
    public int getDamage() {
        return damage;
    }
    /**
     * getHealth
     * __________________________
     * Getter method for enemy health.
     *
     * @return health: the enemy's health
     */
    public int getHealth() {
        return health;
    }
    /**
     * getWorth
     * __________________________
     * Getter method for enemy worth
     *
     * @return worth: the amount of points the enemy is worth
     */
    public int getWorth(){ return worth; }
    /**
     * getName
     * __________________________
     * Getter method for enemy Name
     *
     * @return name: the name of the enemy
     */
    public String getName() {
        return name;
    }
}
