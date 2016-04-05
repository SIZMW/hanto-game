/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

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
import hanto.studentanivarthi.common.board.Board;
import hanto.studentanivarthi.common.board.BoardImpl;

/**
 * Tests for the {@link HantoCoordinateImpl} class.
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
        Collection<HantoCoordinate> surroundings = coordinate.getSurroundingPieces();
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
        Board board = new BoardImpl();
        assertFalse(coordinate.isCoordinateSurrounded(board));
    }

    /**
     * Test checking if a piece is not surrounded because the board is null.
     */
    @Test // 4
    public void testIsCoordinateNotSurroundedNullBoard() {
        Board board = null;
        assertFalse(coordinate.isCoordinateSurrounded(board));
    }

    /**
     * Test checking if a piece is not surrounded.
     */
    @Test // 5
    public void testIsCoordinateNotSurrounded() {
        Board board = new BoardImpl();
        board.placePieceAt(new HantoCoordinateImpl(0, 1),
                new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));

        assertFalse(coordinate.isCoordinateSurrounded(board));
    }

    /**
     * Test checking if a piece is surrounded.
     */
    @Test // 6
    public void testIsCoordinateSurrounded() {
        Board board = new BoardImpl();

        board.placePieceAt(new HantoCoordinateImpl(0 + 1, 0),
                new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW));
        board.placePieceAt(new HantoCoordinateImpl(0 - 1, 0),
                new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW));
        board.placePieceAt(new HantoCoordinateImpl(0, 0 + 1),
                new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW));
        board.placePieceAt(new HantoCoordinateImpl(0, 0 - 1),
                new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW));
        board.placePieceAt(new HantoCoordinateImpl(0 + 1, 0 - 1),
                new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW));
        board.placePieceAt(new HantoCoordinateImpl(0 - 1, 0 + 1),
                new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW));

        assertTrue(coordinate.isCoordinateSurrounded(board));
    }
}
