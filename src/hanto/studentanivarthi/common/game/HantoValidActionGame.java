/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.game;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.studentanivarthi.tournament.HantoValidMove;

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
     * @return a {@link HantoValidMove}, or null if no action is found
     */
    HantoValidMove hasValidAction(HantoPieceType previousPieceType,
            HantoCoordinate previousCoordinate);
}
