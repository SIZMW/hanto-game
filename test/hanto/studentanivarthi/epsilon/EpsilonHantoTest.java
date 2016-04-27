/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.epsilon;

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
// TODO Comments and formatting

/**
 * Tests for DeltaHantoGame.
 *
 * @author Aditya Nivarthi
 */
public class EpsilonHantoTest {
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
        game = factory.makeHantoGame(HantoGameID.EPSILON_HANTO);
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
     *             Since the board is not contiguous.
     */
    @Test(expected = HantoException.class) // 4
    public void redPlacesInitialButterflyNotNextToOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(1, 1));
    }

    /**
     * Tests placing blue crane in this variation of Hanto.
     *
     * @throws HantoException
     *             Since the crane is not part of this game type.
     */
    @Test(expected = HantoException.class) // 5
    public void bluePlacesInitialCraneAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.CRANE, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);
    }

    /**
     * Tests placing a red crane after blue in this variation of Hanto.
     *
     * @throws HantoException
     *             Since the crane is not part of this game type.
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesInitialCraneNextToOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        game.makeMove(HantoPieceType.CRANE, null, makeCoordinate(0, 1));
    }

    /**
     * Tests placing two blue sparrows and two red sparrows.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 7
    public void bluePlacesTwoSparrowsAndIsValid() throws HantoException {
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
    }

    /**
     * Tests placing two red sparrows, a blue butterfly and two blue sparrows.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 8
    public void redPlacesTwoSparrowsAndIsValid() throws HantoException {
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
    }

    /**
     * Tests placing two crabs and one sparrow for both blue and red, and then
     * another sparrow for blue, which throws an exception since the blue
     * butterfly was not placed by the fourth turn.
     *
     * @throws HantoException
     *             Since the butterfly was not placed by the fourth turn.
     */
    @Test(expected = HantoException.class) // 9
    public void bluePlacesFourCrabsAndFails() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

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
     * Tests placing a butterfly for blue, two crabs and one sparrow for both
     * blue and red, and then another sparrow for red, which throws an exception
     * since the red butterfly was not placed by the fourth turn.
     *
     * @throws HantoException
     *             Since the butterfly was not placed by the fourth turn.
     */
    @Test(expected = HantoException.class) // 12
    public void redPlacesFourSparrowsAndFails() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(BUTTERFLY, p.getType());

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -1));
        p = game.getPieceAt(makeCoordinate(0, -1));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 1));
        p = game.getPieceAt(makeCoordinate(0, 1));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -2));
        p = game.getPieceAt(makeCoordinate(0, -2));

        assertEquals(OK, mr);
        assertEquals(RED, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

        // Turn 3
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(HantoPieceType.CRAB, p.getType());

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
     *             Since the coordinate was occupied.
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
     *             Since the coordinate was occupied.
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
     *             Since the piece was initially placed next to an opponent.
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
     *             If the move fails.
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
     * Tests making a move that is invalid because it makes the board not
     * contiguous, which throws an exception.
     *
     * @throws HantoException
     *             Since the board is not contiguous.
     */
    @Test(expected = HantoException.class) // 17
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
     *             If the move fails.
     */
    @Test // 18
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
     *             If the move fails.
     */
    @Test // 19
    public void blueRedDrawGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

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
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 5
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 2));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, -2));
        assertEquals(OK, mr);

        // Move blue piece to surround the butterflies
        // Turn 6
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 2), makeCoordinate(0, 1));
        assertEquals(OK, mr);

        // Move red piece to surround both the butterflies
        // Draw game
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(2, -2), makeCoordinate(1, -1));
        assertEquals(MoveResult.DRAW, mr);
    }

    /**
     * Test the results from the game after red wins.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 20
    public void redWinsGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

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
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -1));
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
     *             If the move fails.
     */
    @Test // 21
    public void blueWinsGame() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

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

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Blue wins
        mr = game.makeMove(SPARROW, makeCoordinate(-1, 1), makeCoordinate(0, 1));
        assertEquals(MoveResult.BLUE_WINS, mr);
    }

    /**
     * Test having the blue player resign from the game at will, only placing a
     * butterfly.
     *
     * @throws HantoException
     *             Since the player had a valid move but resigned anyway.
     */
    @Test(expected = HantoException.class) // 22
    public void blueResignsFromGamePrematurelyWithButterfly() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(null, null, null);
    }

    /**
     * Test having the red player resign from the game at will, only placing a
     * butterfly.
     *
     * @throws HantoException
     *             Since the player had a valid move but resigned anyway.
     */
    @Test(expected = HantoException.class) // 23
    public void redResignsFromGamePrematurelyWithButterfly() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(MoveResult.OK, mr);

        // Fails
        mr = game.makeMove(null, null, null);
    }

    /**
     * Test having the red player resign from the game at will, only placing a
     * crab.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 24
    public void redResignsFromGamePrematurelyWithCrab() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
        assertEquals(MoveResult.OK, mr);

        // Fails
        mr = game.makeMove(null, null, null);
    }

    /**
     * Test having the blue butterfly walk one space.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 25
    public void blueButterflyWalksOneSpace() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
        assertEquals(MoveResult.OK, mr);
    }

    /**
     * Test having the blue crab walk one space.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 26
    public void blueCrabWalksOneSpace() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 1));
        assertEquals(MoveResult.OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, -1));
        assertEquals(MoveResult.OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 1), makeCoordinate(-1, 0));
        final HantoPiece p = game.getPieceAt(makeCoordinate(-1, 0));

        assertEquals(MoveResult.OK, mr);
        assertEquals(p.getColor(), HantoPlayerColor.BLUE);
        assertEquals(p.getType(), HantoPieceType.CRAB);
    }

    /**
     * Test having the blue butterfly walk one space, but there is not
     * sufficient room, so it fails.
     *
     * @throws HantoException
     *             Since there is not sufficient sliding room.
     */
    @Test(expected = HantoException.class) // 27
    public void blueButterflyWalksOneSpaceAndCannotMove() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(3, 0));
        assertEquals(OK, mr);

        // Turn 4, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(-1, 0));
    }

    /**
     * Test making a crab walk to a destination too far away.
     *
     * @throws HantoException
     *             Since the destination is too far away.
     */
    @Test(expected = HantoException.class) // 28
    public void makeBlueCrabWalkTooFarAndFail() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(10, 0));
    }

    /**
     * Test making a crab walk to a destination closer than its maximum walking
     * distance. This is the same as walking one space since there is no other
     * distance to test.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 29
    public void makeBlueCrabWalkCloserThanMaximumDistance() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(-1, 1));
        assertEquals(OK, mr);
    }

    /**
     * Test making a crab walk to a destination that makes the board become not
     * contiguous.
     *
     * @throws HantoException
     *             Since the board is not contiguous.
     */
    @Test(expected = HantoException.class) // 30
    public void makeBlueCrabWalkAndPathMakesBoardNotContiguous() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(-2, 1));
    }

    /**
     * Test making a crab walk to a destination that is occupied.
     *
     * @throws HantoException
     *             Since the destination is occupied.
     */
    @Test(expected = HantoException.class) // 31
    public void makeBlueCrabWalkToOccupiedCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(HantoPieceType.CRAB, makeCoordinate(-1, 0), makeCoordinate(0, 0));
    }

    /**
     * Test making a sparrow fly to a destination that is valid.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 32
    public void makeBlueSparrowFlyToValidCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(-1, 0), makeCoordinate(3, 0));
        assertEquals(OK, mr);
    }

    /**
     * Test making a sparrow fly to a destination that is the maximum distance
     * away.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 33
    public void makeBlueSparrowFlyMaximumDistance() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(2, 0), makeCoordinate(-1, -1));
        assertEquals(OK, mr);
    }

    /**
     * Test making a sparrow fly to a destination that is occupied.
     *
     * @throws HantoException
     *             Since the destination is occupied.
     */
    @Test(expected = HantoException.class) // 34
    public void makeBlueSparrowFlyToOccupiedCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(HantoPieceType.SPARROW, makeCoordinate(-1, 0), makeCoordinate(2, 0));
    }

    /**
     * Test making a piece move from and to the same coordinate, which fails.
     *
     * @throws HantoException
     *             Since the source and destination are the same.
     */
    @Test(expected = HantoException.class) // 35
    public void makeMoveToAndFromSameCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.SPARROW, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        // Fails
        mr = game.makeMove(HantoPieceType.BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, 0));
    }

    /**
     * Tests passing a null piece type to the move.
     *
     * @throws HantoException
     *             Since the piece type is null.
     */
    @Test(expected = HantoException.class) // 36
    public void makeMoveWithNullPieceType() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        // Fails
        mr = game.makeMove(null, null, makeCoordinate(1, 0));
    }

    /**
     * Test having the blue butterfly walk two spaces which fails.
     *
     * @throws HantoException
     *             Since the piece cannot walk that far.
     */
    @Test(expected = HantoException.class) // 37
    public void blueButterflyWalksTwoSpaces() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 2));
    }

    /**
     * Tests running the game for MAX_RUNS moves, at least to show with some
     * reason that there is no move limit.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 38
    public void runGameToMillionMoves() throws HantoException {
        final long MAX_RUNS = 1000000;
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
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
     * Tests trying to make a piece walk without placing the butterfly on the
     * board.
     *
     * @throws HantoException
     *             Since the butterfly is not on the board.
     */
    @Test(expected = HantoException.class) // 39
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
     *             Since the source does not have the piece to move.
     */
    @Test(expected = HantoException.class) // 40
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
     *             Since the destination is null.
     */
    @Test(expected = HantoException.class) // 41
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
     *             Since there is not sufficient sliding room to move.
     */
    @Test(expected = HantoException.class) // 42
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
    @Test // 43
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
     *             Since the piece is the opponent's piece.
     */
    @Test(expected = HantoException.class) // 44
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
    @Test(expected = HantoException.class) // 45
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
     * Test the results from the game after both blue and red win by surrounding
     * each other, then blue playing a move after the game is over which fails.
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 46
    public void blueRedDrawGameAndMakeMoveAfterGameEnds() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, -1));
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

        // Fails
        mr = game.makeMove(SPARROW, makeCoordinate(0, 1), makeCoordinate(0, 2));
    }

    /**
     * Test the results from the game after red wins, and make a move after the
     * game ends, which fails.
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 47
    public void redWinsGameAndMakeMoveAfterGameEnds() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(SPARROW, null, makeCoordinate(-2, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(SPARROW, null, makeCoordinate(2, -1));
        assertEquals(OK, mr);

        // Turn 4
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(0, -1));
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

        // Fails
        mr = game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(-1, 1));
    }

    /**
     * Test the results from the game after blue wins, and make a move after the
     * game ends which fails.
     *
     * @throws HantoException
     *             Since the game is already over.
     */
    @Test(expected = HantoException.class) // 48
    public void blueWinsGameAndMakeMoveAfterGameEnds() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(1, 1));
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

        // Fails
        mr = game.makeMove(SPARROW, makeCoordinate(0, 1), makeCoordinate(0, 2));
    }

    /**
     * Test having the blue player resign from the game at will.
     *
     * @throws HantoException
     *             Since the player had a valid move, but resigned anyway.
     */
    @Test(expected = HantoException.class) // 49
    public void blueResignsFromGamePrematurelyWithSparrow() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2, fails
        mr = game.makeMove(null, null, null);
    }

    /**
     * Test moving the horse by jumping to an empty coordinate.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 50
    public void testMoveHorseToEmptyCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.HORSE, makeCoordinate(-1, 0), makeCoordinate(3, 0));
        assertEquals(OK, mr);
    }

    /**
     * Test moving the horse by jumping to an occupied coordinate.
     *
     * @throws HantoException
     *             Since the destination is occupied.
     */
    @Test(expected = HantoException.class) // 51
    public void testMoveHorseToOccupiedCoordinate() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(HantoPieceType.HORSE, makeCoordinate(-1, 0), makeCoordinate(2, 0));
    }

    /**
     * Test moving the horse by jumping in an invalid direction.
     *
     * @throws HantoException
     *             Since the jump does not go in one consistent direction.
     */
    @Test(expected = HantoException.class) // 52
    public void testMoveHorseInInvalidDirection() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(HantoPieceType.HORSE, makeCoordinate(-1, 0), makeCoordinate(2, -1));
    }

    /**
     * Test having the blue player resign from the game at will.
     *
     * @throws HantoException
     *             Since the player had a valid move but resigned anyway.
     */
    @Test(expected = HantoException.class) // 53
    public void blueResignsFromGamePrematurelyWithHorse() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 0));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(null, null, null);
    }

    /**
     * Test moving the horse by jumping to an empty coordinate, but over
     * unoccupied coordinates.
     *
     * @throws HantoException
     *             Since the jump range has unoccupied spaces.
     */
    @Test(expected = HantoException.class) // 54
    public void testMoveHorseToEmptyCoordinateOverEmptySpaces() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(1, 1));
        assertEquals(OK, mr);

        // Turn 3
        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(-2, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.CRAB, null, makeCoordinate(2, 1));
        assertEquals(OK, mr);

        // Turn 4
        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(-3, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(3, 0));
        assertEquals(OK, mr);

        // Turn 5, fails
        mr = game.makeMove(HantoPieceType.HORSE, makeCoordinate(-3, 0), makeCoordinate(4, 0));
    }

    /**
     * Test moving the horse by jumping to the coordinate adjacent, without
     * jumping over any pieces.
     *
     * @throws HantoException
     *             Since the jump does not go over any occupied spaces.
     */
    @Test(expected = HantoException.class) // 55
    public void testMoveHorseOverNoSpaces() throws HantoException {
        // Turn 1
        MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        // Turn 2
        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(0, -1));
        assertEquals(OK, mr);

        mr = game.makeMove(HantoPieceType.HORSE, null, makeCoordinate(2, -1));
        assertEquals(OK, mr);

        // Turn 3, fails
        mr = game.makeMove(HantoPieceType.HORSE, makeCoordinate(0, -1), makeCoordinate(1, -1));
    }
}
