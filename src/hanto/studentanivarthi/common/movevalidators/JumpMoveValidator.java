/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.HantoDirection;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The implementation of the jump move in Hanto, based on the
 * {@link MoveValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class JumpMoveValidator extends AbstractMoveValidator {

    /**
     * Creates a JumpMoveValidator instance.
     *
     * @param distance
     *            The maximum distance for this move. The distance is not used,
     *            as there is no limit on the jump distance.
     */
    public JumpMoveValidator(int distance) {
        super(-1);
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#canMove(hanto.common.HantoCoordinate,
     *      hanto.common.HantoCoordinate, hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        // Get superclass return value
        boolean canMove = super.canMove(src, dest, piece, board);

        // Short circuit
        if (!canMove) {
            return false;
        }

        // If the direction to the destination is a valid one
        if (HantoDirection.getDirectionTo(src.getX(), src.getY(), dest.getX(), dest.getY())
                .equals(HantoDirection.NONE)) {
            return false;
        }

        return true;
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#canMoveAtAll(hanto.common.HantoCoordinate,
     *      hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMoveAtAll(HantoCoordinate coordinate, HantoPiece piece,
            HantoGameBoard board) {
        HantoCoordinate coord = null;

        // Look in each direction, moving one step farther every time
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            for (HantoDirection e : HantoDirection.values()) {
                coord = new HantoCoordinateImpl(coordinate.getX() + e.getX() * i,
                        coordinate.getY() + e.getY() * i);

                // Check if empty and simulate move
                if (!board.hasPieceAt(coord)
                        && canMoveSimulateDirectMove(coordinate, coord, piece, board)) {
                    return true;
                }
            }
        }

        return false;
    }
};
