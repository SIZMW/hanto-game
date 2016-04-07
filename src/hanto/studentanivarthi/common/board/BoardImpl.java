/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.board;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentanivarthi.common.HantoCoordinateImpl;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;

/**
 * The BoardImpl class is an implementation of the {@link Board} interface for
 * the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public class BoardImpl implements Board {
    private final Map<HantoCoordinate, HantoPiece> board;

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
        final LinkedList<HantoCoordinate> list = new LinkedList<>();
        final Set<HantoCoordinate> visitedCoordinates = new HashSet<>();

        final HantoCoordinate starterCoordinate = board.keySet().iterator().next();
        list.add(starterCoordinate);

        while (!list.isEmpty()) {
            final HantoCoordinateImpl coordinate = new HantoCoordinateImpl(list.removeFirst());
            final Collection<HantoCoordinate> surroundings = coordinate.getSurroundingPieces();

            for (HantoCoordinate e : surroundings) {
                if (hasPieceAt(e)) {
                    if (!visitedCoordinates.contains(e)) {
                        visitedCoordinates.add(e);
                        list.addLast(e);
                    }
                }
            }
        }

        return visitedCoordinates.size() == board.keySet().size();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#getPieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate coordinate) {
        try {
            // Convert to our coordinate implementation
            final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
            final HantoPiece piece = board.get(coordinateImpl);
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
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        return board.containsKey(coordinateImpl) && board.get(coordinateImpl) != null;
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
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(piece);

        board.put(coordinateImpl, pieceImpl);
        return true;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.Board#removePieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public HantoPiece removePieceAt(HantoCoordinate coordinate) throws ClassCastException {
        try {
            final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
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
        final StringBuilder stringBuilder = new StringBuilder();
        for (HantoCoordinate c : board.keySet()) {
            stringBuilder.append(c);
            stringBuilder.append(": ");
            stringBuilder.append(board.get(c));
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
