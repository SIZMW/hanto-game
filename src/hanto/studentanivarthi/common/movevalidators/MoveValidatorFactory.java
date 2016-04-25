/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.studentanivarthi.beta.BetaHantoGame;
import hanto.studentanivarthi.delta.DeltaHantoGame;
import hanto.studentanivarthi.epsilon.EpsilonHantoGame;
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
     * @param id
     *            The {@link HantoGameID} of the game.
     * @param pieceType
     *            The {@link HantoPieceType} to retrieve {@link MoveValidator}s
     *            for.
     * @return a {@link MoveValidator}
     */
    public MoveValidator getMoveValidator(HantoGameID id, HantoPieceType pieceType) {
        MoveValidator validator = null;
        switch (id) {
            case BETA_HANTO:
                validator = getBetaHantoMoveValidator(pieceType);
                break;
            case GAMMA_HANTO:
                validator = getGammaHantoMoveValidator(pieceType);
                break;
            case DELTA_HANTO:
                validator = getDeltaHantoMoveValidator(pieceType);
                break;
            case EPSILON_HANTO:
                validator = getEpsilonHantoMoveValidator(pieceType);
                break;
        }

        return validator;
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
        MoveValidator validator = null;
        switch (pieceType) {
            case BUTTERFLY:
                validator = new NoMoveValidator(0);
                break;
            case SPARROW:
                validator = new NoMoveValidator(0);
                break;
        }

        return validator;
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
        MoveValidator validator = null;
        switch (pieceType) {
            case BUTTERFLY:
                validator = new WalkMoveValidator(1);
                break;
            case SPARROW:
                validator = new FlyMoveValidator(-1);
                break;
            case CRAB:
                validator = new WalkMoveValidator(3);
                break;
        }

        return validator;
    }

    /**
     * Returns the {@link MoveValidator} associated with
     * {@link EpsilonHantoGame}.
     *
     * @param pieceType
     *            The {@link HantoPieceType} to retrieve {@link MoveValidator}s
     *            for.
     * @return a {@link MoveValidator}
     */
    protected MoveValidator getEpsilonHantoMoveValidator(HantoPieceType pieceType) {
        MoveValidator validator = null;
        switch (pieceType) {
            case BUTTERFLY:
                validator = new WalkMoveValidator(1);
                break;
            case SPARROW:
                validator = new FlyMoveValidator(4);
                break;
            case CRAB:
                validator = new WalkMoveValidator(1);
                break;
            case HORSE:
                // validator = new JumpMoveValidator(-1);
                // TODO Check this distance
                break;
        }

        return validator;
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
        MoveValidator validator = null;
        switch (pieceType) {
            case BUTTERFLY:
                validator = new WalkMoveValidator(1);
                break;
            case SPARROW:
                validator = new WalkMoveValidator(1);
                break;
        }

        return validator;
    }
}
