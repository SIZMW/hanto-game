/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.playerturn;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager;

/**
 * The HantoPlayerTurn interface defines the properties of a player turn that need to
 * be stored during the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface HantoPlayerTurn {

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
     * Returns the player piece manager for this player.
     *
     * @return a {@link HantoPlayerPieceManager}
     */
    HantoPlayerPieceManager getPlayerPieceManager();

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
