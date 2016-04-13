package hanto.studentanivarthi.delta;

import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.game.AbstractHantoGame;

/**
 * The implementation of Delta Hanto, based on the {@link HantoGame} interface.
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
}
