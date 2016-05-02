package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

public class Factory extends AbstractTile {

    /**
     * Assume that all factories are worth the max amount (4)
     */
    public static final int SCORE_FOR_TILE = 4;

    public static final int LOAD_CHARACTER = 1;

    public Factory(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);
    }

    @Override
    public int getScore() {
        return SCORE_FOR_TILE;
    }
}
