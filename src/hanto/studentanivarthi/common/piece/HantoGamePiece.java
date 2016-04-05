/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.piece;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.Board;

/**
 * The HantoGamePiece interface extends the existing HantoPiece interface in
 * order to add additional methods for determine if a piece is able to move from
 * a coordinate to another coordinate.
 *
 * @author Aditya Nivarthi
 */
public interface HantoGamePiece extends HantoPiece {

    /**
     * Returns whether this piece can move to the specified location.
     *
     * @param src
     *            The {@link HantoCoordinate} to move from.
     * @param dest
     *            The {@link HantoCoordinate} to move to.
     * @param board
     *            The game {@link Board} with the occupied locations.
     * @return true if able to move, false otherwise.
     */
    boolean canMove(HantoCoordinate src, HantoCoordinate dest, Board board);
}
