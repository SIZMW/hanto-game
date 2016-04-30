/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;

/**
 * The HantoGameBoardImpl class is an implementation of the
 * {@link HantoGameBoard} interface for the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public class HantoGameBoardImpl implements HantoGameBoard {
    protected final Map<HantoCoordinate, HantoPiece> board;

    /**
     * Creates a HantoGameBoardImpl instance.
     */
    public HantoGameBoardImpl() {
        board = new HashMap<>();
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoBoard#arePiecesContiguous()
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
            final Collection<HantoCoordinate> surroundings = coordinate.getSurroundingCoordinates();

            // Search all the surroundings
            for (HantoCoordinate coord : surroundings) {
                if (hasPieceAt(coord)) {
                    if (!visitedCoordinates.contains(coord)) {
                        visitedCoordinates.add(coord);
                        list.addLast(coord);
                    }
                }
            }
        }

        // Make sure we visited all the coordinates that have pieces
        return visitedCoordinates.size() == getNumberOfPieces();
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoGameBoard#copy()
     */
    @Override
    public HantoGameBoard copy() {
        final HantoGameBoard newBoard = new HantoGameBoardImpl();

        for (HantoCoordinate coordinate : board.keySet()) {
            newBoard.placePieceAt(getPieceAt(coordinate), coordinate);
        }

        return newBoard;
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoGameBoard#getCoordinatesWithPiecesOfColor(hanto.common.HantoPlayerColor)
     */
    @Override
    public Collection<HantoCoordinate> getCoordinatesWithPiecesOfColor(HantoPlayerColor color) {
        final Collection<HantoCoordinate> coordinates = new ArrayList<>();

        for (HantoCoordinate coordinate : board.keySet()) {
            if (board.get(coordinate).getColor().equals(color)) {
                coordinates.add(coordinate);
            }
        }

        return coordinates;
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoGameBoard#getEmptySurroundingCoordinates(hanto.common.HantoCoordinate)
     */
    @Override
    public Collection<HantoCoordinate> getEmptySurroundingCoordinates(HantoCoordinate coordinate) {
        final Collection<HantoCoordinate> emptySurroundings = new ArrayList<>();
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        final Collection<HantoCoordinate> surroundings = coordinateImpl.getSurroundingCoordinates();

        for (HantoCoordinate coord : surroundings) {
            HantoCoordinateImpl coordImpl = new HantoCoordinateImpl(coord);
            if (!board.containsKey(coordImpl)) {
                emptySurroundings.add(coordImpl);
            }
        }

        return emptySurroundings;
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoGameBoard#getNumberOfPieces()
     */
    @Override
    public int getNumberOfPieces() {
        return board.keySet().size();
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoBoard#getPieceAt(hanto.common.HantoCoordinate)
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
     * @see hanto.studentanivarthi.common.board.HantoBoard#hasPieceAt(hanto.common.HantoCoordinate)
     */
    @Override
    public boolean hasPieceAt(HantoCoordinate coordinate) {
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        return board.containsKey(coordinateImpl) && board.get(coordinateImpl) != null;
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoGameBoard#isBoardEmpty()
     */
    @Override
    public boolean isBoardEmpty() {
        return board.isEmpty();
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoBoard#placePieceAt(hanto.common.HantoPiece,
     *      hanto.common.HantoCoordinate)
     */
    @Override
    public void placePieceAt(HantoPiece piece, HantoCoordinate coordinate) {
        final HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
        final HantoPieceImpl pieceImpl = new HantoPieceImpl(piece);
        board.put(coordinateImpl, pieceImpl);
    }

    /**
     * @see hanto.studentanivarthi.common.board.HantoBoard#removePieceAt(hanto.common.HantoCoordinate)
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
     * @see java.lang.Object#toString()
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
