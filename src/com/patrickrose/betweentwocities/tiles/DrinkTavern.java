package com.patrickrose.betweentwocities.tiles;


import com.patrickrose.betweentwocities.GameBoard;

public class DrinkTavern extends AbstractTavern {

    public DrinkTavern(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);

        this.setTavernType(TavernTypes.DRINK);
    }

}
