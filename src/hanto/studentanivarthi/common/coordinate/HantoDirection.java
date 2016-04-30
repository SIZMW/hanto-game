/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.common.coordinate;

/**
 * This is the implementation of directions on the Hanto game board.
 *
 * @author Aditya Nivarthi
 */
public enum HantoDirection {
    /**
     * The direction for vertically above.
     */
    N(0, 1),
    /**
     * The direction for vertically rising and horizontally to the right.
     */
    NE(1, 0),
    /**
     * The direction for vertically descending and horizontally to the right.
     */
    SE(1, -1),
    /**
     * The direction for vertically below.
     */
    S(0, -1),
    /**
     * The direction for vertically descending and horizontally to the left.
     */
    SW(-1, 0),
    /**
     * The direction for vertically rising and horizontally to the left.
     */
    NW(-1, 1),
    /**
     * The result for no consistent direction.
     */
    NONE(0, 0);

    private final int x;
    private final int y;

    /**
     * Creates a HantoDirection instance.
     *
     * @param x
     *            The X direction.
     * @param y
     *            The Y direction.
     */
    private HantoDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X direction.
     *
     * @return an integer
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y direction.
     *
     * @return an integer
     */
    public int getY() {
        return y;
    }
}
