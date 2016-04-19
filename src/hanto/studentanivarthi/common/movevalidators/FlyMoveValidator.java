/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The implementation of the fly move in Hanto, based on the
 * {@link MoveValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class FlyMoveValidator extends AbstractMoveValidator {

    /**
     * Creates a FlyMoveValidator instance.
     *
     * @param distance
     *            The maximum distance for this move.
     */
    public FlyMoveValidator(int distance) {
        super(distance);
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#canMove(hanto.common.HantoCoordinate,
     *      hanto.common.HantoCoordinate, hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        return super.canMove(src, dest, piece, board);
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#isMoveValid(hanto.common.HantoCoordinate,
     *      hanto.common.HantoCoordinate, hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean isMoveValid(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        return board.arePiecesContiguous();
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#isMoveDistanceTooFar(hanto.studentanivarthi.common.HantoCoordinateImpl,
     *      hanto.studentanivarthi.common.HantoCoordinateImpl)
     */
    @Override
    protected boolean isMoveDistanceTooFar(HantoCoordinateImpl src, HantoCoordinateImpl dest) {
        // If distance is -1, then unlimited fly distance
        return distance >= 0 ? src.getDistanceTo(dest) > distance : false;
    }
}
