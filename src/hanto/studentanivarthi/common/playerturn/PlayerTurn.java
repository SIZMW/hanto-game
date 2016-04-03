package hanto.studentanivarthi.common.playerturn;

import java.util.Optional;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager;

public interface PlayerTurn {

    public HantoPlayerColor getColor();

    public HantoPlayerPieceManager getPlayerPieceManager();

    public int getTurnCount();

    public void updateTurnCount(int update);

    public Optional<HantoCoordinate> getPlayerButterflyCoordinate();

    public void setPlayerButterflyCoordinate(HantoCoordinate coord);
}
