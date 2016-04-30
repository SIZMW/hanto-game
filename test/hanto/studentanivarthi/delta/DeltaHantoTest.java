/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.delta;

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
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.HantoGameFactory;

/**
 * Tests for DeltaHantoGame.
 *
 * @author Aditya Nivarthi
 */
public class DeltaHantoTest {
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
        game = factory.makeHantoGame(HantoGameID.DELTA_HANTO);
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
        // Turn 1
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
     *             Since the move was not at the origin.
     */
    @Test(expected = HantoException.class) // 3
    public void bluePlacesInitialButterflyNotAtOrigin() throws HantoException {
        // Turn 1
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
        // Turn 1
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(1, 1));
    }

    /**
     * Tests placing a BLUE CRANE in this variation of Hanto.
     *
     * @throws HantoException
     *             Since the piece is not part of this game type.
     */
    @Test(expected = HantoException.class) // 5
    public void bluePlacesInitialCraneAtOrigin() throws HantoException {
        // Turn 1
        final MoveResult mr = game.makeMove(HantoPieceType.CRANE, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
    }

    /**
     * Tests placing a RED CRANE in this variation of Hanto.
     *
     * @throws HantoException
     *             Since the piece is not part of this game type.
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesInitialCraneAfterValidBlue() throws HantoException {
        // Turn 1
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        game.makeMove(HantoPieceType.CRANE, null, makeCoordinate(0, 1));
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
     * Tests placing a piece where there already is one, and getting an
     * exception.
     *
     * @throws HantoException
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 14
    public void placePieceInOccupiedSpot() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
    }

    /**
     * Tests the print out of the game board after placing one BUTTERFLY.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 15
    public void checkPrintableBoard() throws HantoException {
        // Turn 1
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
    @Test // 16
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
     * Tests placing a piece where there already is one, but not within the
     * first two moves of the game, and getting an exception.
     *
     * @throws HantoException
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 17
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
    @Test(expected = HantoException.class) // 18
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
     *             Since the board was not contiguous.
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
     * Test having the BLUE BUTTERFLY walk one space.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 20
    public void blueButterflyWalksOneSpace() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
        assertEquals(MoveResult.OK, mr);
    }

    /**
     * Test having the BLUE CRAB walk three spaces.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 21
    public void blueCrabWalksThreeSpaces() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 1));
        assertEquals(MoveResult.OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, -1));
        assertEquals(MoveResult.OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 1), makeCoordinate(2, 0));
        final HantoPiece p = game.getPieceAt(makeCoordinate(2, 0));

        assertEquals(MoveResult.OK, mr);
        assertEquals(p.getColor(), HantoPlayerColor.BLUE);
        assertEquals(p.getType(), HantoPieceType.CRAB);
    }

    /**
     * Test having the BLUE BUTTERFLY walk one space, but there is not
     * sufficient room, so it fails.
     *
     * @throws HantoException
     *             Since there was not sufficient room to walk.
     */
    @Test(expected = HantoException.class) // 22
    public void blueButterflyWalksOneSpaceAndCannotMove() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(3, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(-1, 0));
    }

    /**
     * Test making a CRAB walk to a destination too far away.
     *
     * @throws HantoException
     *             Since the distance was too far away.
     */
    @Test(expected = HantoException.class) // 23
    public void makeBlueCrabWalkTooFarAndFail() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(10, 0));
    }

    /**
     * Test making a CRAB walk to a destination closer than its maximum walking
     * distance.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 24
    public void makeBlueCrabWalkCloserThanMaximumDistance() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(-1, 1));
        assertEquals(OK, mr);
    }

    /**
     * Test making a CRAB walk to a destination that makes the board become not
     * contiguous.
     *
     * @throws HantoException
     *             Since the board was not contiguous.
     */
    @Test(expected = HantoException.class) // 25
    public void makeBlueCrabWalkAndPathMakesBoardNotContiguous() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(-2, 1));
        assertEquals(OK, mr);
    }

    /**
     * Test making a CRAB walk to a destination that is occupied.
     *
     * @throws HantoException
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 26
    public void makeBlueCrabWalkToOccupiedCoordinate() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(0, 0));
    }

    /**
     * Test making a SPARROW fly to a destination that is valid.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 27
    public void makeBlueSparrowFlyToValidCoordinate() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(-1, 0), makeCoordinate(3, 0));
        assertEquals(OK, mr);
    }

    /**
     * Test making a SPARROW fly to a destination that is 10 spaces away, which
     * in Delta Hanto is not too far away.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 28
    public void makeBlueSparrowFlyFartherAway() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(3, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-3, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(4, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-4, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(5, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(-5, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(5, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(-5, 0), makeCoordinate(6, 1));
        assertEquals(OK, mr);
    }

    /**
     * Test making a SPARROW fly to a destination that is occupied.
     *
     * @throws HantoException
     *             Since the coordinate is occupied.
     */
    @Test(expected = HantoException.class) // 29
    public void makeBlueSparrowFlyToOccupiedCoordinate() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(-1, 0), makeCoordinate(2, 0));
    }

    /**
     * Test making a piece move from and to the same coordinate, which fails.
     *
     * @throws HantoException
     *             Since the source and destination are the same.
     */
    @Test(expected = HantoException.class) // 30
    public void makeMoveToAndFromSameCoordinate() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, 0));
    }

    /**
     * Tests passing a null piece type to the move.
     *
     * @throws HantoException
     *             Since the piece was null.
     */
    @Test(expected = HantoException.class) // 31
    public void makeMoveWithNullPieceType() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(null, null, makeCoordinate(1, 0));
    }

    /**
     * Test having the BLUE BUTTERFLY walk two spaces which fails.
     *
     * @throws HantoException
     *             Since the distance was too far away.
     */
    @Test(expected = HantoException.class) // 32
    public void blueButterflyWalksTwoSpaces() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 2));
    }

    /**
     * Tests running the game for MAX_RUNS moves, at least to show with some
     * reason that there is no move limit.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 33
    public void runGameToMillionMoves() throws HantoException {
        final long MAX_RUNS = 1000000;
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Run back and forth moves for MAX_RUNS moves
        for (int i = 0; i < MAX_RUNS; i++) {
            if (i == 0 || i % 2 == 0) {
                mr = game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
                assertEquals(OK, mr);

                mr = game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(2, -1));
                assertEquals(OK, mr);
            } else {
                mr = game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
                assertEquals(OK, mr);

                mr = game.makeMove(SPARROW, makeCoordinate(2, -1), makeCoordinate(2, 0));
                assertEquals(OK, mr);
            }
        }
    }

    /**
     * Tests trying to make a piece walk without placing the BUTTERFLY on the
     * board.
     *
     * @throws HantoException
     *             Since the BUTTERFLY was not placed.
     */
    @Test(expected = HantoException.class) // 34
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
     * Tests trying to make a piece walk and passing a location that is empty as
     * the source.
     *
     * @throws HantoException
     *             Since the source was empty.
     */
    @Test(expected = HantoException.class) // 35
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
     * Tests trying to move a piece from a coordinate to a null coordinate,
     * which should fail.
     *
     * @throws HantoException
     *             Since the destination was null.
     */
    @Test(expected = HantoException.class) // 36
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
     *             Since there was not sufficient room to walk.
     */
    @Test(expected = HantoException.class) // 37
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
    @Test // 38
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
     * Tests trying to move the opponent's piece, which fails.
     *
     * @throws HantoException
     *             Since the piece was of the opponent.
     */
    @Test(expected = HantoException.class) // 39
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
     *             Since the piece did not match.
     */
    @Test(expected = HantoException.class) // 40
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
     * Tests setting up a similar scenario to the one from the Hanto guide on
     * walking and always having a connected configuration.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 41
    public void testASimilarWalkingScenarioFromHantoGuide() throws HantoException {
        // Setup
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(3, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(-2, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(3, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, -2));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(4, -2));
        assertEquals(OK, mr);

        // Move BLUE CRAB
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, -2), makeCoordinate(1, -1));
        assertEquals(OK, mr);

        // Move RED CRAB
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(4, -2), makeCoordinate(1, -2));
        assertEquals(OK, mr);

        // Move BLUE SPARROW
        mr = game.makeMove(SPARROW, makeCoordinate(-2, -1), makeCoordinate(0, -1));
        assertEquals(OK, mr);

        // Move RED SPARROW
        mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(3, -1), makeCoordinate(4, -1));
        assertEquals(OK, mr);

        // Move BLUE CRAB again
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(1, -1), makeCoordinate(4, -2));
        assertEquals(OK, mr);

        // Move RED SPARROW again
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(1, -2), makeCoordinate(-1, -1));
        assertEquals(OK, mr);
    }

    /**
     * Test the results from the game after both BLUE and RED win by surrounding
     * each other, then BLUE playing a move after the game is over which fails.
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 42
    public void blueRedDrawGameAndMakeMoveAfterGameEnds() throws HantoException {
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

        // Fails
        mr = game.makeMove(SPARROW, makeCoordinate(0, 1), makeCoordinate(0, 2));
    }

    /**
     * Test the results from the game after RED wins, and make a move after the
     * game ends, which fails.
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 43
    public void redWinsGameAndMakeMoveAfterGameEnds() throws HantoException {
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

        // Fails
        mr = game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(-1, 1));
    }

    /**
     * Test the results from the game after BLUE wins, and make a move after the
     * game ends which fails.
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 44
    public void blueWinsGameAndMakeMoveAfterGameEnds() throws HantoException {
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

        // Fails
        mr = game.makeMove(SPARROW, makeCoordinate(0, 1), makeCoordinate(0, 2));
    }

    /**
     * Test having the BLUE player resign from the game at will.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 45
    public void blueResignsFromGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(null, null, null);
        assertEquals(MoveResult.RED_WINS, mr);
    }

    /**
     * Test having the RED player resign from the game at will.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 46
    public void redResignsFromGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(MoveResult.OK, mr);

        mr = game.makeMove(null, null, null);
        assertEquals(MoveResult.BLUE_WINS, mr);
    }

    /**
     * Test having the RED player resign from the game at will, and trying to
     * move after the game is over
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 47
    public void redResignsFromGameAndBlueTriesToPlayAfterGameEnds() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(MoveResult.OK, mr);

        mr = game.makeMove(null, null, null);
        assertEquals(MoveResult.BLUE_WINS, mr);

        // Fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
    }
}
