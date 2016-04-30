/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.tournament.HantoValidMove;

/**
 * The implementation of the piece placement validation for the second move of
 * the Hanto game, based on the {@link PlacePieceValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class SecondTurnPlacePieceValidator implements PlacePieceValidator {
    private final HantoCoordinateImpl ORIGIN = new HantoCoordinateImpl(0, 0);

    /**
     * @see hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePiece(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canPlacePiece(HantoPiece piece, HantoCoordinate dest, HantoGameBoard board) {
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        // Piece already in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        // Check if location is adjacent to some piece already on the board
        boolean isAdjacentToPiece = false;
        final Collection<HantoCoordinate> surroundings = destCoordImpl.getSurroundingCoordinates();

        for (HantoCoordinate c : surroundings) {
            if (board.hasPieceAt(c)) {
                isAdjacentToPiece = true;
                break;
            }
        }

        return isAdjacentToPiece;
    }

    /**
     * @see hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePieceAtAll(hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public HantoValidMove canPlacePieceAtAll(HantoPiece piece, HantoGameBoard board) {
        for (HantoCoordinate e : ORIGIN.getSurroundingCoordinates()) {
            if (!board.hasPieceAt(e)) {
                return new HantoValidMove(piece.getType(), null, e);
            }
        }

        return null;
    }
}
