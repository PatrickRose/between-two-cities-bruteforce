package com.patrickrose.betweentwocities;

import com.patrickrose.betweentwocities.tiles.AbstractTile;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void testTheStringMustBeCorrect() {
        GameBoard validGameBoard = new GameBoard("1111111111111111");

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
    public void testYouCanGetATestTile() {

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