/************************************************************************
 * This class was created for the Hanto game implementation for CS 4233.
 *
 * @author Aditya Nivarthi
 ************************************************************************/

package hanto.studentanivarthi.tournament;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.HantoGameFactory;
import hanto.studentanivarthi.common.game.HantoValidActionGame;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * This class defines a basic AI player for the {@link HantoGame} games.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayer implements HantoGamePlayer {
    protected HantoValidActionGame game;
    protected boolean amIFirstMove;
    protected HantoPieceType prevPieceType = null;
    protected HantoCoordinate prevCoordinate = null;

    /**
     * @see hanto.tournament.HantoGamePlayer#makeMove(hanto.tournament.HantoMoveRecord)
     */
    @Override
    public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
        // Check if move passed in is null
        if (opponentsMove != null) {
            try {
                game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(),
                        opponentsMove.getTo());
            } catch (HantoException e) {
                System.err.println("ANIVARTHI: Opponent move failed.");
            }
        }

        // Get move to do from game
        final HantoValidAction move = game.hasValidAction(prevPieceType, prevCoordinate);
        if (move != null) {
            try {
                game.makeMove(move.getPieceType(), move.getSource(), move.getDestination());

                // Store move as previous for next move
                prevPieceType = move.getPieceType();
                prevCoordinate = move.getDestination();

                return new HantoMoveRecord(move.getPieceType(), move.getSource(),
                        move.getDestination());
            } catch (HantoException e) {
                System.err.println("ANIVARTHI: My move failed.");
            }
        }

        // Have to resign
        return new HantoMoveRecord(null, null, null);
    }

    /**
     * @see hanto.tournament.HantoGamePlayer#startGame(hanto.common.HantoGameID,
     *      hanto.common.HantoPlayerColor, boolean)
     */
    @Override
    public void startGame(HantoGameID version, HantoPlayerColor myColor, boolean doIMoveFirst) {
        System.out.println("ANIVARTHI: Start Game.");
        amIFirstMove = doIMoveFirst;

        // Get starting color
        final HantoPlayerColor starterColor = doIMoveFirst ? myColor
                : myColor.equals(HantoPlayerColor.BLUE) ? HantoPlayerColor.RED
                        : HantoPlayerColor.BLUE;

        // Create game
        game = (HantoValidActionGame) HantoGameFactory.getInstance().makeHantoGame(version,
                starterColor);
    }
}
