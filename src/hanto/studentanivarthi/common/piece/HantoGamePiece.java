package hanto.studentanivarthi.common.piece;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

public interface HantoGamePiece extends HantoPiece {

    /**
     * Returns whether this piece can move to the specified location.
     *
     * @param from
     *            The location to move from.
     * @param to
     *            The location to move to.
     * @param board
     *            The board and the occupied locations.
     * @return true if able to move, false otherwise.
     */
    public boolean canMove(HantoCoordinate from, HantoCoordinate to,
            Map<HantoCoordinate, HantoPiece> board);
}
