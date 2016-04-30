/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.board.HantoGameBoard;
import hanto.studentanivarthi.common.board.HantoGameBoardImpl;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;

/**
 * Tests for the {@link HantoCoordinateImpl}.
 *
 * @author Aditya Nivarthi
 */
public class HantoCoordinateImplTest {
    private HantoCoordinateImpl coordinate;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        coordinate = new HantoCoordinateImpl(0, 0);
    }

    /**
     * Test setting and getting the coordinates.
     */
    @Test // 1
    public void testCoordinates() {
        assertEquals(coordinate.getX(), 0);
        assertEquals(coordinate.getY(), 0);
    }

    /**
     * Tests getting the surrounding coordinate locations.
     */
    @Test // 2
    public void testSurroundingCoordinates() {
        final Collection<HantoCoordinate> surroundings = coordinate.getSurroundingCoordinates();
        assertEquals(surroundings.size(), 6);

        final List<HantoCoordinate> surr = new ArrayList<>();
        surr.add(new HantoCoordinateImpl(0 + 1, 0));
        surr.add(new HantoCoordinateImpl(0 - 1, 0));
        surr.add(new HantoCoordinateImpl(0, 0 + 1));
        surr.add(new HantoCoordinateImpl(0, 0 - 1));
        surr.add(new HantoCoordinateImpl(0 + 1, 0 - 1));
        surr.add(new HantoCoordinateImpl(0 - 1, 0 + 1));

        for (HantoCoordinate c : surr) {
            assertTrue(surroundings.contains(c));
        }
    }

    /**
     * Test checking if a piece is not surrounded because the board is empty.
     */
    @Test // 3
    public void testIsCoordinateNotSurroundedEmptyBoard() {
        final HantoGameBoard board = new HantoGameBoardImpl();
        assertFalse(board.isCoordinateSurrounded(coordinate));
    }

    /**
     * Test checking if a piece is not surrounded because the board is null.
     */
    @Test // 4
    public void testIsCoordinateNotSurroundedNullBoard() {
        final HantoGameBoard board = new HantoGameBoardImpl();
        assertFalse(board.isCoordinateSurrounded(coordinate));
    }

    /**
     * Test checking if a piece is not surrounded.
     */
    @Test // 5
    public void testIsCoordinateNotSurrounded() {
        final HantoGameBoard board = new HantoGameBoardImpl();
        board.placePieceAt(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY),
                new HantoCoordinateImpl(0, 1));

        assertFalse(board.isCoordinateSurrounded(coordinate));
    }

    /**
     * Test checking if a piece is surrounded.
     */
    @Test // 6
    public void testIsCoordinateSurrounded() {
        final HantoGameBoard board = new HantoGameBoardImpl();

        board.placePieceAt(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW),
                new HantoCoordinateImpl(0 + 1, 0));
        board.placePieceAt(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW),
                new HantoCoordinateImpl(0 - 1, 0));
        board.placePieceAt(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW),
                new HantoCoordinateImpl(0, 0 + 1));
        board.placePieceAt(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW),
                new HantoCoordinateImpl(0, 0 - 1));
        board.placePieceAt(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW),
                new HantoCoordinateImpl(0 + 1, 0 - 1));
        board.placePieceAt(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW),
                new HantoCoordinateImpl(0 - 1, 0 + 1));

        assertTrue(board.isCoordinateSurrounded(coordinate));
    }
}
