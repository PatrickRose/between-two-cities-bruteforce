package com.patrickrose.betweentwocities.tiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest extends BaseTileTest {

    @Test
    public void getScore() {
        Factory tile = new Factory(this.gameBoard, 0, 0);
        this.tiles[0][0] = tile;

        assertEquals(4, tile.getScore());
    }

}