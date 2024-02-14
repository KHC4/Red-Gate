package GameModel;

import java.io.*;
import java.util.*;

import GameModel.Enemy.Enemy;
import GameModel.Room.*;
import GameModel.Scores.ScoreBoard;
import GameModel.Scores.SingletonScore;
import jdk.jshell.spi.ExecutionControl;

/**
 * Class AdventureGame.  Handles all the necessary tasks to run the Adventure game.
 */
public class AdventureGame implements Serializable {

    /** An attribute to store the current room of the game. */
    private Room currentRoom;
    /** //An attribute to store the next room of the game. */
    private Room nextRoom;
    /** The Player of the game. */
    public Player player;
    /** A singleton design pattern to store the score and statistics */
    public SingletonScore stats;
    /** MaryTTS instance */
    public TTSClass tts = new TTSClass();
    /** A boolean to check if the game is over */
    public boolean gameEnded = false;

    /**
     * Adventure Game Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public AdventureGame() {
        this.currentRoom = new RegularRoom(1);
        RoomFactory roomGen = new RoomFactory();
        this.nextRoom = roomGen.createRoom(2);
        this.player = new Player(this.currentRoom, this);
        this.stats = SingletonScore.getInstance(0.0,this.player.getName());
    }

    /**
     * movePlayer
     * __________________________
     * Move the Player to the next room.
     */
    public void movePlayer(boolean ttstoggle){
        if (this.nextRoom instanceof HealingRoom){
            player.setHealth(Math.min(player.getHealth() + 5, player.getMaxHealth()));
            checktts("You have entered a healing room and gained 5 health", ttstoggle);
        }else {
            checktts("You have entered a Regular Room, beware of enemies", ttstoggle);
        }
        player.setCurrentRoom(nextRoom);
        currentRoom = nextRoom;
        RoomFactory roomGen = new RoomFactory();
        this.nextRoom = roomGen.createRoom(currentRoom.getRoomNum()+1);
    }

    /**
     * fleeFromEnemy
     * __________________________
     * Called if the player successfully runs away from the enemy
     * Removes enemy from room
     * Does not add give the player points
     */
    public void fleeFromEnemy(){
        currentRoom.getEnemiesInRoom().remove(0);
    }


    /**
     * playerAttack
     * __________________________
     * Attacks the enemy dealing damage according to the player's weapon.
     * @return Returns boolean according to if the enemy is still alive
     */
    public boolean playerAttack(boolean ttstoggle){
        Double playerDamage = player.attack();
        stats.addStat("Damage Dealt", playerDamage);
        Enemy enemy = currentRoom.getEnemiesInRoom().get(0);
        if (enemy.getAttacked(playerDamage)){
            checktts("You hit the enemy for " + playerDamage + " damage", ttstoggle);
            return true;
        }else{
            defeatEnemy(enemy, ttstoggle);
            stats.addStat("Enemies Defeated", 1);
            return false;
        }
    }

    /**
     * defeatEnemy
     * __________________________
     * Called when the enemy dies
     * Removes the Enemy from the room and adds the corresponding points to player's score
     */
    public void defeatEnemy(Enemy enemy, boolean ttstoggle){
        player.addPoints(enemy.getWorth());
        checktts("You have defeated the enemy and gained " + enemy.getWorth() + " points", ttstoggle);
        currentRoom.getEnemiesInRoom().remove(0);
    }


    /**
     * endGame
     * __________________________
     * Called when the player  dies
     * Updates the scoreboard based on play through
     */
    public void endGame(boolean ttstoggle){

        checktts("You have died fighting the enemy", ttstoggle);
        gameEnded = true;
        SingletonScore scores = SingletonScore.getInstance(0.0,this.player.getName());
        //at this point the singleton is initialized
        scores.addScore(this.player.getPoints());
        ScoreBoard scoresBoard = new ScoreBoard(this.player.getName());
        scoresBoard.updateScoreBoard();

    }
    /**
     * getCurrentRoom
     * __________________________
     * Getter method for currentRoom attribute
     * @return Returns currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * getNextRoom
     * __________________________
     * Getter method for nextRoom attribute
     * @return Returns nextRoom
     */
    public Room getNextRoom(){return nextRoom;}

    private void checktts(String text, boolean ttstoggle){
        if (ttstoggle){
            tts.say(text);
        }
    }
}
