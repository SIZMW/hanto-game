/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.movevalidators;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.tournament.HantoValidAction;

/**
 * The implementation of the fly move in Hanto, based on the
 * {@link MoveValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class FlyMoveValidator extends AbstractMoveValidator {
    /**
     * Creates a FlyMoveValidator instance.
     *
     * @param distance
     *            The maximum distance for this move. If this value is < 0, the
     *            move distance is infinite.
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
    public HantoValidAction canMoveAtAll(HantoPiece piece, HantoCoordinate coordinate,
            HantoGameBoard board) {
        final int checkDistance = isInfiniteDistance() ? Integer.MAX_VALUE : distance;
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);

        // Get ring of coordinates at each distance
        for (int i = 1; i <= checkDistance; i++) {
            final Collection<HantoCoordinate> list = coordinateImpl.getCoordinatesAtDistance(i);

            // Check if any of the coordinates are empty
            for (HantoCoordinate e : list) {
                if (!board.hasPieceAt(e)
                        && canMoveSimulateDirectMove(coordinate, e, piece, board)) {
                    return new HantoValidAction(piece.getType(), coordinate, e);
                }
            }
        }

        return null;
    }
}
