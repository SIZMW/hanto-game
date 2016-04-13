package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The NoMoveValidator is a subset of the {@link MoveValidator} that considers
 * pieces that do not move at all.
 *
 * @author Aditya Nivarthi
 */
public class NoMoveValidator extends AbstractMoveValidator {
    public NoMoveValidator(int distance) {
        super(0);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, hanto.common.HantoPiece, hanto.studentanivarthi.common.board.HantoGameBoard)}
     */
    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        return false;
    }
}
