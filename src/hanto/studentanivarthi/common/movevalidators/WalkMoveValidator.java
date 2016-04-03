package hanto.studentanivarthi.common.movevalidators;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

public class WalkMoveValidator implements MoveValidator {

    @Override
    public boolean canMove(HantoCoordinate from, HantoCoordinate to,
            Map<HantoCoordinate, HantoPiece> board) {
        return false; // TODO
    }
}
