/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.tournament;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.HantoGameFactory;
import hanto.tournament.HantoMoveRecord;

/**
 * Tests for HantoPlayer.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerTest {
    private static HantoGameFactory factory = null;
    private HantoGame game;
    private final static int MOVE_COUNT = 1000;

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
     * Returns if the game is over.
     *
     * @param resultThe
     *            result to check.
     * @return true if over, false otherwise
     */
    private boolean isGameOver(MoveResult result) {
        return !result.equals(MoveResult.OK);
    }

    /**
     * Runs two players against each other for some number of moves, or until
     * the game ends.
     *
     * @throws HantoException
     *             If the move fails.
     */
    @Test // 1
    public void playSomeValidMovesFromGame() throws HantoException {
        HantoPlayer one = new HantoPlayer();
        HantoPlayer two = new HantoPlayer();

        one.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
        two.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);

        HantoMoveRecord record = null;
        MoveResult mr = null;

        for (int i = 0; i < MOVE_COUNT; i++) {
            record = one.makeMove(record);
            mr = game.makeMove(record.getPiece(), record.getFrom(), record.getTo());

            assertEquals(mr.equals(MoveResult.OK) || mr.equals(MoveResult.BLUE_WINS)
                    || mr.equals(MoveResult.RED_WINS) || mr.equals(MoveResult.DRAW), true);

            if (isGameOver(mr)) {
                break;
            }

            record = two.makeMove(record);
            mr = game.makeMove(record.getPiece(), record.getFrom(), record.getTo());

            assertEquals(mr.equals(MoveResult.OK) || mr.equals(MoveResult.BLUE_WINS)
                    || mr.equals(MoveResult.RED_WINS) || mr.equals(MoveResult.DRAW), true);
            if (isGameOver(mr)) {
                break;
            }
        }

        assertEquals(mr.equals(MoveResult.OK) || mr.equals(MoveResult.BLUE_WINS)
                || mr.equals(MoveResult.RED_WINS) || mr.equals(MoveResult.DRAW), true);
    }
}
