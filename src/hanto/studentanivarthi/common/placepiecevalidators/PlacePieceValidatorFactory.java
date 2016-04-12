/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.placepiecevalidators;

import hanto.studentanivarthi.common.movevalidators.MoveValidator;

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
     * @param isFirstMove
     *            The state of whether the game has just started.
     * @param totalTurnCount
     *            The number of turns that have passed in the game.
     * @return a {@link PlacePieceValidator}
     */
    public PlacePieceValidator getPlacePieceValidator(boolean isFirstMove, int totalTurnCount) {
        if (isFirstMove) {
            return new FirstTurnPlacePieceValidator();
        } else if (totalTurnCount < FIRST_TURN_CYCLE) {
            return new SecondTurnPlacePieceValidator();
        } else {
            return new StandardPlacePieceValidator();
        }
    }
}
