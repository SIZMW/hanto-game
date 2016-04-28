/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.game.AbstractHantoGame;

/**
 * The implementation of Gamma Hanto, based on the {@link AbstractHantoGame}
 * class.
 *
 * @author Aditya Nivarthi
 */
public class GammaHantoGame extends AbstractHantoGame {
    /**
     * The maximum number of moves that can be played.
     */
    private final int MAX_MOVES = 40;

    /**
     * Creates a GammaHantoGame instance.
     *
     * @param movesFirst
     *            The {@link HantoPlayerColor} to start.
     */
    public GammaHantoGame(HantoPlayerColor movesFirst) {
        super(HantoGameID.GAMMA_HANTO, movesFirst);
    }

    /**
     * @see hanto.studentanivarthi.common.game.AbstractHantoGame#getMoveResult()
     */
    @Override
    protected MoveResult getMoveResult() {
        MoveResult result = super.getMoveResult();

        // Check if the maximum number of moves has been exceeded
        if (blueTurn.getTurnCount() + redTurn.getTurnCount() >= MAX_MOVES) {
            if (result.equals(MoveResult.OK)) {
                result = MoveResult.DRAW;
            }

            markGameIsOver();
        }

        return result;
    };

    /**
     * @see hanto.studentanivarthi.common.game.AbstractHantoGame#hasPlayerResigned(hanto.common.HantoPieceType,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
     */
    @Override
    protected boolean hasPlayerResigned(HantoPieceType pieceType, HantoCoordinate src,
            HantoCoordinate dest) {
        return false;
    }
}
