/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

/**
 * The PlacePieceValidator interface defines the methods for piece placement
 * validators for the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface PlacePieceValidator {
    /**
     * Determines if the piece can be placed at the specified coordinate given
     * the current board.
     *
     * @param to
     *            The {@link HantoCoordinate} to place the piece at.
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param board
     *            The current board.
     * @return true if it can be placed, false otherwise
     */
    boolean canPlacePiece(HantoCoordinate to, HantoPiece piece,
            Map<HantoCoordinate, HantoPiece> board);
}
