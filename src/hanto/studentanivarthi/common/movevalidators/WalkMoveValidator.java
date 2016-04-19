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
 * The implementation of the walk move in Hanto, based on the
 * {@link MoveValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class WalkMoveValidator extends AbstractMoveValidator {

    /**
     * Creates a WalkMoveValidator instance.
     *
     * @param distance
     *            The maximum distance for this move.
     */
    public WalkMoveValidator(int distance) {
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
        // Get superclass return value
        boolean canMove = super.canMove(src, dest, piece, board);

        // Short circuit
        if (!canMove) {
            return false;
        }

        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        // Recursive check for walking validation
        return this.canMove(srcCoordImpl, destCoordImpl, board, distance);
    }

    /**
     * Recursively checks if the walk move can be made. Validates each path that
     * the move can proceed from the source coordinate. It then simulates a
     * movement to the next step, and repeats the validation from the new
     * coordinate, until it reaches the maximum number of steps it can make, or
     * reaches the destination coordinate. At every iteration, it validates that
     * the board is contiguous.
     *
     * @param srcCoordImpl
     *            The starting {@link HantoCoordinateImpl}.
     * @param destCoordImpl
     *            The destination {@link HantoCoordinateImpl}.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @param distanceTraveled
     *            The number of steps made in the recursive calls.
     * @return true if the destination coordinate is reached, false otherwise
     */
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

            // If there is sufficient sliding space to move, continue
            if (hasSlidingSpaceToMove(srcCoordImpl, eImpl, board)) {
                HantoGameBoard boardCopy = board.copy();
                HantoPiece piece = boardCopy.removePieceAt(srcCoordImpl);
                boardCopy.placePieceAt(eImpl, piece);

                boolean hasPathReached = this.canMove(eImpl, destCoordImpl, boardCopy,
                        distanceTraveled - 1);
                if (hasPathReached) {
                    return true;
                }
            }
        }

        return false;
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
    protected boolean hasSlidingSpaceToMove(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoGameBoard board) {
        // Get the surrounding coordinates of each coordinate
        final Collection<HantoCoordinate> srcSurroundings = src.getSurroundingPieces();
        final Collection<HantoCoordinate> destSurroundings = dest.getSurroundingPieces();

        // Remove the source and destination from the opposing list
        srcSurroundings.remove(dest);
        destSurroundings.remove(src);

        // Find the common coordinates, the coordinates adjacent to both source
        // and destination
        final Collection<HantoCoordinate> common = new ArrayList<>(srcSurroundings);
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
