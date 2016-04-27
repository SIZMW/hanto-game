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
        Collection<HantoCoordinate> ownedCoordinates = board
                .getCoordinatesWithPiecesOfColor(piece.getColor());

        // Get all of the same color pieces
        for (HantoCoordinate e : ownedCoordinates) {
            Collection<HantoCoordinate> surroundings = new HantoCoordinateImpl(e)
                    .getSurroundingCoordinates();
            if (hasValidCoordinateNextToOwnedPiece(piece, board, surroundings, e)) {
                return new HantoValidMove(piece.getType(), null, e);
            }
        }

        return null;
    }

    /**
     * Determines if there is a valid coordinate to place a piece given the
     * already owned piece and its surrounding coordinates.
     *
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @param surroundings
     *            The {@link Collection}&lt;{@link HantoCoordinate}&gt; of
     *            coordinates around the piece.
     * @param ownedCoordinate
     *            The {@link HantoCoordinate} of the already owned piece.
     * @return true if valid coordinate exists, false otherwise
     */
    protected boolean hasValidCoordinateNextToOwnedPiece(HantoPiece piece, HantoGameBoard board,
            Collection<HantoCoordinate> surroundings, HantoCoordinate ownedCoordinate) {
        // Get coordinates around the same color piece
        for (HantoCoordinate s : surroundings) {
            if (!board.hasPieceAt(s)) {
                Collection<HantoCoordinate> commonNeighbors = new HantoCoordinateImpl(s)
                        .getCommonNeighborCoordinates(ownedCoordinate);

                // Check if the empty coordinate next to the owned piece is also
                // next to an opposing piece
                if (!hasOpponentPieceAdjacent(piece, board, commonNeighbors)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determines if any of the common neighbors has a piece of the opposing
     * color.
     *
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @param commonNeighbors
     *            The {@link Collection}&lt;{@link HantoCoordinate}&gt; of
     *            common neighboring coordinates.
     * @return true if there is an opponent, false otherwise
     */
    protected boolean hasOpponentPieceAdjacent(HantoPiece piece, HantoGameBoard board,
            Collection<HantoCoordinate> commonNeighbors) {
        // Get common neighbors between the empty coordinate and same color
        // piece
        for (HantoCoordinate c : commonNeighbors) {
            // If any neighbor has the opposite color
            if (board.hasPieceAt(c) && !board.getPieceAt(c).getColor().equals(piece.getColor())) {
                return false;
            }
        }

        return true;
    }
}
