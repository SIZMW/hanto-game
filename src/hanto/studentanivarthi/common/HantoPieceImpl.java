/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentanivarthi.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.common.movevalidators.MoveValidator;
import hanto.studentanivarthi.common.movevalidators.WalkMoveValidator;
import hanto.studentanivarthi.common.piece.HantoGamePiece;

/**
 * Implementation of the HantoPiece.
 *
 * @version Mar 2,2016
 */
public class HantoPieceImpl implements HantoGamePiece {
    private final HantoPlayerColor color;
    private final HantoPieceType type;
    private final List<MoveValidator> moveValidators;

    /**
     * Default constructor.
     *
     * @param color
     *            the piece color
     * @param type
     *            the piece type
     */
    public HantoPieceImpl(HantoPlayerColor color, HantoPieceType type) {
        this.color = color;
        this.type = type;
        moveValidators = new ArrayList<>();

        addMoveValidators();
    }

    /**
     * Copy constructor.
     *
     * @param color
     *            the piece color
     * @param type
     *            the piece type
     */
    public HantoPieceImpl(HantoPiece piece) {
        color = piece.getColor();
        type = piece.getType();
        moveValidators = new ArrayList<>();

        addMoveValidators();
    }

    /**
     * @see {@link hanto.studentanivarthi.common.piece.HantoGamePiece#canMove(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, java.util.Map)}
     */
    @Override
    public boolean canMove(HantoCoordinate from, HantoCoordinate to,
            Map<HantoCoordinate, HantoPiece> board) {
        for (MoveValidator m : moveValidators) {
            if (m.canMove(from, to, board)) {
                return true;
            }
        }

        return false;
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

    private void addMoveValidators() {
        switch (type) {
            case BUTTERFLY:
                moveValidators.add(new WalkMoveValidator());
                break;
            case SPARROW:
                moveValidators.add(new WalkMoveValidator());
                break;
            default:
                moveValidators.add(new WalkMoveValidator());
                break;
        }
    }
}
