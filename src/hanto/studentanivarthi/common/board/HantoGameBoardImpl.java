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
 * The HantoGameBoardImpl class is an implementation of the
 * {@link HantoGameBoard} interface for the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public class HantoGameBoardImpl implements HantoGameBoard {
    private final Map<HantoCoordinate, HantoPiece> board;

    /**
     * Creates a HantoGameBoardImpl instance.
     */
    public HantoGameBoardImpl() {
        board = new HashMap<>();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.HantoGameBoard#arePiecesContiguous()}
     */
    @Override
    public boolean arePiecesContiguous() {
        final LinkedList<HantoCoordinate> list = new LinkedList<>();
        final Set<HantoCoordinate> visitedCoordinates = new HashSet<>();
        final HantoCoordinate starterCoordinate = board.keySet().iterator().next();
        list.add(starterCoordinate);

        // Try to reach every coordinate in the board using the surroundings of
        // the previously visited coordinates
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

        // Make sure we visited all the coordinates that have pieces
        return visitedCoordinates.size() == board.keySet().size();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.HantoGameBoard#getPieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate coordinate) {
        try {
            final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
            final HantoPiece piece = board.get(coordinateImpl);
            return piece;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.HantoGameBoard#hasPieceAt(hanto.common.HantoCoordinate)}
     */
    @Override
    public boolean hasPieceAt(HantoCoordinate coordinate) {
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        return board.containsKey(coordinateImpl) && board.get(coordinateImpl) != null;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.HantoGameBoard#isBoardEmpty()}
     */
    @Override
    public boolean isBoardEmpty() {
        return board.isEmpty();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.HantoGameBoard#placePieceAt(hanto.common.HantoCoordinate, hanto.common.HantoPiece)}
     */
    @Override
    public boolean placePieceAt(HantoCoordinate coordinate, HantoPiece piece) {
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(piece);
        board.put(coordinateImpl, pieceImpl);
        return true;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.board.HantoGameBoard#removePieceAt(hanto.common.HantoCoordinate)}
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
