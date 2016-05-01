package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class Office extends AbstractTile {

    public static final int MAX_IN_SET = 6;
    public static final char LOAD_CHARACTER = 'O';

    public Office(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);
    }

    @Override
    public int getScore() {
        int score;

        this.markAsScored();

        List<Office> officesInSet = this.getNumberOfTilesInSet();

        switch (officesInSet.size()) {
            case 1:
                score = 1;
                break;
            case 2:
                score = 3;
                break;
            case 3:
                score = 6;
                break;
            case 4:
                score = 10;
                break;
            case 5:
                score = 15;
                break;
            case 6:
                score = 21;
                break;
            default:
                score = 0;
        }

        for (Office tile : officesInSet) {
            if (tile == null) {
                continue;
            }

            if (tile.isNextToATavern()) {
                score++;
            }
        }

        return score;
    }

    private boolean isNextToATavern() {
        GameBoard gameBoard = this.getGameBoard();

        return (gameBoard.getTileAtPosition(this.getX(), this.getY() - 1) instanceof AbstractTavern) ||
                (gameBoard.getTileAtPosition(this.getX(), this.getY() + 1) instanceof AbstractTavern) ||
                (gameBoard.getTileAtPosition(this.getX() - 1, this.getY()) instanceof AbstractTavern) ||
                (gameBoard.getTileAtPosition(this.getX() + 1, this.getY()) instanceof AbstractTavern);
    }

    private List<Office> getNumberOfTilesInSet() {
        List<Office> toReturn = new ArrayList<>();
        toReturn.add(this);
        int numberOfTilesInSet = 1;

        for (int x = 0; x < GameBoard.ROWS; x++) {
            for (int y = 0; y < GameBoard.COLS; y++) {
                if (!(this.getGameBoard().getTileAtPosition(x, y) instanceof Office)) {
                    // Not an office, don't care about it
                    continue;
                }

                Office tile = (Office) this.getGameBoard().getTileAtPosition(x, y);

                if (tile.hasBeenScored()) {
                    continue;
                }

                tile.markAsScored();
                toReturn.add(tile);
                numberOfTilesInSet++;

                if (numberOfTilesInSet == MAX_IN_SET) {
                    // Must have all of them, so we can stop
                    return toReturn;
                }
            }
        }

        return toReturn;
    }
}
