/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.tournament.HantoValidMove;

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
     * @see hanto.studentanivarthi.common.movevalidators.AbstractMoveValidator#canMoveAtAll(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public HantoValidMove canMoveAtAll(HantoPiece piece, HantoCoordinate coordinate,
            HantoGameBoard board) {
        int checkDistance = isInfiniteDistance() ? Integer.MAX_VALUE : distance;
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);

        // Get ring of coordinates at each distance
        for (int i = 1; i <= checkDistance; i++) {
            final Collection<HantoCoordinate> list = coordinateImpl.getCoordinatesAtDistance(i);

            // Check if any of the coordinates are empty
            for (HantoCoordinate e : list) {
                if (!board.hasPieceAt(e)
                        && canMoveSimulateDirectMove(coordinate, e, piece, board)) {
                    return new HantoValidMove(piece.getType(), coordinate, e);
                }
            }
        }

        return null;
    }
}
