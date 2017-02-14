package ie.ul.konane;

/**
 * This class stores the information on a move made in the
 * game of Konane.
 * It stores the source row and column, and the destination
 * row and column, and provides methods to test for a jump
 * and whether or not it is a special case move.
 *
 */

public class KonaneMove {
    // A KonaneMove object represents a move in the game of Konane.
    // It holds the row and column of the piece that is to be moved
    // and the row and column of the square to which it is to be moved.
    // (This class makes no guarantee that the move is legal.)
  
	private int fromRow, fromCol;  // Position of piece to be moved.
	private int toRow, toCol;      // Square it is to move to.
	private boolean noMove = false;
	
	/**
	 * Empty class constructor
	 * 
	 */
	public KonaneMove() {}
	
	/**
	 * Class constructor. Populates the data
	 * 
	 * @param r1 An int that is the row of the source of the move
	 * @param c1 An int that is the column of the source of the move
	 * @param r2 An int that is the row of the destination of the move
	 * @param c2 An int that is the column of the destination of the move
	 */
	public KonaneMove(int r1, int c1, int r2, int c2) {
		// Constructor.  Just set the values of the instance variables.
		fromRow = r1;
		fromCol = c1;
		toRow = r2;
		toCol = c2;
	}
	
	/**
	 * Copy constructor. Populates the data
	 * 
	 * @param c A KonaneMove operator to be copied
	 */
	public KonaneMove(KonaneMove c) {
		// Constructor.  Just set the values of the instance variables.
		fromRow = c.sourceRow();
		fromCol = c.sourceCol();
		toRow = c.destinationRow();
		toCol = c.destinationCol();
	}
	
	/**
	 * Class constructor for the special case first moves.
	 * Only requires a row and column co-ordinate, and does
	 * not check that the move is correct.
	 * 
	 * @param r An int that is the row of the source of the move
	 * @param c An int that is the column of the source of the move
	 */
	public KonaneMove(int r, int c) {
		// constructor for special moves. Sets from and to
		// variables to the same
		fromRow = r;
		toRow = r;
		fromCol = c;
		toCol = c;
		
	}
	
	// The next four methods expose the source / destination
	// co-ordinates
	/**
	 * Returns the row of the source of the move
	 * @return An int that is the row of the source of the move
	 */
	public int sourceRow() {return fromRow;}
	/**
	 * Returns the column of the source of the move
	 * @return An int that is the column of the source of the move
	 */
	public int sourceCol() {return fromCol;}
	/**
	 * Returns the row of the destination of the move
	 * @return An int that is the row of the destination of the move
	 */
	public int destinationRow() {return toRow;}
	/**
	 * Returns the column of the destination of the move
	 * @return An int that is the column of the destination of the move
	 */
	public int destinationCol() {return toCol;}
	
	/**
	 * Tests whether the specified move is a special
	 * case (i.e. first) move
	 * 
	 * @return 'True' if it is a special move <BR>
	 * 			'False' if it is not a special move
	 */
	public boolean isSpecialMove() {
		// checks if this is a special case move
		if (fromRow == toRow && fromCol == toCol)
			return true;
		else
			return false;
	}
	
	/**
	 * Tests whether the specified move is a jump
	 * 
	 * @return 'True' if the move is a jump <BR>
	 * 			'False' if the move is not a jump
	 */
	public boolean isJump() {
	      // Test whether this move is a jump.  It is assumed that
	      // the move is legal.  In a jump, the piece moves two or more
		  // rows.  (In a regular move, it only moves one row.)
		  return (fromRow - toRow % 2 == 0);
	}
	
	/**
	 * Sets a flag indicating there are no more moves.
	 *
	 */
	public void lostGame() {
		noMove = true;
	}
	
	/**
	 * Returns the no more moves flag
	 * @return 'True' if there are no more moves <BR>
	 * 			'False' otherwise
	 */
	public boolean noMoves() {
		return noMove;
	}
	
} // end class KonaneMove.