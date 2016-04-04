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
        game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO);
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

        // Check the piece
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

        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));

        assertEquals(OK, mr);
        assertEquals(BLUE, p.getColor());
        assertEquals(SPARROW, p.getType());
    }

    /**
     * Tests placing three red sparrows and two blue sparrows.
     *
     * @throws HantoException
     */
    @Test // 8
    public void redPlacesThreeSparrowsAndIsValid() throws HantoException {
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
        p = game.getPieceAt(makeCoordinate(0, 3));
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
        p = game.getPieceAt(makeCoordinate(0, -4));
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
        assertEquals(OK, mr);
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
        p = game.getPieceAt(makeCoordinate(0, 2));
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

        // Fails
        mr = game.makeMove(SPARROW, null, makeCoordinate(0, 2));
        p = game.getPieceAt(makeCoordinate(0, 2));
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
}
