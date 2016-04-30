package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

import java.util.ArrayList;

public class Shop extends AbstractTile {

    public static final char LOAD_CHARACTER = 'S';

    public Shop(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);
    }

    @Override
    public int getScore() {
        ArrayList<Shop> tileRow;
        ArrayList<Shop> tilesInColumn = new ArrayList<>();
        ArrayList<Shop> tilesInRow = new ArrayList<>();

        for (int i = 0; i < GameBoard.ROWS; i++) {
            AbstractTile tile = this.getGameBoard().getTileAtPosition(i, this.getY());

            if (tile.getClass() == this.getClass() && !tile.hasBeenScored()) {
                tilesInColumn.add((Shop) tile);
            }
        }

        for (int i = 0; i < GameBoard.ROWS; i++) {
            AbstractTile tile = this.getGameBoard().getTileAtPosition(this.getX(), i);

            if (tile.getClass() == this.getClass() && !tile.hasBeenScored()) {
                tilesInRow.add((Shop) tile);
            }
        }

        tileRow = tilesInColumn.size() > tilesInRow.size() ? tilesInColumn : tilesInRow;

        for (Shop tile: tileRow) {
            tile.markAsScored();
        }

        switch (tileRow.size()) {
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 16;
            default:
                return 0;
        }
    }

}
