/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.board;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

/**
 * The HantoBoard interface defines core methods for board implementations in
 * the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface HantoBoard {
    /**
     * Returns whether the pieces on the game board are all organized in a
     * contiguous manner.
     *
     * @return true if contiguous, false otherwise
     */
    boolean arePiecesContiguous();

    /**
     * Returns the piece at the specified coordinate.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} on the board.
     * @return a {@link HantoPiece}
     */
    HantoPiece getPieceAt(HantoCoordinate coordinate);

    /**
     * Returns whether there is a piece at the specified coordinate.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} on the board.
     * @return true if occupied, false otherwise
     */
    boolean hasPieceAt(HantoCoordinate coordinate);

    /**
     * Places the specified piece at the specified coordinate.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} on the board.
     * @param piece
     *            The {@link HantoPiece} to place.
     */
    void placePieceAt(HantoCoordinate coordinate, HantoPiece piece);

    /**
     * Removes the piece at the specified coordinate.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} on the board.
     * @return true if removed, false otherwise
     */
    HantoPiece removePieceAt(HantoCoordinate coordinate);
}
