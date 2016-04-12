package hanto.studentanivarthi.common.playerturn;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;

public class HantoPlayerTurnFactory {
    private static final HantoPlayerTurnFactory instance = new HantoPlayerTurnFactory();

    public static HantoPlayerTurnFactory getInstance() {
        return instance;
    }

    private HantoPlayerTurnFactory() {
    }

    public HantoPlayerTurn makePlayerTurnInstance(HantoGameID gameID, HantoPlayerColor color) {
        HantoPlayerTurn playerTurn = null;
        switch (gameID) {
            case ALPHA_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 0, 0, 0, 0, 0);
                break;
            case BETA_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 0, 0, 0, 0, 5);
                break;
            case GAMMA_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 0, 0, 0, 0, 5);
                break;
            case DELTA_HANTO:
                playerTurn = new HantoPlayerTurnImpl(color, 1, 0, 0, 0, 0, 5); // TODO
                break;
            default:
                break;
        }

        return playerTurn;
    }
}
