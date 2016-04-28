/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The implementation of no movement in Hanto, based on the
 * {@link MoveValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class NoMoveValidator extends AbstractMoveValidator {
    /**
     * Creates a NoMoveValidator instance.
     *
     * @param distance
     *            The maximum distance for this move.
     */
    public NoMoveValidator(int distance) {
        super(0);
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#canMove(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMove(HantoPiece piece, HantoCoordinate src, HantoCoordinate dest,
            HantoGameBoard board) {
        return false;
    }
}
