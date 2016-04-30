/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPieceType;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;

/**
 * Tests for {@link HantoValidAction}.
 *
 * @author Aditya Nivarthi
 */
public class HantoValidActionTest {
    private HantoValidAction move;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        move = new HantoValidAction(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0, 0),
                new HantoCoordinateImpl(0, 0));
    }

    /**
     * Test to see if two move objects are equal when created the same way.
     */
    @Test // 1
    public void testEqualsOnSameObjects() {
        HantoValidAction move2 = new HantoValidAction(HantoPieceType.BUTTERFLY,
                new HantoCoordinateImpl(0, 0), new HantoCoordinateImpl(0, 0));

        assertEquals(move, move2);
    }

    /**
     * Test to see if two move objects are equal when one has null fields.
     */
    @Test // 2
    public void testEqualsOnObjectWithNullFields() {
        HantoValidAction move2 = new HantoValidAction(null, null, null);

        assertNotEquals(move, move2);
        assertNotEquals(move2, move);
    }

    /**
     * Test to see if two move objects are equal with the same reference.
     */
    @Test // 3
    public void testEqualsOnObjectWithSameReference() {
        HantoValidAction move2 = move;

        assertEquals(move, move2);
        assertEquals(move2, move);
    }

    /**
     * Test to see if two move objects are equal with different objects.
     */
    @Test // 4
    public void testEqualsWithDifferentClasses() {
        Integer i = new Integer(1);

        assertNotEquals(move, i);
    }

    /**
     * Test to see if two move objects are equal with different piece types.
     */
    @Test // 5
    public void testEqualsDifferentPieceTypes() {
        HantoValidAction move2 = new HantoValidAction(HantoPieceType.CRAB,
                new HantoCoordinateImpl(0, 0), new HantoCoordinateImpl(0, 0));

        assertNotEquals(move, move2);
    }

    /**
     * Test to see if two move objects are equal with different sources.
     */
    @Test // 6
    public void testEqualsDifferentSources() {
        HantoValidAction move2 = new HantoValidAction(HantoPieceType.BUTTERFLY,
                new HantoCoordinateImpl(1, 0), new HantoCoordinateImpl(0, 0));

        assertNotEquals(move, move2);
    }

    /**
     * Test to see if two move objects are equal with different destinations.
     */
    @Test // 7
    public void testEqualsDifferentDestinations() {
        HantoValidAction move2 = new HantoValidAction(HantoPieceType.BUTTERFLY,
                new HantoCoordinateImpl(0, 0), new HantoCoordinateImpl(1, 0));

        assertNotEquals(move, move2);
    }

    /**
     * Test to see if two move objects are equal with one null source.
     */
    @Test // 8
    public void testEqualsWithNullSource() {
        HantoValidAction move2 = new HantoValidAction(HantoPieceType.BUTTERFLY, null,
                new HantoCoordinateImpl(0, 0));

        assertNotEquals(move2, move);
    }

    /**
     * Test toString().
     */
    @Test // 9
    public void testToString() {
        assertEquals(move.toString(),
                "Piece Type: BUTTERFLY, Source: HantoCoordinateImpl [x = 0, y = 0], Destination: HantoCoordinateImpl [x = 0, y = 0]");
    }

    /**
     * Test the hashCode() method by adding the move to a HashMap and retrieving
     * it.
     */
    @Test // 10
    public void testHashCodeWithHashMap() {
        Map<HantoValidAction, Integer> map = new HashMap<>();
        map.put(move, 1);

        assertEquals(map.get(move), new Integer(1));
    }
}
