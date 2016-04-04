package hanto.studentanivarthi.common.placepiecevalidators;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

public interface PlacePieceValidator {
    public boolean canPlacePiece(HantoCoordinate to, HantoPiece piece, Map<HantoCoordinate, HantoPiece> board);
}
