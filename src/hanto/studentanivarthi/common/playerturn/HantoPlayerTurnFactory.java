/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.playerturn;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;

/**
 * The HantoPlayerTurnFactory class is a singleton class that creates
 * {@link HantoPlayerTurn} instances based on the game ID and player color.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerTurnFactory {
    private static final HantoPlayerTurnFactory instance = new HantoPlayerTurnFactory();

    /**
     * Returns the instance of the HantoPlayerTurnFactory.
     *
     * @return a HantoPlayerTurnFactory
     */
    public static HantoPlayerTurnFactory getInstance() {
        return instance;
    }

    /**
     * Creates a HantoPlayerTurnFactory instance.
     */
    private HantoPlayerTurnFactory() {
    }

    /**
     * Returns the appropriate {@link HantoPlayerTurn} based on the game ID and
     * color.
     *
     * @param gameID
     *            The {@link HantoGameID} for the game.
     * @param color
     *            The {@link HantoPlayerColor} for the player.
     * @return a {@link HantoPlayerTurn}
     */
    public HantoPlayerTurn makePlayerTurnInstance(HantoGameID gameID, HantoPlayerColor color) {
        HantoPlayerTurn playerTurn = null;
        switch (gameID) {
            case BETA_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 0, 0, 0, 0, 5);
                break;
            case GAMMA_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 0, 0, 0, 0, 5);
                break;
            case DELTA_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 4, 0, 0, 0, 4);
                break;
            case EPSILON_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 6, 0, 0, 4, 2);
                break;
            default:
                break;
        }

        return playerTurn;
    }
}
