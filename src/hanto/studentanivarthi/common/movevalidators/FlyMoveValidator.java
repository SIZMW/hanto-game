/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The implementation of the fly move in Hanto, based on the
 * {@link MoveValidator} interface. Passing a value less than 0 for the distance
 * will give this validator an unlimited fly distance.
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
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#canMoveAtAll(hanto.common.HantoCoordinate,
     *      hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMoveAtAll(HantoCoordinate coordinate, HantoPiece piece,
            HantoGameBoard board) {
        int checkDistance = isInfiniteDistance() ? Integer.MAX_VALUE : distance;
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);

        // Get ring of coordinates at each distance
        for (int i = 1; i <= checkDistance; i++) {
            final Collection<HantoCoordinate> list = coordinateImpl.getCoordinatesAtDistance(i);

            // Check if any of the coordinates are empty
            for (HantoCoordinate e : list) {
                if (!board.hasPieceAt(e) && canMoveSimulateMove(coordinate, e, piece, board)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determines if the move can actually be made, and if it is valid after
     * being executed.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return
     */
    protected boolean canMoveSimulateMove(HantoCoordinate src, HantoCoordinate dest,
            HantoPiece piece, HantoGameBoard board) {
        HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        // Check superclass if can move
        if (!canMove(srcCoordImpl, destCoordImpl, piece, board)) {
            return false;
        }

        // Simulate move
        HantoGameBoard boardCopy = board.copy();
        HantoPiece removedPiece = boardCopy.removePieceAt(srcCoordImpl);
        boardCopy.placePieceAt(destCoordImpl, removedPiece);

        // Check post conditions of simulating move
        return isMoveValid(srcCoordImpl, destCoordImpl, removedPiece, boardCopy);
    }

    /**
     * Returns whether the distance for this fly move is infinite or not.
     *
     * @return true if infinite, false otherwise
     */
    protected boolean isInfiniteDistance() {
        return distance < 0;
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#isMoveDistanceTooFar(hanto.studentanivarthi.common.HantoCoordinateImpl,
     *      hanto.studentanivarthi.common.HantoCoordinateImpl)
     */
    @Override
    protected boolean isMoveDistanceTooFar(HantoCoordinateImpl src, HantoCoordinateImpl dest) {
        // If distance is -1, then unlimited fly distance
        return !isInfiniteDistance() ? super.isMoveDistanceTooFar(src, dest) : false;
    }
}
