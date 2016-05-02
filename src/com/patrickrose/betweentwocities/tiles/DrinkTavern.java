package com.patrickrose.betweentwocities.tiles;


import com.patrickrose.betweentwocities.GameBoard;

public class DrinkTavern extends AbstractTavern {

    public static final int LOAD_CHARACTER = 3;

    public DrinkTavern(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);

        this.setTavernType(TavernTypes.DRINK);
    }

}
