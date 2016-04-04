/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.movevalidators;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;

/**
 * The WalkMoveValidator is a subset of the move validators that considers
 * pieces that are able to walk in the game of Hanto.
 * 
 * @author Aditya Nivarthi
 */
public class WalkMoveValidator implements MoveValidator {

    /**
     * @see {@link hanto.studentanivarthi.common.movevalidators.MoveValidator#canMove(hanto.common.HantoCoordinate, hanto.common.HantoCoordinate, java.util.Map)}
     */
    @Override
    public boolean canMove(HantoCoordinate from, HantoCoordinate to,
            Map<HantoCoordinate, HantoPiece> board) {
        return false; // TODO finish this
    }
}
