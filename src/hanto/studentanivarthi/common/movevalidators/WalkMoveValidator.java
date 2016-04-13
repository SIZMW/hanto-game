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
public class WalkMoveValidator extends AbstractMoveValidator {

    /**
     * Creates a WalkMoveValidator instance.
     *
     * @param distance
     *            The maximum distance that the piece can travel.
     */
    public WalkMoveValidator(int distance) {
        super(distance);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, hanto.common.HantoPiece, hanto.studentanivarthi.common.board.HantoGameBoard)}
     */
    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        boolean canMove = super.canMove(src, dest, piece, board);
        if (!canMove) {
            return false;
        }

        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        boolean canDoWalkMove = this.canMove(srcCoordImpl, destCoordImpl, board, distance);
        //
        // // TODO Make this generic for x length walks
        // // TODO Make this more generic in the future
        // final HantoCoordinateImpl srcCoordImpl = new
        // HantoCoordinateImpl(src);
        // final HantoCoordinateImpl destCoordImpl = new
        // HantoCoordinateImpl(dest);
        //
        // final Collection<HantoCoordinate> surroundings =
        // srcCoordImpl.getSurroundingPieces();
        //
        // // Coordinate is not next to the source
        // if (!surroundings.contains(destCoordImpl)) {
        // return false;
        // }
        //
        // // Not enough sliding space
        // return isThereSpaceToMove(srcCoordImpl, destCoordImpl, board);

        return canDoWalkMove;
    }

    protected boolean canMove(HantoCoordinateImpl srcCoordImpl, HantoCoordinateImpl destCoordImpl,
            HantoGameBoard board, int distanceTraveled) {
        // Pieces must be contiguous at every level
        if (!board.arePiecesContiguous()) {
            return false;
        }

        // Base case, we reached the destination
        if (srcCoordImpl.equals(destCoordImpl)) {
            return true;
        }

        // We have moved more coordinates than the maximum, and have not reached
        // the destination
        if (distanceTraveled <= 0) {
            return false;
        }

        // Check empty surroundings and proceed to next step of path to
        // destination
        final Collection<HantoCoordinate> emptySurroundings = board
                .getEmptySurroundingCoordinates(srcCoordImpl);

        for (HantoCoordinate e : emptySurroundings) {
            HantoCoordinateImpl eImpl = new HantoCoordinateImpl(e);

            if (isThereSpaceToMove(srcCoordImpl, eImpl, board)) {
                HantoGameBoard boardCopy = board.clone();
                HantoPiece piece = boardCopy.removePieceAt(srcCoordImpl);
                boardCopy.placePieceAt(eImpl, piece);

                boolean hasPathReached = this.canMove(eImpl, destCoordImpl, boardCopy,
                        distanceTraveled - 1);
                if (hasPathReached) {
                    return true;
                }
            }
        }

        return false; // TODO check contiguous simulate moving
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
        // Get the surrounding coordinates of each coordinate
        final Collection<HantoCoordinate> srcSurroundings = src.getSurroundingPieces();
        final Collection<HantoCoordinate> destSurroundings = dest.getSurroundingPieces();

        // Remove the source and destination from the opposing list
        srcSurroundings.remove(dest);
        destSurroundings.remove(src);

        // Find the common elements, the coordinates adjacent to both source and
        // destination
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
