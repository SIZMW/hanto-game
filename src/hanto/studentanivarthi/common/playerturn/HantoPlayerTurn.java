/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.playerturn;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * The HantoPlayerTurn interface defines the properties of a player turn that
 * need to be stored during the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface HantoPlayerTurn {
    /**
     * Returns whether the piece can be placed for this player. If it can, the
     * number remaining is decremented.
     *
     * @param pieceType
     *            The {@link HantoPieceType} to check.
     * @return true if it can be placed, false otherwise
     */
    boolean canPlacePiece(HantoPieceType pieceType);

    /**
     * Returns the color of the player associated with this turn.
     *
     * @return a {@link HantoPlayerColor}
     */
    HantoPlayerColor getColor();

    /**
     * Returns this player's butterfly coordinate if it exists, or null if it
     * has not been placed.
     *
     * @return a {@link HantoCoordinate}
     */
    HantoCoordinate getPlayerButterflyCoordinate();

    /**
     * Returns the number of turns this player has played.
     *
     * @return an integer
     */
    int getTurnCount();

    /**
     * Returns whether this player has a butterfly placed on the board.
     *
     * @return true if butterfly exists, false otherwise
     */
    boolean hasButterflyCoordinate();

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
     * @param pieceType
     *            The {@link HantoPieceType} to place.
     */
    void placePiece(HantoPieceType pieceType);

    /**
     * Sets the player's butterfly location to the specified coordinate.
     *
     * @param coordinate
     *            The {@link HantoCoordinate} of the butterfly.
     */
    void setPlayerButterflyCoordinate(HantoCoordinate coordinate);

    /**
     * Updates the turn count by the specified amount.
     *
     * @param delta
     *            The integer value to update by.
     */
    void updateTurnCount(int delta);
}
