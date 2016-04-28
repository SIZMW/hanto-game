/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi;

import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.alpha.AlphaHantoGame;
import hanto.studentanivarthi.beta.BetaHantoGame;
import hanto.studentanivarthi.delta.DeltaHantoGame;
import hanto.studentanivarthi.epsilon.EpsilonHantoGame;
import hanto.studentanivarthi.gamma.GammaHantoGame;

/**
 * The HantoGameFactory class is a singleton class that creates
 * {@link HantoGame} instances based on the game ID and player color.
 *
 * @author Aditya Nivarthi
 */
public class HantoGameFactory {
    private static final HantoGameFactory instance = new HantoGameFactory();

    /**
     * Returns the instance of the HantoGameFactory.
     *
     * @return a HantoGameFactory
     */
    public static HantoGameFactory getInstance() {
        return instance;
    }

    /**
     * Creates a HantoGameFactory instance.
     */
    private HantoGameFactory() {
    }

    /**
     * Creates the specified Hanto game version with the BLUE player moving
     * first.
     *
     * @param gameId
     *            The {@link HantoGameID} ID.
     * @return a {@link HantoGame}
     */
    public HantoGame makeHantoGame(HantoGameID gameId) {
        return makeHantoGame(gameId, HantoPlayerColor.BLUE);
    }

    /**
     * Factory method that returns the appropriately configured Hanto game.
     *
     * @param gameId
     *            The {@link HantoGameID} ID.
     * @param movesFirst
     *            The {@link HantoPlayerColor} of the player who moves first.
     * @return a {@link HantoGame}
     */
    public HantoGame makeHantoGame(HantoGameID gameId, HantoPlayerColor movesFirst) {
        HantoGame game = null;
        switch (gameId) {
            case ALPHA_HANTO:
                game = new AlphaHantoGame();
                break;
            case BETA_HANTO:
                game = new BetaHantoGame(movesFirst);
                break;
            case GAMMA_HANTO:
                game = new GammaHantoGame(movesFirst);
                break;
            case DELTA_HANTO:
                game = new DeltaHantoGame(movesFirst);
                break;
            case EPSILON_HANTO:
                game = new EpsilonHantoGame(movesFirst);
                break;
        }
        return game;
    }
}
