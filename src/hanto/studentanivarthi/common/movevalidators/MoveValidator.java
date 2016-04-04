/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.studentanivarthi.common.board.Board;

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
     * @param from
     *            The starting {@link HantoCoordinate}.
     * @param to
     *            The destination {@link HantoCoordinate}.
     * @param board
     *            The current game {@link Board}.
     * @return true if the move can be made, false otherwise.
     */
    boolean canMove(HantoCoordinate from, HantoCoordinate to, Board board);
}
