/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.playerturn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Test cases for {@link HantoPlayerTurnImpl}.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerTurnImplTest {
    HantoPlayerTurn playerTurn;
    HantoPlayerTurn emptyPlayerTurn;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        playerTurn = new HantoPlayerTurnImpl(HantoPlayerColor.BLUE, 2, 2, 2, 2, 2, 2);
        emptyPlayerTurn = new HantoPlayerTurnImpl(HantoPlayerColor.RED, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Checks if the BUTTERFLY can be placed.
     */
    @Test // 1
    public void checkButterfly() {
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Checks if the BUTTERFLY can be placed with an empty playerTurn.
     */
    @Test // 2
    public void checkButterflyEmptyManager() {
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Checks if the CRAB can be placed.
     */
    @Test // 3
    public void checkCrab() {
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Checks if the CRAB can be placed with an empty playerTurn.
     */
    @Test // 4
    public void checkCrabEmptyManager() {
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Checks if the CRANE can be placed.
     */
    @Test // 5
    public void checkCrane() {
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Checks if the CRANE can be placed with an empty playerTurn.
     */
    @Test // 6
    public void checkCraneEmptyManager() {
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Checks if the DOVE can be placed.
     */
    @Test // 7
    public void checkDove() {
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Checks if the DOVE can be placed with an empty playerTurn.
     */
    @Test // 8
    public void checkDoveEmptyManager() {
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Checks if the HORSE can be placed.
     */
    @Test // 9
    public void checkHorse() {
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Checks if the HORSE can be placed with an empty playerTurn.
     */
    @Test // 10
    public void checkHorseEmptyManager() {
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Checks if the SPARROW can be placed.
     */
    @Test // 11
    public void checkSparrow() {
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.SPARROW));
    }

    /**
     * Checks if the SPARROW can be placed with an empty playerTurn.
     */
    @Test // 12
    public void checkSparrowEmptyManager() {
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.SPARROW));
    }

    /**
     * Checks if the player is out of pieces to play.
     */
    @Test // 13
    public void checkIsOutOfPieces() {
        assertFalse(playerTurn.isOutOfPieces());
    }

    /**
     * Checks if the empty playerTurn player is out of pieces to play.
     */
    @Test // 14
    public void checkIsOutOfPiecesEmptyManager() {
        assertTrue(emptyPlayerTurn.isOutOfPieces());
    }

    /**
     * Place and check if the BUTTERFLY can be placed.
     */
    @Test // 15
    public void placeAndCheckButterfly() {
        playerTurn.placePiece(HantoPieceType.BUTTERFLY);
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Place and check if the BUTTERFLY can be placed from the empty playerTurn.
     */
    @Test // 16
    public void placeAndCheckButterflyEmptyManager() {
        emptyPlayerTurn.placePiece(HantoPieceType.BUTTERFLY);
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Place and check if the CRAB can be placed.
     */
    @Test // 17
    public void placeAndCheckCrab() {
        playerTurn.placePiece(HantoPieceType.CRAB);
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Place and check if the CRAB can be placed from the empty playerTurn.
     */
    @Test // 18
    public void placeAndCheckCrabEmptyManager() {
        emptyPlayerTurn.placePiece(HantoPieceType.CRAB);
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Place and check if the CRANE can be placed.
     */
    @Test // 19
    public void placeAndCheckCrane() {
        playerTurn.placePiece(HantoPieceType.CRANE);
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Place and check if the CRANE can be placed from the empty playerTurn.
     */
    @Test // 20
    public void placeAndCheckCraneEmptyManager() {
        emptyPlayerTurn.placePiece(HantoPieceType.CRANE);
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Place and check if the DOVE can be placed.
     */
    @Test // 21
    public void placeAndCheckDove() {
        playerTurn.placePiece(HantoPieceType.DOVE);
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Place and check if the DOVE can be placed from the empty playerTurn.
     */
    @Test // 22
    public void placeAndCheckDoveEmptyManager() {
        emptyPlayerTurn.placePiece(HantoPieceType.DOVE);
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Place and check if the HORSE can be placed.
     */
    @Test // 23
    public void placeAndCheckHorse() {
        playerTurn.placePiece(HantoPieceType.HORSE);
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Place and check if the HORSE can be placed from the empty playerTurn.
     */
    @Test // 24
    public void placeAndCheckHorseEmptyManager() {
        emptyPlayerTurn.placePiece(HantoPieceType.HORSE);
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Place and check if the SPARROW can be placed.
     */
    @Test // 25
    public void placeAndCheckSparrow() {
        playerTurn.placePiece(HantoPieceType.SPARROW);
        assertTrue(playerTurn.canPlacePiece(HantoPieceType.SPARROW));
    }

    /**
     * Place and check if the SPARROW can be placed from the empty playerTurn.
     */
    @Test // 26
    public void placeAndCheckSparrowEmptyManager() {
        emptyPlayerTurn.placePiece(HantoPieceType.SPARROW);
        assertFalse(emptyPlayerTurn.canPlacePiece(HantoPieceType.SPARROW));
    }
}
