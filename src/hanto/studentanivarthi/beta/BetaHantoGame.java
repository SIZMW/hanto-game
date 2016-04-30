/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.game.AbstractHantoGame;

/**
 * The implementation of Beta Hanto, based on the {@link AbstractHantoGame}
 * class.
 *
 * @author Aditya Nivarthi
 */
public class BetaHantoGame extends AbstractHantoGame {
    /**
     * Creates a BetaHantoGame instance.
     *
     * @param movesFirst
     *            The {@link HantoPlayerColor} to move first.
     */
    public BetaHantoGame(HantoPlayerColor movesFirst) {
        super(HantoGameID.BETA_HANTO, movesFirst);
    }

    /**
     * @see hanto.studentanivarthi.common.game.AbstractHantoGame#getMoveResult()
     */
    @Override
    protected MoveResult getMoveResult() {
        MoveResult result = super.getMoveResult();

        // Check if both players are out of pieces
        if (blueTurn.isOutOfPieces() && redTurn.isOutOfPieces()) {
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
        // No resignation allowed
        return false;
    }
}
