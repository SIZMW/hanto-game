/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import hanto.common.HantoGameID;
import hanto.studentanivarthi.beta.BetaHantoGame;
import hanto.studentanivarthi.common.movevalidators.MoveValidator;
import hanto.studentanivarthi.delta.DeltaHantoGame;
import hanto.studentanivarthi.epsilon.EpsilonHantoGame;
import hanto.studentanivarthi.gamma.GammaHantoGame;

/**
 * The MoveValidatorFactory class is a singleton class that creates
 * {@link MoveValidator}s based on the piece type that is being moved.
 *
 * @author Aditya Nivarthi
 */
public class PlacePieceValidatorFactory {
    private static final PlacePieceValidatorFactory instance = new PlacePieceValidatorFactory();

    /**
     * Returns the instance of the PlacePieceValidatorFactory.
     *
     * @return a PlacePieceValidatorFactory
     */
    public static PlacePieceValidatorFactory getInstance() {
        return instance;
    }

    private final int FIRST_TURN_CYCLE = 2;

    /**
     * Creates a PlacePieceValidatorFactory instance.
     */
    private PlacePieceValidatorFactory() {
    }

    /**
     * Returns the {@link MoveValidator} associated with the specified point in
     * time during the Hanto game.
     *
     * @param gameID
     *            The {@link HantoGameID} of the game.
     * @param isFirstMove
     *            The state of whether the game has just started.
     * @param totalTurnCount
     *            The number of turns that have passed in the game.
     * @return a {@link PlacePieceValidator}
     */
    public PlacePieceValidator getPlacePieceValidator(HantoGameID gameID, boolean isFirstMove,
            long totalTurnCount) {
        switch (gameID) {
            case BETA_HANTO:
                return getBetaHantoPlacePieceValidator(isFirstMove, totalTurnCount);
            case GAMMA_HANTO:
                return getGammaHantoPlacePieceValidator(isFirstMove, totalTurnCount);
            case DELTA_HANTO:
                return getDeltaHantoPlacePieceValidator(isFirstMove, totalTurnCount);
            case EPSILON_HANTO:
                return getEpsilonHantoPlacePieceValidator(isFirstMove, totalTurnCount);
            default:
                return null;
        }
    }

    /**
     * Gets the {@link PlacePieceValidator} for {@link BetaHantoGame}.
     *
     * @param isFirstMove
     *            State of whether it is the first move of the game.
     * @param totalTurnCount
     *            The number of turns that happened in the game so far.
     * @return a {@link PlacePieceValidator}
     */
    protected PlacePieceValidator getBetaHantoPlacePieceValidator(boolean isFirstMove,
            long totalTurnCount) {
        if (isFirstMove) {
            return new FirstTurnPlacePieceValidator();
        } else {
            return new SecondTurnPlacePieceValidator();
        }
    }

    /**
     * Gets the {@link PlacePieceValidator} for {@link DeltaHantoGame}.
     *
     * @param isFirstMove
     *            State of whether it is the first move of the game.
     * @param totalTurnCount
     *            The number of turns that happened in the game so far.
     * @return a {@link PlacePieceValidator}
     */
    protected PlacePieceValidator getDeltaHantoPlacePieceValidator(boolean isFirstMove,
            long totalTurnCount) {
        return getGammaHantoPlacePieceValidator(isFirstMove, totalTurnCount);
    }

    /**
     * Gets the {@link PlacePieceValidator} for {@link EpsilonHantoGame}.
     *
     * @param isFirstMove
     *            State of whether it is the first move of the game.
     * @param totalTurnCount
     *            The number of turns that happened in the game so far.
     * @return a {@link PlacePieceValidator}
     */
    protected PlacePieceValidator getEpsilonHantoPlacePieceValidator(boolean isFirstMove,
            long totalTurnCount) {
        return getGammaHantoPlacePieceValidator(isFirstMove, totalTurnCount);
    }

    /**
     * Gets the {@link PlacePieceValidator} for {@link GammaHantoGame}.
     *
     * @param isFirstMove
     *            State of whether it is the first move of the game.
     * @param totalTurnCount
     *            The number of turns that happened in the game so far.
     * @return a {@link PlacePieceValidator}
     */
    protected PlacePieceValidator getGammaHantoPlacePieceValidator(boolean isFirstMove,
            long totalTurnCount) {
        if (isFirstMove) {
            return new FirstTurnPlacePieceValidator();
        } else if (totalTurnCount < FIRST_TURN_CYCLE) {
            return new SecondTurnPlacePieceValidator();
        } else {
            return new StandardPlacePieceValidator();
        }
    }
}
