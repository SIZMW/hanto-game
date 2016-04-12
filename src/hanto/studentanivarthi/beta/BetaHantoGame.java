/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.game.AbstractHantoGame;

/**
 * The implementation of Beta Hanto.
 *
 * @author Aditya Nivarthi
 */
public class BetaHantoGame extends AbstractHantoGame {
    /**
     * Creates a BetaHantoGame with the specified player color of who moves
     * first.
     *
     * @param movesFirst
     *            The player to move first.
     */
    public BetaHantoGame(HantoPlayerColor movesFirst) {
        super(HantoGameID.BETA_HANTO, movesFirst);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.game.AbstractHantoGame#makeMove(hanto.common.HantoPieceType, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)}
     */
    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate src, HantoCoordinate dest)
            throws HantoException {
        return super.makeMove(pieceType, src, dest);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.game.AbstractHantoGame#getMoveResult()}
     */
    @Override
    protected MoveResult getMoveResult() {
        MoveResult result = super.getMoveResult();

        if (blueTurn.isOutOfPieces() && redTurn.isOutOfPieces()) {
            if (result.equals(MoveResult.OK)) {
                result = MoveResult.DRAW;
            }

            setGameIsOver();
        }

        return result;
    }
}
