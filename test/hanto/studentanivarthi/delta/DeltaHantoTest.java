/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

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

        /**
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
        // By default, blue moves first.
        game = factory.makeHantoGame(HantoGameID.DELTA_HANTO);
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
     * Tests placing blue crane in this variation of Hanto.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 5
    public void bluePlacesInitialCraneAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.CRANE, null, makeCoordinate(0, 0));
    }

    /**
     * Tests placing a red crane in this variation of Hanto.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesInitialCraneAfterValidBlue() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        game.makeMove(HantoPieceType.CRANE, null, makeCoordinate(0, 1));
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
                "HantoCoordinateImpl [x=0, y=0]: HantoPieceImpl [color=BLUE, type=Butterfly]\n",
                game.getPrintableBoard());
    }

    /**
     * Tests making a move that is invalid because it makes the board not
     * contiguous, which throws an exception.
     *
     * @throws HantoException
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
     */
    @Test // 19
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
    @Test // 20
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
    @Test // 21
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
     * Test having the blue player resign from the game at will.
     *
     * @throws HantoException
     */
    @Test // 22
    public void blueResignsFromGame() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(null, null, null);
        assertEquals(MoveResult.RED_WINS, mr);
    }

    /**
     * Test having the red player resign from the game at will.
     *
     * @throws HantoException
     */
    @Test // 23
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
     * Test having the red player resign from the game at will, and trying to
     * move after the game is over
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 24
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

    /**
     * Test having the blue butterfly walk one space.
     *
     * @throws HantoException
     */
    @Test // 25
    public void blueButterflyWalksOneSpace() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
        assertEquals(MoveResult.OK, mr);
    }

    /**
     * Test having the blue crab walk three spaces.
     *
     * @throws HantoException
     */
    @Test // 26
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
        HantoPiece p = game.getPieceAt(makeCoordinate(2, 0));

        assertEquals(MoveResult.OK, mr);
        assertEquals(p.getColor(), HantoPlayerColor.BLUE);
        assertEquals(p.getType(), HantoPieceType.CRAB);
    }

    /**
     * Test having the blue butterfly walk one space, but there is not
     * sufficient room, so it fails.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 27
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
     * Test making a crab walk to a destination too far away.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 28
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
     * Test making a crab walk to a destination closer than its maximum walking
     * distance.
     *
     * @throws HantoException
     */
    @Test // 29
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
     * Test making a crab walk to a destination that makes the board become not
     * contiguous.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 30
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
     * Test making a crab walk to a destination that is occupied.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 31
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
     * Test making a sparrow fly to a destination that is valid.
     *
     * @throws HantoException
     */
    @Test // 32
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
     * Test making a sparrow fly to a destination that is 10 spaces away, which
     * in Delta Hanto is not too far away.
     *
     * @throws HantoException
     */
    @Test // 33
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
     * Test making a sparrow fly to a destination that is occupied.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 34
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
     */
    @Test(expected = HantoException.class) // 35
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
     */
    @Test(expected = HantoException.class) // 36
    public void makeMoveWithNullPieceType() throws HantoException {
        MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
        assertEquals(OK, mr);

        mr = game.makeMove(null, null, makeCoordinate(1, 0));
    }

    /**
     * Test having the blue butterfly walk two spaces which fails.
     *
     * @throws HantoException
     */
    @Test(expected = HantoException.class) // 37
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
     */
    @Test // 38
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
}
