/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentanivarthi.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPieceType;

/**
 * Test cases for Hanto player piece manager.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerPieceManagerImplTest {
    HantoPlayerPieceManager manager;
    HantoPlayerPieceManager emptyManager;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        manager = new HantoPlayerPieceManagerImpl(2, 2, 2, 2, 2, 2);
        emptyManager = new HantoPlayerPieceManagerImpl(0, 0, 0, 0, 0, 0);
    }

    /**
     * Checks if the butterfly can be placed.
     */
    @Test
    public void checkButterfly() {
        assertTrue(manager.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Checks if the butterfly can be placed with an empty manager.
     */
    @Test
    public void checkButterflyEmptyManager() {
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Checks if the crab can be placed.
     */
    @Test
    public void checkCrab() {
        assertTrue(manager.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Checks if the crab can be placed with an empty manager.
     */
    @Test
    public void checkCrabEmptyManager() {
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Checks if the crane can be placed.
     */
    @Test
    public void checkCrane() {
        assertTrue(manager.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Checks if the crane can be placed with an empty manager.
     */
    @Test
    public void checkCraneEmptyManager() {
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Checks if the dove can be placed.
     */
    @Test
    public void checkDove() {
        assertTrue(manager.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Checks if the dove can be placed with an empty manager.
     */
    @Test
    public void checkDoveEmptyManager() {
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Checks if the horse can be placed.
     */
    @Test
    public void checkHorse() {
        assertTrue(manager.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Checks if the horse can be placed with an empty manager.
     */
    @Test
    public void checkHorseEmptyManager() {
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Checks if the sparrow can be placed.
     */
    @Test
    public void checkSparrow() {
        assertTrue(manager.canPlacePiece(HantoPieceType.SPARROW));
    }

    /**
     * Checks if the sparrow can be placed with an empty manager.
     */
    @Test
    public void checkSparrowEmptyManager() {
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.SPARROW));
    }

    /**
     * Checks if the player is out of pieces to play.
     */
    @Test
    public void checkIsOutOfPieces() {
        assertFalse(manager.isOutOfPieces());
    }

    /**
     * Checks if the empty manager player is out of pieces to play.
     */
    @Test
    public void checkIsOutOfPiecesEmptyManager() {
        assertTrue(emptyManager.isOutOfPieces());
    }

    /**
     * Place and check if the butterfly can be placed.
     */
    @Test
    public void placeAndCheckButterfly() {
        manager.placePiece(HantoPieceType.BUTTERFLY);
        assertTrue(manager.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Place and check if the butterfly can be placed from the empty manager.
     */
    @Test
    public void placeAndCheckButterflyEmptyManager() {
        emptyManager.placePiece(HantoPieceType.BUTTERFLY);
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.BUTTERFLY));
    }

    /**
     * Place and check if the crab can be placed.
     */
    @Test
    public void placeAndCheckCrab() {
        manager.placePiece(HantoPieceType.CRAB);
        assertTrue(manager.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Place and check if the crab can be placed from the empty manager.
     */
    @Test
    public void placeAndCheckCrabEmptyManager() {
        emptyManager.placePiece(HantoPieceType.CRAB);
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.CRAB));
    }

    /**
     * Place and check if the crane can be placed.
     */
    @Test
    public void placeAndCheckCrane() {
        manager.placePiece(HantoPieceType.CRANE);
        assertTrue(manager.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Place and check if the crane can be placed from the empty manager.
     */
    @Test
    public void placeAndCheckCraneEmptyManager() {
        emptyManager.placePiece(HantoPieceType.CRANE);
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.CRANE));
    }

    /**
     * Place and check if the dove can be placed.
     */
    @Test
    public void placeAndCheckDove() {
        manager.placePiece(HantoPieceType.DOVE);
        assertTrue(manager.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Place and check if the dove can be placed from the empty manager.
     */
    @Test
    public void placeAndCheckDoveEmptyManager() {
        emptyManager.placePiece(HantoPieceType.DOVE);
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.DOVE));
    }

    /**
     * Place and check if the horse can be placed.
     */
    @Test
    public void placeAndCheckHorse() {
        manager.placePiece(HantoPieceType.HORSE);
        assertTrue(manager.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Place and check if the horse can be placed from the empty manager.
     */
    @Test
    public void placeAndCheckHorseEmptyManager() {
        emptyManager.placePiece(HantoPieceType.HORSE);
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.HORSE));
    }

    /**
     * Place and check if the sparrow can be placed.
     */
    @Test
    public void placeAndCheckSparrow() {
        manager.placePiece(HantoPieceType.SPARROW);
        assertTrue(manager.canPlacePiece(HantoPieceType.SPARROW));
    }

    /**
     * Place and check if the sparrow can be placed from the empty manager.
     */
    @Test
    public void placeAndCheckSparrowEmptyManager() {
        emptyManager.placePiece(HantoPieceType.SPARROW);
        assertFalse(emptyManager.canPlacePiece(HantoPieceType.SPARROW));
    }
}
