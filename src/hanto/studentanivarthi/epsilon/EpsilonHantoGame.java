/**
 *
 */
package hanto.studentanivarthi.epsilon;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.game.AbstractHantoGame;

/**
 * The implementation of Epsilon Hanto, based on the {@link AbstractHantoGame}
 * class.
 *
 * @author Aditya Nivarthi
 */
public class EpsilonHantoGame extends AbstractHantoGame {
    /**
     * Creates a EpsilonHantoGame instance.
     *
     * @param movesFirst
     *            The {@link HantoPlayerColor} to start.
     */
    public EpsilonHantoGame(HantoPlayerColor movesFirst) {
        super(HantoGameID.EPSILON_HANTO, movesFirst);
    }
}
