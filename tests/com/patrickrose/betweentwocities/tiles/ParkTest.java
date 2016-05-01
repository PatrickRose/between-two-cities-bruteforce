package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ParkTest extends BaseTileTest {

    @Test
    public void getScore() {
        Map<Integer, Integer> numberToScore = new HashMap<>();

        numberToScore.put(1, 2);
        numberToScore.put(2, 8);
        numberToScore.put(3, 12);
        numberToScore.put(4, 13);
        numberToScore.put(5, 14);
        numberToScore.put(6, 15);
        numberToScore.put(7, 16);
        numberToScore.put(8, 17);

        for (Map.Entry<Integer, Integer> entry : numberToScore.entrySet()) {
            this.SetUp();

            int numberToPutIn = entry.getKey() - 1;
            int expected = entry.getValue();

            Park tileUnderTest = new Park(this.gameBoard, 0, 0);
            this.attachTileToBoard(tileUnderTest);

            Park[] extraParks = new Park[numberToPutIn];

            for(int i=1; i<=numberToPutIn; i++) {
                Park extraPark = new Park(this.gameBoard, i % GameBoard.ROWS, i / GameBoard.COLS);

                this.attachTileToBoard(extraPark);
                extraParks[i - 1] = extraPark;
            }

            assertEquals("Got an incorrect score with " + (numberToPutIn + 1) + " tiles", expected, tileUnderTest.getScore());

            assertTrue("Base tile wasn't marked as scored", tileUnderTest.hasBeenScored());

            for (Park extraPark: extraParks) {
                assertTrue("Additional tile wasn't marked as scored", extraPark.hasBeenScored());
            }
        }
    }

}