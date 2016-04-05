/**
 * This class was created for the Hanto game implementation for CS 4233.
 */

package hanto.studentanivarthi.common.piecemanager;

import hanto.common.HantoPieceType;

/**
 * The implementation of the {@link HantoPlayerPieceManager} class to track
 * player pieces remaining.
 *
 * @author Aditya Nivarthi
 */
public class HantoPlayerPieceManagerImpl implements HantoPlayerPieceManager {
    private int butterfly;
    private int crab;
    private int crane;
    private int dove;
    private int horse;
    private int sparrow;

    /**
     * Creates a HantoPlayerPieceManagerImpl instance with the specified counts
     * of each piece type.
     *
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
    public HantoPlayerPieceManagerImpl(int butterfly, int crab, int crane, int dove, int horse,
            int sparrow) {
        this.butterfly = butterfly;
        this.crab = crab;
        this.crane = crane;
        this.dove = dove;
        this.horse = horse;
        this.sparrow = sparrow;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager#canPlacePiece()}
     */
    @Override
    public boolean canPlacePiece(HantoPieceType pieceType) {
        switch (pieceType) {
            case BUTTERFLY:
                return butterfly > 0;
            case CRAB:
                return crab > 0;
            case CRANE:
                return crane > 0;
            case DOVE:
                return dove > 0;
            case HORSE:
                return horse > 0;
            case SPARROW:
                return sparrow > 0;
            default:
                return false;
        }
    }

    /**
     * @see {@link hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager#isOutOfPieces()}
     */
    @Override
    public boolean isOutOfPieces() {
        return butterfly <= 0 && crab <= 0 && crane <= 0 && dove <= 0 && horse <= 0 && sparrow <= 0;
    }

    /**
     * @see {@link hanto.studentanivarthi.common.piecemanager.HantoPlayerPieceManager#placePiece()}
     */
    @Override
    public void placePiece(HantoPieceType pieceType) {
        switch (pieceType) {
            case BUTTERFLY:
                butterfly = butterfly > 0 ? butterfly - 1 : 0;
                break;
            case CRAB:
                crab = crab > 0 ? crab - 1 : 0;
                break;
            case CRANE:
                crane = crane > 0 ? crane - 1 : 0;
                break;
            case DOVE:
                dove = dove > 0 ? dove - 1 : 0;
                break;
            case HORSE:
                horse = horse > 0 ? horse - 1 : 0;
                break;
            case SPARROW:
                sparrow = sparrow > 0 ? sparrow - 1 : 0;
                break;
            default:
                break;
        }
    }
}
