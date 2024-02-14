package GameModel.Scores;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoresTest {
    @Test
    void testScores() throws IOException {
        ScoreBoard scoresBoard = new ScoreBoard("me");
        ScoreList newList = scoresBoard.getScores();
        SingletonScore score = SingletonScore.getInstance(0.0, "null" );
        score.addScore(99999);
        scoresBoard.updateScoreBoard();
        int h = 5;

    }
}
