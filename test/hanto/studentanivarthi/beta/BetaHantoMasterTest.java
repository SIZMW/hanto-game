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
import hanto.common.HantoPieceType;
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

        /**
         * Creates a TestHantoCoordinate instance with the specified x and y.
         *
         * @param x
         *            The x.
         * @param y
         *            The y.
         */
        TestHantoCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /*
         * @see hanto.common.HantoCoordinate#getX()
         */
        @Override
        public int getX() {
            return x;
        }

        /*
         * @see hanto.common.HantoCoordinate#getY()
         */
        @Override
        public int getY() {
            return y;
        }
    }

    private static HantoGameFactory factory = null;

    /**
     * Initialize entities.
     */
    @BeforeClass
    public static void initializeClass() {
        factory = HantoGameFactory.getInstance();
    }

    private HantoGame game;

    /**
     * Setup
     */
    @Before
    public void setup() {
        // By default, blue moves first.
        game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
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
     * Tests placing blue butterfly not at the origin.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 3
    public void bluePlacesInitialButterflyNotAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(2, 1));
        assertEquals(OK, mr);
    }

    /**
     * Tests placing red butterfly not next to the blue butterfly, which is at
     * the origin.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 4
    public void redPlacesInitialCrabNotNextToOrigin() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);
        game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(1, 1));
    }

    /**
     * Tests placing blue crab in this variation of Hanto.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 5
    public void bluePlacesInitialCrabAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
    }

    /**
     * Tests placing a red crab in this variation of Hanto.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesInitialCrabAfterValidBlue() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);
        game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 1));
    }

    /**
     * Tests placing three blue sparrows and two red sparrows.
     *
     * @throws HantoException
     */
    @Test // 7
    public void bluePlacesThreeSparrowsAndIsValid() throws HantoException {
        for (int i = 0; i < 5; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));
            if (i == 0 || i % 2 == 0) {
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
    @Test // 8
    public void redPlacesThreeSparrowsAndIsValid() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);

        for (int i = 1; i < 4; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));

            if (i == 0 || i % 2 == 0) {
                assertEquals(BLUE, p.getColor());
            } else {
                assertEquals(RED, p.getColor());
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
    @Test(expected = HantoException.class) // 9
    public void bluePlacesFourSparrowsAndFails() throws HantoException {
        for (int i = 0; i < 9; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));
            if (i == 0 || i % 2 == 0) {
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
    @Test(expected = HantoException.class) // 10
    public void redPlacesFourSparrowsAndFails() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);
        final HantoPiece pB = game.getPieceAt(makeCoordinate(0, 0));
        assertEquals(BLUE, pB.getColor());

        for (int i = 1; i < 10; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            assertEquals(OK, mr);
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));

            if (i == 0 || i % 2 == 0) {
                assertEquals(BLUE, p.getColor());
            } else {
                assertEquals(RED, p.getColor());
            }
            assertEquals(SPARROW, p.getType());
        }
    }

    /**
     * Test the results from the game after red wins.
     *
     * @throws HantoException
     */
    @Test // 11
    public void redWinsGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, -1));
        assertEquals(MoveResult.RED_WINS, mr);
    }

    /**
     * Test the results from the game after blue wins.
     *
     * @throws HantoException
     */
    @Test // 11
    public void blueWinsGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, -1));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, -1));
        assertEquals(MoveResult.BLUE_WINS, mr);
    }

    /**
     * Tests placing a piece after the game is over.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 13
    public void attemptToMoveAfterGameEnds() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);
        final MoveResult mrR = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mrR);

        for (int i = 2; i < 12; i++) {
            final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, i));
            final HantoPiece p = game.getPieceAt(makeCoordinate(0, i));

            if (i < 11) {
                assertEquals(OK, mr);
            } else {
                assertEquals(MoveResult.DRAW, mr);
            }

            if (i == 0 || i % 2 == 0) {
                assertEquals(BLUE, p.getColor());
            } else {
                assertEquals(RED, p.getColor());
            }
            assertEquals(SPARROW, p.getType());
        }

        game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 12));
    }

    /**
     * Tests the print out of the game board after placing one butterfly.
     *
     * @throws HantoException
     */
    @Test // 14
    public void checkPrintableBoard() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
        assertEquals(
                "HantoCoordinateImpl [x=0, y=0]: HantoPieceImpl [color=BLUE, type=Butterfly]\n",
                game.getPrintableBoard());
    }

    /**
     * Test the results from the game after both blue and red win.
     *
     * @throws HantoException
     */
    @Test // 15
    public void blueRedDrawGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(2, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, -1));
        assertEquals(MoveResult.DRAW, mr);
    }

    /**
     * Tests placing a piece where there already is one, and getting an
     * exception.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 16
    public void placePieceInOccupiedSpot() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
    }

    /**
     * Tests placing red butterfly at the origin as the first move. Game is
     * created with red being the first player.
     *
     * @throws HantoException
     */
    @Test // 17
    public void redStartsAndPlacesInitialButterflyAtOrigin() throws HantoException {
        game = factory.makeHantoGame(HantoGameID.BETA_HANTO, RED);
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
        final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
        assertEquals(RED, p.getColor());
        assertEquals(BUTTERFLY, p.getType());
    }
}
