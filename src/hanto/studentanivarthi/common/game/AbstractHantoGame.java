/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.game;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.board.HantoGameBoardImpl;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.common.movevalidators.MoveValidator;
import hanto.studentanivarthi.common.movevalidators.MoveValidatorFactory;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;
import hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator;
import hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidatorFactory;
import hanto.studentanivarthi.common.playerturn.HantoPlayerTurn;
import hanto.studentanivarthi.common.playerturn.HantoPlayerTurnFactory;
import hanto.studentanivarthi.tournament.HantoValidMove;

/**
 * This class defines the commonality between different versions of
 * {@link HantoGame} and methods that are shared among the various
 * implementations.
 *
 * @author Aditya Nivarthi
 */
public abstract class AbstractHantoGame implements HantoValidActionGame {
    /**
     * The number of turns before the {@link HantoPieceType#BUTTERFLY} must be
     * placed. If the {@link HantoPieceType#BUTTERFLY} must be placed by the nth
     * turn, then this value is (n - 1) so it represents the turn prior.
     */
    protected final int MAX_TURNS_BEFORE_PLACE_BUTTERFLY = 3;

    protected HantoPlayerTurn currentTurn;
    protected final HantoPlayerTurn blueTurn;
    protected final HantoPlayerTurn redTurn;

    protected final HantoGameBoard board;
    protected boolean isGameOver = false;
    protected boolean isFirstMove = true;
    protected HantoGameID id;

    /**
     * Creates an AbstractHantoGame instance.
     *
     * @param id
     *            The {@link HantoGameID} of the game type being created.
     * @param movesFirst
     *            The {@link HantoPlayerColor} for the player to move first.
     */
    protected AbstractHantoGame(HantoGameID id, HantoPlayerColor movesFirst) {
        this.id = id;
        board = new HantoGameBoardImpl();
        blueTurn = HantoPlayerTurnFactory.getInstance().makePlayerTurnInstance(id,
                HantoPlayerColor.BLUE);
        redTurn = HantoPlayerTurnFactory.getInstance().makePlayerTurnInstance(id,
                HantoPlayerColor.RED);
        currentTurn = movesFirst.equals(HantoPlayerColor.BLUE) ? blueTurn : redTurn;
    }

    /**
     * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate coordinate) {
        return board.getPieceAt(coordinate);
    }

    /**
     * @see hanto.common.HantoGame#getPrintableBoard()
     */
    @Override
    public String getPrintableBoard() {
        return board.toString();
    }

    /**
     * @see hanto.studentanivarthi.common.game.HantoValidActionGame#hasValidAction(hanto.common.HantoPieceType,
     *      hanto.common.HantoCoordinate)
     */
    @Override
    public HantoValidMove hasValidAction(HantoPieceType previousPieceType,
            HantoCoordinate previousCoordinate) {
        // Skip piece placement if no more pieces
        if (!currentTurn.isOutOfPieces()) {
            HantoValidMove placePiece = hasValidPiecePlacement();
            if (placePiece != null) {
                return placePiece;
            }
        }

        HantoValidMove move = hasValidMove(previousPieceType, previousCoordinate);
        if (move != null) {
            return move;
        }

        return null;
    }

