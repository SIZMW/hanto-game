/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.piece;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

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
     * @param from
     *            The {@link HantoCoordinate} to move from.
     * @param to
     *            The {@link HantoCoordinate} to move to.
     * @param board
     *            The board with the occupied locations.
     * @return true if able to move, false otherwise.
     */
    boolean canMove(HantoCoordinate from, HantoCoordinate to,
            Map<HantoCoordinate, HantoPiece> board);
}
