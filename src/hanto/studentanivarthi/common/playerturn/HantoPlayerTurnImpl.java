/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.playerturn;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * The implementation for the HantoPlayerTurn interface.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerTurnImpl implements HantoPlayerTurn {
    private final HantoPlayerColor color;
    private long turnCount = 0;
    private Optional<HantoCoordinate> butterflyCoordinate;
    private final Map<HantoPieceType, Integer> manager;

    /**
     * * Creates a HantoPlayerTurnImpl instance with the specified color and
     * piece playerTurn.
     *
     * @param color
     *            The {@link HantoPlayerColor} of the player.
     * @param butterfly
     *            The butterfly count.
     * @param crab
     *            The crab count.
     * @param crane
     *            The crane count.
     * @param dove
     *            The dove count.
     * @param horse
     *            The horse count.
     * @param sparrow
     *            The sparrow count.
     */
    public HantoPlayerTurnImpl(HantoPlayerColor color, int butterfly, int crab, int crane, int dove,
            int horse, int sparrow) {
        this.color = color;
        butterflyCoordinate = Optional.empty();

        manager = new HashMap<>();

        manager.put(HantoPieceType.BUTTERFLY, butterfly);
        manager.put(HantoPieceType.CRAB, crab);
        manager.put(HantoPieceType.CRANE, crane);
        manager.put(HantoPieceType.DOVE, dove);
        manager.put(HantoPieceType.HORSE, horse);
        manager.put(HantoPieceType.SPARROW, sparrow);
    }

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#canPlacePiece(hanto.common.HantoPieceType)
     */
    @Override
    public boolean canPlacePiece(HantoPieceType pieceType) {
        final int value = manager.get(pieceType);
        return value > 0;
    }

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#getColor()
     */
    @Override
    public HantoPlayerColor getColor() {
        return color;
    }

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#getPlayerButterflyCoordinate()
     */
    @Override
    public HantoCoordinate getPlayerButterflyCoordinate() {
        return butterflyCoordinate.get();
    };

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#getTurnCount()
     */
    @Override
    public long getTurnCount() {
        return turnCount;
    }

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#hasButterflyCoordinate()
     */
    @Override
    public boolean hasButterflyCoordinate() {
        return butterflyCoordinate.isPresent();
    }

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#isOutOfPieces()
     */
    @Override
    public boolean isOutOfPieces() {
        return manager.get(HantoPieceType.BUTTERFLY) <= 0 && manager.get(HantoPieceType.CRAB) <= 0
                && manager.get(HantoPieceType.CRANE) <= 0 && manager.get(HantoPieceType.DOVE) <= 0
                && manager.get(HantoPieceType.HORSE) <= 0
                && manager.get(HantoPieceType.SPARROW) <= 0;
    }

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#placePiece(hanto.common.HantoPieceType)
     */
    @Override
    public void placePiece(HantoPieceType pieceType) {
        final int value = manager.get(pieceType);
        if (value > 0) {
            manager.put(pieceType, value - 1);
        } else {
            manager.put(pieceType, 0);
        }
    }

    /**
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#setPlayerButterflyCoordinate(hanto.common.HantoCoordinate)
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
     * @see hanto.studentanivarthi.common.playerturn.HantoPlayerTurn#updateTurnCount(int)
     */
    @Override
    public void updateTurnCount(int delta) {
        turnCount += delta;
    }
}
