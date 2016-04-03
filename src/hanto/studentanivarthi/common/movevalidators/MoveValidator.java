package hanto.studentanivarthi.common.movevalidators;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

public interface MoveValidator {
    public boolean canMove(HantoCoordinate from, HantoCoordinate to,
            Map<HantoCoordinate, HantoPiece> board);
}
