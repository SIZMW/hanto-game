/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.piece;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Implementation of the {@link HantoPiece}.
 *
 * @author Aditya Nivarthi
 */
public class HantoPieceImpl implements HantoPiece {
    private final HantoPlayerColor color;
    private final HantoPieceType type;

    /**
     * Copy constructor.
     *
     * @param piece
     *            The {@link HantoPiece} to copy from.
     */
    public HantoPieceImpl(HantoPiece piece) {
        color = piece.getColor();
        type = piece.getType();
    }

    /**
     * Creates a HantoPieceImpl instance.
     *
     * @param color
     *            The {@link HantoPlayerColor} color.
     * @param pieceType
     *            The {@link HantoPieceType} type.
     */
    public HantoPieceImpl(HantoPlayerColor color, HantoPieceType pieceType) {
        this.color = color;
        type = pieceType;
    }

    /**
     * @see hanto.common.HantoPiece#getColor()
     */
    @Override
    public HantoPlayerColor getColor() {
        return color;
    }

    /**
     * @see hanto.common.HantoPiece#getType()
     */
    @Override
    public HantoPieceType getType() {
        return type;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HantoPieceImpl [color=" + color + ", type=" + type + "]";
    }
}
