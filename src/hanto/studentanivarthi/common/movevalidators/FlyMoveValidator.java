package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;

public class FlyMoveValidator extends AbstractMoveValidator {

    public FlyMoveValidator(int distance) {
        super(distance);
    }

    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        return super.canMove(src, dest, piece, board);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#isMoveValid(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, hanto.common.HantoPiece, hanto.studentanivarthi.common.board.HantoGameBoard)}
     */
    @Override
    public boolean isMoveValid(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        return board.arePiecesContiguous();
    }

}
