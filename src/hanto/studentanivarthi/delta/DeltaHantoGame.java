/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.game.AbstractHantoGame;
import hanto.studentanivarthi.tournament.HantoValidAction;

/**
 * The implementation of Delta Hanto, based on the {@link AbstractHantoGame}
 * class.
 *
 * @author Aditya Nivarthi
 */
public class DeltaHantoGame extends AbstractHantoGame {
    /**
     * Creates a DeltaHantoGame instance.
     *
     * @param movesFirst
     *            The {@link HantoPlayerColor} to start.
     */
    public DeltaHantoGame(HantoPlayerColor movesFirst) {
        super(HantoGameID.DELTA_HANTO, movesFirst);
    }

    /**
     * @see hanto.studentanivarthi.common.game.AbstractHantoGame#hasValidAction(hanto.common.HantoPieceType,
     *      hanto.common.HantoCoordinate)
     */
    @Override
    public HantoValidAction hasValidAction(HantoPieceType pieceType, HantoCoordinate coordinate) {
        return null;
    }
}
