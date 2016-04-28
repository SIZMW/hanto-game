/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.coordinate;

/**
 * This is the implementation of directions on the Hanto game board.
 *
 * @author Aditya Nivarthi
 */
public enum HantoDirection {
    N(0, 1), NE(1, 0), SE(1, -1), S(0, -1), SW(-1, 0), NW(-1, 1), NONE(0, 0);

    private int x;
    private int y;

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
