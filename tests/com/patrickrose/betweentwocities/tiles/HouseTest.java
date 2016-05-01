package com.patrickrose.betweentwocities.tiles;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HouseTest extends BaseTileTest {

    @Test
    public void worthTheNumberOfOtherTypesOfTiles() {
        char[] loadChars = {
                Shop.LOAD_CHARACTER,
                Factory.LOAD_CHARACTER,
                // Just test with SleepTavern for now
                SleepTavern.LOAD_CHARACTER,
                Office.LOAD_CHARACTER,
                Park.LOAD_CHARACTER
        };

        for (char firstChar : loadChars) {
            for (char secondChar : loadChars) {
                for (char thirdChar : loadChars) {
                    for (char fourthChar : loadChars) {
                        for (char fifthChar : loadChars) {
                            this.SetUp();
                            // Work out what the score should be
                            Map<Character, Boolean> characterBooleanMap = new HashMap<>();

                            characterBooleanMap.put(firstChar, true);
                            characterBooleanMap.put(secondChar, true);
                            characterBooleanMap.put(thirdChar, true);
                            characterBooleanMap.put(fourthChar, true);
                            characterBooleanMap.put(fifthChar, true);

                            House house = new House(this.gameBoard, 0, 0);

                            this.attachTileToBoard(house);
                            this.attachTileToBoard(AbstractTile.getTileForCharacter(firstChar, this.gameBoard, 3, 0));
                            this.attachTileToBoard(AbstractTile.getTileForCharacter(secondChar, this.gameBoard, 3, 1));
                            this.attachTileToBoard(AbstractTile.getTileForCharacter(thirdChar, this.gameBoard, 3, 2));
                            this.attachTileToBoard(AbstractTile.getTileForCharacter(fourthChar, this.gameBoard, 3, 3));
                            this.attachTileToBoard(AbstractTile.getTileForCharacter(fifthChar, this.gameBoard, 0, 0));

                            assertEquals("Did not get the expected score. Used string: " + firstChar + secondChar + thirdChar + fourthChar + fifthChar, characterBooleanMap.size(), house.getScore());
                        }
                    }
                }
            }
        }
    }

    @Test
    public void worthOneWhenNextToAFactory() {
        char[] loadChars = {
                Shop.LOAD_CHARACTER,
                Factory.LOAD_CHARACTER,
                // Just test with SleepTavern for now
                SleepTavern.LOAD_CHARACTER,
                Office.LOAD_CHARACTER,
                Park.LOAD_CHARACTER
        };

        for (int[] differentPositions : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
            int xPos = differentPositions[0];
            int yPos = differentPositions[1];

            for (char firstChar : loadChars) {
                for (char secondChar : loadChars) {
                    for (char thirdChar : loadChars) {
                        for (char fourthChar : loadChars) {
                            for (char fifthChar : loadChars) {
                                this.SetUp();

                                House house = new House(this.gameBoard, 1, 1);
                                Factory factory = new Factory(this.gameBoard, 1 + xPos, 1 + yPos);

                                this.attachTileToBoard(house);
                                this.attachTileToBoard(factory);
                                this.attachTileToBoard(AbstractTile.getTileForCharacter(firstChar, this.gameBoard, 3, 0));
                                this.attachTileToBoard(AbstractTile.getTileForCharacter(secondChar, this.gameBoard, 3, 1));
                                this.attachTileToBoard(AbstractTile.getTileForCharacter(thirdChar, this.gameBoard, 3, 2));
                                this.attachTileToBoard(AbstractTile.getTileForCharacter(fourthChar, this.gameBoard, 3, 3));
                                this.attachTileToBoard(AbstractTile.getTileForCharacter(fifthChar, this.gameBoard, 0, 0));

                                assertEquals("Did not get the expected score. Used string: " + firstChar + secondChar + thirdChar + fourthChar + fifthChar, 1, house.getScore());
                            }
                        }
                    }
                }
            }
        }
    }

}