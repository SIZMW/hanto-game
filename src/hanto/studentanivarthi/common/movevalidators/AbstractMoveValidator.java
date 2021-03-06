/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.tournament.HantoValidAction;

/**
 * This class defines the commonality between different versions of
 * {@link MoveValidator} and methods that are shared among the various
 * implementations.
 *
 * @author Aditya Nivarthi
 */
public abstract class AbstractMoveValidator implements MoveValidator {
    /**
     * The maximum distance for the move. If this value is < 0, the move
     * distance is infinite.
     */
    protected int distance = 0;

    /**
     * Creates an AbstractMoveValidator instance.
     *
     * @param distance
     *            The maximum distance for this move. If this value is < 0, the
     *            move distance is infinite.
     */
    protected AbstractMoveValidator(int distance) {
        this.distance = distance;
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canMove(HantoPiece piece, HantoCoordinate src, HantoCoordinate dest,
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
     * @see hanto.studentanivarthi.common.movevalidators.MoveValidator#canMoveAtAll(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public HantoValidAction canMoveAtAll(HantoPiece piece, HantoCoordinate coordinate,
            HantoGameBoard board) {
        return null;
    }

    /**
     * @see hanto.studentanivarthi.common.movevalidators.MoveValidator#isMoveValid(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean isMoveValid(HantoPiece piece, HantoCoordinate src, HantoCoordinate dest,
            HantoGameBoard board) {
        return board.isBoardContiguous();
    }

    /**
     * Determines if the move can actually be made, and if it is valid after
     * being executed. This method is useful for simulating moves that are
     * direct and not continuous.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if move can be executed, false otherwise
     */
    protected boolean canMoveSimulateDirectMove(HantoCoordinate src, HantoCoordinate dest,
            HantoPiece piece, HantoGameBoard board) {
        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        // Check superclass if can move
        if (!canMove(piece, srcCoordImpl, destCoordImpl, board)) {
            return false;
        }

        // Simulate move
        final HantoGameBoard boardCopy = board.copy();
        final HantoPiece removedPiece = boardCopy.removePieceAt(srcCoordImpl);
        boardCopy.placePieceAt(removedPiece, destCoordImpl);

        // Check post conditions of simulating move
        return isMoveValid(removedPiece, srcCoordImpl, destCoordImpl, boardCopy);
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
        return !isInfiniteDistance() ? src.getDistanceTo(dest) > distance : false;
    }
}
