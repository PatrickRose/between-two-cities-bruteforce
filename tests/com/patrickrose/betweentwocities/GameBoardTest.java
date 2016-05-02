package com.patrickrose.betweentwocities;

import com.patrickrose.betweentwocities.tiles.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void testTheStringMustBeCorrect() {
        GameBoard validGameBoard = new GameBoard("0000000000000000");

        assertTrue("Did not get a GameBoard back", validGameBoard instanceof GameBoard);

        String[] invalidLength = {
                "123456789012345",
                "12345678901234567",
                "",
                null
        };

        for (String invalidString : invalidLength) {
            boolean failedValidation = false;

            try {
                new GameBoard(invalidString);
            } catch (IllegalArgumentException ex) {
                failedValidation = true;
            }

            assertTrue("The initialising string " + invalidString + " did not cause an IllegalArgumentException", failedValidation);
        }
    }

    @Test
    public void testWeCanInitialiseTheBoardWithSomeTiles() {
        AbstractTile[][] testTiles = {
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()}
        };

        GameBoard validGameBoard = new GameBoard(testTiles);

        assertTrue("Did not get a GameBoard back", validGameBoard instanceof GameBoard);

        // Then we check that it shouts at you if you don't have the right length
        for (int x = 1; x <= GameBoard.ROWS + 2; x++) {
            for (int y = 1; y <= GameBoard.COLS + 2; y++) {
                boolean failedValidation = false;

                if (x == GameBoard.ROWS && y == GameBoard.COLS) {
                    continue;
                }

                AbstractTile[][] invalidTiles = new TestTile[x][y];

                try {
                    new GameBoard(invalidTiles);
                } catch (IllegalArgumentException ex) {
                    failedValidation = true;
                }

                assertTrue("A 2d array with " + x + " rows and " + y + " columns didn't fail validation", failedValidation);
            }
        }
    }

    @Test
    public void testWeCanGetTheScoreForABoard() {
        TestTile[][] testTiles = {
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()}
        };

        for(TestTile[] testTileRow: testTiles) {
            for(TestTile testTile: testTileRow) {
                testTile.setTestScore(1);
            }
        }

        GameBoard gameBoard = new GameBoard(testTiles);
        assertEquals("Did not get the expected score", 16, gameBoard.getScoreForBoard());

        for(int x = 0; x < GameBoard.ROWS; x++) {
            for(int y = 0; y < GameBoard.COLS; y++) {
                assertTrue("The tile at [" + x + "," + y + "] was not marked as scored", testTiles[x][y].hasBeenScored());
            }
        }
    }

    @Test
    public void testYouCanGetATile() {

        TestTile[][] testTiles = {
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()}
        };

        GameBoard gameBoard = new GameBoard(testTiles);

        for(int x = 0; x < GameBoard.ROWS; x++) {
            for(int y = 0; y < GameBoard.COLS; y++) {
                assertEquals(testTiles[x][y], gameBoard.getTileAtPosition(x, y));
            }
        }
    }


    @Test
    public void testTilesThatHaveBeenScoredDoNotCountTowardsFinalScore() {
        TestTile[][] testTiles = {
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()}
        };

        for(TestTile[] testTileRow: testTiles) {
            for(TestTile testTile: testTileRow) {
                testTile.setTestScore(1);
                testTile.markAsScored();
            }
        }

        GameBoard gameBoard = new GameBoard(testTiles);
        assertEquals("Did not get the expected score", 0, gameBoard.getScoreForBoard());
    }

    @Test
    public void testYouCanScoreTheSameBoardTwice() {
        TestTile[][] testTiles = {
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()},
                {new TestTile(), new TestTile(), new TestTile(), new TestTile()}
        };

        for(TestTile[] testTileRow: testTiles) {
            for(TestTile testTile: testTileRow) {
                testTile.setTestScore(1);
            }
        }

        GameBoard gameBoard = new GameBoard(testTiles);
        assertEquals("Did not get the expected score", 16, gameBoard.getScoreForBoard());
        assertEquals("Did not get the expected score when redoing", 16, gameBoard.getScoreForBoard());
    }

    @Test
    public void exampleScoresWithAbstractTiles() {
        Map<Integer, String> scoreToTiles = new HashMap<>();

        int shop = Shop.LOAD_CHARACTER;
        int house = House.LOAD_CHARACTER;
        int office = Office.LOAD_CHARACTER;
        int music = MusicTavern.LOAD_CHARACTER;
        int park = Park.LOAD_CHARACTER;
        int factory = Factory.LOAD_CHARACTER;
        int drink = DrinkTavern.LOAD_CHARACTER;
        int food = FoodTavern.LOAD_CHARACTER;
        int sleep = SleepTavern.LOAD_CHARACTER;

        scoreToTiles.put(56, "" +
                shop + shop + shop + shop +
                house + house + office + music +
                office + park + office + office +
                office + park + house + park
        );
        scoreToTiles.put(52, "" +
                music + factory + factory + factory +
                park + park + factory + park +
                shop + shop + music + park +
                house + house + house + factory
        );
        scoreToTiles.put(65, "" +
                music + drink + factory + factory +
                house + food + factory + park +
                shop + house + sleep + park +
                house + house + house + office
        );
        scoreToTiles.put(66, "" +
                music + drink + factory + factory +
                office + food + office + sleep +
                office + office + office + office +
                shop + shop + shop + shop
        );
        scoreToTiles.put(64, "" +
                music + drink + house + house +
                shop + food + office + sleep +
                shop + office + house + house +
                shop + factory + park + park
        );
        scoreToTiles.put(57, "" +
                factory + shop + factory + factory +
                park + factory + park + park +
                factory + sleep + house + park +
                park + park + house + house
        );


        for (Map.Entry<Integer, String> entry : scoreToTiles.entrySet()) {
            GameBoard gameBoard = new GameBoard(entry.getValue());

            assertEquals("Got incorrect value for " + entry.getValue(), entry.getKey().intValue(), gameBoard.getScoreForBoard());
        }
    }

    private class TestTile extends AbstractTile {

        private int scores;

        public TestTile() {
            this.scores = 0;
        }

        @Override
        public int getScore() {
            return this.scores;
        }

        public void setTestScore(int newScore) {
            this.scores = newScore;
        }
    }
}