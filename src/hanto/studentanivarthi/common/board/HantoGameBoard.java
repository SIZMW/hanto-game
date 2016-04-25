/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.board;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

/**
 * The HantoGameBoard interface defines methods for board implementations for
 * the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface HantoGameBoard { // TODO clean up this interface
    /**
     * Returns whether the pieces on the game board are all organized in a
     * contiguous fashion.
     *
     * @return true if contiguous, false otherwise
     */
    boolean arePiecesContiguous();

    /**
     * Returns a copy of this HantoGameBoard.
     *
     * @return a {@link HantoGameBoard}
     */
    HantoGameBoard copy();

    /**
     * Returns a collection of coordinates surrounding the specified coordinate
     * that do not have a piece occupying them.
     *
     * @param coordinate
     *            The coordinate to get the empty surrounding coordinates
     *            around.
     * @return a {@link Collection}&lt;{@link HantoCoordinate}&gt;
     */
    Collection<HantoCoordinate> getEmptySurroundingCoordinates(HantoCoordinate coordinate);

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

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    String toString();
}
