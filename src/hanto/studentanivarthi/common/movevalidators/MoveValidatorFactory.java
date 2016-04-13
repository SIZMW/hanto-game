/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.studentanivarthi.beta.BetaHantoGame;
import hanto.studentanivarthi.delta.DeltaHantoGame;
import hanto.studentanivarthi.gamma.GammaHantoGame;

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
    public MoveValidator getMoveValidator(HantoGameID id, HantoPieceType pieceType) {
        switch (id) {
            case BETA_HANTO:
                return getBetaHantoMoveValidator(pieceType);
            case GAMMA_HANTO:
                return getGammaHantoMoveValidator(pieceType);
            case DELTA_HANTO:
                return getDeltaHantoMoveValidator(pieceType);
            default:
                return null; // TODO Returning null move validator
        }
    }

    /**
     * Returns the {@link MoveValidator} associated with {@link BetaHantoGame}.
     *
     * @param pieceType
     *            The {@link HantoPieceType} to retrieve {@link MoveValidator}s
     *            for.
     * @return a {@link MoveValidator}
     */
    protected MoveValidator getBetaHantoMoveValidator(HantoPieceType pieceType) {
        switch (pieceType) {
            case BUTTERFLY:
                return new NoMoveValidator(0);
            case SPARROW:
                return new NoMoveValidator(0);
            default:
                return new NoMoveValidator(0);
        }
    }

    /**
     * Returns the {@link MoveValidator} associated with {@link GammaHantoGame}.
     *
     * @param pieceType
     *            The {@link HantoPieceType} to retrieve {@link MoveValidator}s
     *            for.
     * @return a {@link MoveValidator}
     */
    protected MoveValidator getGammaHantoMoveValidator(HantoPieceType pieceType) {
        switch (pieceType) {
            case BUTTERFLY:
                return new WalkMoveValidator(1);
            case SPARROW:
                return new WalkMoveValidator(1);
            default:
                return new WalkMoveValidator(1);
        }
    }

    /**
     * Returns the {@link MoveValidator} associated with {@link DeltaHantoGame}.
     *
     * @param pieceType
     *            The {@link HantoPieceType} to retrieve {@link MoveValidator}s
     *            for.
     * @return a {@link MoveValidator}
     */
    protected MoveValidator getDeltaHantoMoveValidator(HantoPieceType pieceType) {
        switch (pieceType) {
            case BUTTERFLY:
                return new WalkMoveValidator(1);
            case SPARROW:
                return new FlyMoveValidator(10); // TODO Check distance
            case CRAB:
                return new WalkMoveValidator(3);
            default:
                return new WalkMoveValidator(1);
        }
    }
}
