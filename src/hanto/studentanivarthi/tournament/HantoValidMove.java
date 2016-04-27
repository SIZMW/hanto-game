/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

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
     * Returns the destination coordinate.
     *
     * @return a {@link HantoCoordinate}
     */
    public HantoCoordinate getDestination() {
        return destination;
    }

}
