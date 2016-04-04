/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.playerturn;

import java.util.Optional;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager;

/**
 * The implementation for the PlayerTurn interface.
 *
 * @author Aditya Nivarthi
 */
public class PlayerTurnImpl implements PlayerTurn {
    private HantoPlayerColor color;
    private HantoPlayerPieceManager pieceManager;
    private int turnCount = 0;
    private Optional<HantoCoordinate> butterflyCoordinate;

    /**
     * Creates a PlayerTurnImpl instance with the specified color and piece
     * manager.
     *
     * @param color
     *            The {@link HantoPlayerColor} of the player.
     * @param pieceManager
     *            The {@link HantoPlayerPieceManager} for this player.
     */
    public PlayerTurnImpl(HantoPlayerColor color, HantoPlayerPieceManager pieceManager) {
        this.color = color;
        this.pieceManager = pieceManager;
        butterflyCoordinate = Optional.empty();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.PlayerTurn#getColor()}
     */
    @Override
    public HantoPlayerColor getColor() {
        return color;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.PlayerTurn#getPlayerButterflyCoordinate()}
     */
    @Override
    public Optional<HantoCoordinate> getPlayerButterflyCoordinate() {
        return butterflyCoordinate;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.PlayerTurn#getPlayerPieceManager()}
     */
    @Override
    public HantoPlayerPieceManager getPlayerPieceManager() {
        return pieceManager;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.PlayerTurn#getTurnCount()}
     */
    @Override
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.PlayerTurn#setPlayerButterflyCoordinate(hanto.common.HantoCoordinate)}
     */
    @Override
    public void setPlayerButterflyCoordinate(HantoCoordinate coord) {
        if (coord != null) {
            butterflyCoordinate = Optional.of(coord);
        }
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.PlayerTurn#updateTurnCount(int)}
     */
    @Override
    public void updateTurnCount(int update) {
        turnCount += update;
    }
}