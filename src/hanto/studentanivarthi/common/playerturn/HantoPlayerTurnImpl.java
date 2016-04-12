/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.playerturn;

import java.util.NoSuchElementException;
import java.util.Optional;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager;

/**
 * The implementation for the HantoPlayerTurn interface.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerTurnImpl implements HantoPlayerTurn {
    private final HantoPlayerColor color;
    private final HantoPlayerPieceManager pieceManager;
    private int turnCount = 0;
    private Optional<HantoCoordinate> butterflyCoordinate;

    /**
     * Creates a HantoPlayerTurnImpl instance with the specified color and piece
     * manager.
     *
     * @param color
     *            The {@link HantoPlayerColor} of the player.
     * @param pieceManager
     *            The {@link HantoPlayerPieceManager} for this player.
     */
    public HantoPlayerTurnImpl(HantoPlayerColor color, HantoPlayerPieceManager pieceManager) {
        this.color = color;
        this.pieceManager = pieceManager;
        butterflyCoordinate = Optional.empty();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#getColor()}
     */
    @Override
    public HantoPlayerColor getColor() {
        return color;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#getPlayerButterflyCoordinate()}
     */
    @Override
    public HantoCoordinate getPlayerButterflyCoordinate() {
        try {
            return butterflyCoordinate.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#getPlayerPieceManager()}
     */
    @Override
    public HantoPlayerPieceManager getPlayerPieceManager() {
        return pieceManager;
    };

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#getTurnCount()}
     */
    @Override
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#hasButterflyCoordinate()}
     */
    @Override
    public boolean hasButterflyCoordinate() {
        return butterflyCoordinate.isPresent();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#setPlayerButterflyCoordinate(hanto.common.HantoCoordinate)}
     */
    @Override
    public void setPlayerButterflyCoordinate(HantoCoordinate coordinate) {
        try {
            butterflyCoordinate = Optional.of(coordinate);
        } catch (NullPointerException e) {
            butterflyCoordinate = Optional.empty();
        }
    }

    /**
     * @see {@link hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#updateTurnCount(int)}
     */
    @Override
    public void updateTurnCount(int delta) {
        turnCount += delta;
    }
}
