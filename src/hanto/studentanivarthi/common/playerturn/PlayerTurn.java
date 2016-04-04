/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.playerturn;

import java.util.Optional;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager;

/**
 * The PlayerTurn interface defines the properties of a player turn that need to
 * be stored during the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface PlayerTurn {

    /**
     * Returns the color of the player associated with this turn.
     *
     * @return a {@link HantoPlayerColor}
     */
    public HantoPlayerColor getColor();

    /**
     * Returns this player's butterfly coordinate as an {@link Optional}, to
     * handle if the butterfly has not been placed.
     *
     * @return an {@link Optional}&lt;{@link HantoCoordinate}&gt;
     */
    public Optional<HantoCoordinate> getPlayerButterflyCoordinate();

    /**
     * Returns the player piece manager for this player.
     *
     * @return a {@link HantoPlayerPieceManager}
     */
    public HantoPlayerPieceManager getPlayerPieceManager();

    /**
     * Returns the number of turns this player has played.
     *
     * @return an integer
     */
    public int getTurnCount();

    /**
     * Sets the player's butterfly location to the specified coordinate.
     *
     * @param coord
     *            The {@link HantoCoordinate} of the butterfly.
     */
    public void setPlayerButterflyCoordinate(HantoCoordinate coord);

    /**
     * Updates the turn count by the specified amount.
     * 
     * @param update
     *            The integer value to update by.
     */
    public void updateTurnCount(int update);
}
