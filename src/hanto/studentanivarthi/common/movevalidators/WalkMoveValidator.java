/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import java.util.ArrayList;
import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The WalkMoveValidator is a subset of the {@link MoveValidator} that considers
 * pieces that are able to walk in the game of Hanto.
 *
 * @author Aditya Nivarthi
 */
public class WalkMoveValidator implements MoveValidator {
    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, hanto.common.HantoPiece, hanto.studentanivarthi.common.board.HantoGameBoard)}
     */
    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        // TODO Make this generic for x length walks
        // TODO Make this more generic in the future
        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        final Collection<HantoCoordinate> surroundings = srcCoordImpl.getSurroundingPieces();

        // Coordinate is not next to the source
        if (!surroundings.contains(destCoordImpl)) {
            return false;
        }

        // No piece exists to move
        if (!board.hasPieceAt(new HantoCoordinateImpl(src))) {
            return false;
        }

        // Piece in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        // Source and destination are the same
        if (src.equals(dest)) {
            return false;
        }

        final HantoPiece boardPiece = board.getPieceAt(new HantoCoordinateImpl(src));

        // Piece to move is not the same color
        if (!piece.getColor().equals(boardPiece.getColor())
                || !piece.getType().equals(boardPiece.getType())) {
            return false;
        }

        // Not enough sliding space
        return isThereSpaceToMove(srcCoordImpl, destCoordImpl, board);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#isMoveValid(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, hanto.common.HantoPiece, hanto.studentanivarthi.common.board.HantoGameBoard)}
     */
    @Override
    public boolean isMoveValid(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        return board.arePiecesContiguous();
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
    protected boolean isThereSpaceToMove(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoGameBoard board) {
        // TODO Make this generic in the future

        // Get the surrounding coordinates of each coordinate
        final Collection<HantoCoordinate> srcSurroundings = src.getSurroundingPieces();
        final Collection<HantoCoordinate> destSurroundings = dest.getSurroundingPieces();

        // Remove the source and destination from the opposing list
        srcSurroundings.remove(dest);
        destSurroundings.remove(src);

        // Find the common elements, the coordinates adjacent to both source and
        // destination (TODO this only works for one hex move)
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
