/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentanivarthi.beta;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.MoveResult;
import hanto.studentanivarthi.HantoGameFactory;

/**
 * Test cases for Beta Hanto.
 *
 * @version Sep 14, 2014
 */
public class BetaHantoMasterTest {
    /**
     * Internal class for these test cases.
     *
     * @version Sep 13, 2014
     */
    class TestHantoCoordinate implements HantoCoordinate {
        private final int x, y;

        public TestHantoCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * @see hanto.common.HantoCoordinate#getX()
         */
        @Override
        public int getX() {
            return x;
        }

        /**
         * @see hanto.common.HantoCoordinate#getY()
         */
        @Override
        public int getY() {
            return y;
        }
    }

    private static HantoGameFactory factory;

    /**
     * Initialize entities.
     */
    @BeforeClass
    public static void initializeClass() {
        factory = HantoGameFactory.getInstance();
    }

    private HantoGame game;

    @Before
    public void setup() {
        // By default, blue moves first.
        game = factory.makeHantoGame(HantoGameID.BETA_HANTO, BLUE);
    }

    // Helper methods
    private HantoCoordinate makeCoordinate(int x, int y) {
        return new TestHantoCoordinate(x, y);
    }

    /**
     * Tests placing blue butterfly at the origin as the first move.
     *
     * @throws HantoException
     */
    @Test // 1
    public void bluePlacesInitialButterflyAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
        final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());
    }

    /**
     * Tests placing red butterfly next to the blue butterfly which is at the
     * origin.
     *
     * @throws HantoException
     */
    @Test // 2
    public void redPlacesValidButterflyAfterBlue() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);
        final MoveResult mrR = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mrR);
    }

    /**
     * Tests placing three blue sparrows and two red sparrows.
     *
     * @throws HantoException
     */
    @Test // 3
    public void bluePlacesThreeSparrowsAndIsValid() throws HantoException {
        for (int i = 0; i < 5; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));
            if ((i == 0) || ((i % 2) == 0)) {
                assertEquals(BLUE, p.getColor());
            } else {
                assertEquals(RED, p.getColor());
            }
            assertEquals(SPARROW, p.getType());
        }
    }

    /**
     * Tests placing three red sparrows and two blue sparrows.
     *
     * @throws HantoException
     */
    @Test // 4
    public void redPlacesThreeSparrowsAndIsValid() throws HantoException {
        for (int i = 0; i < 3; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));
            if ((i == 0) || ((i % 2) == 0)) {
                assertEquals(RED, p.getColor());
            } else {
                assertEquals(BLUE, p.getColor());
            }
            assertEquals(SPARROW, p.getType());
        }
    }

    /**
     * Tests placing four blue sparrows and three red sparrows, which throws an
     * exception since the blue butterfly was not placed by the fourth turn.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 5
    public void bluePlacesFourSparrowsAndFails() throws HantoException {
        for (int i = 0; i < 7; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));
            if ((i == 0) || ((i % 2) == 0)) {
                assertEquals(BLUE, p.getColor());
            } else {
                assertEquals(RED, p.getColor());
            }
            assertEquals(SPARROW, p.getType());
        }
    }

    /**
     * Tests placing four red sparrows and three blue sparrows, which throws an
     * exception since the red butterfly was not placed by the fourth turn.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesFourSparrowsAndFails() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);
        final HantoPiece pB = game.getPieceAt(makeCoordinate(0, 0));
        assertEquals(BLUE, pB.getColor());
        assertEquals(RED, pB.getColor());

        for (int i = 1; i < 8; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));
            if ((i == 0) || ((i % 2) == 0)) {
                assertEquals(RED, p.getColor());
            } else {
                assertEquals(BLUE, p.getColor());
            }
            assertEquals(SPARROW, p.getType());
        }
    }

    /**
     * Tests placing a piece after the game is over.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 7
    public void attemptToMoveAfterGameEnds() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);
        final MoveResult mrR = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mrR);

        for (int i = 2; i < 12; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));
            if ((i == 0) || ((i % 2) == 0)) {
                assertEquals(BLUE, p.getColor());
            } else {
                assertEquals(RED, p.getColor());
            }
            assertEquals(SPARROW, p.getType());
        }

        game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 12));
    }
}
