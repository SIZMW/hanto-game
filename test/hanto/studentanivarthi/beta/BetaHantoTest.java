/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

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
 * @author Aditya Nivarthi
 */
public class BetaHantoTest {
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
     * Setup.
     */
    @Before
    public void setup() {
        // By default, BLUE moves first.
        game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
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
     * Tests placing a BLUE BUTTERFLY at the origin as the first move.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 1
    public void bluePlacesInitialButterflyAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Check the piece
        final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());
    }

    /**
     * Tests placing a RED BUTTERFLY next to the BLUE BUTTERFLY which is at the
     * origin.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 2
    public void redPlacesValidButterflyAfterBlue() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);

        final MoveResult mrR = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        assertEquals(OK, mrR);
    }

    /**
     * Tests placing a BLUE BUTTERFLY not at the origin.
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
     * Tests placing a RED BUTTERFLY not next to the BLUE BUTTERFLY, which is at
     * the origin.
     *
     * @throws HantoException
     *             Since the board is not contiguous.
     */
    @Test(expected = HantoException.class) // 4
    public void redPlacesInitialButterflyNotNextToOrigin() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);

        game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(1, 1));
    }

    /**
     * Tests placing a BLUE CRAB in this variation of Hanto.
     *
     * @throws HantoException
     *             Since the CRAB is not part of this game type.
     */
    @Test(expected = HantoException.class) // 5
    public void bluePlacesInitialCrabAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
    }

    /**
     * Tests placing a RED CRAB in this variation of Hanto.
     *
     * @throws HantoException
     *             Since the CRAB is not part of this game type.
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesInitialCrabAfterValidBlue() throws HantoException {
        final MoveResult mrB = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mrB);

        game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 1));
    }

    /**
     * Tests placing three BLUE SPARROWs and two RED SPARROWs.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 7
    public void bluePlacesThreeSparrowsAndIsValid() throws HantoException {
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        p = game.getPieceAt(makeCoordinate(0, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());
    }

    /**
     * Tests placing three RED SPARROWs and two BLUE SPARROWs.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 8
    public void redPlacesThreeSparrowsAndIsValid() throws HantoException {
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

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        p = game.getPieceAt(makeCoordinate(0, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 5));
        p = game.getPieceAt(makeCoordinate(0, 5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());
    }

    /**
     * Tests placing four BLUE SPARROWs and three RED SPARROWs, which throws an
     * exception since the BLUE BUTTERFLY was not placed by the fourth turn.
     *
     * @throws HantoException
     *             Since the BUTTERFLY was not placed by the fourth turn.
     */
    @Test(expected = HantoException.class) // 9
    public void bluePlacesFourSparrowsAndFails() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        p = game.getPieceAt(makeCoordinate(0, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 5));
        p = game.getPieceAt(makeCoordinate(0, 5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 4, fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 6));
    }

    /**
     * Tests placing four RED SPARROWs and three BLUE SPARROWs, which throws an
     * exception since the RED BUTTERFLY was not placed by the fourth turn.
     *
     * @throws HantoException
     *             Since the BUTTERFLY was not placed by the fourth turn.
     */
    @Test(expected = HantoException.class) // 10
    public void redPlacesFourSparrowsAndFails() throws HantoException {
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

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        p = game.getPieceAt(makeCoordinate(0, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 5));
        p = game.getPieceAt(makeCoordinate(0, 5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Turn 4
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 6));
        p = game.getPieceAt(makeCoordinate(0, 6));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        // Fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 7));
    }

    /**
     * Test the results from the game after RED wins.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 11
    public void redWinsGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Surround the BLUE BUTTERFLY
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
     * Test the results from the game after BLUE wins.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 12
    public void blueWinsGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Surround the RED BUTTERFLY
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
     * Test the results from the game after both BLUE and RED win.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 13
    public void blueRedDrawGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Surround both RED and BLUE BUTTERFLY pieces, which start next to each
        // other
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

        // The last piece to surround both BUTTERFLY pieces
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, -1));
        assertEquals(MoveResult.DRAW, mr);
    }

    /**
     * Test winning the game on the last move of the game.
     *
     * @throws HantoException
     *             If the moves fails.
     */
    @Test // 14
    public void blueWinsOnLastMove() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Surround the RED BUTTERFLY
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

        // Add extra moves
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 5));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 6));
        assertEquals(OK, mr);

        // Blue wins
        mr = game.makeMove(SPARROW, null, makeCoordinate(1, -1));
        assertEquals(MoveResult.BLUE_WINS, mr);
    }

    /**
     * Tests placing a piece after the game is over.
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 15
    public void attemptToMoveAfterGameEnds() throws HantoException {
        // Place the BLUE BUTTERFLY
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Place the RED BUTTERFLY
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 3));
        p = game.getPieceAt(makeCoordinate(0, 3));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 4));
        p = game.getPieceAt(makeCoordinate(0, 4));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 5));
        p = game.getPieceAt(makeCoordinate(0, 5));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 6));
        p = game.getPieceAt(makeCoordinate(0, 6));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 7));
        p = game.getPieceAt(makeCoordinate(0, 7));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 8));
        p = game.getPieceAt(makeCoordinate(0, 8));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 9));
        p = game.getPieceAt(makeCoordinate(0, 9));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 10));
        p = game.getPieceAt(makeCoordinate(0, 10));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 11));
        p = game.getPieceAt(makeCoordinate(0, 11));

        assertEquals(MoveResult.DRAW, mr);
        assertEquals(RED, p.getColor());
        assertEquals(SPARROW, p.getType());

        // This should fail
        game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 12));
    }

    /**
     * Tests placing a piece where there already is one, and getting an
     * exception.
     *
     * @throws HantoException
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 16
    public void placePieceInOccupiedSpot() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Place on same coordinate
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
    }

    /**
     * Test trying to move a BUTTERFLY and failing.
     *
     * @throws HantoException
     *             Since moves are not allowed in this game type.
     */
    @Test(expected = HantoException.class) // 17
    public void moveButterflyAndFail() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(1, -1));
    }

    /**
     * Test trying to move a SPARROW and failing.
     *
     * @throws HantoException
     *             Since moves are not allowed in this game type.
     */
    @Test(expected = HantoException.class) // 18
    public void moveSparrowAndFail() throws HantoException {
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(1, -1));
    }

    /**
     * Tests the print out of the game board after placing one BUTTERFLY.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 19
    public void checkPrintableBoard() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Get the printable board
        assertEquals(
                "HantoCoordinateImpl [x = 0, y = 0]: HantoPieceImpl [color = BLUE, type = Butterfly]\n",
                game.getPrintableBoard());
    }

    /**
     * Tests placing a RED BUTTERFLY at the origin as the first move. Game is
     * created with RED being the first player.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 20
    public void redStartsAndPlacesInitialButterflyAtOrigin() throws HantoException {
        game = factory.makeHantoGame(HantoGameID.BETA_HANTO, RED);

        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(RED, p.getColor());
        assertEquals(BUTTERFLY, p.getType());
    }
}
