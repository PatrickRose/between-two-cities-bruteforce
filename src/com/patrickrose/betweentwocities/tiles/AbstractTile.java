package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

/**
 * An abstract tile
 */
abstract public class AbstractTile {

    /**
     * The game board that this tile is on
     */
    private final GameBoard gameBoard;

    /**
     * The x location this tile is located on the above game board
     */
    private final int x;

    /**
     * The y location this tile is located on the above game board
     */
    private final int y;

    /**
     * Whether this has been scored
     */
    protected boolean hasBeenScored = false;

    /**
     * Useful for unit tests
     */
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

    /**
     * Create a tile for the given tile
     * @param character The correct tile
     * @return An abstract tile
     * @throws IllegalArgumentException If the character cannot be converted to a tile
     */
    public static AbstractTile getTileForCharacter(char character) {
        return null;
    }

    /**
     * Calculate the score for this tile on this board
     *
     * @return The score for this tile
     */
    abstract public int getScore();

    /**
     * Mark this tile as scored
     */
    public void markAsScored() {
        this.hasBeenScored = true;
    }

    /**
     * Returns if this tile has been scored
     *
     * @return True if this tile has been scored. Otherwise false.
     */
    public boolean hasBeenScored() {
        return this.hasBeenScored;
    }
}
