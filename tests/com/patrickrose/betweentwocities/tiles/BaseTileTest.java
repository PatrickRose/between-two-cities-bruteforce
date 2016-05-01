package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;
import org.junit.Before;

public class BaseTileTest {

    protected AbstractTile[][] tiles;

    protected GameBoard gameBoard;

    @Before
    public void SetUp() {
        this.tiles = new AbstractTile[][]{
                {new StubTile(), new StubTile(), new StubTile(), new StubTile()},
                {new StubTile(), new StubTile(), new StubTile(), new StubTile()},
                {new StubTile(), new StubTile(), new StubTile(), new StubTile()},
                {new StubTile(), new StubTile(), new StubTile(), new StubTile()}
        };

        this.gameBoard = new GameBoard(tiles);
    }

    public void attachTileToBoard(AbstractTile tile) {
        this.tiles[tile.getX()][tile.getY()] = tile;
    }


    protected class StubTile extends AbstractTile {

        @Override
        public int getScore() {
            return 0;
        }
    }

}
