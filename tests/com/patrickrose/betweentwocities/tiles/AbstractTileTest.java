package com.patrickrose.betweentwocities.tiles;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AbstractTileTest {

    @Test
    public void getTileForCharacter() throws Exception {
        Map<Integer, AbstractTile> characterToTile = new HashMap<>();
        characterToTile.put(Shop.LOAD_CHARACTER, new Shop(null, 0, 0));
        characterToTile.put(Factory.LOAD_CHARACTER, new Factory(null, 0, 0));
        characterToTile.put(FoodTavern.LOAD_CHARACTER, new FoodTavern(null, 0, 0));
        characterToTile.put(SleepTavern.LOAD_CHARACTER, new SleepTavern(null, 0, 0));
        characterToTile.put(DrinkTavern.LOAD_CHARACTER, new DrinkTavern(null, 0, 0));
        characterToTile.put(MusicTavern.LOAD_CHARACTER, new MusicTavern(null, 0, 0));
        characterToTile.put(Office.LOAD_CHARACTER, new Office(null, 0, 0));
        characterToTile.put(House.LOAD_CHARACTER, new House(null, 0, 0));
        characterToTile.put(Park.LOAD_CHARACTER, new Park(null, 0, 0));

        for (Map.Entry<Integer, AbstractTile> entry : characterToTile.entrySet()) {
            AbstractTile tileForCharacter = AbstractTile.getTileForCharacter(entry.getKey(), null, 0, 0);
            Class<? extends AbstractTile> expectedClass = entry.getValue().getClass();
            Class<? extends AbstractTile> actualClass = tileForCharacter.getClass();
            assertEquals(expectedClass, actualClass);
        }
    }

}