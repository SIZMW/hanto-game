/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import java.util.ArrayList;
import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;

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
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoGameBoard board) {
        // TODO Make this generic for x length walks
        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        final Collection<HantoCoordinate> surroundings = srcCoordImpl.getSurroundingPieces();

        // Coordinate is not next to the source
        if (!surroundings.contains(destCoordImpl)) {
            return false;
        }

        // Piece in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        // TODO Make this more generic in the future
        // Not enough sliding space
        if (!isThereSpaceToMove(srcCoordImpl, destCoordImpl, board)) {
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

    /**
     * Returns whether there is sufficient sliding space to move from the source
     * to the destination coordinate.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if enough space, false otherwise
     */
    private boolean isThereSpaceToMove(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoGameBoard board) {
        // Get the surrounding coordinates of each coordinate
        final Collection<HantoCoordinate> srcSurroundings = src.getSurroundingPieces();
        final Collection<HantoCoordinate> destSurroundings = dest.getSurroundingPieces();

        // Remove the source and destination from the opposing list
        srcSurroundings.remove(dest);
        destSurroundings.remove(src);

        // Find the common elements, the coordinates adjacent to both source and
        // destination (this only works for one hex move)
        // TODO Make this generic in the future
        Collection<HantoCoordinate> common = new ArrayList<>(srcSurroundings);
        common.retainAll(destSurroundings);

        // If any of the common adjacent coordinates is empty, then it can move
        for (HantoCoordinate e : common) {
            if (!board.hasPieceAt(e)) {
                return true;
            }
        }

        return false;
    }
}
