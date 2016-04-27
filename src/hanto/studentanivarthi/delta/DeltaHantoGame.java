/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.delta;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.game.AbstractHantoGame;
import hanto.studentanivarthi.tournament.HantoValidMove;

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
     * @see hanto.studentanivarthi.common.game.AbstractHantoGame#hasValidMove()
     */
    @Override
    public HantoValidMove hasValidAction() {
        return null;
    }
}
