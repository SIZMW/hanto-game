/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The PlacePieceValidator interface defines the methods for piece placement
 * validation for the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface PlacePieceValidator {
    /**
     * Determines if the piece can be placed at the specified coordinate given
     * the current board.
     *
     * @param dest
     *            The {@link HantoCoordinate} to place the piece at.
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if it can be placed, false otherwise
     */
    boolean canPlacePiece(HantoCoordinate dest, HantoPiece piece, HantoGameBoard board);

    /**
     * Determines if the piece can be placed at all on the board.
     * 
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if ti can be placed, false otherwise
     */
    boolean canPlacePieceAtAll(HantoPiece piece, HantoGameBoard board);
}
