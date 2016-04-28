/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.board;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;

/**
 * The HantoGameBoard interface defines additional game methods for board
 * implementations in the Hanto game.
 *
 * @author Aditya Nivarthi
 */
public interface HantoGameBoard extends HantoBoard {
    /**
     * Returns a copy of this HantoGameBoard. Creates a new internal board with
     * the same references to coordinates and pieces.
     *
     * @return a {@link HantoGameBoard}
     */
    HantoGameBoard copy();

    /**
     * Returns a list of coordinates with pieces of the specified color.
     *
     * @param color
     *            The {@link HantoPlayerColor} of the pieces to search for.
     * @return a {@link Collection}&lt;{@link HantoCoordinate}&gt;
     */
    Collection<HantoCoordinate> getCoordinatesWithPiecesOfColor(HantoPlayerColor color);

    /**
     * Returns a collection of coordinates surrounding the specified coordinate
     * that do not have a piece occupying them.
     *
     * @param coordinate
     *            The coordinate to get the empty surrounding coordinates
     *            around.
     * @return a {@link Collection}&lt;{@link HantoCoordinate}&gt;
     */
    Collection<HantoCoordinate> getEmptySurroundingCoordinates(HantoCoordinate coordinate);

    /**
     * Returns the number of pieces currently on the game board.
     *
     * @return an integer
     */
    int getNumberOfPieces();

    /**
     * Returns if the board is empty.
     *
     * @return true if empty, false otherwise
     */
    boolean isBoardEmpty();
}
