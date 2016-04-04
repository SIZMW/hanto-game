/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.Board;

/**
 * The FirstTurnPlacePieceValidator defines the requirements for placing a piece
 * within the first turn cycle of the game.
 *
 * @author Aditya Nivarthi
 */
public class FirstTurnPlacePieceValidator implements PlacePieceValidator {

    /**
     * @see {@link hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePiece(hanto.common.HantoCoordinate, hanto.common.HantoPiece, java.util.Map)}
     */
    @Override
    public boolean canPlacePiece(HantoCoordinate to, HantoPiece piece, Board board) {
        HantoCoordinateImpl coord = new HantoCoordinateImpl(to);

        // Piece already in that spot
        if (board.hasPieceAt(coord) && board.getPieceAt(coord) != null) {
            return false;
        }

        // Check if location is adjacent to some piece already on the board
        boolean isAdjacentToPiece = false;
        final List<HantoCoordinate> surroundings = coord.getSurroundingPieces();

        for (HantoCoordinate e : surroundings) {
            if (board.hasPieceAt(e) && board.getPieceAt(e) != null) {
                isAdjacentToPiece = true;
                break;
            }
        }

        return isAdjacentToPiece;
    }
}
