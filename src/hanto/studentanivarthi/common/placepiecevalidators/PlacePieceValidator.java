/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.placepiecevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.tournament.HantoValidAction;

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
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param dest
     *            The {@link HantoCoordinate} to place the piece at.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if it can be placed, false otherwise
     */
    boolean canPlacePiece(HantoPiece piece, HantoCoordinate dest, HantoGameBoard board);

    /**
     * Determines if the piece can be placed at all on the board.
     *
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return a {@link HantoValidAction} if a move exists, null otherwise
     */
    HantoValidAction canPlacePieceAtAll(HantoPiece piece, HantoGameBoard board);
}
