package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class Park extends AbstractTile {

    public static final char LOAD_CHARACTER = 'P';

    public Park(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);
    }

    @Override
    public int getScore() {
        final int parksInSet = this.getNumberOfParksInSet();

        if (parksInSet == 1) {
            return 2;
        }

        if (parksInSet == 2) {
            return 8;
        }

        return 12 + parksInSet - 3;
    }

    private int getNumberOfParksInSet() {
        int toReturn = 1;
        
        this.markAsScored();

        AbstractTile tileToLeft = this.getGameBoard().getTileAtPosition(this.getX() - 1, this.getY());
        if (tileToLeft instanceof Park && !tileToLeft.hasBeenScored()) {
            toReturn += ((Park) tileToLeft).getNumberOfParksInSet();
        }

        AbstractTile tileAbove = this.getGameBoard().getTileAtPosition(this.getX(), this.getY() - 1);
        if (tileAbove instanceof Park && !tileAbove.hasBeenScored()) {
            toReturn += ((Park) tileAbove).getNumberOfParksInSet();
        }

        AbstractTile tileToRight = this.getGameBoard().getTileAtPosition(this.getX() + 1, this.getY());
        if (tileToRight instanceof Park && !tileToRight.hasBeenScored()) {
            toReturn += ((Park) tileToRight).getNumberOfParksInSet();
        }

        AbstractTile tileBelow = this.getGameBoard().getTileAtPosition(this.getX(), this.getY() + 1);
        if (tileBelow instanceof Park && !tileBelow.hasBeenScored()) {
            toReturn += ((Park) tileBelow).getNumberOfParksInSet();
        }

        return toReturn;
    }
}
