package hanto.studentanivarthi.common.movevalidators;

import hanto.common.HantoPieceType;

public class MoveValidatorFactory {
    private static final MoveValidatorFactory instance = new MoveValidatorFactory();

    public static MoveValidatorFactory getInstance() {
        return instance;
    }

    private MoveValidatorFactory() {
    }

    public MoveValidator getMoveValidator(HantoPieceType pieceType) { // TODO
        switch (pieceType) {
            case BUTTERFLY:
                return new WalkMoveValidator();
            case SPARROW:
                return new WalkMoveValidator();
            default:
                return new WalkMoveValidator();
        }
    }
}
