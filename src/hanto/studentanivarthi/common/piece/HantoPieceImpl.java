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
 * @version Mar 2,2016
 */
public class HantoPieceImpl implements HantoPiece {
    private final HantoPlayerColor color;
    private final HantoPieceType type;

    /**
     * Copy constructor.
     *
     * @param piece
     *            the piece to copy
     */
    public HantoPieceImpl(HantoPiece piece) {
        color = piece.getColor();
        type = piece.getType();
    }

    /**
     * Default constructor.
     *
     * @param color
     *            the piece color
     * @param pieceType
     *            the piece type
     */
    public HantoPieceImpl(HantoPlayerColor color, HantoPieceType pieceType) {
        this.color = color;
        type = pieceType;
    }

    /**
     * @see {@link hanto.common.HantoPiece#getColor()}
     */
    @Override
    public HantoPlayerColor getColor() {
        return color;
    }

    /**
     * @see {@link hanto.common.HantoPiece#getType()}
     */
    @Override
    public HantoPieceType getType() {
        return type;
    }

    /**
     * @see {@link java.lang.Object#toString()}
     */
    @Override
    public String toString() {
        return "HantoPieceImpl [color=" + color + ", type=" + type + "]";
    }
}
