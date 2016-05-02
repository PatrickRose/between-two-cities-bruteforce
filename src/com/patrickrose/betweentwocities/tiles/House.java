package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

public class House extends AbstractTile {

    public static final int LOAD_CHARACTER = 8;

    public House(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);
    }

    @Override
    public int getScore() {
        if (this.isNextToAFactory()) {
            return 1;
        }

        int score = 0;

        boolean haveFactory = false;
        boolean haveOffice = false;
        boolean haveShop = false;
        boolean haveTavern = false;
        boolean havePark = false;

        for (int x = 0; x < GameBoard.ROWS; x++) {
            for (int y = 0; y < GameBoard.COLS; y++) {
                AbstractTile tile = this.getGameBoard().getTileAtPosition(x, y);

                if ((tile instanceof Factory) && !haveFactory) {
                    haveFactory = true;
                    score++;
                }
                else if ((tile instanceof AbstractTavern) && !haveTavern) {
                    haveTavern = true;
                    score++;
                }
                else if ((tile instanceof Office) && !haveOffice) {
                    haveOffice = true;
                    score++;
                }
                else if ((tile instanceof Shop) && !haveShop) {
                    haveShop = true;
                    score++;
                }
                else if ((tile instanceof Park) && !havePark) {
                    havePark = true;
                    score++;
                }
            }
        }

        return score;
    }

    private boolean isNextToAFactory() {
        GameBoard gameBoard = this.getGameBoard();

        return (gameBoard.getTileAtPosition(this.getX(), this.getY() - 1) instanceof Factory) ||
                (gameBoard.getTileAtPosition(this.getX(), this.getY() + 1) instanceof Factory) ||
                (gameBoard.getTileAtPosition(this.getX() - 1, this.getY()) instanceof Factory) ||
                (gameBoard.getTileAtPosition(this.getX() + 1, this.getY()) instanceof Factory);
    }
}
