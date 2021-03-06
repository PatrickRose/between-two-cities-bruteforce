package com.patrickrose.betweentwocities;

import com.patrickrose.betweentwocities.tiles.AbstractTile;

public class GameBoard {

    public static final int ROWS = 4;
    public static final int COLS = 4;

    AbstractTile[][] tiles = new AbstractTile[ROWS][COLS];

    private int score = -1;

    /**
     * Create a game board using a string
     *
     * @param setup Must be a string ROWS * COLS characters long
     * @see AbstractTile To see which characters are allowed
     *
     * @throws IllegalArgumentException If the string is invalid
     */
    public GameBoard(String setup) throws IllegalArgumentException {
        if (setup == null) {
            throw new IllegalArgumentException("Got null, not a string");
        }

        if (setup.length() != (ROWS * COLS)) {
            throw new IllegalArgumentException("The setup string " + setup + " has an incorrect length (" + setup.length() + " instead of " + (ROWS * COLS));
        }

        int rowPos = 0;
        int colPos = 0;

        for (char character : setup.toCharArray()) {
            tiles[rowPos][colPos] = AbstractTile.getTileForCharacter(Integer.parseInt("" +character), this, rowPos, colPos);

            rowPos++;
            if (rowPos == 4) {
                rowPos = 0;
                colPos++;
            }
        }
    }

    /**
     * Create a game board with the given tiles
     *
     * Useful for testing
     *
     * @param tiles
     * @throws IllegalArgumentException If the array isn't the correct size
     */
    public GameBoard(AbstractTile[][] tiles) throws IllegalArgumentException {
        if (tiles.length != ROWS) {
            throw new IllegalArgumentException("There were " + tiles.length + "rows given, instead of " + ROWS);
        }

        for(AbstractTile[] tileRow: tiles) {
            if (tileRow.length != COLS) {
                throw new IllegalArgumentException("There were " + tileRow.length + "rows given, instead of " + COLS);
            }
        }

        this.tiles = tiles;
    }

    /**
     * Score the entire board
     *
     * @return int
     */
    public int getScoreForBoard() {
        if (this.score == -1) {
            int score = 0;

            for (AbstractTile[] tileRow : tiles) {
                for (AbstractTile tile : tileRow) {
                    if (!tile.hasBeenScored()) {
                        score += tile.getScore();
                        tile.markAsScored();
                    }
                }
            }
            this.score = score;
        }

        return this.score;
    }

    public AbstractTile getTileAtPosition(int x, int y) {
        if (x < 0 || x >= ROWS) {
            return null;
        }
        if (y < 0 || y >= COLS) {
            return null;
        }

        return tiles[x][y];
    }
}
