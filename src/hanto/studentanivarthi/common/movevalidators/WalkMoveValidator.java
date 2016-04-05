/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.HantoPieceImpl;
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
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, Board board) {
        // TODO make this generic for x length walks
        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        final List<HantoCoordinate> surroundings = srcCoordImpl.getSurroundingPieces();

        // Coordinate is not next to the source
        if (!surroundings.contains(destCoordImpl)) {
            return false;
        }

        // Piece in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        // Simulate the move to check continuity in the pieces
        final HantoPieceImpl srcPieceImpl = new HantoPieceImpl(board.removePieceAt(src));
        board.placePieceAt(dest, srcPieceImpl);

        // Fails continuity test
        if (!board.arePiecesContiguous()) {
            return false;
        }

        // Return piece back to original place
        final HantoPieceImpl returnPieceImpl = new HantoPieceImpl(board.removePieceAt(dest));
        board.placePieceAt(src, returnPieceImpl);

        return true;
    }
}
