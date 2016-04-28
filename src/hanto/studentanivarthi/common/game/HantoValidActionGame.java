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
     * Returns a valid move or piece placement action if one exists for the
     * current player, or null if none exist.
     *
     * @return a {@link HantoValidMove} if it exists, null otherwise
     */
    HantoValidMove hasValidAction(HantoPieceType pieceType, HantoCoordinate coordinate);
}
