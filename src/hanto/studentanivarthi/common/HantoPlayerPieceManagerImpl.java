/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The
 * course was taken at Worcester Polytechnic Institute. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentanivarthi.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * The implementation of the {@link HantoPlayerPieceManager} class to track player
 * pieces remaining.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerPieceManagerImpl implements HantoPlayerPieceManager {
    private final HantoPlayerColor color;
    private int butterfly;
    private int crab;
    private int crane;
    private int dove;
    private int horse;
    private int sparrow;

    /**
     * Creates a HantoPlayerPieceManagerImpl instance with the specified counts of
     * each piece type.
     *
     * @param color
     *            The player color.
     * @param butterfly
     *            Butterfly count.
     * @param crab
     *            Crab count.
     * @param crane
     *            Crane count.
     * @param dove
     *            Dove count.
     * @param horse
     *            Horse count.
     * @param sparrow
     *            Sparrow count.
     */
    public HantoPlayerPieceManagerImpl(HantoPlayerColor color, int butterfly, int crab, int crane,
            int dove, int horse, int sparrow) {
        this.color = color;
        this.butterfly = butterfly;
        this.crab = crab;
        this.crane = crane;
        this.dove = dove;
        this.horse = horse;
        this.sparrow = sparrow;
    }

    /**
     * @see hanto.studentanivarthi.common.HantoPlayerPieceManager#getPlayerColor()
     */
    @Override
    public HantoPlayerColor getPlayerColor() {
        return color;
    }

    /**
     * @see hanto.studentanivarthi.common.HantoPlayerPieceManager#canPlacePiece()
     */
    @Override
    public boolean canPlacePiece(HantoPieceType piece) {
        switch (piece) {
            case BUTTERFLY:
                return (butterfly > 0);
            case CRAB:
                return (crab > 0);
            case CRANE:
                return (crane > 0);
            case DOVE:
                return (dove > 0);
            case HORSE:
                return (horse > 0);
            case SPARROW:
                return (sparrow > 0);
            default:
                return false;
        }
    }

    /**
     * @see hanto.studentanivarthi.common.HantoPlayerPieceManager#isOutOfPieces()
     */
    @Override
    public boolean isOutOfPieces() {
        return (butterfly <= 0) && (crab <= 0) && (crane <= 0) && (dove <= 0) && (horse <= 0)
                && (sparrow <= 0);
    }

    /**
     * @see hanto.studentanivarthi.common.HantoPlayerPieceManager#placePiece()
     */
    @Override
    public void placePiece(HantoPieceType type) {
        switch (type) {
            case BUTTERFLY:
                butterfly = (butterfly > 0) ? butterfly - 1 : 0;
                break;
            case CRAB:
                crab = (crab > 0) ? crab - 1 : 0;
                break;
            case CRANE:
                crane = (crane > 0) ? crane - 1 : 0;
                break;
            case DOVE:
                dove = (dove > 0) ? dove - 1 : 0;
                break;
            case HORSE:
                horse = (horse > 0) ? horse - 1 : 0;
                break;
            case SPARROW:
                sparrow = (sparrow > 0) ? sparrow - 1 : 0;
                break;
            default:
                break;
        }
    }
}
