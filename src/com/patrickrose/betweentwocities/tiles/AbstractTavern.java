package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;
import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class AbstractTavern extends AbstractTile {

    private TavernTypes tavernType;

    protected enum TavernTypes {
        FOOD,
        MUSIC,
        DRINK,
        SLEEP
    }

    public AbstractTavern(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);
    }

    public static AbstractTavern getTavernOfType(TavernTypes tavernType, GameBoard gameBoard, int x, int y) throws IllegalArgumentException {
        switch(tavernType) {
            case FOOD:
                return new FoodTavern(gameBoard, x, y);
            case MUSIC:
                return new MusicTavern(gameBoard, x, y);
            case DRINK:
                return new DrinkTavern(gameBoard, x, y);
            case SLEEP:
                return new SleepTavern(gameBoard, x, y);
            default:
                throw new IllegalArgumentException("Unknown tavern type");
        }
    }

    @Override
    public int getScore() {
        int numberOfTypesInSet = getNumberOfTypesInSet();

        switch (numberOfTypesInSet) {
            case 1:
                return 1;
            case 2:
                return 4;
            case 3:
                return 9;
            case 4:
                return 17;
            default:
                return 0;
        }
    }

    private int getNumberOfTypesInSet() {
        int numberOfTypesInSet = 1;
        List<TavernTypes> foundTypes = new ArrayList<>(4);

        foundTypes.add(this.getTavernType());
        this.markAsScored();

        for (int x = 0; x < GameBoard.ROWS; x++) {
            for (int y = 0; y < GameBoard.COLS; y++) {
                if (!(this.getGameBoard().getTileAtPosition(x, y) instanceof AbstractTavern)) {
                    // Not a tavern, don't care about it
                    continue;
                }

                AbstractTavern tile = (AbstractTavern) this.getGameBoard().getTileAtPosition(x, y);

                if (tile.hasBeenScored()) {
                    continue;
                }

                if (foundTypes.contains(tile.getTavernType())) {
                    continue;
                }

                tile.markAsScored();
                foundTypes.add(tile.getTavernType());
                numberOfTypesInSet++;

                if (numberOfTypesInSet == TavernTypes.values().length) {
                    // Must have all of them, so we can stop
                    return numberOfTypesInSet;
                }
            }
        }

        return numberOfTypesInSet;
    }

    public TavernTypes getTavernType() {
        return tavernType;
    }

    protected void setTavernType(TavernTypes tavernType) {
        this.tavernType = tavernType;
    }
}
