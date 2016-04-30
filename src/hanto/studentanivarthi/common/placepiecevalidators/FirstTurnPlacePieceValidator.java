/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.placepiecevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.tournament.HantoValidMove;

/**
 * The implementation of the piece placement validation for the first move of
 * the Hanto game, based on the {@link PlacePieceValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class FirstTurnPlacePieceValidator implements PlacePieceValidator {
    private final HantoCoordinate ORIGIN = new HantoCoordinateImpl(0, 0);

    /**
     * @see hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePiece(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canPlacePiece(HantoPiece piece, HantoCoordinate dest, HantoGameBoard board) {
        return isCoordinateOrigin(new HantoCoordinateImpl(dest));
    }

    /**
     * @see hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePieceAtAll(hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public HantoValidMove canPlacePieceAtAll(HantoPiece piece, HantoGameBoard board) {
        if (!board.hasPieceAt(ORIGIN)) {
            return new HantoValidMove(piece.getType(), null, ORIGIN);
        }

        return null;
    }

    /**
     * Returns whether the specified coordinate is the origin on the board or
     * not.
     *
     * @param coordinate
     *            The coordinate to verify against the origin.
     * @return true if it is the origin, false otherwise
     */
    private boolean isCoordinateOrigin(HantoCoordinate coordinate) {
        return coordinate.equals(ORIGIN);
    }
}
