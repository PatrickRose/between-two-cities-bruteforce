package com.patrickrose.betweentwocities.tiles;

import com.patrickrose.betweentwocities.GameBoard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract tile
 */
abstract public class AbstractTile {

    protected static final Map<Integer, String> CLASS_MAP;

    static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(Shop.LOAD_CHARACTER, Shop.class.getName());
        aMap.put(Factory.LOAD_CHARACTER, Factory.class.getName());
        aMap.put(SleepTavern.LOAD_CHARACTER, SleepTavern.class.getName());
        aMap.put(DrinkTavern.LOAD_CHARACTER, DrinkTavern.class.getName());
        aMap.put(FoodTavern.LOAD_CHARACTER, FoodTavern.class.getName());
        aMap.put(MusicTavern.LOAD_CHARACTER, MusicTavern.class.getName());
        aMap.put(Office.LOAD_CHARACTER, Office.class.getName());
        aMap.put(Park.LOAD_CHARACTER, Park.class.getName());
        aMap.put(House.LOAD_CHARACTER, House.class.getName());

        CLASS_MAP = Collections.unmodifiableMap(aMap);
    }


    /**
     * The game board that this tile is on
     */
    private final GameBoard gameBoard;

    /**
     * The x location this tile is located on the above game board
     */
    private final int x;

    /**
     * The y location this tile is located on the above game board
     */
    private final int y;

    /**
     * Whether this has been scored
     */
    private boolean hasBeenScored = false;

    /**
     * Useful for unit tests
     */
    public AbstractTile() {
        this.gameBoard = null;
        this.x = 0;
        this.y = 0;
    }

    public AbstractTile(GameBoard gameBoard, int x, int y) {
        this.gameBoard = gameBoard;
        this.x = x;
        this.y = y;
    }

    /**
     * Create a tile for the given tile
     * @param character The correct tile
     * @return An abstract tile
     * @throws IllegalArgumentException If the character cannot be converted to a tile
     */
    public static AbstractTile getTileForCharacter(int character, GameBoard gameBoard, int x, int y) throws IllegalArgumentException {
        String className = CLASS_MAP.get(character);

        if (className == null) {
            throw new IllegalArgumentException("Could not convert " + character + " to a tile");
        }

        try {
            return (AbstractTile) Class.forName(className).getConstructor(GameBoard.class, int.class, int.class).newInstance(gameBoard, x, y);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert " + character + " to a tile", e);
        }
    }

    /**
     * Calculate the score for this tile on this board
     *
     * @return The score for this tile
     */
    abstract public int getScore();

    /**
     * Mark this tile as scored
     */
    public void markAsScored() {
        this.hasBeenScored = true;
    }

    /**
     * Returns if this tile has been scored
     *
     * @return True if this tile has been scored. Otherwise false.
     */
    public boolean hasBeenScored() {
        return this.hasBeenScored;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
