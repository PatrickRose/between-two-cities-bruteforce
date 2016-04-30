package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

/**
 * Created by patrick on 30/04/16.
 */
abstract public class AbstractTile {

    private final GameBoard gameBoard;
    private final int x;
    private final int y;

    protected boolean hasBeenScored = false;

    public AbstractTile() {
        this.gameBoard = null;
        this.x = 0;
        this.y = 0;
    }

    public AbstractTile(GameBoard gameBoard, int x, int y) {
        this.gameBoard = gameBoard;
        this.x = x;
        this.y = y;
    }

    abstract public int getScore();

    public static AbstractTile getTileForCharacter(char character) {
        return null;
    }

    public void markAsScored() {
        this.hasBeenScored = true;
    }

    public boolean hasBeenScored() {
        return this.hasBeenScored;
    }
}
