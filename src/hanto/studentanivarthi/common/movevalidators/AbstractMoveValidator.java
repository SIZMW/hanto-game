/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * This class defines the commonality between different versions of
 * {@link MoveValidator}s and methods that are shared among the various
 * implementations.
 *
 * @author Aditya Nivarthi
 */
public abstract class AbstractMoveValidator implements MoveValidator {
    protected int distance = 0;

    /**
     * Creates an AbstractMoveValidator instance.
     *
     * @param distance
     *            The maximum distance for this move.
     */
    protected AbstractMoveValidator(int distance) {
        this.distance = distance;
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoCoordinate,
     *      hanto.common.HantoCoordinate, hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        // Source and destination are the same
        if (srcCoordImpl.equals(destCoordImpl)) {
            return false;
        }

        // Source is too far from the destination
        if (isMoveDistanceTooFar(srcCoordImpl, destCoordImpl)) {
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

        final HantoPiece boardPiece = board.getPieceAt(srcCoordImpl);

        // Piece to move is not the same color
        if (!piece.getColor().equals(boardPiece.getColor())
                || !piece.getType().equals(boardPiece.getType())) {
            return false;
        }

        return true;
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.MoveValidator#canMoveAtAll(hanto.common.HantoCoordinate,
     *      hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMoveAtAll(HantoCoordinate coordinate, HantoPiece piece,
            HantoGameBoard board) {
        return true;
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.MoveValidator#isMoveValid(hanto.common.HantoCoordinate,
     *      hanto.common.HantoCoordinate, hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean isMoveValid(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        return board.arePiecesContiguous();
    }

    /**
     * Returns whether the source coordinate and destination coordinate are too
     * far apart for the move distance.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @return true if distance is too far, false otherwise
     */
    protected boolean isMoveDistanceTooFar(HantoCoordinateImpl src, HantoCoordinateImpl dest) {
        return src.getDistanceTo(dest) > distance;
    }
}
