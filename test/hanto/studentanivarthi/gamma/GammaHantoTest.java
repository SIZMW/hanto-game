/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.gamma;

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
// TODO Comments and formatting

/**
 * Tests for Gamma Hanto.
 */
public class GammaHantoTest {
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

        /**
         * @see {@link hanto.common.HantoCoordinate#getX()}
         */
        @Override
        public int getX() {
            return x;
        }

        /**
         * @see {@link hanto.common.HantoCoordinate#getY()}
         */
        @Override
        public int getY() {
            return y;
        }
    }

    private static HantoGameFactory factory = null;
    private HantoGame game;

    /**
     * Initialize entities.
     */
    @BeforeClass
    public static void initializeClass() {
        factory = HantoGameFactory.getInstance();
    }

    /**
     * Setup.
     */
    @Before
    public void setup() {
        // By default, blue moves first.
        game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO);
    }

    /**
     * Creates a new coordinate with the specified X and Y.
     *
     * @param x
     *            The x coordinate.
     * @param y
     *            The y coordinate.
     * @return a {@link TestHantoCoordinate}
     */
    private HantoCoordinate makeCoordinate(int x, int y) {
        return new TestHantoCoordinate(x, y);
    }

    /**
     * Tests placing blue butterfly at the origin as the first move.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 1
    public void bluePlacesInitialButterflyAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());
    }

    /**
     * Tests placing red butterfly next to the blue butterfly which is at the
     * origin.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 2
    public void redPlacesValidButterflyAfterBlue() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);
    }

    /**
     * Tests placing blue butterfly not at the origin.
     *
     * @throws HantoException
     *             Since the first move is not at the origin.
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
    public void redPlacesInitialButterflyNotNextToOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

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
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 1));
    }

    /**
     * Tests placing three blue sparrows and two red sparrows.
     *
     * @throws HantoException
     */
    @Test // 7
    public void bluePlacesThreeSparrowsAndIsValid() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());
    }

    /**
     * Tests placing three red sparrows, a blue butterfly and two blue sparrows.
     *
     * @throws HantoException
     */
    @Test // 8
    public void redPlacesThreeSparrowsAndIsValid() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -3));
        p = game.getPieceAt(makeCoordinate(0, -3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());
    }

    /**
     * Tests placing four blue sparrows and three red sparrows, which throws an
     * exception since the blue butterfly was not placed by the fourth turn.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 9
    public void bluePlacesFourSparrowsAndFails() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -3));
        p = game.getPieceAt(makeCoordinate(0, -3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 4, fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
    }

    /**
     * Tests placing four red sparrows and three blue sparrows, which throws an
     * exception since the red butterfly was not placed by the fourth turn.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 12
    public void redPlacesFourSparrowsAndFails() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -3));
        p = game.getPieceAt(makeCoordinate(0, -3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 4
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -4));
    }

    /**
     * Tests placing a piece where there already is one, and getting an
     * exception.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 13
    public void placePieceInOccupiedSpot() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Place on same coordinate
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
    }

    /**
     * Tests placing a piece where there already is one, but not within the
     * first two moves of the game, and getting an exception.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 14
    public void placePieceLaterInOccupiedSpot() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
    }

    /**
     * Tests placing a piece next to the opponent's piece, which throws an
     * exception.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 15
    public void bluePlacesPieceNextToOpponentPiece() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 2, fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
    }

    /**
     * Tests the print out of the game board after placing one butterfly.
     *
     * @throws HantoException
     */
    @Test // 16
    public void checkPrintableBoard() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Get the printable board
        assertEquals(
                "HantoCoordinateImpl [x = 0, y = 0]: HantoPieceImpl [color=BLUE, type=Butterfly]\n",
                game.getPrintableBoard());
    }

    /**
     * Tests playing the game to 20 turns, where no one wins and it is called a
     * draw.
     *
     * @throws HantoException
     */
    @Test // 17
    public void gameRunsToTwentyTurnsAndDraw() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -3));
        p = game.getPieceAt(makeCoordinate(0, -3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 4
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -4));
        p = game.getPieceAt(makeCoordinate(0, -4));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 5
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        p = game.getPieceAt(makeCoordinate(0, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -5));
        p = game.getPieceAt(makeCoordinate(0, -5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 6
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 5));
        p = game.getPieceAt(makeCoordinate(0, 5));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -6));
        p = game.getPieceAt(makeCoordinate(0, -6));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 7, start moves
        mr = game.makeMove(SPARROW, makeCoordinate(0, 5), makeCoordinate(1, 4));
        p = game.getPieceAt(makeCoordinate(1, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(0, -6), makeCoordinate(1, -6));
        p = game.getPieceAt(makeCoordinate(1, -6));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 8
        mr = game.makeMove(SPARROW, makeCoordinate(1, 4), makeCoordinate(1, 3));
        p = game.getPieceAt(makeCoordinate(1, 3));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -6), makeCoordinate(1, -5));
        p = game.getPieceAt(makeCoordinate(1, -5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 9
        mr = game.makeMove(SPARROW, makeCoordinate(1, 3), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -5), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 10
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 11
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 12
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 13
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 14
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 15
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 16
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 17
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 18
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 19
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 20
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        // Draw game
        assertEquals(MoveResult.DRAW, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());
    }

    /**
     * Tests playing the game to 20 turns, where no one wins and it is called a
     * draw, then trying another move which fails.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 18
    public void makeMoveAfterGameEndsInDrawByGoingToTwentyTurns() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -3));
        p = game.getPieceAt(makeCoordinate(0, -3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 4
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -4));
        p = game.getPieceAt(makeCoordinate(0, -4));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 5
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        p = game.getPieceAt(makeCoordinate(0, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -5));
        p = game.getPieceAt(makeCoordinate(0, -5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 6
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 5));
        p = game.getPieceAt(makeCoordinate(0, 5));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -6));
        p = game.getPieceAt(makeCoordinate(0, -6));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 7, start moves
        mr = game.makeMove(SPARROW, makeCoordinate(0, 5), makeCoordinate(1, 4));
        p = game.getPieceAt(makeCoordinate(1, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(0, -6), makeCoordinate(1, -6));
        p = game.getPieceAt(makeCoordinate(1, -6));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 8
        mr = game.makeMove(SPARROW, makeCoordinate(1, 4), makeCoordinate(1, 3));
        p = game.getPieceAt(makeCoordinate(1, 3));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -6), makeCoordinate(1, -5));
        p = game.getPieceAt(makeCoordinate(1, -5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 9
        mr = game.makeMove(SPARROW, makeCoordinate(1, 3), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -5), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 10
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 11
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 12
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 13
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 14
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 15
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 16
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 17
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 18
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 19
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 2));
        p = game.getPieceAt(makeCoordinate(1, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -4));
        p = game.getPieceAt(makeCoordinate(1, -4));

        assertEquals(MoveResult.OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 20
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
        p = game.getPieceAt(makeCoordinate(1, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, makeCoordinate(1, -4), makeCoordinate(1, -3));
        p = game.getPieceAt(makeCoordinate(1, -3));

        // Draw game
        assertEquals(MoveResult.DRAW, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Fails
        mr = game.makeMove(SPARROW, makeCoordinate(1, 2), makeCoordinate(1, 1));
    }

    /**
     * Tests making a move that is invalid because it makes the board not
     * contiguous, which throws an exception.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 19
    public void makeInvalidMoveMakesGameBoardNotContiguous() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3, fails
        mr = game.makeMove(SPARROW, makeCoordinate(0, 1), makeCoordinate(1, 1));
    }

    /**
     * Tests placing red butterfly at the origin as the first move. Game is
     * created with red being the first player.
     *
     * @throws HantoException
     */
    @Test // 20
    public void redStartsAndPlacesInitialButterflyAtOrigin() throws HantoException {
        game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO, RED);

        // Turn 1
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(BUTTERFLY, p.getType());
    }

    /**
     * Test the results from the game after both blue and red win by surrounding
     * each other.
     *
     * @throws HantoException
     */
    @Test // 21
    public void blueRedDrawGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Surround both red and blue butterfly pieces, which start next to each
        // other
        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, -1));
        assertEquals(OK, mr);

        // Turn 4
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 5
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, -2));
        assertEquals(OK, mr);

        // Move blue piece to surround the butterflies
        // Turn 6
        mr = game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Move red piece to surround both the butterflies
        // Draw game
        mr = game.makeMove(SPARROW, makeCoordinate(2, -2), makeCoordinate(1, -1));
        assertEquals(MoveResult.DRAW, mr);
    }

    /**
     * Test the results from the game after red wins.
     *
     * @throws HantoException
     */
    @Test // 22
    public void redWinsGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Surround the blue butterfly
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(-2, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, -1));
        assertEquals(OK, mr);

        // Turn 4
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        // Move red piece closer to blue butterfly
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Move blue piece closer to blue butterfly
        // Turn 5
        mr = game.makeMove(SPARROW, makeCoordinate(-2, 1), makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        // Red wins
        mr = game.makeMove(SPARROW, makeCoordinate(2, -1), makeCoordinate(1, -1));
        assertEquals(MoveResult.RED_WINS, mr);
    }

    /**
     * Test the results from the game after blue wins.
     *
     * @throws HantoException
     */
    @Test // 23
    public void blueWinsGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Surround the red butterfly
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, -1));
        assertEquals(OK, mr);

        // Move blue piece closer to red butterfly
        // Turn 4
        mr = game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(1, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Blue wins
        mr = game.makeMove(SPARROW, makeCoordinate(-1, 1), makeCoordinate(0, 1));
        assertEquals(MoveResult.BLUE_WINS, mr);
    }

    /**
     * Tests trying to make a piece walk to a location that is not one
     * coordinate away.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 24
    public void walkPieceTwoCoordinatesAway() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 3));
    }

    /**
     * Tests trying to make a piece walk to a location that is occupied.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 25
    public void walkPieceToOccupiedCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
    }

    /**
     * Tests trying to make a piece walk and passing a location that is empty as
     * the source.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 26
    public void walkPieceFromEmptyCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, -1), makeCoordinate(0, 0));
    }

    /**
     * Tests trying to make a piece walk without placing the butterfly on the
     * board.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 27
    public void walkPieceWithoutPlacingButterfly() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, 1));
    }

    /**
     * Tests trying to move the opponent's piece, which fails.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 28
    public void movingOpponentPieceAndFailing() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 1), makeCoordinate(-1, 1));
    }

    /**
     * Tests trying to move a piece but referencing it with the wrong type,
     * which fails.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 29
    public void movingPieceWithWrongTypeAndFailing() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, -1));
    }

    /**
     * Tests trying to move a piece from a coordinate to the same coordinate,
     * which should fail.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 30
    public void movePieceFromAndToSameCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 0));
    }

    /**
     * Tests trying to move a piece from a coordinate to a null coordinate,
     * which should fail.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 31
    public void movePieceToNullCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), null);
    }

    /**
     * Tests trying to move a piece from a coordinate to another coordinate, and
     * there are two pieces on either side of the destination, so there is not
     * enough space and it should fail.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 32
    public void movePieceToCoordinateWithoutSufficientSlidingRoom() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(1, -1));
    }

    /**
     * Tests trying to move a piece from a coordinate to another coordinate, and
     * there is one piece on the side of the destination, so there is enough
     * space and it should move.
     *
     * @throws HantoException
     */
    @Test // 33
    public void movePieceToCoordinateWithSufficientSlidingRoom() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, -1));
    }

    /**
     * Tests winning the game on the last move.
     *
     * @throws HantoException
     */
    @Test // 34
    public void blueWinsOnLastMove() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -2));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -3));
        assertEquals(OK, mr);

        // Turn 4
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, -2));
        assertEquals(OK, mr);

        // Turn 5
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(1, -3));
        assertEquals(OK, mr);

        // Turn 6
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -4));
        assertEquals(OK, mr);

        // Turn 7, moves
        mr = game.makeMove(SPARROW, makeCoordinate(-1, 1), makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(-1, -2), makeCoordinate(-1, -1));
        assertEquals(OK, mr);

        // Turn 8
        mr = game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -2));
        assertEquals(OK, mr);

        // Turn 9
        mr = game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -3));
        assertEquals(OK, mr);

        // Turn 10
        mr = game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -2));
        assertEquals(OK, mr);

        // Turn 11
        mr = game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -3));
        assertEquals(OK, mr);

        // Turn 12
        mr = game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -2));
        assertEquals(OK, mr);

        // Turn 13
        mr = game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -3));
        assertEquals(OK, mr);

        // Turn 14
        mr = game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -2));
        assertEquals(OK, mr);

        // Turn 15
        mr = game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -3));
        assertEquals(OK, mr);

        // Turn 16
        mr = game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -2));
        assertEquals(OK, mr);

        // Turn 17
        mr = game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -3));
        assertEquals(OK, mr);

        // Turn 18
        mr = game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -2));
        assertEquals(OK, mr);

        // Turn 19
        mr = game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -2), makeCoordinate(1, -3));
        assertEquals(OK, mr);

        // Turn 20
        mr = game.makeMove(SPARROW, makeCoordinate(1, 0), makeCoordinate(1, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(1, -3), makeCoordinate(1, -2));
        assertEquals(MoveResult.BLUE_WINS, mr);
    }
}
