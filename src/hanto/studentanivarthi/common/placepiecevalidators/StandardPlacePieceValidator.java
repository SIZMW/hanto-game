/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.Board;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;

/**
 * The StandardPlacePieceValidator defines the requirements to place a piece
 * during normal play of the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public class StandardPlacePieceValidator implements PlacePieceValidator {

    /**
     * @see {@link hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePiece(hanto.common.HantoCoordinate, hanto.common.HantoPiece, java.util.Map)}
     */
    @Override
    public boolean canPlacePiece(HantoCoordinate dest, HantoPiece piece, Board board) {
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(piece);

        // Piece already in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        // Check if location is adjacent to some piece already on the board
        final Collection<HantoCoordinate> surroundings = destCoordImpl.getSurroundingPieces();

        for (HantoCoordinate e : surroundings) {
            if (board.hasPieceAt(e) && board.getPieceAt(e) != null) {
                if (!pieceImpl.getColor().equals(board.getPieceAt(e).getColor())) {
                    return false;
                }
            }
        }

        return true;
    }
}
