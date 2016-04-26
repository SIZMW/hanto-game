/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common;

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

    /**
     * Returns the direction from the first set of coordinates (x, y) to the
     * second set of coordinates (x, y).
     *
     * @param x1
     *            The X coordinate of the first coordinate.
     * @param y1
     *            The Y coordinate of the first coordinate.
     * @param x2
     *            The X coordinate of the second coordinate.
     * @param y2
     *            The Y coordinate of the second coordinate.
     * @return a {@link HantoDirection}
     */
    public static HantoDirection getDirectionTo(int x1, int y1, int x2, int y2) {
        int srcx = x1;
        int srcy = y1;

        for (HantoDirection e : HantoDirection.values()) {
            while (srcx != x2 || srcy != y2) {
                srcx += e.getX();
                srcy += e.getY();
            }

            if (srcx == x2 && srcy == y2) {
                return e;
            }

            srcx = x1;
            srcy = y1;
        }

        return NONE;
    }
}
