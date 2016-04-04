/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.piecemanager;

import hanto.common.HantoPieceType;

/**
 * This interface defines the necessary methods for classes that implement
 * keeping track of the restrictions on each player's pieces.
 *
 * @author Aditya Nivarthi
 */
public interface HantoPlayerPieceManager {

    /**
     * Returns whether the piece can be placed for this player. If it can, the
     * number remaining is decremented.
     *
     * @param piece
     *            The {@link HantoPieceType} to check.
     * @return true if it can be placed, false otherwise
     */
    boolean canPlacePiece(HantoPieceType piece);

    /**
     * Returns whether the player has any more pieces left to play.
     *
     * @return true if there are pieces, false otherwise
     */
    boolean isOutOfPieces();

    /**
     * Marks the specified piece type as being placed, and lowers the remaining
     * number of that piece.
     *
     * @param type
     *            The {@link HantoPieceType} to place.
     */
    void placePiece(HantoPieceType type);
}
