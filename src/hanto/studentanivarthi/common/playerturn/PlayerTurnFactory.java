package hanto.studentanivarthi.common.playerturn;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManagerImpl;

public class PlayerTurnFactory {
    private static final PlayerTurnFactory instance = new PlayerTurnFactory();

    public static PlayerTurnFactory getInstance() {
        return instance;
    }

    private PlayerTurnFactory() {

    }

    public PlayerTurn makePlayerTurnInstance(HantoGameID gameID, HantoPlayerColor color) {
        PlayerTurn playerTurn = null;
        switch (gameID) {
            case ALPHA_HANTO:
                playerTurn = new PlayerTurnImpl(color,
                        new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 0));
                break;
            case BETA_HANTO:
                playerTurn = new PlayerTurnImpl(color,
                        new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 5));
                break;
            case GAMMA_HANTO:
                playerTurn = new PlayerTurnImpl(color,
                        new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 5));
                break;
            case DELTA_HANTO:
                playerTurn = new PlayerTurnImpl(color,
                        new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 5)); // TODO
                                                                            // Fix
                                                                            // this
                break;
            default:
                break;
        }

        return playerTurn;
    }
}
