/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.HantoPieceImpl;
import hanto.studentanivarthi.common.board.Board;

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
    public boolean canPlacePiece(HantoCoordinate to, HantoPiece piece, Board board) {
        HantoCoordinateImpl coord = new HantoCoordinateImpl(to);
        HantoPieceImpl type = new HantoPieceImpl(piece);

        // Piece already in that spot
        if (board.hasPieceAt(coord)) {
            return false;
        }

        // Check if location is adjacent to some piece already on the board
        final List<HantoCoordinate> surroundings = coord.getSurroundingPieces();

        for (HantoCoordinate e : surroundings) {
            if (board.hasPieceAt(e) && board.getPieceAt(e) != null) {
                if (!type.getColor().equals(board.getPieceAt(e).getColor())) {
                    return false;
                }
            }
        }

        return true;
    }
}
