/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoPieceType;

/**
 * The MoveValidatorFactory class is a singleton class that creates
 * {@link MoveValidator}s based on the piece type that is being moved.
 *
 * @author Aditya Nivarthi
 */
public class MoveValidatorFactory {
    private static final MoveValidatorFactory instance = new MoveValidatorFactory();

    /**
     * Returns the instance of the MoveValidatorFactory.
     *
     * @return a MoveValidatorFactory
     */
    public static MoveValidatorFactory getInstance() {
        return instance;
    }

    /**
     * Creates a MoveValidatorFactory instance.
     */
    private MoveValidatorFactory() {
    }

    /**
     * Returns the {@link MoveValidator} associated with the specified piece
     * type.
     *
     * @param pieceType
     *            The {@link HantoPieceType} to retrieve {@link MoveValidator}s
     *            for.
     * @return a {@link MoveValidator}
     */
    public MoveValidator getMoveValidator(HantoPieceType pieceType) {
        // TODO Fix this for game IDs too
        // TODO Add new move types
        switch (pieceType) {
            case BUTTERFLY:
                return new WalkMoveValidator();
            case SPARROW:
                return new WalkMoveValidator();
            default:
                return new WalkMoveValidator();
        }
    }
}
