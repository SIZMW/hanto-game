/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.studentanivarthi.common.board.HantoGameBoard;

/**
 * The implementation for my version of Hanto.
 *
 * @version Mar 2, 2016
 */
public class HantoCoordinateImpl implements HantoCoordinate {
    private final int x, y;

    /**
     * Copy constructor that creates an instance of HantoCoordinateImpl from an
     * object that implements HantoCoordinate.
     *
     * @param coordinate
     *            an object that implements the {@link HantoCoordinate}
     *            interface.
     */
    public HantoCoordinateImpl(HantoCoordinate coordinate) {
        this(coordinate.getX(), coordinate.getY());
    }

    /**
     * Creates a HantoCoordinateImpl instance with the specified x and y.
     *
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     */
    public HantoCoordinateImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @see {@link java.lang.Object#equals(java.lang.Object)}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof HantoCoordinateImpl)) {
            return false;
        }

        final HantoCoordinateImpl other = (HantoCoordinateImpl) obj;

        if (x != other.x) {
            return false;
        }

        if (y != other.y) {
            return false;
        }

        return true;
    }

    /**
     * Returns a list of the surrounding coordinates around this coordinate.
     *
     * @return a {@link Collection}&lt;{@link HantoCoordinate}&gt;
     */
    public Collection<HantoCoordinate> getSurroundingPieces() {
        final List<HantoCoordinate> surroundings = new ArrayList<>();
        surroundings.add(new HantoCoordinateImpl(x + 1, y));
        surroundings.add(new HantoCoordinateImpl(x - 1, y));
        surroundings.add(new HantoCoordinateImpl(x, y + 1));
        surroundings.add(new HantoCoordinateImpl(x, y - 1));
        surroundings.add(new HantoCoordinateImpl(x + 1, y - 1));
        surroundings.add(new HantoCoordinateImpl(x - 1, y + 1));
        return surroundings;
    }

    /**
     * @see {@link hanto.common.HantoCoordinate#getX()}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @see {@link hanto.common.HantoCoordinate#getY()}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * @see {@link java.lang.Object#hashCode()}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    /**
     * Returns whether the specified coordinate is completely surrounded.
     *
     * @param board
     *            The game {@link HantoGameBoard} of coordinates.
     * @return true if surrounded completely, false otherwise
     */
    public boolean isCoordinateSurrounded(HantoGameBoard board) {
        if (board == null || board.isBoardEmpty()) {
            return false;
        }

        final Collection<HantoCoordinate> surroundings = getSurroundingPieces();
        boolean hasEmptyAdjacentSpot = false;

        for (HantoCoordinate e : surroundings) {
            if (board.hasPieceAt(e)) {
                hasEmptyAdjacentSpot = false;
            } else {
                hasEmptyAdjacentSpot = true;
                break;
            }
        }

        return !hasEmptyAdjacentSpot;
    }

    /**
     * @see {@link java.lang.Object#toString()}
     */
    @Override
    public String toString() {
        return "HantoCoordinateImpl [x=" + x + ", y=" + y + "]";
    }

    /**
     * Returns the distance between this coordinate and another coordinate.
     *
     * @see <a href=
     *      "http://keekerdc.com/2011/03/hexagon-grids-coordinate-systems-and-distance-calculations/">
     *      KeekerDC </a>
     * @param coordinate
     *            The {@link HantoCoordinate} to get the distance to.
     * @return an integer
     */
    public int getDistanceTo(HantoCoordinate coordinate) {
        if (coordinate != null) {
            HantoCoordinateImpl coordinateImpl = new HantoCoordinateImpl(coordinate);
            int xDist = x - coordinateImpl.getX();
            int yDist = y - coordinateImpl.getY();

            if (xDist == 0 && yDist == 0) {
                return 0;
            }

            int signX = xDist == 0 ? 0 : xDist < 0 ? -1 : 1;
            int signY = yDist == 0 ? 0 : yDist < 0 ? -1 : 1;

            if (signX == 1 && signY == 1 || signX == -1 && signY == -1) {
                signY = 0;
            }

            return 1 + getDistanceTo(new HantoCoordinateImpl(coordinateImpl.getX() + signX,
                    coordinateImpl.getY() + signY));
        }

        return 0;
    }
}
