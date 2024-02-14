package GameModel.MiniGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class TicTacToe.
 *__________________________
 * This is the Class that contains the TicTacToe mini-game.
 *
 */
public class TicTacToe implements MiniGame{
    // currentBoard will have coordinate [x,y] as its keys and the player as the value.
    // "X" represents player X
    // "O" represents player O
    // "N" represents that none of the players are in that specific coordinate
    /** Hashmap storing current board information */
    HashMap<int[], String> currentBoard = new HashMap<>();
    /** List storing the board coordinates */
    ArrayList<int []> coordinates = new ArrayList<>();
    /** Player reference */
    String currentPlayer;
    /**
     * TicTacToe Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public TicTacToe(){
        currentBoard.put(new int[]{0, 0}, "N");
        currentBoard.put(new int[]{0, 1}, "N");
        currentBoard.put(new int[]{0, 2}, "N");
        currentBoard.put(new int[]{1, 0}, "N");
        currentBoard.put(new int[]{1, 1}, "N");
        currentBoard.put(new int[]{1, 2}, "N");
        currentBoard.put(new int[]{2, 0}, "N");
        currentBoard.put(new int[]{2, 1}, "N");
        currentBoard.put(new int[]{2, 2}, "N");
        coordinates.add(new int[]{0, 0});
        coordinates.add(new int[]{0,1});
        coordinates.add(new int[]{0, 2});
        coordinates.add(new int[]{1, 0});
        coordinates.add(new int[]{1, 1});
        coordinates.add(new int[]{1, 2});
        coordinates.add(new int[]{2, 0});
        coordinates.add(new int[]{2, 1});
        coordinates.add(new int[]{2, 2});
        this.currentPlayer = "X";
    }

    /**
     * checkIfCoordinateOccupied
     * __________________________
     * Checks if the specified coordinate is occupied
     * @param coordinate: the coordinate to check
     * @return boolean: Returns true if coordinate is occupied. Otherwise, returns false.
     */
    public boolean checksIfCoordinateOccupied(int[] coordinate){
        return !currentBoard.get(coordinate).equals("N");
    }

    public HashMap<int[], String> marksPlayer(int[] coordinate, String player){
        if (currentPlayer.equals("X")){
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
        if (checksIfCoordinateOccupied(coordinate)){
            return currentBoard;
        } currentBoard.put(coordinate, player);
        return currentBoard;
    }

    // Returns the winner.
    // If there is no winner yet, then "N" is returned.
    // If player X wins, then "X" is returned.
    // If player O wins, then "O" is returned.
    // If it is a tie, then "T" is returned.
    /**
     * checkWinner
     * __________________________
     * Checks which player won
     *
     * @return String: Returns the player who won "X" or "O".
     */
    public String checkWinner() {
        boolean boardsFull = true;
        for (int[] i : coordinates) {
            if (currentBoard.get(i).equals("N")) {
                boardsFull = false;
                break;
            }
        } if(checkIfWin("X") && checkIfWin("O")){
            return "T";
        } else if (checkIfWin("X")){
            return "X";
        } else if (checkIfWin("O")){
            return "O";
        } if (boardsFull){
            return "T";
        } else {
            return "N";
        }
    }

    /**
     * checkIfWin
     * __________________________
     * Checks if somebody has won
     *
     * @return boolean: Returns true if somebody has won, false otherwise.
     */
    private boolean checkIfWin(String player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (currentBoard.get(new int[]{row, 0}).equals(player)
                    && currentBoard.get(new int[]{row, 1}).equals(player)
                    && currentBoard.get(new int[]{row, 2}).equals(player)) {
                return true;
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (currentBoard.get(new int[]{0, col}).equals(player)
                    && currentBoard.get(new int[]{1, col}).equals(player)
                    && currentBoard.get(new int[]{2, col}).equals(player)) {
                return true;
            }
        }
        // Check diagonals
        if (currentBoard.get(new int[]{0, 0}).equals(player)
                && currentBoard.get(new int[]{1, 1}).equals(player)
                && currentBoard.get(new int[]{2, 2}).equals(player)) {
            return true;
        }

        if (currentBoard.get(new int[]{0, 2}).equals(player)
                && currentBoard.get(new int[]{1, 1}).equals(player)
                && currentBoard.get(new int[]{2, 0}).equals(player)) {
            return true;
        }
        return false;
    }

}