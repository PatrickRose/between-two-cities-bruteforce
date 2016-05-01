package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.tiles.AbstractTavern.TavernTypes;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by patrick on 01/05/16.
 */
public class AbstractTavernTest extends BaseTileTest {

    @Test
    public void testSingleTavern() {
        for (TavernTypes firstType : TavernTypes.values()) {
            AbstractTavern tavern = AbstractTavern.getTavernOfType(firstType, this.gameBoard, 0, 0);

            this.attachTileToBoard(tavern);

            assertEquals(1, tavern.getScore());
            assertTrue("The tavern was not marked as scored", tavern.hasBeenScored());
        }
    }

    @Test
    public void testTwoTaverns() {
        for (TavernTypes firstType : TavernTypes.values()) {
            for (TavernTypes secondType : TavernTypes.values()) {
                if (secondType == firstType) {
                    // Separate test
                    continue;
                }
                AbstractTavern firstTavern = AbstractTavern.getTavernOfType(firstType, this.gameBoard, 0, 0);
                AbstractTavern secondTavern = AbstractTavern.getTavernOfType(secondType, this.gameBoard, 0, 1);

                this.attachTileToBoard(firstTavern);
                this.attachTileToBoard(secondTavern);

                assertEquals(4, firstTavern.getScore());
                assertTrue("The first tavern was not marked as scored", firstTavern.hasBeenScored());
                assertTrue("The second tavern was not marked as scored", secondTavern.hasBeenScored());
            }
        }
    }

    @Test
    public void testThreeTaverns() {
        for (TavernTypes firstType : TavernTypes.values()) {
            for (TavernTypes secondType : TavernTypes.values()) {
                if (secondType == firstType) {
                    // Separate test
                    continue;
                }

                for (TavernTypes thirdType : TavernTypes.values()) {
                    if (thirdType == firstType || thirdType == secondType) {
                        continue;
                    }

                    AbstractTavern firstTavern = AbstractTavern.getTavernOfType(firstType, this.gameBoard, 0, 0);
                    AbstractTavern secondTavern = AbstractTavern.getTavernOfType(secondType, this.gameBoard, 0, 1);
                    AbstractTavern thirdTavern = AbstractTavern.getTavernOfType(thirdType, this.gameBoard, 0, 2);

                    this.attachTileToBoard(firstTavern);
                    this.attachTileToBoard(secondTavern);
                    this.attachTileToBoard(thirdTavern);

                    assertEquals(9, firstTavern.getScore());
                    assertTrue("The first tavern was not marked as scored", firstTavern.hasBeenScored());
                    assertTrue("The second tavern was not marked as scored", secondTavern.hasBeenScored());
                    assertTrue("The third tavern was not marked as scored", thirdTavern.hasBeenScored());
                }
            }
        }
    }

    @Test
    public void testFourTaverns() {
        for (TavernTypes firstType : TavernTypes.values()) {
            for (TavernTypes secondType : TavernTypes.values()) {
                if (secondType == firstType) {
                    // Separate test
                    continue;
                }

                for (TavernTypes thirdType : TavernTypes.values()) {
                    if (thirdType == firstType || thirdType == secondType) {
                        continue;
                    }

                    for (TavernTypes fourthType : TavernTypes.values()) {
                        if (fourthType == firstType || fourthType == secondType || fourthType == thirdType) {
                            continue;
                        }

                        AbstractTavern firstTavern = AbstractTavern.getTavernOfType(firstType, this.gameBoard, 0, 0);
                        AbstractTavern secondTavern = AbstractTavern.getTavernOfType(secondType, this.gameBoard, 0, 1);
                        AbstractTavern thirdTavern = AbstractTavern.getTavernOfType(thirdType, this.gameBoard, 0, 2);
                        AbstractTavern fourthTavern = AbstractTavern.getTavernOfType(fourthType, this.gameBoard, 0, 3);

                        this.attachTileToBoard(firstTavern);
                        this.attachTileToBoard(secondTavern);
                        this.attachTileToBoard(thirdTavern);
                        this.attachTileToBoard(fourthTavern);

                        assertEquals(17, firstTavern.getScore());
                        assertTrue("The first tavern was not marked as scored", firstTavern.hasBeenScored());
                        assertTrue("The second tavern was not marked as scored", secondTavern.hasBeenScored());
                        assertTrue("The third tavern was not marked as scored", thirdTavern.hasBeenScored());
                        assertTrue("The fourth tavern was not marked as scored", fourthTavern.hasBeenScored());
                    }
                }
            }
        }
    }

