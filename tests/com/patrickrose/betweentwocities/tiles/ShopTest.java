package com.patrickrose.betweentwocities.tiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShopTest extends BaseTileTest {

    @Test
    public void getScore() {
        Shop shop = new Shop(this.gameBoard, 0, 0);

        this.tiles[0][0] = shop;
        assertEquals(2, shop.getScore());
        assertTrue("Tile was not marked as scored", shop.hasBeenScored());
    }

    @Test
    public void getScoreForTwoInRow() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 0, 1);

        this.tiles[0][0] = shop;
        this.tiles[0][1] = shop2;
        assertEquals(5, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Second tile was not marked as scored", shop2.hasBeenScored());
    }

    @Test
    public void getScoreForThreeInRow() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 0, 1);
        Shop shop3 = new Shop(this.gameBoard, 0, 2);

        this.tiles[0][0] = shop;
        this.tiles[0][1] = shop2;
        this.tiles[0][2] = shop3;
        assertEquals(10, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Second tile was not marked as scored", shop2.hasBeenScored());
        assertTrue("Third tile was not marked as scored", shop3.hasBeenScored());
    }

    @Test
    public void getScoreForFourInRow() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 0, 1);
        Shop shop3 = new Shop(this.gameBoard, 0, 2);
        Shop shop4 = new Shop(this.gameBoard, 0, 3);

        this.tiles[0][0] = shop;
        this.tiles[0][1] = shop2;
        this.tiles[0][2] = shop3;
        this.tiles[0][3] = shop4;
        assertEquals(16, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Second tile was not marked as scored", shop2.hasBeenScored());
        assertTrue("Third tile was not marked as scored", shop3.hasBeenScored());
        assertTrue("Fourth tile was not marked as scored", shop4.hasBeenScored());
    }

    @Test
    public void getScoreForTwoInColumn() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 1, 0);

        this.tiles[0][0] = shop;
        this.tiles[1][0] = shop2;
        assertEquals(5, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Second tile was not marked as scored", shop2.hasBeenScored());
    }

    @Test
    public void getScoreForThreeInColumn() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 1, 0);
        Shop shop3 = new Shop(this.gameBoard, 2, 0);

        this.tiles[0][0] = shop;
        this.tiles[1][0] = shop2;
        this.tiles[2][0] = shop3;
        assertEquals(10, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Second tile was not marked as scored", shop2.hasBeenScored());
        assertTrue("Third tile was not marked as scored", shop3.hasBeenScored());
    }

    @Test
    public void getScoreForFourInColumn() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 1, 0);
        Shop shop3 = new Shop(this.gameBoard, 2, 0);
        Shop shop4 = new Shop(this.gameBoard, 3, 0);

        this.tiles[0][0] = shop;
        this.tiles[1][0] = shop2;
        this.tiles[2][0] = shop3;
        this.tiles[3][0] = shop4;
        assertEquals(16, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Second tile was not marked as scored", shop2.hasBeenScored());
        assertTrue("Third tile was not marked as scored", shop3.hasBeenScored());
        assertTrue("Fourth tile was not marked as scored", shop4.hasBeenScored());
    }

    @Test
    public void takesTheHighestScore() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 1, 0);
        Shop shop3 = new Shop(this.gameBoard, 2, 0);
        Shop shop4 = new Shop(this.gameBoard, 0, 1);

        this.tiles[0][0] = shop;
        this.tiles[1][0] = shop2;
        this.tiles[2][0] = shop3;
        this.tiles[0][1] = shop4;
        assertEquals(10, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Second tile was not marked as scored", shop2.hasBeenScored());
        assertTrue("Third tile was not marked as scored", shop3.hasBeenScored());
        assertFalse("Fourth tile was marked as scored", shop4.hasBeenScored());
    }

    @Test
    public void doesntUseTheSameTileTwice() {
        Shop shop = new Shop(this.gameBoard, 0, 0);
        Shop shop2 = new Shop(this.gameBoard, 1, 0);
        shop2.markAsScored();
        Shop shop3 = new Shop(this.gameBoard, 2, 0);
        shop3.markAsScored();
        Shop shop4 = new Shop(this.gameBoard, 0, 1);

        this.tiles[0][0] = shop;
        this.tiles[1][0] = shop2;
        this.tiles[2][0] = shop3;
        this.tiles[0][1] = shop4;
        assertEquals(5, shop.getScore());
        assertTrue("Base tile was not marked as scored", shop.hasBeenScored());
        assertTrue("Fourth tile was marked as scored", shop4.hasBeenScored());
    }
}