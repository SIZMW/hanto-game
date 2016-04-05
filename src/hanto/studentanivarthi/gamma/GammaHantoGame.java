/**
 * This class was created for the Hanto game implementation for CS 4233.
 */
package hanto.studentanivarthi.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.HantoPieceImpl;
import hanto.studentanivarthi.common.board.Board;
import hanto.studentanivarthi.common.board.BoardImpl;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManagerImpl;
import hanto.studentanivarthi.common.placepiecevalidators.FirstTurnPlacePieceValidator;
import hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator;
import hanto.studentanivarthi.common.placepiecevalidators.StandardPlacePieceValidator;
import hanto.studentanivarthi.common.playerturn.PlayerTurn;
import hanto.studentanivarthi.common.playerturn.PlayerTurnImpl;

/**
 * The implementation of Gamma Hanto.
 *
 * @author Aditya Nivarthi
 */
public class GammaHantoGame implements HantoGame {
    /**
     * The number of turns before the {@link HantoPieceType#BUTTERFLY} must be
     * placed. If the {@link HantoPieceType#BUTTERFLY} must be placed by the nth
     * turn, then this value is (n - 1) so it represents the turn prior.
     */
    private final int MAX_TURNS_BEFORE_PLACE_BUTTERFLY = 3;

    /**
     * The maximum number of turns that can occur in Gamma Hanto.
     */
    private final int MAX_TURNS = 20;

    /**
     * The constant associated with the number of internal turns used for the
     * first turn cycle (blue goes, red goes, or vice versa).
     */
    private final int FIRST_TURN = 2;

    /**
     * Origin constant.
     */
    private final HantoCoordinate ORIGIN = new HantoCoordinateImpl(0, 0);

    /**
     * Game variables.
     */
    private final Board board;

    /**
     * Turn related attributes.
     */
    private PlayerTurn currentTurn;
    private final PlayerTurn blueTurn;
    private final PlayerTurn redTurn;

    /**
     * Game state variables.
     */
    private boolean isGameOver = false;
    private boolean isFirstMove = true;

