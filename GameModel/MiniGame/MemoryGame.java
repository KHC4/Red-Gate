package GameModel.MiniGame;

import java.util.*;

public class MemoryGame implements MiniGame{
    /** coordinates of the memory game that should be followed by the player */
    ArrayList<int[]> listOfCoordinates;
    /**
     * MemoryGame Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public MemoryGame(){
        this.listOfCoordinates = new ArrayList<int[]>();
        Random random = new Random();
        int x1 = random.nextInt(3);
        int x2 = random.nextInt(3);
        int x3 = random.nextInt(3);
        int y1 = random.nextInt(3);
        int y2 = random.nextInt(3);
        int y3 = random.nextInt(3);
        listOfCoordinates.add(new int[]{x1,y1});
        listOfCoordinates.add(new int[]{x2,y2});
        listOfCoordinates.add(new int[]{x3,y3});
        listOfCoordinates = MemoryGameGUI.removeDuplicates(listOfCoordinates);
    }
    //.
    /**
     * checkIfWin
     * __________________________
     * Check if the player won the game
     * @return boolean: Returns true if won, Otherwise, returns false
     */
    public boolean checkIfWin(ArrayList<int[]> coordinatesFromUser){
        if (this.listOfCoordinates.size() == coordinatesFromUser.size())
        {
            for(int i=0; i<listOfCoordinates.size();i++)
            {
                boolean hasItem = false;
                for(int j=0; j<coordinatesFromUser.size();j++)
                {
                    if (coordinatesFromUser.get(i)[0]==listOfCoordinates.get(j)[0] && coordinatesFromUser.get(i)[1]==listOfCoordinates.get(j)[1])
                    {

                        hasItem = true;
                    }


                }
                if(!hasItem){return false;}
            }
            return true;
        }
        return false;
    }
    /**
     * getListOfCoordinates
     * __________________________
     * Getter method for listOfCoordinates attribute
     *
     * @return ArrayList<int[]>: the list of coordinates
     */
    public ArrayList<int[]> getListOfCoordinates(){return this.listOfCoordinates;}







}