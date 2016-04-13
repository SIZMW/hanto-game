package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;

public abstract class AbstractMoveValidator implements MoveValidator {
    protected int distance = 0;

    public AbstractMoveValidator(int distance) {
        this.distance = distance;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, hanto.common.HantoPiece, hanto.studentanivarthi.common.board.HantoGameBoard)}
     */
    @Override
    public boolean canMove(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece,
            HantoGameBoard board) {
        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);

        // Source and destination are the same
        if (srcCoordImpl.equals(destCoordImpl)) {
            return false;
        }

        // Source and destination are too far apart
        if (srcCoordImpl.getDistanceTo(destCoordImpl) > distance) {
            return false;
        }

        // No piece exists to move
        if (!board.hasPieceAt(new HantoCoordinateImpl(src))) {
            return false;
        }

        // Piece in that spot
        if (board.hasPieceAt(destCoordImpl)) {
            return false;
        }

        final HantoPiece boardPiece = board.getPieceAt(srcCoordImpl);

        // Piece to move is not the same color
        if (!piece.getColor().equals(boardPiece.getColor())
                || !piece.getType().equals(boardPiece.getType())) {
            return false;
        }

        return true;
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
