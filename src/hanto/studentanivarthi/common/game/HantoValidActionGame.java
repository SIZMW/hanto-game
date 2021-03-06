/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.game;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.studentanivarthi.tournament.HantoValidAction;

/**
 * This interface extends the {@link HantoGame} interface to add methods usable
 * by AI players who need to get valid moves from the game.
 *
 * @author Aditya Nivarthi
 */
public interface HantoValidActionGame extends HantoGame {
    /**
     * Determines if the current player has a valid action that can be made, and
     * if so, returns a move object. If not, returns null. A piece and
     * coordinate can be passed in for the previous action to find an action
     * different from the last action.
     *
     * @param previousPieceType
     *            The {@link HantoPieceType} type in the previous action.
     * @param previousCoordinate
     *            The destination {@link HantoCoordinate} in the previous
     *            action.
     * @return a {@link HantoValidAction}, or null if no action is found
     */
    HantoValidAction hasValidAction(HantoPieceType previousPieceType,
            HantoCoordinate previousCoordinate);
}
