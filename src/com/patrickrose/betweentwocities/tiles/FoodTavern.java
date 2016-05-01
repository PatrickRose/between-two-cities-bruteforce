package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

public class FoodTavern extends AbstractTavern {

    public static final char LOAD_CHARACTER = 'f';

    public FoodTavern(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);

        this.setTavernType(TavernTypes.FOOD);
    }

}
