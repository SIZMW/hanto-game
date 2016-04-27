/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;
import hanto.studentanivarthi.tournament.HantoValidMove;

/**
 * The implementation of the piece placement validation for any standard move of
 * the Hanto game, based on the {@link PlacePieceValidator} interface.
 *
 * @author Aditya Nivarthi
 */
public class StandardPlacePieceValidator implements PlacePieceValidator {
    /**
     * @see hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePiece(hanto.common.HantoCoordinate,
     *      hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public boolean canPlacePiece(HantoCoordinate dest, HantoPiece piece, HantoGameBoard board) {
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(piece);

        // Piece already in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        // Check if location is adjacent to some piece already on the board
        final Collection<HantoCoordinate> surroundings = destCoordImpl.getSurroundingCoordinates();

        for (HantoCoordinate e : surroundings) {
            if (board.hasPieceAt(e)) {
                // Check if not adjacent to piece of opposing color
                if (!pieceImpl.getColor().equals(board.getPieceAt(e).getColor())) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @see hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator#canPlacePieceAtAll(hanto.common.HantoPiece,
     *      hanto.studentanivarthi.common.board.HantoGameBoard)
     */
    @Override
    public HantoValidMove canPlacePieceAtAll(HantoPiece piece, HantoGameBoard board) {
        // Get all of the same color pieces
        Collection<HantoCoordinate> ownedCoordinates = board
                .getCoordinatesWithPiecesOfColor(piece.getColor());

        // Check surroundings around owned pieces for empty coordinates
        for (HantoCoordinate e : ownedCoordinates) {
            Collection<HantoCoordinate> surroundings = new HantoCoordinateImpl(e)
                    .getSurroundingCoordinates();

            // Find valid empty coordinate in surroundings
            for (HantoCoordinate s : surroundings) {
                if (!board.hasPieceAt(s)) {
                    if (canPlacePiece(s, piece, board)) {
                        return new HantoValidMove(piece.getType(), null, s);
                    }
                }
            }
        }

        return null;
    }
}
