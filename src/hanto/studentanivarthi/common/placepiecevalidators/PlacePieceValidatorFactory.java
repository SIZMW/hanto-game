package hanto.studentanivarthi.common.placepiecevalidators;

public class PlacePieceValidatorFactory {
    private static final PlacePieceValidatorFactory instance = new PlacePieceValidatorFactory();

    public static PlacePieceValidatorFactory getInstance() {
        return instance;
    }

    private final int FIRST_TURN_CYCLE = 2;

    private PlacePieceValidatorFactory() {
    }

    public PlacePieceValidator getPlacePieceValidator(boolean isFirstMove, int totalTurnCount) {
        if (isFirstMove) {
            return new FirstTurnPlacePieceValidator();
        } else if (totalTurnCount < FIRST_TURN_CYCLE) {
            return new SecondTurnPlacePieceValidator();
        } else {
            return new StandardPlacePieceValidator();
        }
    }
}
