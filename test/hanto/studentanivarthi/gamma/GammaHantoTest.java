/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

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

/**
 * Tests for Gamma Hanto.
 *
 * @author Aditya Nivarthi
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

        /*
         * @see {@link hanto.common.HantoCoordinate#getX()}
         */
        @Override
        public int getX() {
            return x;
        }

        /*
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
        // By default, BLUE moves first.
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
     * Tests placing a BLUE BUTTERFLY at the origin as the first move.
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
     * Tests placing a RED BUTTERFLY next to the BLUE BUTTERFLY which is at the
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
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(1, 1));
    }

    /**
     * Tests placing a BLUE CRAB in this variation of Hanto.
     *
     * @throws HantoException
     *             Since the piece is not part of this game type.
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
     *             Since the piece is not part of this game type.
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesInitialCrabAfterValidBlue() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

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
     * Tests placing three RED SPARROWs, a BLUE BUTTERFLY and two BLUE SPARROWs.
     *
     * @throws HantoException
     *             If the move fails.
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
     * Test the results from the game after RED wins.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 11
    public void redWinsGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Surround the BLUE BUTTERFLY
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

        // Move RED piece closer to BLUE BUTTERFLY
        mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Move BLUE piece closer to BLUE BUTTERFLY
        // Turn 5
        mr = game.makeMove(SPARROW, makeCoordinate(-2, 1), makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        // Red wins
        mr = game.makeMove(SPARROW, makeCoordinate(2, -1), makeCoordinate(1, -1));
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
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Surround the RED BUTTERFLY
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

        // Move BLUE piece closer to RED BUTTERFLY
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
     * Test the results from the game after both BLUE and RED win by surrounding
     * each other.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 13
    public void blueRedDrawGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Surround both RED and BLUE BUTTERFLY pieces, which start next to each
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

        // Move BLUE piece to surround the butterflies
        // Turn 6
        mr = game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Move RED piece to surround both the butterflies
        // Draw game
        mr = game.makeMove(SPARROW, makeCoordinate(2, -2), makeCoordinate(1, -1));
        assertEquals(MoveResult.DRAW, mr);
    }

    /**
     * Tests winning the game on the last move.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 14
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

    /**
     * Tests placing a piece where there already is one, and getting an
     * exception.
     *
     * @throws HantoException
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 15
    public void placePieceInOccupiedSpot() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Place on same coordinate
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
    }

    /**
     * Tests the print out of the game board after placing one BUTTERFLY.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 16
    public void checkPrintableBoard() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Get the printable board
        assertEquals(
                "HantoCoordinateImpl [x = 0, y = 0]: HantoPieceImpl [color = BLUE, type = Butterfly]\n",
                game.getPrintableBoard());
    }

    /**
     * Tests placing RED BUTTERFLY at the origin as the first move. Game is
     * created with RED being the first player.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 17
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
     * Tests playing the game to 20 turns, where no one wins and it is called a
     * draw.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 18
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
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 19
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
     * Tests placing a piece where there already is one, but not within the
     * first two moves of the game, and getting an exception.
     *
     * @throws HantoException
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 20
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
     *             Since the piece was placed next to an opponent's piece.
     */
    @Test(expected = HantoException.class) // 21
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
     * Tests making a move that is invalid because it makes the board not
     * contiguous, which throws an exception.
     *
     * @throws HantoException
     *             Since the board is not contiguous.
     */
    @Test(expected = HantoException.class) // 22
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
     * Tests trying to make a piece walk to a location that is not one
     * coordinate away.
     *
     * @throws HantoException
     *             Since the distance is too far away.
     */
    @Test(expected = HantoException.class) // 23
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
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 24
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
     *             Since the source is empty.
     */
    @Test(expected = HantoException.class) // 25
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
     * Tests trying to make a piece walk without placing the BUTTERFLY on the
     * board.
     *
     * @throws HantoException
     *             Since the BUTTERFLY was not placed.
     */
    @Test(expected = HantoException.class) // 26
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
     *             Since the piece was of the opponent.
     */
    @Test(expected = HantoException.class) // 27
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
     *             Since the piece types do not match.
     */
    @Test(expected = HantoException.class) // 28
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
     *             Since the source and destination are the same.
     */
    @Test(expected = HantoException.class) // 29
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
     *             Since the destination is null.
     */
    @Test(expected = HantoException.class) // 30
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
     *             Since there is not sufficient room to move.
     */
    @Test(expected = HantoException.class) // 31
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
     *             If the move fails.
     */
    @Test // 32
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
}
