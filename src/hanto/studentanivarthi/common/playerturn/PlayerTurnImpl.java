package hanto.studentanivarthi.common.playerturn;

import java.util.Optional;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager;

public class PlayerTurnImpl implements PlayerTurn {
    private HantoPlayerColor color;
    private HantoPlayerPieceManager pieceManager;
    private int turnCount = 0;
    private Optional<HantoCoordinate> butterflyCoordinate;

    public PlayerTurnImpl(HantoPlayerColor color, HantoPlayerPieceManager pieceManager) {
        this.color = color;
        this.pieceManager = pieceManager;
        butterflyCoordinate = Optional.empty();
    }

    @Override
    public HantoPlayerPieceManager getPlayerPieceManager() {
        return pieceManager;
    }

    @Override
    public int getTurnCount() {
        return turnCount;
    }

    @Override
    public void updateTurnCount(int update) {
        turnCount += update;
    }

    @Override
    public Optional<HantoCoordinate> getPlayerButterflyCoordinate() {
        return butterflyCoordinate;
    }

    @Override
    public void setPlayerButterflyCoordinate(HantoCoordinate coord) {
        if (coord != null) {
            butterflyCoordinate = Optional.of(coord);
        }
    }

    @Override
    public HantoPlayerColor getColor() {
        return color;
    }
}
