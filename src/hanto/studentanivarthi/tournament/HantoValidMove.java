/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.tournament;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;

/**
 * This class is a container for holding a valid move that the player can make.
 *
 * @author Aditya Nivarthi
 */
public class HantoValidMove {
    protected HantoPieceType pieceType;
    protected HantoCoordinate source;
    protected HantoCoordinate destination;

    /**
     * Creates a HantoValidMove instance.
     *
     * @param pieceType
     *            The {@link HantoPieceType} of the piece.
     * @param source
     *            The starting {@link HantoCoordinate}.
     * @param destination
     *            The destination {@link HantoCoordinate}.
     */
    public HantoValidMove(HantoPieceType pieceType, HantoCoordinate source,
            HantoCoordinate destination) {
        this.pieceType = pieceType;
        this.source = source;
        this.destination = destination;
    }

    /**
     * Creates a HantoValidMove instance from an other instance.
     *
     * @param move
     *            The other {@link HantoValidMove.
     */
    public HantoValidMove(HantoValidMove move) {
        pieceType = move.getPieceType();
        source = move.getSource();
        destination = move.getDestination();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        HantoValidMove other = (HantoValidMove) obj;

        if (destination == null) {
            if (other.destination != null) {
                return false;
            }
        } else if (!destination.equals(other.destination)) {
            return false;
        }
        if (pieceType != other.pieceType) {
            return false;
        }
        if (source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the destination coordinate.
     *
     * @return a {@link HantoCoordinate}
     */
    public HantoCoordinate getDestination() {
        return destination;
    }

    /**
     * Returns the piece type.
     *
     * @return a {@link HantoPieceType}
     */
    public HantoPieceType getPieceType() {
        return pieceType;
    }

    /**
     * Returns the source coordinate.
     *
     * @return a {@link HantoCoordinate}
     */
    public HantoCoordinate getSource() {
        return source;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (destination == null ? 0 : destination.hashCode());
        result = prime * result + (pieceType == null ? 0 : pieceType.hashCode());
        result = prime * result + (source == null ? 0 : source.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Piece Type: " + pieceType.name() + ", Source: " + source + ", Destination: "
                + destination;
    }
}
