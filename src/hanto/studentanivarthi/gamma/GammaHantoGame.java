/**
 * This class was created for the Hanto game implementation for CS 4233.
 */
package hanto.studentanivarthi.gamma;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.game.AbstractHantoGame;

/**
 * The implementation of Gamma Hanto.
 *
 * @author Aditya Nivarthi
 */
public class GammaHantoGame extends AbstractHantoGame {
    /**
     * The maximum number of moves that can occur in Gamma Hanto.
     */
    private final int MAX_MOVES = 40;

    /**
     * Creates a GammaHantoGame instance with the specified starting player.
     *
     * @param movesFirst
     *            The {@link HantoPlayerColor} to start.
     */
    public GammaHantoGame(HantoPlayerColor movesFirst) {
        super(HantoGameID.GAMMA_HANTO, movesFirst);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.game.AbstractHantoGame#getMoveResult()}
     */
    @Override
    protected MoveResult getMoveResult() {
        MoveResult result = super.getMoveResult();

        if (blueTurn.getTurnCount() + redTurn.getTurnCount() >= MAX_MOVES) {
            if (result.equals(MoveResult.OK)) {
                result = MoveResult.DRAW;
            }

            isGameOver = true;
        }

        return result;
    }
}
