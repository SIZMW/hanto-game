/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.Collection;

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
    public boolean canPlacePiece(HantoCoordinate dest, HantoPiece piece, Board board) {
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        // Piece already in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        // Check if location is adjacent to some piece already on the board
        boolean isAdjacentToPiece = false;
        final Collection<HantoCoordinate> surroundings = destCoordImpl.getSurroundingPieces();

        for (HantoCoordinate c : surroundings) {
            if (board.hasPieceAt(c) && board.getPieceAt(c) != null) {
                isAdjacentToPiece = true;
                break;
            }
        }

        return isAdjacentToPiece;
    }
}
