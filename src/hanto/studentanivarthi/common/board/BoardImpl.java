/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.board;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.HantoPieceImpl;

/**
 * The BoardImpl class is an implementation of the Board interface for the Hanto
 * game.
 *
 * @author Aditya Nivarthi
 */
public class BoardImpl implements Board {
    private Map<HantoCoordinate, HantoPiece> board;

    /**
     * Creates a BoardImpl instance.
     */
    public BoardImpl() {
        board = new HashMap<>();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#arePiecesContiguous()}
     */
    @Override
    public boolean arePiecesContiguous() {
        return false; // TODO
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#getPieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate coordinate) {
        try {
            // Convert to our coordinate implementation
            final HantoCoordinateImpl c = new HantoCoordinateImpl(coordinate);
            final HantoPiece piece = board.get(c);
            return piece;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#hasPieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public boolean hasPieceAt(HantoCoordinate coordinate) {
        HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        return board.containsKey(coordinateImpl);
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#isBoardEmpty()}
     */
    @Override
    public boolean isBoardEmpty() {
        return board.isEmpty();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#placePieceAt(hanto.common.HantoCoordinate, hanto.common.HantoPiece)}
     */
    @Override
    public boolean placePieceAt(HantoCoordinate coordinate, HantoPiece piece) {
        HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        HantoPieceImpl pieceImpl = new HantoPieceImpl(piece);

        if (hasPieceAt(coordinateImpl)) {
            return false;
        }

        board.put(coordinateImpl, pieceImpl);
        return true;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#removePieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public HantoPiece removePieceAt(HantoCoordinate coordinate) throws ClassCastException {
        try {
            HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
            return board.remove(coordinateImpl);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @see {@link java.lang.Object#toString()}
     */
    @Override
    public String toString() {
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
