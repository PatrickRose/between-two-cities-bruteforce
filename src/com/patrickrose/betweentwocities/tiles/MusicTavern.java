package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

public class MusicTavern extends AbstractTavern {

    public static final int LOAD_CHARACTER = 5;

    public MusicTavern(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);

        this.setTavernType(TavernTypes.MUSIC);
    }

}
