package hanto.studentanivarthi.gamma;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.HantoPieceImpl;
import hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManagerImpl;
import hanto.studentanivarthi.common.placepiecevalidators.FirstTurnPlacePieceValidator;
import hanto.studentanivarthi.common.placepiecevalidators.PlacePieceValidator;
import hanto.studentanivarthi.common.placepiecevalidators.StandardPlacePieceValidator;
import hanto.studentanivarthi.common.playerturn.PlayerTurn;
import hanto.studentanivarthi.common.playerturn.PlayerTurnImpl;

public class GammaHantoGame implements HantoGame {
    /**
     * The number of turns before the {@link HantoPieceType#BUTTERFLY} must be
     * placed. If the {@link HantoPieceType#BUTTERFLY} must be placed by the nth
     * turn, then this value is (n - 1) so it represents the turn prior.
     */
    private final int MAX_TURNS_BEFORE_PLACE_BUTTERFLY = 3;
    private final int MAX_TURNS = 20;
    private final int FIRST_TURN = 2;

    /**
     * Origin constant.
     */
    private final HantoCoordinate ORIGIN = new HantoCoordinateImpl(0, 0);

    /**
     * Game variables.
     */
    private final Map<HantoCoordinate, HantoPiece> board;

    private PlayerTurn currentTurn;
    private final PlayerTurn blueTurn;
    private final PlayerTurn redTurn;

    private boolean isGameOver = false;
    private boolean isFirstMove = true;

    public GammaHantoGame(HantoPlayerColor movesFirst) {
        board = new HashMap<>();

        // Set up piece managers based on rule set
        blueTurn = new PlayerTurnImpl(HantoPlayerColor.BLUE,
                new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 5));
        redTurn = new PlayerTurnImpl(HantoPlayerColor.RED,
                new HantoPlayerPieceManagerImpl(1, 0, 0, 0, 0, 5));

        currentTurn = movesFirst.equals(HantoPlayerColor.BLUE) ? blueTurn : redTurn;
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

    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
            throws HantoException {
        // Game already over
        if (isGameOver) {
            throw new HantoException("You cannot move after the game is finished");
        }

        final HantoCoordinateImpl dest = new HantoCoordinateImpl(to);
        final HantoPieceImpl loc = new HantoPieceImpl(currentTurn.getColor(), pieceType);

        if (from == null) { // Place new piece
            if (!isPlacePieceValid(dest, loc)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot place a piece in that location");
            }

            placePlayerPiece(dest, loc);
        } else { // Move piece
            final HantoCoordinateImpl src = new HantoCoordinateImpl(from);

            if (!isMoveValid(src, dest, loc)) {
                throw new HantoException(
                        currentTurn.getColor().name() + " cannot move a piece to that location");
            }

            movePlayerPiece(src, dest, loc);
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
        boolean blueIsOutOfPieces = false;
        boolean redIsOutOfPieces = false;
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

        // Check if players are out of pieces
        blueIsOutOfPieces = blueTurn.getPlayerPieceManager().isOutOfPieces();
        redIsOutOfPieces = redTurn.getPlayerPieceManager().isOutOfPieces();

        isGameOver = true;

        // Determine correct result
        if (blueIsSurrounded && redIsSurrounded) {
            mr = MoveResult.DRAW;
        } else if (!blueIsSurrounded && !redIsSurrounded && blueIsOutOfPieces && redIsOutOfPieces) {
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
     * Places the specified piece at the specified coordinate.
     *
     * @param coord
     *            The location to place the piece.
     * @param piece
     *            The piece to place.
     */
    private void placePlayerPiece(HantoCoordinate coord, HantoPiece piece) {
        board.put(coord, piece);

        if (piece.getType() == HantoPieceType.BUTTERFLY) {
            currentTurn.setPlayerButterflyCoordinate(new HantoCoordinateImpl(coord));
        }

        currentTurn.getPlayerPieceManager().placePiece(piece.getType());
    }

    private void movePlayerPiece(HantoCoordinate from, HantoCoordinate to, HantoPiece piece) {
        board.remove(from);
        board.put(to, piece);
    }

    private boolean isCoordinateOrigin(HantoCoordinate coord) {
        return coord.equals(ORIGIN);
    }

    private boolean isMoveValid(HantoCoordinateImpl from, HantoCoordinateImpl to,
            HantoPieceImpl type) {
        return type.canMove(from, to, board);
    }

    private boolean isPlacePieceValid(HantoCoordinateImpl coord, HantoPieceImpl type) {
        if (!currentTurn.getPlayerButterflyCoordinate().isPresent()
                && currentTurn.getTurnCount() >= MAX_TURNS_BEFORE_PLACE_BUTTERFLY) {
            return false;
        }

        if (!currentTurn.getPlayerPieceManager().canPlacePiece(type.getType())) {
            return false;
        }

        // First move
        if (isFirstMove) {
            // Not origin move
            if (!isCoordinateOrigin(coord)) {
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

            return validator.canPlacePiece(coord, type, board);
        }
    }

    /**
     * @see {@link hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate where) {
        try {
            // Convert to our coordinate implementation
            final HantoCoordinateImpl c = new HantoCoordinateImpl(where);
            final HantoPiece piece = board.get(c);
            return piece;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @see {@link hanto.common.HantoGame#getPrintableBoard()}
     */
    @Override
    public String getPrintableBoard() {
        final StringBuilder sb = new StringBuilder();
        for (HantoCoordinate c : board.keySet()) {
            sb.append(c);
            sb.append(": ");
            sb.append(board.get(c));
            sb.append('\n');
        }
        return sb.toString();
    }
}
