/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The implementation of the piece placement validation for the first move of
 * the Hanto game, based on the {@link PlacePieceValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class FirstTurnPlacePieceValidator implements PlacePieceValidator {
    private final HantoCoordinate ORIGIN = new HantoCoordinateImpl(0, 0);

    /**
     * @see hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePiece(hanto.common.HantoCoordinate,
     *      hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canPlacePiece(HantoCoordinate dest, HantoPiece piece, HantoGameBoard board) {
        return isCoordinateOrigin(new HantoCoordinateImpl(dest));
    }

    /**
     * Returns whether the specified coordinate is the origin or not.
     *
     * @param coordinate
     *            The {@link HantoCoordiante} to check.
     * @return true if origin, false otherwise
     */
    private boolean isCoordinateOrigin(HantoCoordinate coordinate) {
        return coordinate.equals(ORIGIN);
    }
}
