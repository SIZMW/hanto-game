/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html Copyright
 * ©2016 Gary F. Pollice
 *******************************************************************************/
package hanto.studentanivarthi.beta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import hanto.studentanivarthi.common.HantoPlayerPieceManager;
import hanto.studentanivarthi.common.HantoPlayerPiecesImpl;

/**
 * The implementation of Beta Hanto.
 *
 * @author Aditya Nivarthi
 * @version Mar 26, 2016
 */
public class BetaHantoGame implements HantoGame {
    private final int MAX_TURNS_BEFORE_PLACE_BUTTERFLY = 3;

    private final Map<HantoCoordinate, HantoPiece> board;
    private HantoPlayerColor playerTurn;

    private final HantoPlayerPieceManager bluePieces;
    private final HantoPlayerPieceManager redPieces;

    private HantoCoordinate blueButterfly = null;
    private HantoCoordinate redButterfly = null;

    private boolean blueHasPlacedButterfly = false;
    private boolean redHasPlacedButterfly = false;
    private boolean isGameOver = false;
    private boolean isFirstMove = true;
    private int blueTurnCount = 0;
    private int redTurnCount = 0;

    /**
     * Creates a BetaHantoGame with the specified player color of who moves
     * first.
     *
     * @param movesFirst
     *            The player to move first.
     */
    public BetaHantoGame(HantoPlayerColor movesFirst) {
        board = new HashMap<>();
        playerTurn = movesFirst;
        bluePieces = new HantoPlayerPiecesImpl(HantoPlayerColor.BLUE, 1, 0, 0, 0, 0, 5);
        redPieces = new HantoPlayerPiecesImpl(HantoPlayerColor.RED, 1, 0, 0, 0, 0, 5);
    }

    /**
     * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
     */
    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
            throws HantoException {
        // Game already over
        if (isGameOver) {
            throw new HantoException("You cannot move after the game is finished");
        }

        // // Check piece types
        // if ((pieceType != HantoPieceType.BUTTERFLY) && (pieceType !=
        // HantoPieceType.SPARROW)) {
        // throw new HantoException("Piece must be a " +
        // HantoPieceType.BUTTERFLY + " or a "
        // + HantoPieceType.SPARROW);
        // }

        final HantoCoordinateImpl dest = new HantoCoordinateImpl(to);
        final HantoPieceImpl loc = new HantoPieceImpl(playerTurn, pieceType);

        // Check if move is valid
        if (!isMoveValid(dest, loc)) {
            throw new HantoException(playerTurn.name() + " cannot place a piece in that location");
        }

        placePlayerPiece(dest, loc);

        // Switch turn and get result
        switchPlayerTurn();
        return getMoveResult();

    }

    /**
     * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate where) {
        try {
            // Convert to our coordinate implementation
            final HantoCoordinateImpl c = new HantoCoordinateImpl(where.getX(), where.getY());
            final HantoPiece piece = board.get(c);
            return piece;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @see hanto.common.HantoGame#getPrintableBoard()
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

        if (piece.getColor() == HantoPlayerColor.BLUE) {
            if (piece.getType() == HantoPieceType.BUTTERFLY) {
                blueButterfly = coord;
                blueHasPlacedButterfly = true;
            }
            bluePieces.placePiece(piece.getType());
        } else {
            if (piece.getType() == HantoPieceType.BUTTERFLY) {
                redButterfly = coord;
                redHasPlacedButterfly = true;
            }
            redPieces.placePiece(piece.getType());
        }
    }

    /**
     * Switches the player who will make the next move. Updates the number of
     * turns played for each player.
     */
    private void switchPlayerTurn() {
        if (playerTurn.equals(HantoPlayerColor.BLUE)) {
            playerTurn = HantoPlayerColor.RED;
            blueTurnCount++;
        } else {
            playerTurn = HantoPlayerColor.BLUE;
            redTurnCount++;
        }
    }

