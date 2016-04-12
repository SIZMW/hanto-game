/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

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

/**
 * The AbstractHantoGame class defines the commonality between versions of Hanto
 * and methods that are shared among the various implementations.
 *
 * @author Aditya Nivarthi
 */
public abstract class AbstractHantoGame implements HantoGame {
    /**
     * The number of turns before the {@link HantoPieceType#BUTTERFLY} must be
     * placed. If the {@link HantoPieceType#BUTTERFLY} must be placed by the nth
     * turn, then this value is (n - 1) so it represents the turn prior.
     */
    protected final int MAX_TURNS_BEFORE_PLACE_BUTTERFLY = 3;

    /**
     * Turn related attributes.
     */
    protected HantoPlayerTurn currentTurn;
    protected final HantoPlayerTurn blueTurn;
    protected final HantoPlayerTurn redTurn;

    /**
     * Game state variables.
     */
    protected final HantoGameBoard board;
    protected boolean isGameOver = false;
    protected boolean isFirstMove = true;
    protected HantoGameID id;

    /**
     * Creates an {@link AbstractHantoGame} instance.
     *
     * @param id
     *            The ID of the game type being created, for use with the
     *            {@link HantoPlayerTurnFactory}.
     * @param movesFirst
     *            The {@link HantoPlayerColor} for the player to move first.
     */
    public AbstractHantoGame(HantoGameID id, HantoPlayerColor movesFirst) {
        this.id = id;
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

    /**
     * @see {@link hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)}
     */
    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate src, HantoCoordinate dest)
            throws HantoException {
        // Game is over
        if (isGameOver) {
            throw new HantoException("You cannot move after the game is finished");
        }

        // Resignation
        if (pieceType == null && src == null && dest == null) {
            isGameOver = true;
            return currentTurn.getColor().equals(HantoPlayerColor.BLUE) ? MoveResult.RED_WINS
                    : MoveResult.BLUE_WINS;
        }

        // No destination to move to or place piece at
        if (dest == null) {
            throw new HantoException(currentTurn.getColor().name()
                    + " cannot have a destination be a null coordinate.");
        }

        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(currentTurn.getColor(), pieceType);

        // Place a new piece
        if (src == null) {
            // Preconditions of place piece
            if (!validatePlacePiecePreconditions(destCoordImpl, pieceImpl)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot place a piece in that location");
            }

            // Place piece
            placePlayerPiece(destCoordImpl, pieceImpl);

            // Post conditions of place piece
            if (!validatePlacePiecePostConditions(destCoordImpl, pieceImpl)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot place a piece in that location");
            }
        } else {
            // Move piece
            final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);
            final MoveValidator validator = MoveValidatorFactory.getInstance().getMoveValidator(id,
                    pieceImpl.getType());

            // Preconditions of move
            if (!validateMovePreconditions(srcCoordImpl, destCoordImpl, pieceImpl, validator)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot move a piece to that location");
            }

            // Move piece
            movePlayerPiece(srcCoordImpl, destCoordImpl, pieceImpl);

            // Post conditions of move
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
            setGameIsOver();
        }

        if (redIsSurrounded) {
            mr = MoveResult.BLUE_WINS;
            setGameIsOver();
        }

        if (blueIsSurrounded && redIsSurrounded) {
            mr = MoveResult.DRAW;
            setGameIsOver();
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

    /**
     * Checks if the move validator for the specified piece type is valid after
     * the move has been completed.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} that was moved.
     * @param validator
     *            The {@link MoveValidator} associated with the piece.
     * @return true if valid, false otherwise
     */
    protected boolean validateMovePostConditions(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoPieceImpl piece, MoveValidator validator) {
        return validator.isMoveValid(src, dest, piece, board);
    }

    /**
     * Checks if the move validator for the specified piece is valid before the
     * move is made.
     *
     * @param src
     *            The starting {@link HantoCoordinate}.
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} that was moved.
     * @param validator
     *            The {@link MoveValidator} associated with the piece.
     * @return true if valid, false otherwise
     */
    protected boolean validateMovePreconditions(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoPieceImpl piece, MoveValidator validator) {
        // Cannot move without having placed the butterfly
        if (!currentTurn.hasButterflyCoordinate()) {
            return false;
        }

        return validator.canMove(src, dest, piece, board);
    }

    /**
     * Checks if the piece placement action is valid after it has been
     * completed.
     *
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} that was placed.
     * @return true if valid, false otherwise
     */
    protected boolean validatePlacePiecePostConditions(HantoCoordinateImpl dest,
            HantoPieceImpl piece) {
        return true; // TODO Always return true for place piece post condition
    }

    /**
     * Checks if the piece placement action is valid before it has been
     * completed.
     *
     * @param dest
     *            The destination {@link HantoCoordinate}.
     * @param piece
     *            The {@link HantoPiece} that was placed.
     * @return true if valid, false otherwise
     */
    protected boolean validatePlacePiecePreconditions(HantoCoordinateImpl dest,
            HantoPieceImpl piece) {
        // Check if butterfly has not been placed
        if (!currentTurn.hasButterflyCoordinate()
                && currentTurn.getTurnCount() >= MAX_TURNS_BEFORE_PLACE_BUTTERFLY) {
            return false;
        }

        // Check if the player has pieces to place of this type
        if (!currentTurn.canPlacePiece(piece.getType())) {
            return false;
        }

        // Get place piece validator from factory
        PlacePieceValidator validator = PlacePieceValidatorFactory.getInstance()
                .getPlacePieceValidator(id, isFirstMove,
                        blueTurn.getTurnCount() + redTurn.getTurnCount());

        // Update is first move state
        isFirstMove = isFirstMove ? false : false;
        return validator.canPlacePiece(dest, piece, board);
    }

    /**
     * Sets the game state to be ended.
     */
    protected void setGameIsOver() {
        isGameOver = true;
    }
}
