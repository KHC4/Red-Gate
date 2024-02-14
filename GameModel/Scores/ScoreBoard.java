package GameModel.Scores;

import GameModel.Player;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class Scoreboard.
 *__________________________
 * This is the Class that manages the leaderboard data.
 *
 */
public class ScoreBoard {
    /** Player Reference */
    Player player;
    /** Player Name */
    String name;
    /** SingletonScore instance */
    SingletonScore score;
    /** file path for the txt file */
    private String filePath;
    /**
     * Scoreboard Constructor
     * __________________________
     * Initializes attributes
     * @param name the name of the player
     */
    public ScoreBoard(String name) {
        this.name = name;
        this.score = SingletonScore.getInstance(0.0, this.name );
    }

    /**
     * getScores
     * __________________________
     * Reads the txt file and returns the data
     * @return ScoreList the data stored in the txt file
     */
    public ScoreList getScores()  {
        try {
            ArrayList<Double> scoreList = new ArrayList<Double>();
            ArrayList<String> nameList = new ArrayList<String>();

            filePath = System.getProperty("user.dir");
            filePath += "/group_66/GameModel/Scores/topFive.txt";

            InputStream loc = this.getClass().getResourceAsStream("topFive.txt");
            InputStreamReader in = new InputStreamReader(loc);
            Scanner objReader = new Scanner(in);
            while (objReader.hasNextLine()) {
                String data = objReader.nextLine();
                String[] processedData = data.split(",");
                scoreList.add(Double.valueOf(processedData[1]));
                nameList.add(processedData[0]);
            }
            objReader.close();
            in.close();

            for (int i = 0; i < scoreList.size(); i++) {

                for (int j = i + 1; j < scoreList.size(); j++) {
                    double tempVal = 0;
                    String tempName = "";
                    if (scoreList.get(j) < scoreList.get(i)) {

                        // Swapping
                        tempVal = scoreList.get(i);
                        tempName = nameList.get(i);
                        scoreList.set(i, scoreList.get(j));
                        nameList.set(i, nameList.get(j));
                        scoreList.set(j, tempVal);
                        nameList.set(j, tempName);
                    }
                }
            }
            ScoreList returnList = new ScoreList(scoreList, nameList);
            return returnList;
        }
        catch (Exception e){ return null;}
    }


    /**
     * updateScoreBoard
     * __________________________
     * updates the scoreboard based on current player's performance
     */
    public void updateScoreBoard()  {
        ScoreList scores = this.getScores();
        ArrayList<Double> scoresList = scores.getScores();
        ArrayList<String> nameList =  scores.getNames();
        double min = Double.POSITIVE_INFINITY;
        int minIndex = 0;
        for (int i = 0; i < scoresList.size(); i++){
            if (min == Double.POSITIVE_INFINITY){min = (double)scoresList.get(i);minIndex = i;}
            else if (Double.compare((double)scoresList.get(i),min)<0 ){ min = (double) scoresList.get(i);minIndex = i;}
        }
        //nameList.set(minIndex, "h");
        nameList.set(minIndex, this.name);
        //scoresList.set(minIndex, this.name);
        scoresList.set(minIndex,score.getScore());
//        try {
//            FileWriter fileWriter = new FileWriter(objFile);
//            BufferedWriter objWriter = new BufferedWriter(fileWriter);
//            for (int i = 1; i < scoresList.size(); i++) {
//                objWriter.write(nameList.get(i).toString() + "," + scoresList.get(i).toString());
//                objWriter.newLine();
//            }
//            objWriter.close();
//            fileWriter.close();
//        }
//        catch (Exception e){}
        //this should be fixed
        try {
//            URL url = getClass().getResource("topFive.txt");
//            File folder = new File(filePath);
            FileWriter myWriter = new FileWriter(filePath);
            for (int i = 0; i < scoresList.size(); i++) {
                myWriter.write(nameList.get(i).toString() + "," + scoresList.get(i).toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
