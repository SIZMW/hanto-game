/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.alpha;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.MoveResult.DRAW;
import static hanto.common.MoveResult.OK;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentanivarthi.common.coordinate.HantoCoordinateImpl;
import hanto.studentanivarthi.common.piece.HantoPieceImpl;

/**
 * The implementation of Alpha Hanto, based on the {@link HantoGame} interface.
 *
 * @author Aditya Nivarthi
 */
public class AlphaHantoGame implements HantoGame {
    private boolean firstMove = true;
    private HantoCoordinateImpl blueButterflyHex = null, redButterflyHex = null;
    private final HantoPiece blueButterfly = new HantoPieceImpl(BLUE, BUTTERFLY);
    private final HantoPiece redButterfly = new HantoPieceImpl(RED, BUTTERFLY);
    private boolean gameOver = false;

    /**
     * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
     */
    @Override
    public HantoPiece getPieceAt(HantoCoordinate coordinate) {
        final HantoCoordinateImpl where = new HantoCoordinateImpl(coordinate);
        return where.equals(blueButterflyHex) ? blueButterfly
                : where.equals(redButterflyHex) ? redButterfly : null;
    }

    /**
     * @see hanto.common.HantoGame#getPrintableBoard()
     */
    @Override
    public String getPrintableBoard() {
        return null;
    }

    /**
     * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType,
     *      hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
     */
    @Override
    public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate source,
            HantoCoordinate destination) throws HantoException {
        if (gameOver) {
            throw new HantoException("You cannot move after the game is finished");
        }
        if (pieceType != BUTTERFLY) {
            throw new HantoException("Only BUTTERFLY pieces are valid in Alpha Hanto");
        }

        final HantoCoordinateImpl to = new HantoCoordinateImpl(destination);

        if (firstMove) {
            if (to.getX() != 0 || to.getY() != 0) {
                throw new HantoException("BLUE did not move BUTTERFLY to origin");
            }
            blueButterflyHex = to;
        } else {
            if (!isCoordinateValidForRed(to)) {
                throw new HantoException("RED cannot place a piece in that coordinate");
            }
            redButterflyHex = to;
            gameOver = true;
        }

        final MoveResult moveResult = firstMove ? OK : DRAW;
        firstMove = false;
        return moveResult;
    }

    /**
     * Returns whether the coordinate is valid for RED.
     *
     * @param coordinate
     *            The {@link HantoCoordinateImpl} to check.
     * @return true if the coordinate is valid for RED, false otherwise
     */
    protected boolean isCoordinateValidForRed(HantoCoordinateImpl coordinate) {
        return coordinate.equals(new HantoCoordinateImpl(0, 1))
                || coordinate.equals(new HantoCoordinateImpl(1, 0))
                || coordinate.equals(new HantoCoordinateImpl(1, -1))
                || coordinate.equals(new HantoCoordinateImpl(0, -1))
                || coordinate.equals(new HantoCoordinateImpl(-1, 0))
                || coordinate.equals(new HantoCoordinateImpl(-1, 1));
    }
}
