/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.tournament.HantoValidAction;

/**
 * The MoveValidator interface defines the methods that are used for move
 * validation.
 *
 * @author Aditya Nivarthi
 */
public interface MoveValidator {
    /**
     * Determines whether the piece can move from the starting coordinate to the
     * destination coordinate given the current board.
     *
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if the move can be made, false otherwise.
     */
    boolean canMove(HantoPiece piece, HantoCoordinate src, HantoCoordinate dest,
            HantoGameBoard board);

    /**
     * Determines if the specified piece at the specified coordinate is able to
     * move at all.
     *
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param coordinate
     *            The {@link HantoCoordinate} of the piece.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return a {@link HantoValidAction} if a move exists, null otherwise
     */
    HantoValidAction canMoveAtAll(HantoPiece piece, HantoCoordinate coordinate, HantoGameBoard board);

    /**
     * Determines whether the game board is valid after the move was made. This
     * assumes the move was made successfully in order to return relevant
     * results.
     *
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if the game board is valid after the move, false otherwise.
     */
    boolean isMoveValid(HantoPiece piece, HantoCoordinate src, HantoCoordinate dest,
            HantoGameBoard board);
}
