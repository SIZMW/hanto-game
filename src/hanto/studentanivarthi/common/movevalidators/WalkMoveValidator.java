/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.Board;

/**
 * The WalkMoveValidator is a subset of the move validators that considers
 * pieces that are able to walk in the game of Hanto.
 *
 * @author Aditya Nivarthi
 */
public class WalkMoveValidator implements MoveValidator {

    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, java.util.Map)}
     */
    @Override
    public boolean canMove(HantoCoordinate from, HantoCoordinate to, Board board) {
        // TODO make this generic for x length walks
        HantoCoordinateImpl src = new HantoCoordinateImpl(from);
        HantoCoordinateImpl dest = new HantoCoordinateImpl(to);

        List<HantoCoordinate> surrounding = src.getSurroundingPieces();

        // Coordinate is not next to the source
        if (!surrounding.contains(dest)) {
            return false;
        }

        // Piece in that spot
        if (board.hasPieceAt(dest) && board.getPieceAt(dest) != null) {
            return false;
        }
        return true;
    }
}
