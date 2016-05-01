package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

public class SleepTavern extends AbstractTavern {

    public static final char LOAD_CHARACTER = 's';

    public SleepTavern(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);

        this.setTavernType(TavernTypes.SLEEP);
    }

}