    /**
     * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
     */
    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate src, HantoCoordinate dest)
            throws HantoException {
        // Game over
        if (isGameOver) {
            throw new HantoException("You cannot move after the game is finished");
        }

        // Resignation
        if (hasPlayerResigned(pieceType, src, dest)) {
            return processResignation();
        }

        // No destination specified
        if (dest == null) {
            throw new HantoException(currentTurn.getColor().name()
                    + " cannot have a destination be a null coordinate.");
        }

        // No piece specified
        if (pieceType == null) {
            throw new HantoException(
                    currentTurn.getColor().name() + " cannot have a piece type be null.");
        }

        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(currentTurn.getColor(), pieceType);

        // Place a new piece
        if (src == null) {
            doPiecePlacement(pieceImpl, destCoordImpl);
        } else {
            doMove(pieceImpl, src, destCoordImpl);
        }

        // Switch player
        switchPlayerTurn();

        // Get result
        return getMoveResult();
    }

    /**
     * Validates before the move, does the move, and validates after the move.
     *
     * @param pieceImpl
     *            The {@link HantoPieceImpl} to place.
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param destCoordImpl
     *            The destination {@link HantoCoordinateImpl}.
     * @throws HantoException
     *             If the preconditions or post conditions of the move are
     *             invalid
     */
    protected void doMove(HantoPieceImpl pieceImpl, HantoCoordinate src,
            HantoCoordinateImpl destCoordImpl) throws HantoException {
        // Move piece
        final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
        final MoveValidator validator = MoveValidatorFactory.getInstance().getMoveValidator(id,
                pieceImpl.getType());

        // Preconditions of move
        if (!isValidBeforeMove(pieceImpl, srcCoordImpl, destCoordImpl, validator)) {
            throw new HantoException(currentTurn.getColor().name()
                    + " cannot move a piece to that location, according to "
                    + validator.getClass().getName());
        }

        // Move piece
        movePlayerPiece(pieceImpl, srcCoordImpl, destCoordImpl);

        // Post conditions of move
        if (!isValidAfterMove(pieceImpl, srcCoordImpl, destCoordImpl, validator)) {
            throw new HantoException(currentTurn.getColor().name()
                    + " cannot move a piece to that location, according to "
                    + validator.getClass().getName());
        }
    }

    /**
     * Validates before the piece placement, does the piece placement, and
     * validates after the piece placement.
     *
     * @param pieceImpl
     *            The {@link HantoPieceImpl} to place. The
     *            {@link HantoCoordinateImpl} to move to.
     * @param destCoordImpl
     *            The destination {@link HantoCoordinateImpl}.
     * @throws HantoException
     *             If the preconditions or post conditions of the piece
     *             placement are invalid
     */
    protected void doPiecePlacement(HantoPieceImpl pieceImpl, HantoCoordinateImpl destCoordImpl)
            throws HantoException {
        // Preconditions of place piece
        if (!isValidBeforePiecePlacement(pieceImpl, destCoordImpl)) {
            throw new HantoException(
                    currentTurn.getColor().name() + " cannot place a piece in that location");
        }

        // Place piece
        placePlayerPiece(pieceImpl, destCoordImpl);

        // Post conditions of place piece
        if (!isValidAfterPiecePlacement(pieceImpl, destCoordImpl)) {
            throw new HantoException(
                    currentTurn.getColor().name() + " cannot place a piece in that location");
        }
    }

    /**
     * Returns the result of the game after a move.
     *
     * @return The {@link MoveResult} state of the game.
     */
    protected MoveResult getMoveResult() {
        boolean blueIsSurrounded = false;
        boolean redIsSurrounded = false;

        MoveResult mr = MoveResult.OK;

        // Check if either butterfly is surrounded
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
            markGameIsOver();
        }

        if (redIsSurrounded) {
            mr = MoveResult.BLUE_WINS;
            markGameIsOver();
        }

        if (blueIsSurrounded && redIsSurrounded) {
            mr = MoveResult.DRAW;
            markGameIsOver();
        }

        return mr;
    }

    /**
     * Returns if the player has resigned from the game by passing all null
     * parameters.
     *
     * @param pieceType
     *            The {@link HantoPieceType} for the move.
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @return true if resigned, false otherwise
     * @throws HantoPrematureResignationException
     *             If the player resigned and had a valid move
     */
    protected boolean hasPlayerResigned(HantoPieceType pieceType, HantoCoordinate src,
            HantoCoordinate dest) throws HantoPrematureResignationException {
        return pieceType == null && src == null && dest == null;
    }

    /**
     * Determines if the current player has a valid move that can be made, and
     * if so, returns a move object. If not, returns null. A piece and
     * coordinate can be passed in for the previous move to find an move
     * different from the last move.
     *
     * @param previousPieceType
     *            The {@link HantoPieceType} type in the previous action.
     * @param previousCoordinate
     *            The destination {@link HantoCoordinate} in the previous
     *            action.
     * @return a {@link HantoValidMove}, or null if no action is found
     */
    protected HantoValidMove hasValidMove(HantoPieceType previousPieceType,
            HantoCoordinate previousCoordinate) {
        final Collection<HantoCoordinate> coordinates = board
                .getCoordinatesWithPiecesOfColor(currentTurn.getColor());
        HantoValidMove reservedMove = null;

        // Check if previous is null
        boolean isPreviousNull = previousPieceType == null || previousCoordinate == null;

        // Get all the pieces of the current player's color on the board
        for (HantoCoordinate e : coordinates) {
            HantoPiece piece = board.getPieceAt(e);
            MoveValidator validator = MoveValidatorFactory.getInstance().getMoveValidator(id,
                    piece.getType());

            HantoValidMove move = validator.canMoveAtAll(piece, e, board);

            if (move == null) {
                // If no move is found, keep searching
            } else {
                // Store the move using the previous piece
                if (!isPreviousNull && piece.getType().equals(previousPieceType)
                        && e.equals(previousCoordinate)) {
                    reservedMove = new HantoValidMove(move);
                    continue;
                }

                if (move.equals(reservedMove)) {
                    continue;
                }

                else {
                    return move;
                }
            }
        }

        // Return the move with the same piece again, or null if nothing was
        // found
        return reservedMove;
    }

    /**
     * Determines if the current player has a valid move that can be made, and
     * if so, returns a move object. If not, returns null.
     *
     * @return a {@link HantoValidMove}, or null if no action is found
     */
    protected HantoValidMove hasValidPiecePlacement() {
        for (HantoPieceType piece : HantoPieceType.values()) {
            if (currentTurn.canPlacePiece(piece)) {
                PlacePieceValidator validator = PlacePieceValidatorFactory.getInstance()
                        .getPlacePieceValidator(id, isFirstMove,
                                blueTurn.getTurnCount() + redTurn.getTurnCount());

                HantoValidMove placePiece = validator.canPlacePieceAtAll(
                        new HantoPieceImpl(currentTurn.getColor(), piece), board);

                // If placement is found, return
                if (placePiece != null) {
                    return placePiece;
                }
            }
        }

        return null;
    }

    /**
     * Validates that the post conditions for a move are met after the move is
     * made. Calls the move validation class's validation method for validation.
     *
     * @param piece
     *            The {@link HantoPiece} that was moved.
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param validator
     *            The {@link MoveValidator} associated with the piece.
     * @return true if valid, false otherwise
     */
    protected boolean isValidAfterMove(HantoPieceImpl piece, HantoCoordinateImpl src,
            HantoCoordinateImpl dest, MoveValidator validator) {
        return validator.isMoveValid(piece, src, dest, board);
    }

    /**
     * Validates that the post conditions for a piece placement are met after
     * the placement is made. At the base Hanto class, there are no post
     * conditions to check.
     *
     * @param piece
     *            The {@link HantoPiece} that was placed.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @return true if valid, false otherwise
     */
    protected boolean isValidAfterPiecePlacement(HantoPieceImpl piece, HantoCoordinateImpl dest) {
        return true; // Nothing needs to happen
    }

    /**
     * Validates that the preconditions for a move are met before the move is
     * made. Checks if the current player has placed a butterfly before
     * attempting a move, and calls the move validation class's validation
     * method for validation.
     *
     * @param piece
     *            The {@link HantoPiece} that was moved.
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param validator
     *            The {@link MoveValidator} associated with the piece.
     * @return true if valid, false otherwise
     * @throws HantoException
     *             If any of the preconditions are not met before the move
     *             validation.
     */
    protected boolean isValidBeforeMove(HantoPieceImpl piece, HantoCoordinateImpl src,
            HantoCoordinateImpl dest, MoveValidator validator) throws HantoException {
        // Cannot move without having placed the butterfly
        if (!currentTurn.hasButterflyCoordinate()) {
            throw new HantoException("Player has not placed the butterfly on the board yet.");
        }

        return validator.canMove(piece, src, dest, board);
    }

    /**
     * Validates that the preconditions for a piece placement are met before the
     * placement is made. Checks if the current player has placed a butterfly
     * before the latest turn possible, if the player has pieces of the required
     * type to place, and calls the piece placement validation class's
     * validation method for validation.
     *
     * @param piece
     *            The {@link HantoPiece} that was placed.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @return true if valid, false otherwise
     * @throws HantoException
     *             If any of the preconditions are not met before the piece
     *             placement validation.
     */
    protected boolean isValidBeforePiecePlacement(HantoPieceImpl piece, HantoCoordinateImpl dest)
            throws HantoException {
        // Check if butterfly has not been placed
        if (!currentTurn.hasButterflyCoordinate()
                && currentTurn.getTurnCount() >= MAX_TURNS_BEFORE_PLACE_BUTTERFLY) {
            throw new HantoException("Butterfly has not been placed by the "
                    + (MAX_TURNS_BEFORE_PLACE_BUTTERFLY + 1) + " turn.");
        }

        // Check if the player has pieces to place of this type
        if (!currentTurn.canPlacePiece(piece.getType())) {
            throw new HantoException("Player cannot place any more of the "
                    + piece.getType().toString() + " piece.");
        }

        // Get place piece validator from factory
        final PlacePieceValidator validator = PlacePieceValidatorFactory.getInstance()
                .getPlacePieceValidator(id, isFirstMove,
                        blueTurn.getTurnCount() + redTurn.getTurnCount());

        // Update if is the first move
        isFirstMove = isFirstMove ? false : false;
        return validator.canPlacePiece(piece, dest, board);
    }

    /**
     * Sets the game state to be ended.
     */
    protected void markGameIsOver() {
        isGameOver = true;
    }

    /**
     * Moves the piece from a coordinate to another coordinate.
     *
     * @param piece
     *            The {@link HantoPiece} to move.
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     */
    protected void movePlayerPiece(HantoPiece piece, HantoCoordinate src, HantoCoordinate dest) {
        board.removePieceAt(src);

        // Store BUTTERFLY
        if (piece.getType().equals(HantoPieceType.BUTTERFLY)) {
            currentTurn.setPlayerButterflyCoordinate(new HantoCoordinateImpl(dest));
        }

        board.placePieceAt(piece, dest);
    }

    /**
     * Places the piece at a coordinate. Saves the coordinate to the current
     * player's instance if the coordinate is for a butterfly.
     *
     * @param piece
     *            The {@link HantoPiece} to place.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     */
    protected void placePlayerPiece(HantoPiece piece, HantoCoordinate dest) {
        board.placePieceAt(piece, dest);

        // Store BUTTERFLY
        if (piece.getType().equals(HantoPieceType.BUTTERFLY)) {
            currentTurn.setPlayerButterflyCoordinate(new HantoCoordinateImpl(dest));
        }

        currentTurn.placePiece(piece.getType());
    }

    /**
     * Manages ending the game due to resignation.
     *
     * @return The result of the game.
     * @throws HantoException
     *             If there was a valid move to make but resignation is called
     */
    protected MoveResult processResignation() throws HantoException {
        // Try to find a valid action
        HantoValidMove move = hasValidAction(null, null);
        if (move != null) {
            throw new HantoPrematureResignationException();
        }

        markGameIsOver();
        return currentTurn.getColor().equals(HantoPlayerColor.BLUE) ? MoveResult.RED_WINS
                : MoveResult.BLUE_WINS;
    }

    /**
     * Switches the player who will make the next move. Updates the number of
     * turns played for that player.
     */
    protected void switchPlayerTurn() {
        currentTurn.updateTurnCount(1);

        if (currentTurn.getColor().equals(HantoPlayerColor.BLUE)) {
            currentTurn = redTurn;
        } else {
            currentTurn = blueTurn;
        }
    }
}
