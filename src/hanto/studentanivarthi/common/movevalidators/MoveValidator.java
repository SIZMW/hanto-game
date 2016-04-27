/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.tournament.HantoValidMove;

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
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if the move can be made, false otherwise.
     */
    boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board);

    /**
     * Determines if the specified piece at the specified coordinate is able to
     * move at all.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} of the piece.
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return a {@link HantoValidMove} if a move exists, null otherwise
     */
    HantoValidMove canMoveAtAll(HantoCoordinate coordinate, HantoPiece piece, HantoGameBoard board);

    /**
     * Determines whether the game board is valid after the move was made. This
     * assumes the move was made successfully in order to return relevant
     * results.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param board
     *            The current game {@link HantoGameBoard}.
     * @return true if the game board is valid after the move, false otherwise.
     */
    boolean isMoveValid(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board);
}
