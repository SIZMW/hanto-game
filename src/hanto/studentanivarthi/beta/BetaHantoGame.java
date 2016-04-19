/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.game.AbstractHantoGame;

/**
 * The implementation of Beta Hanto, based on the {@link HantoGame} interface.
 *
 * @author Aditya Nivarthi
 */
public class BetaHantoGame extends AbstractHantoGame {
    /**
     * Creates a BetaHantoGame instance.
     *
     * @param movesFirst
     *            The player to move first.
     */
    public BetaHantoGame(HantoPlayerColor movesFirst) {
        super(HantoGameID.BETA_HANTO, movesFirst);
    }

    /**
     * @see hanto.studentanivarthi.common.game.AbstractHantoGame#makeMove(hanto.common.HantoPieceType,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
     */
    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate src, HantoCoordinate dest)
            throws HantoException {
        return super.makeMove(pieceType, src, dest);
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
        return false;
    }
}
