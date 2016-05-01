package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;
import com.patrickrose.betweentwocities.tiles.AbstractTavern.TavernTypes;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class OfficeTest extends BaseTileTest {

    @Test
    public void getScoreForSingleTile() {
        Office tile = new Office(this.gameBoard, 0, 0);
        this.attachTileToBoard(tile);

        assertEquals(1, tile.getScore());
        assertTrue("The tile was not marked as scored", tile.hasBeenScored());
    }

    @Test
    public void getScoreForMultipleTiles() {
        Map<Integer, Integer> countToScore = new HashMap<>();
        countToScore.put(2, 3);
        countToScore.put(3, 6);
        countToScore.put(4, 10);
        countToScore.put(5, 15);
        countToScore.put(6, 21);

        for (Map.Entry<Integer, Integer> entry : countToScore.entrySet()) {
            this.SetUp();
            int numberToInsert = entry.getKey().intValue() - 1;
            int expectedScore = entry.getValue().intValue();

            Office tileUnderTest = new Office(this.gameBoard, GameBoard.ROWS - 1, GameBoard.COLS - 1);
            this.attachTileToBoard(tileUnderTest);

            Office[] extraTiles = new Office[numberToInsert];

            for (int i = 0; i < numberToInsert; i++) {
                Office newTile = new Office(this.gameBoard, i / GameBoard.ROWS, i % GameBoard.COLS);

                this.attachTileToBoard(newTile);
                extraTiles[i] = newTile;
            }

            assertEquals("Got an incorrect score when there were " + entry.getKey() + " tiles", expectedScore, tileUnderTest.getScore());
            assertTrue("The tile under test was not marked as scored", tileUnderTest.hasBeenScored());

            for (Office tile : extraTiles) {
                assertTrue("Another tile was not marked as scored", tile.hasBeenScored());
            }
        }
    }

    @Test
    public void getScoreForSevenTiles() {
        Office tileUnderTest = new Office(this.gameBoard, GameBoard.ROWS - 1, GameBoard.COLS - 1);
        this.attachTileToBoard(tileUnderTest);

        Office[] extraTiles = new Office[5];

        for (int i = 0; i < 5; i++) {
            Office newTile = new Office(this.gameBoard, i / GameBoard.ROWS, i % GameBoard.COLS);

            this.attachTileToBoard(newTile);
            extraTiles[i] = newTile;
        }

        Office newSet = new Office(this.gameBoard, 2, 2);
        this.attachTileToBoard(newSet);

        assertEquals(21, tileUnderTest.getScore());
        assertTrue("The tile under test was not marked as scored", tileUnderTest.hasBeenScored());

        for (Office tile : extraTiles) {
            assertTrue("Another tile was not marked as scored", tile.hasBeenScored());
        }

        assertFalse("The spare office was marked as scored", newSet.hasBeenScored());
    }

    @Test
    public void beingNextToATavernGivesAnExtraPoint() {
        for (TavernTypes tavernType : TavernTypes.values()) {
            this.SetUp();
            Office office = new Office(this.gameBoard, 0, 0);
            AbstractTavern tavern = AbstractTavern.getTavernOfType(tavernType, this.gameBoard, 0, 1);

            this.attachTileToBoard(office);
            this.attachTileToBoard(tavern);

            assertEquals(2, office.getScore());
        }
    }

    @Test
    public void itDoesNotMatterWhereTheTavernIs() {
        for (int[] differentPositions : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
            int xPos = differentPositions[0];
            int yPos = differentPositions[1];

            for (TavernTypes tavernType : TavernTypes.values()) {
                this.SetUp();
                Office office = new Office(this.gameBoard, 1, 1);

                this.attachTileToBoard(office);
                this.attachTileToBoard(AbstractTavern.getTavernOfType(tavernType, this.gameBoard, 1 + xPos, 1 + yPos));

                assertEquals(2, office.getScore());
            }
        }
    }

    @Test
    public void beingNextToManyTavernsIsOnlyOnePoint() {
        for (TavernTypes tavernType : TavernTypes.values()) {
            this.SetUp();
            Office office = new Office(this.gameBoard, 1, 1);

            this.attachTileToBoard(office);
            this.attachTileToBoard(AbstractTavern.getTavernOfType(tavernType, this.gameBoard, 0, 1));
            this.attachTileToBoard(AbstractTavern.getTavernOfType(tavernType, this.gameBoard, 2, 1));
            this.attachTileToBoard(AbstractTavern.getTavernOfType(tavernType, this.gameBoard, 1, 0));
            this.attachTileToBoard(AbstractTavern.getTavernOfType(tavernType, this.gameBoard, 1, 2));

            assertEquals(2, office.getScore());
        }
    }

    @Test
    public void itCountsTavernsNextToTheOfficesInTheSet() {
        Map<Integer, Integer> countToScore = new HashMap<>();
        countToScore.put(2, 3);
        countToScore.put(3, 6);
        countToScore.put(4, 10);
        countToScore.put(5, 15);
        countToScore.put(6, 21);

        for (Map.Entry<Integer, Integer> entry : countToScore.entrySet()) {
            int numberToInsert = entry.getKey().intValue() - 1;
            int expectedScore = entry.getValue().intValue();

            Office tileUnderTest = new Office(this.gameBoard, GameBoard.ROWS - 1, GameBoard.COLS - 1);
            this.attachTileToBoard(tileUnderTest);
            this.attachTileToBoard(new SleepTavern(this.gameBoard, GameBoard.ROWS - 1, GameBoard.COLS - 2));

            Office[] extraTiles = new Office[numberToInsert];

            for (int i = 0; i < numberToInsert; i++) {
                int xPos = i < GameBoard.COLS ? 0 : 2;
                int yPos = i % GameBoard.ROWS;

                Office newTile = new Office(this.gameBoard, xPos, yPos);

                this.attachTileToBoard(newTile);
                this.attachTileToBoard(new SleepTavern(this.gameBoard, xPos + 1, yPos));
                extraTiles[i] = newTile;
            }

            assertEquals("Got an incorrect score when there were " + entry.getKey() + " tiles", expectedScore + entry.getKey(), tileUnderTest.getScore());
            assertTrue("The tile under test was not marked as scored", tileUnderTest.hasBeenScored());

            for (Office tile : extraTiles) {
                assertTrue("Another tile was not marked as scored", tile.hasBeenScored());
            }
        }
    }

}