    @Test
    public void testFiveTaverns() {
        for (TavernTypes firstType : TavernTypes.values()) {
            for (TavernTypes secondType : TavernTypes.values()) {
                if (secondType == firstType) {
                    // Separate test
                    continue;
                }

                for (TavernTypes thirdType : TavernTypes.values()) {
                    if (thirdType == firstType || thirdType == secondType) {
                        continue;
                    }

                    for (TavernTypes fourthType : TavernTypes.values()) {
                        if (fourthType == firstType || fourthType == secondType || fourthType == thirdType) {
                            continue;
                        }

                        for (TavernTypes fifthType : TavernTypes.values()) {
                            AbstractTavern firstTavern = AbstractTavern.getTavernOfType(firstType, this.gameBoard, 0, 0);
                            AbstractTavern secondTavern = AbstractTavern.getTavernOfType(secondType, this.gameBoard, 0, 1);
                            AbstractTavern thirdTavern = AbstractTavern.getTavernOfType(thirdType, this.gameBoard, 0, 2);
                            AbstractTavern fourthTavern = AbstractTavern.getTavernOfType(fourthType, this.gameBoard, 0, 3);
                            AbstractTavern fifthTavern = AbstractTavern.getTavernOfType(fifthType, this.gameBoard, 1, 0);

                            this.attachTileToBoard(firstTavern);
                            this.attachTileToBoard(secondTavern);
                            this.attachTileToBoard(thirdTavern);
                            this.attachTileToBoard(fourthTavern);
                            this.attachTileToBoard(fifthTavern);

                            assertEquals(17, firstTavern.getScore());
                            assertTrue("The first tavern was not marked as scored", firstTavern.hasBeenScored());
                            assertTrue("The second tavern was not marked as scored", secondTavern.hasBeenScored());
                            assertTrue("The third tavern was not marked as scored", thirdTavern.hasBeenScored());
                            assertTrue("The fourth tavern was not marked as scored", fourthTavern.hasBeenScored());
                            assertFalse("The fifth tavern was marked as scored", fifthTavern.hasBeenScored());
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testDuplicatesDoNotCount() {
        for (TavernTypes firstType : TavernTypes.values()) {
            for (TavernTypes secondType : TavernTypes.values()) {
                if (secondType == firstType) {
                    // Separate test
                    continue;
                }

                for (TavernTypes thirdType : TavernTypes.values()) {
                    if (thirdType == firstType || thirdType == secondType) {
                        continue;
                    }

                    AbstractTavern firstTavern = AbstractTavern.getTavernOfType(firstType, this.gameBoard, 0, 0);
                    AbstractTavern secondTavern = AbstractTavern.getTavernOfType(secondType, this.gameBoard, 0, 1);
                    AbstractTavern thirdTavern = AbstractTavern.getTavernOfType(thirdType, this.gameBoard, 0, 2);
                    AbstractTavern fourthTavern = AbstractTavern.getTavernOfType(firstType, this.gameBoard, 0, 3);

                    this.attachTileToBoard(firstTavern);
                    this.attachTileToBoard(secondTavern);
                    this.attachTileToBoard(thirdTavern);
                    this.attachTileToBoard(fourthTavern);

                    assertEquals(9, firstTavern.getScore());
                    assertTrue("The first tavern was not marked as scored", firstTavern.hasBeenScored());
                    assertTrue("The second tavern was not marked as scored", secondTavern.hasBeenScored());
                    assertTrue("The third tavern was not marked as scored", thirdTavern.hasBeenScored());
                    assertFalse("The fourth tavern was marked as scored", fourthTavern.hasBeenScored());
                }
            }
        }
    }

}