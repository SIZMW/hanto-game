/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.common.coordinate.HantoDirection;

/**
 * The implementation of the jump move in Hanto, based on the
 * {@link MoveValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class JumpMoveValidator extends AbstractMoveValidator {
    private static final int MULTIPLIER = 100;

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

        HantoDirection direction;

        // If the direction to the destination is a valid one
        if ((direction = HantoDirection.getDirectionTo(src.getX(), src.getY(), dest.getX(),
                dest.getY())).equals(HantoDirection.NONE)) {
            return false;
        }

        int pieces = 0;
        HantoCoordinateImpl runner = new HantoCoordinateImpl(src);

        // Start checking at next coordinate in that direction
        runner = new HantoCoordinateImpl(runner.getX() + direction.getX(),
                runner.getY() + direction.getY());

        // While we have not reached the destination, check if all are occupied
        while (!runner.equals(dest)) {
            if (!board.hasPieceAt(runner)) {
                return false;
            }

            // Increment
            pieces++;
            runner = new HantoCoordinateImpl(runner.getX() + direction.getX(),
                    runner.getY() + direction.getY());
        }

        // Jumping over at least one piece
        return pieces > 0;
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
        for (int i = 1; i < board.getNumberOfPieces() * MULTIPLIER; i++) {
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