    /**
     * Returns whether the specified coordinate is the origin location.
     *
     * @param coord
     *            The coordinate to check.
     * @return true if it is the origin, false otherwise
     */
    private boolean isCoordinateOrigin(HantoCoordinate coord) {
        return (coord.getX() == 0) && (coord.getY() == 0);
    }

    /**
     * Returns whether the specified coordinate is a valid location to move.
     *
     * @param coord
     *            The coordinate to check.
     * @param type
     *            The piece to check.
     * @return true if valid, false otherwise
     */
    private boolean isMoveValid(HantoCoordinate coord, HantoPiece type) {
        // Check by whose turn it is
        if (playerTurn == HantoPlayerColor.BLUE) {
            if ((!blueHasPlacedButterfly) && (blueTurnCount > MAX_TURNS_BEFORE_PLACE_BUTTERFLY)) {
                return false;
            }

            if (!bluePieces.canPlacePiece(type.getType())) {
                return false;
            }
        } else {
            if ((!redHasPlacedButterfly) && (redTurnCount > MAX_TURNS_BEFORE_PLACE_BUTTERFLY)) {
                return false;
            }

            if (!redPieces.canPlacePiece(type.getType())) {
                return false;
            }
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
            // Piece already in that spot
            if (board.containsKey(coord) && (board.get(coord) != null)) {
                return false;
            }

            // Check if location is adjacent to some piece already on the board
            boolean isAdjacentToPiece = false;
            final List<HantoCoordinate> surroundings = getSurroundingPieces(coord);

            for (HantoCoordinate e : surroundings) {
                if (board.containsKey(e) && (board.get(e) != null)) {
                    isAdjacentToPiece = true;
                    break;
                }
            }

            return isAdjacentToPiece;
        }
    }

    /**
     * Returns a list of the surrounding locations given the specified location.
     *
     * @param coord
     *            The location to get the surrounding coordinates for.
     * @return a {@link List}&lt;{@link HantoCoordinate}&gt;
     */
    private List<HantoCoordinate> getSurroundingPieces(HantoCoordinate coord) {
        final List<HantoCoordinate> surroundings = new ArrayList<>();
        surroundings.add(new HantoCoordinateImpl(coord.getX() + 1, coord.getY()));
        surroundings.add(new HantoCoordinateImpl(coord.getX() - 1, coord.getY()));
        surroundings.add(new HantoCoordinateImpl(coord.getX(), coord.getY() + 1));
        surroundings.add(new HantoCoordinateImpl(coord.getX(), coord.getY() - 1));
        surroundings.add(new HantoCoordinateImpl(coord.getX() + 1, coord.getY() - 1));
        surroundings.add(new HantoCoordinateImpl(coord.getX() - 1, coord.getY() + 1));
        return surroundings;
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

        blueIsSurrounded = isCoordinateSurrounded(blueButterfly);
        redIsSurrounded = isCoordinateSurrounded(redButterfly);
        blueIsOutOfPieces = bluePieces.isOutOfPieces();
        redIsOutOfPieces = redPieces.isOutOfPieces();

        isGameOver = true;
        if ((blueIsSurrounded && redIsSurrounded)) {
            mr = MoveResult.DRAW;
        } else if (!blueIsSurrounded && !redIsSurrounded && blueIsOutOfPieces && redIsOutOfPieces) {
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
     * Returns whether the specified coordinate is completely surrounded.
     *
     * @param coord
     *            The coordinate to check.
     * @return true if surrounded completely, false otherwise
     */
    private boolean isCoordinateSurrounded(HantoCoordinate coord) {
        if (coord == null) {
            return false;
        }

        final List<HantoCoordinate> surroundings = getSurroundingPieces(coord);

        boolean hasEmptyAdjacentSpot = false;

        for (HantoCoordinate e : surroundings) {
            if (board.containsKey(e) && (board.get(e) != null)) {
                hasEmptyAdjacentSpot = false;
            } else {
                hasEmptyAdjacentSpot = true;
                break;
            }
        }

        return !hasEmptyAdjacentSpot;
    }
}
