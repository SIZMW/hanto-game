/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentanivarthi.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentanivarthi.HantoGameFactory;
import hanto.studentanivarthi.common.game.HantoValidActionGame;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * Description
 *
 * @version Oct 13, 2014
 */
public class HantoPlayer implements HantoGamePlayer {

    protected HantoValidActionGame game;
    protected boolean amIFirstMove;

    /**
     * @see hanto.tournament.HantoGamePlayer#startGame(hanto.common.HantoGameID,
     *      hanto.common.HantoPlayerColor, boolean)
     */
    @Override
    public void startGame(HantoGameID version, HantoPlayerColor myColor, boolean doIMoveFirst) {
        System.out.println("startGame");
        amIFirstMove = doIMoveFirst;

        HantoPlayerColor starterColor = doIMoveFirst ? myColor
                : myColor.equals(HantoPlayerColor.BLUE) ? HantoPlayerColor.RED
                        : HantoPlayerColor.BLUE;
        HantoGameFactory.getInstance().makeHantoGame(version, starterColor);
    }

    /**
     * @see hanto.tournament.HantoGamePlayer#makeMove(hanto.tournament.
     *      HantoMoveRecord)
     */
    @Override
    public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
        if (opponentsMove != null) {
            try {
                game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(),
                        opponentsMove.getTo());
            } catch (HantoException e) {
                System.out.println("Opponent move failed.");
                e.printStackTrace();
            }
        }

        HantoValidMove move = game.hasValidAction();
        if (move != null) {
            try {
                game.makeMove(move.getPieceType(), move.getSource(), move.getDestination());
                return new HantoMoveRecord(move.getPieceType(), move.getSource(),
                        move.getDestination());
            } catch (HantoException e) {
                System.out.println("My move failed.");
                e.printStackTrace();
            }
        }

        return new HantoMoveRecord(null, null, null);
    }
}
