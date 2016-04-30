/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.alpha;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.CRAB;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.MoveResult.DRAW;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
 * Test cases for Alpha Hanto.
 *
 * @author Aditya Nivarthi
 */
public class AlphaHantoTest {
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
        game = factory.makeHantoGame(HantoGameID.ALPHA_HANTO, BLUE);
    }

    /**
     * Tests blue making the first move with the BUTTERFLY.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 1
    public void blueMakesValidFirstMove() throws HantoException {
        assertEquals(OK, game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)));
    }

    /**
     * Tests red making a valid move after blue with the BUTTERFLY.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 2
    public void redMakesValidMove() throws HantoException {
        game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0));
        assertEquals(DRAW, game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)));
    }

    /**
     * Tests blue making the first move not to the origin with the BUTTERFLY.
     *
     * @throws HantoException
     *             Since the first move is not to the origin.
     */
    @Test(expected = HantoException.class) // 3
    public void blueMovesToNonOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1));
        assertEquals(OK, mr);
    }

    /**
     * Tests blue trying to place a SPARROW as the first move.
     *
     * @throws HantoException
     *             Since the SPARROW is not part of this game type.
     */
    @Test(expected = HantoException.class) // 4
    public void blueTriesToPlaceSparrow() throws HantoException {
        final MoveResult mr = game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));
        assertEquals(OK, mr);
    }

    /**
     * Tests red trying to place a CRAB.
     *
     * @throws HantoException
     *             Since the CRAB is not part of this game type.
     */
    @Test(expected = HantoException.class) // 5
    public void redTriesToPlaceCrab() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null,
                new TestHantoCoordinate(0, 0));
        assertEquals(OK, mr);
        game.makeMove(CRAB, null, new TestHantoCoordinate(0, 0));
    }

    /**
     * Tests red placing a BUTTERFLY not next to another piece.
     *
     * @throws HantoException
     *             Since the board is not contiguous.
     */
    @Test(expected = HantoException.class) // 6
    public void redPlacesButterflyAtInvalidLocation() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null,
                new TestHantoCoordinate(0, 0));
        assertEquals(OK, mr);
        game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(2, 0));
    }

    /**
     * Tests red placing a BUTTERFLY at the origin.
     *
     * @throws HantoException
     *             Since there is already a piece at the origin.
     */
    @Test(expected = HantoException.class) // 7
    public void redPlacesButterflyAtOrigin() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null,
                new TestHantoCoordinate(0, 0));
        assertEquals(OK, mr);
        game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
    }

    /**
     * Tests if the BUTTERFLY for blue is actually at the origin.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 8
    public void blueButterflyIsAtOrginAfterMove() throws HantoException {
        assertNull(game.getPieceAt(new TestHantoCoordinate(0, 0)));
        game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
        final HantoPiece butterfly = game.getPieceAt(new TestHantoCoordinate(0, 0));
        assertEquals(BUTTERFLY, butterfly.getType());
        assertEquals(BLUE, butterfly.getColor());
    }

    /**
     * Tests if the BUTTERFLY for red is at the correct location.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 9
    public void redButterflyIsAtCorrectPlaceAfterRedMoves() throws HantoException {
        game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0));
        game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, -1));
        final HantoPiece butterfly = game.getPieceAt(new TestHantoCoordinate(1, -1));
        assertEquals(BUTTERFLY, butterfly.getType());
        assertEquals(RED, butterfly.getColor());
    }

    /**
     * Tests moving after the game is over.
     *
     * @throws HantoException
     *             Since the game is over.
     */
    @Test(expected = HantoException.class) // 10
    public void attemptToMoveAfterGameEnds() throws HantoException {
        final MoveResult mr = game.makeMove(HantoPieceType.BUTTERFLY, null,
                new TestHantoCoordinate(0, 0));
        assertEquals(OK, mr);
        game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, -1));
        game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, 0));
    }
}