    /**
     * Creates a GammaHantoGame instance with the specified starting player.
     *
     * @param movesFirst
     *            The {@link HantoPlayerColor} to start.
     */
    public GammaHantoGame(HantoPlayerColor movesFirst) {
        board = new BoardImpl();

        // Set up piece managers based on rule set
        blueTurn = new PlayerTurnImpl(HantoPlayerColor.BLUE,
                new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 5));
        redTurn = new PlayerTurnImpl(HantoPlayerColor.RED,
                new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 5));

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
        // Game already over
        if (isGameOver) {
            throw new HantoException("You cannot move after the game is finished");
        }

        final HantoCoordinateImpl destCoordImpl = new HantoCoordinateImpl(dest);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(currentTurn.getColor(), pieceType);

        // Place a new piece
        if (src == null) {
            if (!isPlacePieceValid(destCoordImpl, pieceImpl)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot place a piece in that location");
            }

            placePlayerPiece(destCoordImpl, pieceImpl);
        } else { // Move piece
            final HantoCoordinateImpl srcCoordImpl = new HantoCoordinateImpl(src);

            if (!isMoveValid(srcCoordImpl, destCoordImpl, pieceImpl)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot move a piece to that location");
            }

            movePlayerPiece(srcCoordImpl, destCoordImpl, pieceImpl);
        }

        switchPlayerTurn();
        return getMoveResult();
    }

    /**
     * Returns the status on the game after a move.
     *
     * @return The {@link MoveResult} state of the game.
     */
    private MoveResult getMoveResult() {
        boolean blueIsSurrounded = false;
        boolean redIsSurrounded = false;

        MoveResult mr = MoveResult.OK;

        // Check if butterfly is surrounded if it has been placed
        if (blueTurn.getPlayerButterflyCoordinate().isPresent()) {
            blueIsSurrounded = new HantoCoordinateImpl(
                    blueTurn.getPlayerButterflyCoordinate().get()).isCoordinateSurrounded(board);
        }

        if (redTurn.getPlayerButterflyCoordinate().isPresent()) {
            redIsSurrounded = new HantoCoordinateImpl(redTurn.getPlayerButterflyCoordinate().get())
                    .isCoordinateSurrounded(board);
        }

        isGameOver = true;

        // Determine correct result
        if (blueIsSurrounded && redIsSurrounded) {
            mr = MoveResult.DRAW;
        } else if (blueTurn.getTurnCount() + redTurn.getTurnCount() >= MAX_TURNS) {
            mr = MoveResult.DRAW;
        } else if (blueIsSurrounded) {
            mr = MoveResult.RED_WINS;
        } else if (redIsSurrounded) {
            mr = MoveResult.BLUE_WINS;
        } else {
            isGameOver = false;
        }

        return mr;
    }

    /**
     * Returns whether the specified coordinate is the origin or not.
     *
     * @param coordinate
     *            The {@link HantoCoordiante} to check.
     * @return true if origin, false otherwise
     */
    private boolean isCoordinateOrigin(HantoCoordinate coordinate) {
        return coordinate.equals(ORIGIN);
    }

    /**
     * Determines if a piece can be moved from a coordinate to another
     * coordinate.
     *
     * @param src
     *            The starting {@link HantoCoordinateImpl}.
     * @param dest
     *            The destination {@link HantoCoordinateImpl}.
     * @param type
     *            The {@link HantoPieceImpl} to move.
     * @return true if can be moved, false otherwise
     */
    private boolean isMoveValid(HantoCoordinateImpl src, HantoCoordinateImpl dest,
            HantoPieceImpl type) {
        if (!currentTurn.getPlayerButterflyCoordinate().isPresent()) {
            return false;
        }

        return type.canMove(src, dest, board);
    }

    /**
     * Determines if a piece can be placed at the specified coordinate.
     *
     * @param dest
     *            The {@link HantoCoordinateImpl} to place at.
     * @param piece
     *            The {@link HantoPieceImpl} to place.
     * @return true if can be placed, false otherwise
     */
    private boolean isPlacePieceValid(HantoCoordinateImpl dest, HantoPieceImpl piece) {
        // Check if the butterfly is placed before the fourth turn
        if (!currentTurn.getPlayerButterflyCoordinate().isPresent()
                && currentTurn.getTurnCount() >= MAX_TURNS_BEFORE_PLACE_BUTTERFLY) {
            return false;
        }

        // Check if there are remaining pieces of that type to place
        if (!currentTurn.getPlayerPieceManager().canPlacePiece(piece.getType())) {
            return false;
        }

        // First move
        if (isFirstMove) {
            // Not origin move
            if (!isCoordinateOrigin(dest)) {
                return false;
            }

            isFirstMove = false;
            return true;
        } else {
            PlacePieceValidator validator;
            if (blueTurn.getTurnCount() + redTurn.getTurnCount() < FIRST_TURN) {
                validator = new FirstTurnPlacePieceValidator();
            } else {
                validator = new StandardPlacePieceValidator();
            }

            return validator.canPlacePiece(dest, piece, board);
        }
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
    private void movePlayerPiece(HantoCoordinate src, HantoCoordinate dest, HantoPiece piece) {
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
    private void placePlayerPiece(HantoCoordinate dest, HantoPiece piece) {
        board.placePieceAt(dest, piece);

        if (piece.getType() == HantoPieceType.BUTTERFLY) {
            currentTurn.setPlayerButterflyCoordinate(new HantoCoordinateImpl(dest));
        }

        currentTurn.getPlayerPieceManager().placePiece(piece.getType());
    }

    /**
     * Switches the player who will make the next move. Updates the number of
     * turns played for each player.
     */
    private void switchPlayerTurn() {
        currentTurn.updateTurnCount(1);

        if (currentTurn.getColor().equals(HantoPlayerColor.BLUE)) {
            currentTurn = redTurn;
        } else {
            currentTurn = blueTurn;
        }
    }
}
