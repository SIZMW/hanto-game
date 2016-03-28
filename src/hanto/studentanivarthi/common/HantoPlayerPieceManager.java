/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentanivarthi.common;

import hanto.common.HantoPieceType;

/**
 * This interface defines the necessary methods for classes that implement
 * keeping track of the restrictions on each player's pieces.
 *
 * @author Aditya Nivarthi
 */
public interface HantoPlayerPieceManager {

    /**
     * Returns whether the piece can be placed for this player. If it can, the
     * number remaining is decremented.
     *
     * @param piece
     *            The piece to check.
     * @return true if it can be placed, false otherwise
     */
    boolean canPlacePiece(HantoPieceType piece);

    /**
     * Returns whether the player has any more pieces left to play.
     *
     * @return true if there are pieces, false otherwise
     */
    boolean isOutOfPieces();

    /**
     * Marks the specified piece type as being placed, and lowers the remaining
     * number of that piece.
     *
     * @param type
     *            The piece to place.
     */
    void placePiece(HantoPieceType type);
}
