package hanto.studentanivarthi.common.game;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.board.HantoGameBoardImpl;
import hanto.studentanivarthi.common.movevalidators.MoveValidator;
import hanto.studentanivarthi.common.movevalidators.MoveValidatorFactory;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;
import hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator;
import hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidatorFactory;
import hanto.studentanivarthi.common.playerturn.HantoPlayerTurn;
import hanto.studentanivarthi.common.playerturn.HantoPlayerTurnFactory;

public abstract class AbstractHantoGame implements HantoGame {
    /**
     * The number of turns before the {@link HantoPieceType#BUTTERFLY} must be
     * placed. If the {@link HantoPieceType#BUTTERFLY} must be placed by the nth
     * turn, then this value is (n - 1) so it represents the turn prior.
     */
    protected final int MAX_TURNS_BEFORE_PLACE_BUTTERFLY = 3;

    /**
     * Game variables.
     */
    protected final HantoGameBoard board;

    /**
     * Turn related attributes.
     */
    protected HantoPlayerTurn currentTurn;
    protected final HantoPlayerTurn blueTurn;
    protected final HantoPlayerTurn redTurn;

    /**
     * Game state variables.
     */
    protected boolean isGameOver = false;
    protected boolean isFirstMove = true;

    public AbstractHantoGame(HantoGameID id, HantoPlayerColor movesFirst) {
        board = new HantoGameBoardImpl();

        blueTurn = HantoPlayerTurnFactory.getInstance().makePlayerTurnInstance(id,
                HantoPlayerColor.BLUE);
        redTurn = HantoPlayerTurnFactory.getInstance().makePlayerTurnInstance(id,
                HantoPlayerColor.RED);

        currentTurn = movesFirst.equals(HantoPlayerColor.BLUE) ? blueTurn : redTurn;
    }

    /**
     * @see {@link hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate coordinate) {
        return board.getPieceAt(coordinate);
    }

    /**
     * @see {@link hanto.common.HantoGame#getPrintableBoard()}
     */
    @Override
    public String getPrintableBoard() {
        return board.toString();
    }

    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate src, HantoCoordinate dest)
            throws HantoException {
        if (isGameOver) {
            throw new HantoException("You cannot move after the game is finished");
        }

        if (dest == null) {
            throw new HantoException(currentTurn.getColor().name()
                    + " cannot have a destination be a null coordinate.");
        }

        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(currentTurn.getColor(), pieceType);

        // Place a new piece
        if (src == null) {
            if (!validatePlacePiecePreconditions(destCoordImpl, pieceImpl)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot place a piece in that location");
            }

            placePlayerPiece(destCoordImpl, pieceImpl);

            if (!validatePlacePiecePostConditions(destCoordImpl, pieceImpl)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot place a piece in that location");
            }
        } else {
            // Move piece
            final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
            final MoveValidator validator = MoveValidatorFactory.getInstance()
                    .getMoveValidator(pieceImpl.getType());

            if (!validateMovePreconditions(srcCoordImpl, destCoordImpl, pieceImpl, validator)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot move a piece to that location");
            }

            movePlayerPiece(srcCoordImpl, destCoordImpl, pieceImpl);

            if (!validateMovePostConditions(srcCoordImpl, destCoordImpl, pieceImpl, validator)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot move a piece to that location");
            }
        }

        switchPlayerTurn();
        return getMoveResult();
    }

    /**
     * Returns the status on the game after a move.
     *
     * @return The {@link MoveResult} state of the game.
     */
    protected MoveResult getMoveResult() {
        boolean blueIsSurrounded = false;
        boolean redIsSurrounded = false;

        MoveResult mr = MoveResult.OK;

        // Check if butterfly is surrounded if it has been placed
        if (blueTurn.hasButterflyCoordinate()) {
            blueIsSurrounded = new HantoCoordinateImpl(blueTurn.getPlayerButterflyCoordinate())
                    .isCoordinateSurrounded(board);
        }

        if (redTurn.hasButterflyCoordinate()) {
            redIsSurrounded = new HantoCoordinateImpl(redTurn.getPlayerButterflyCoordinate())
                    .isCoordinateSurrounded(board);
        }

        // Determine correct result
        if (blueIsSurrounded) {
            mr = MoveResult.RED_WINS;
            isGameOver = true;
        }

        if (redIsSurrounded) {
            mr = MoveResult.BLUE_WINS;
            isGameOver = true;
        }

        if (blueIsSurrounded && redIsSurrounded) {
            mr = MoveResult.DRAW;
            isGameOver = true;
        }

        return mr;
    }

    /**
     * Moves the piece from a coordinate to the other coordinate.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} to move.
     */
    protected void movePlayerPiece(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece) {
        board.removePieceAt(src);
        board.placePieceAt(dest, piece);
    }

    /**
     * Places the specified piece at the specified coordinate.
     *
     * @param dest
     *            The {@link HantoCoordinate} to place the piece.
     * @param piece
     *            The {@link HantoPiece} to place.
     */
    protected void placePlayerPiece(HantoCoordinate dest, HantoPiece piece) {
        board.placePieceAt(dest, piece);

        if (piece.getType() == HantoPieceType.BUTTERFLY) {
            currentTurn.setPlayerButterflyCoordinate(new HantoCoordinateImpl(dest));
        }

        currentTurn.placePiece(piece.getType());
    }

    /**
     * Switches the player who will make the next move. Updates the number of
     * turns played for each player.
     */
    protected void switchPlayerTurn() {
        currentTurn.updateTurnCount(1);

        if (currentTurn.getColor().equals(HantoPlayerColor.BLUE)) {
            currentTurn = redTurn;
        } else {
            currentTurn = blueTurn;
        }
    }

    protected boolean validateMovePostConditions(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoPieceImpl type, MoveValidator validator) {
        return validator.isMoveValid(src, dest, type, board);
    }

    protected boolean validateMovePreconditions(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoPieceImpl type, MoveValidator validator) {
        if (!currentTurn.hasButterflyCoordinate()) {
            return false;
        }

        return validator.canMove(src, dest, type, board);
    }

    protected boolean validatePlacePiecePostConditions(HantoCoordinateImpl dest,
            HantoPieceImpl piece) {
        return true; // TODO
    }

    protected boolean validatePlacePiecePreconditions(HantoCoordinateImpl dest,
            HantoPieceImpl piece) {
        if (!currentTurn.hasButterflyCoordinate()
                && currentTurn.getTurnCount() >= MAX_TURNS_BEFORE_PLACE_BUTTERFLY) {
            return false;
        }

        if (!currentTurn.canPlacePiece(piece.getType())) {
            return false;
        }

        PlacePieceValidator validator = PlacePieceValidatorFactory.getInstance()
                .getPlacePieceValidator(isFirstMove,
                        blueTurn.getTurnCount() + redTurn.getTurnCount());

        isFirstMove = isFirstMove ? false : false;
        return validator.canPlacePiece(dest, piece, board);
    }
}
