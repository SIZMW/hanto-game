/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.board;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

/**
 * The HantoGameBoard interface defines methods for board implementations for the Hanto
 * game.
 *
 * @author Aditya Nivarthi
 */
public interface HantoGameBoard {
    /**
     * Returns whether the pieces on the game board are all organized in a
     * contiguous fashion.
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
     * Returns if the board is empty.
     *
     * @return true if empty, false otherwise
     */
    boolean isBoardEmpty();

    /**
     * Places the specified piece at the specified coordinate.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} on the board.
     * @param piece
     *            The {@link HantoPiece} to place.
     * @return true if placed, false otherwise
     */
    boolean placePieceAt(HantoCoordinate coordinate, HantoPiece piece);

    /**
     * Removes the piece at the specified coordinate.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} on the board.
     * @return true if removed, false otherwise
     */
    HantoPiece removePieceAt(HantoCoordinate coordinate);

    /**
     * @see {@link java.lang.Object#toString()}
     */
    @Override
    String toString();
}
