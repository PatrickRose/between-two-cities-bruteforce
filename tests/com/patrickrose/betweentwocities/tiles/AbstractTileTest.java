package com.patrickrose.betweentwocities.tiles;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AbstractTileTest {

    @Test
    public void getTileForCharacter() throws Exception {
        Map<Character, AbstractTile> characterToTile = new HashMap<>();
        characterToTile.put(Shop.LOAD_CHARACTER, new Shop(null, 0, 0));

        for (Map.Entry<Character, AbstractTile> entry : characterToTile.entrySet()) {
            AbstractTile tileForCharacter = AbstractTile.getTileForCharacter(entry.getKey().charValue(), null, 0, 0);
            assertEquals(entry.getValue().getClass(), tileForCharacter.getClass());
        }
    }

}