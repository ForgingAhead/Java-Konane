package ie.ul.konane;

import java.lang.Character;
import java.lang.Math;
import java.lang.String;
import java.util.ArrayList;

/**
 * 	With thanks to Dr Lisa Meeden, this class implements Konane.
 * <BR>
 *	Konane is the Hawaiian version of checkers.
 *	The board is represented as a two-dimensional list. Each
 *	location on the board contains one of the following symbols:
 *	<UL>
 *		<LI>'<code>b</code>' for a black piece
 *		<LI>'<code>w</code>' for a white piece
 *		<LI>'<code>.</code>' for an empty location
 *	</ul>
 *	The black player always goes first. The opening moves by both
 *	players are special cases and involve removing one piece from
 *	specific designated locations. Subsequently, each move is a
 *	jump over one of the opponent's pieces into an empty location.
 *	The jump may continue in the same direction, if appropriate.
 *	The jumped pieces are removed, and then it is the opponent's
 *	turn.  Play continues until one player has no possible moves,
 *	making the other player the winner. 
 *	
 */
public class Konane {
	
	private int iSize;
	private char cBoard[][];
	public static String newline = System.getProperty("line.separator");
	
	/**
	 * Blank class constructor.
	 *
	 */
	public Konane() {}
	
	/**
	 * 
	 * Class constructor
	 *
	 * @param n	An int that determines the size of the board.
	 * 			The board is (n x n). n must be between 4 and 8
	 * @throws KonaneException
	 */
	public Konane(int n) throws KonaneException {
		if (n > 8 || n < 4) {
			// Insert error handling here
			throw new KonaneException(5, "Invalid board size selected");
		}
		// Set the size of the board
		iSize = n;
		// reset the board
		reset();
	} // end of constructor
	
	/**
	 * Class constructor allowing the addition of a board. This special
	 * constructor allows the board state to be loaded as-is. There are
	 * no players attached, so lists of moves can be generated from the
	 * current board state.
	 *
	 * @param n	An int that determines the size of the board.
	 * 			The board is (n x n). n must be between 4 and 8
	 * @param workingBoard A 2 Dimensional array of chars describing a board
	 * @throws KonaneException
	 */
	public Konane(int n, char[][] workingBoard) throws KonaneException {
		if (n > 8 || n < 4) {
			// Insert error handling here
			throw new KonaneException(5, "Invalid board size selected");
		}
		// Set the size of the board
		iSize = n;
		cBoard = workingBoard;
		// reset the board
		// reset();
	} // end of constructor
	
	/**
	 * Copy Constructor takes an object of type Konane and makes a copy
	 * 
	 * @param k An object of type Konane to be copied.
	 */
	public Konane(Konane k) {
		cBoard = k.getBoardCopy();
		iSize = k.boardSize();
	}
	
	/**
	 * This resets the board using a different size than the last game
	 * 
	 * @param n An int that determines the size of the board.
	 * 			The board is (n x n). n must be between 4 and 8
	 * @throws KonaneException
	 */
	public void reset(int n) throws KonaneException {
		if (n > 8 || n < 4) {
			// Insert error handling here
			throw new KonaneException(5, "Invalid board size selected");
		}
		// Set the size of the board
		iSize = n;
		// reset the board
		reset();
	} // end of reset(int n)
	
	/**
	 * This resets the board to its start state
	 *
	 * @throws KonaneException
	 */
	public void reset() throws KonaneException {
		if (iSize > 8 || iSize < 4) {
			// Insert error handling here
			throw new KonaneException (5, "Invalid board size");
		}
		// Set the board to null before initializing it
		cBoard = null;
		// Initialize the board to the specified size
		cBoard = new char[iSize][iSize];
		// Define two counters and set them to zero
		int r, c;
		r = c = 0;
		
		// iterate on the vertical (rows)
		for (r = 0; r < iSize; r++) {
			
			// iterate on the horizontal (columns)
			for (c = 0; c < iSize; c++) {
				// set out the board with alternating
				// black and white squares
				if (r % 2 == 0) {
					if (c % 2 == 0)
						cBoard[r][c] = 'b';
					else
						cBoard[r][c] = 'w';
				} else {
					if (c % 2 == 1)
						cBoard[r][c] = 'b';
					else
						cBoard[r][c] = 'w';
				}
			}
		}
	} // End of reset
	
	/**
	 * This returns a char representing the occupant of the specified
	 * position on the board.
	 * 
	 * @param r An int specifying the row to return the value from
	 * @param c	An int specifying the column to return the value from
	 * @return 	A char that conforms to the following
	 * 			'<code>b</code>' if the position is occupied by the Black player<BR>
	 * 		 	'<code>w</code>' if the position is occupied by the White player<BR>
	 * 			'<code>.</code>' if the position is unoccupied<BR>
	 * 
	 */
	public char positionOccupant(int r, int c) {
		// return the specified value
		return cBoard[r][c];
		
	} // End of Position Occupant
	
	/**
	 * This checks whether or not the specified position is within the bounds
	 * of the board as specified in the initialization
	 * 
	 * @param c An int specifying the column that the position being checked is on
	 * @param r An int specifying the row that the position being checked is on
	 * @return 	'<code>True</code>' if the position is within the bounds of the board <BR>
	 * 			'<code>False</code>' if the position is outside the bounds of the board<BR>
	 */
	private boolean validPosition(int r, int c){
		
		boolean valid = true;
		// If x or y is less than 0 or greater than or equal to iSize
		// then it's invalid
		if ((c < 0 || c >= iSize) || (r < 0 || r >= iSize))
			valid = false;
		
		return valid;
	} // End of ValidPosition
	
	/**
	 * This returns wheter the specified symbol occupies the specified square
	 * 
	 * @param r An int specifying the row to check for the specified symbol
	 * @param c An int specifying the column to check for the specified symbol
	 * @param symbol A char representing a symbol to check for <BR>
	 * <UL>
	 * 	<LI>'<code>b</code>' for a black piece
	 * 	<LI>'<code>w</code>' for a white piece
	 * 	<LI>'<code>.</code>' for an empty location
	 * </ul>
	 * @return 	'<code>True</code>' if the specified symbol is in the specified location <BR>
	 * 			'<code>False</code>' if the specified symbol is not in the specified location<BR>
	 */
	private boolean contains(int r, int c, char symbol){
		
		boolean bContains = false;
		
		// If the position is valid
		if (validPosition(r, c)) {
			// if the location on the board matches the symbol
			// Contains is true.
			//System.out.println("Row: " + r + ", Col: "+ c + ", symbol: " + symbol);
			if (cBoard[r][c] == Character.toLowerCase(symbol))
				bContains = true;
		}	
		return bContains;
	} // End of contains
	
	/**
	 * This returns wheter the specified symbol occupies the specified square
	 * 
	 * @param r An int specifying the row to check for the specified symbol
	 * @param c An int specifying the column to check for the specified symbol
	 * @param symbol A char representing a symbol to check for <BR>
	 * <UL>
	 * 	<LI>'<code>b</code>' for a black piece
	 * 	<LI>'<code>w</code>' for a white piece
	 * 	<LI>'<code>.</code>' for an empty location
	 * </ul>
	 * @param workingBoard A 2 dimensional array of type char that describes a board
	 * @return 	'<code>True</code>' if the specified symbol is in the specified location <BR>
	 * 			'<code>False</code>' if the specified symbol is not in the specified location<BR>
	 */
	public boolean contains(int r, int c, char symbol, char[][] workingBoard){
		
		boolean bContains = false;
		
		// If the position is valid
		if (validPosition(r, c)) {
			// if the location on the board matches the symbol
			// Contains is true.
			//System.out.println("Row: " + r + ", Col: "+ c + ", symbol: " + symbol);
			if (workingBoard[r][c] == Character.toLowerCase(symbol))
				bContains = true;
		}	
		return bContains;
	} // End of contains
	
	/**
	 * Counts the number of times the specified symbol occurs.
	 * Can be used as a score counter
	 * 
	 * @param symbol A char that specifies the symbol to search for
	 * @return An int with the number of occurences of the symbol
	 */
	public int countSymbol(char symbol) {
		
		// convert to lower case if upper case passed
		if (Character.isUpperCase(symbol)) {
			symbol = Character.toLowerCase(symbol);
		}
		// set the count to zero
		int count = 0;
		// check all squares and count the symbol
		for (int c = 0 ; c < iSize; c++) {
			for (int r = 0; r < iSize; r++) {
				if (cBoard[c][r] == symbol) {
					count++;
				}
			}
		}
		// return the count
		return count;
	} // end of countSymbol class
	
	/**
	 * Returns the opponent of the specified player
	 * 
	 * @param player A char describing the current player. Valid chars are 'b' or 'w'
	 * @return 'b' if player is 'w', or 'w' if player is 'b'
	 */
	public char opponent(char player) {
		if (player == 'b')
			return 'w';
		else
			return 'b';
	}// end of opponent
	
	/**
	 * Gives the distance between two points in a straight line
	 * 
	 * @param r1 An int giving the row of the origin
	 * @param c1 An int giving the column of the origin
	 * @param r2 An int giving the column of the destination
	 * @param c2 An int giving the column of the destination
	 * @return An int giving the distance between two points
	 */
	private int distance(int r1, int c1, int r2, int c2) {
		// return the distance between them as a positive int
		// Horizontal, diagonal or vertical straight lines only!
		return Math.abs(((r2 - r1) + (c2 - c1)));
		
	} // End of distance
	
	/**
	 * This makes a copy of the current board, tries the move
	 * on it, and if the move is valid, copies the board back
	 * over the game-board
	 * 
	 * @param player A char noting the player making the move
	 * @param m A KonaneMove containing the details of the move
	 * @throws KonaneException
	 */
	public void makeMove(char player, KonaneMove m) throws KonaneException {
		// just act as wrapper for the move
		makeMove(player,
				m.sourceRow(), m.sourceCol(),
				m.destinationRow(), m.destinationCol());
	}
	
	/**
	 * This makes a copy of the current board, tries the move
	 * on it, and if the move is valid, copies the board back
	 * over the game-board 
	 * 
	 * @param player A char noting the player making the move
	 * @param c1 An int giving the column of the origin
	 * @param r1 An int giving the row of the origin
	 * @param c2 An int giving the column of the destination 
	 * @param r2 An int giving the column of the destination
	 * @throws KonaneException
	 */
	public void makeMove(char player, int r1, int c1, int r2, int c2) throws KonaneException 
	
	{
		
		// System.out.println("R: " + r1 + "; C: " + c1 + "; R2: " + r2 + "; C2: " + c2);
		// if the next move is valid, replace the board
		// with the one used in the next move method.
		// otherwise, leave it as it is!		
		char newBoard[][] = new char[iSize][iSize];
		
		// replicate the board in a new array. Must be done
		// because arrayClone does not deep copy multi-
		// dimensional arrays
		for (int i = 0; i < iSize; i++)
			System.arraycopy(cBoard[i], 0, newBoard[i], 0, iSize);
		
		//System.out.println("New board");
		//boardToString(newBoard);
		
		// if the origin or destination is invalid
		if (!validPosition(r1, c1) && !validPosition(r2, c2)) {
			// Error handling here
			throw new KonaneException(3, "Invalid board location specified");
		}
		// If the origin does not contain the player
		if (!contains(r1, c1, player)) {
			// Error handling here
			throw new KonaneException(1, "Origin must contain player");
		}
		
		// Actual game play here
		int dist = distance(r1, c1, r2, c2);
		
		// System.out.println("got Distance: " + dist + "; location: " + newBoard[r2][c2]);
		
		if (dist == 0){
			// check for opening move
			if (openingMove()) 
				// remove the specified token
				newBoard[r1][c1] = '.';
		// If the move isn't going to a blank square
		} else if (newBoard[r2][c2] != '.') {
			throw new KonaneException(2, "Target must be blank");
		// otherwise continue
		} else {
			int jumps = dist/2;
			int dr, dc;
			dr = (r2-r1)/dist;
			dc = (c2-c1)/dist;
			//System.out.println("Jumps: " + jumps + "; dr: " + dr + "; dc: "+ dc + "; dist: "+ dist);
			// for each jump
			for (int i = 0; i < jumps; i++) {
				//System.out.println("Jumps: " + i);
				// if the next jump isn't an opponent
				//if (newBoard[r1 + dr][c1 + dc] != opponent(player)) {
				//	throw new KonaneException(8, "Target is not an opponent");
				//}
				// otherwise
				newBoard[r1][c1] = '.';
				newBoard[r1 + dr][c1 + dc] = '.';
				r1 += 2 * dr;
				c1 += 2 * dc;
				newBoard[r1][c1] = player;
			}
		
		} // End of move logic
		
		// System.out.println("New board after move");
		// boardToString(newBoard);
		
		// Copy new board back
		for (int i = 0; i < iSize; i++)
			System.arraycopy(newBoard[i], 0, cBoard[i], 0, iSize);
		
		// boardToString();
		
		// blank newBoard before leaving
		newBoard = null;
		
	}// End of makeMove
	
	/**
	 * Checks whether the current move is the opening move
	 * 
	 * @return 	'<code>True</code>' if the opening moves have not yet been made <BR>
	 * 		  	'<code>False</code>' if the opening moves have been made.
	 */
	public boolean openingMove() {
		
		return (countSymbol('.') <= 1);
		
	} // End of openingMove
	
	/**
	 * This returns a string that describes the board
	 * @return A string that describes the board
	 */
	public String boardToString() {
		
		String result = new String();
		
		// initialise as  two spaces
		result = "  ";
		
		// Now print out the numbers on the top row
		for (int i=0; i < iSize; i++) {
			result += Integer.toString(i) + " ";
		}
		result += newline;
		// Now, print out the current state of the table
		for (int y = 0; y < iSize; y++) {
			result += Integer.toString(y) + " ";
			for (int x = 0; x < iSize; x++) {
				result += Character.toString(cBoard[y][x]) + " ";
			}
			// insert a newline
			result += newline;
		}
		
		return result;
	}
	
	/**
	 * This returns a string that describes a board
	 * @param board A 2 dimensional array of chars describing the board
	 * @return A string that describes the board
	 */
	static public String boardToString(char board[][], int size) {
		
		String result = new String();
		
		// initialise as  two spaces
		result = "  ";
		
		// Now print out the numbers on the top row
		for (int i=0; i < size; i++) {
			result += Integer.toString(i) + " ";
		}
		result += newline;
		// Now, print out the current state of the table
		for (int y = 0; y < size; y++) {
			result += Integer.toString(y) + " ";
			for (int x = 0; x < size; x++) {
				result += Character.toString(board[y][x]) + " ";
			}
			// insert a newline
			result += newline;
		}
		
		return result;
	}
	
	/**
	 * This function generates the first move in the game and stores them in an
	 * ArrayList.
	 * 
	 * @param size An int that describes the size of the board
	 * @return An ArrayList of KonaneMove objects
	 */
	private ArrayList<KonaneMove> generateFirstMoves(int size) {

		ArrayList<KonaneMove> firstMoves = new ArrayList<KonaneMove>();
		// get the moves, put them in an ArrayList
		// Each element is an integer array of size 2
		firstMoves.add(new KonaneMove(0,0));
		firstMoves.add(new KonaneMove((size - 1), (size - 1)));
		firstMoves.add(new KonaneMove((size/2), (size/2)));
		firstMoves.add(new KonaneMove((size/2 - 1), (size/2 - 1)));
		
		return firstMoves;
		
	} // end of generateFirstMoves
	
	/**
	 *  This generates the correct second moves based on the first move specified,
	 *  and stores them in an ArrayList.
	 * 
	 * @param workingBoard A 2 dimensional array of type char describing a board
	 * @return An ArrayList of KonaneMove objects
	 */
	private ArrayList<KonaneMove> generateSecondMoves(char[][] workingBoard) {
		
		int wPos;
		ArrayList<KonaneMove> secondMoves = new ArrayList<KonaneMove>();
	
		// If the first move was [0][0]
		if (workingBoard[0][0] == '.') {
			// The two moves are 0,1 or 1,0
			secondMoves.add(new KonaneMove(0, 1));
			secondMoves.add(new KonaneMove(1, 0));
		} else if (workingBoard[iSize - 1][iSize - 1] == '.'){
			// Otherwise if it's the far corner
			// the valid moves will be Size - 1, Size - 2
			// or Size - 2, Size - 1
			secondMoves.add(new KonaneMove(iSize - 1, iSize - 2));
			secondMoves.add(new KonaneMove(iSize - 2, iSize - 1));
		} else if (workingBoard[(iSize / 2) - 1][(iSize / 2) - 1] == '.'){
			// otherwise the moves will be the white centres
			wPos = (iSize / 2) - 1;
			// this works out as the white centre locations
			// as calculated below
			secondMoves.add(new KonaneMove(wPos, wPos + 1));
			secondMoves.add(new KonaneMove(wPos + 1, wPos));
			secondMoves.add(new KonaneMove(wPos - 1, wPos));
			secondMoves.add(new KonaneMove(wPos, wPos - 1));			
		} else {
			// otherwise the moves will be the white centres
			wPos = (iSize / 2);
			// this works out as the white centre locations
			// as calculated below
			secondMoves.add(new KonaneMove(wPos, wPos + 1));
			secondMoves.add(new KonaneMove(wPos + 1, wPos));
			secondMoves.add(new KonaneMove(wPos - 1, wPos));
			secondMoves.add(new KonaneMove(wPos, wPos - 1));
		}
		return secondMoves;
	} // End of generateSecondMoves
	
	/**
	 * Checks whether a jump is possible starting at (r,c) and going in the
	 * direction determined by the row delta, rd, and the column delta, cd.
	 * The factor is used to recursively check for multiple jumps in the same
	 * direction.  Returns all possible jumps in the given direction in an ArrayList
	 * 
	 * @param r An int describing the row of the origin of the move
	 * @param c An int describing the column of the origin of the move
	 * @param dr An int describing the delta of the row
	 * @param dc An int describing the delta of the column
	 * @param factor An int which is the factor by which the move should be multiplied
	 * @param opponent A char describing the opponent
	 * @return An ArrayList of KonaneMove objects.
	 */
	private ArrayList<KonaneMove> check(int r, int c, int dr, int dc, int factor, char opponent) {
		
		ArrayList<KonaneMove> moves = new ArrayList<KonaneMove>();
		
		//System.out.println("Debug: In check();" + r + ", " + c + "; " + dr + ", " + dc + "; f: " + factor + "; opp: " + opponent);
		
		if (contains( (r + (factor * dr)), (c + (factor * dc)),  opponent)&& 
			contains( (r + ((factor + 1) * dr)), (c + ((factor + 1) * dc)), '.')) {
			
				//System.out.println("Debug: In check();" + r + ", " + c + "; " + dr + ", " + dc + "; f: " + factor + "; opp: " + opponent);
				//System.out.println("Inside loop!");
			
				// if the move is valid, add it
				moves.add(new KonaneMove(r, c, (r + ((factor + 1) * dr)), (c + ((factor + 1)* dc))));
				// check if there's a further jump
				moves.addAll(check(r, c, dr, dc, factor + 2, opponent));
				return moves;
		} else {
			//System.out.println("Size of array: " + moves.size());
			return moves;
			
		}
		
	} // End of check
	
	/**
	 * This generates a list of all possible moves from the current position
	 * 
	 * @param player A char, either 'b' or 'w' to identify the player taking the move
	 * @return An ArrayList of type KonaneMove with the list of all possible
	 * 			moves
	 */
	public ArrayList<KonaneMove> generateMoves(char player) {
		
		// If its the first move, then
		// just return the pre-specified moves
		if (openingMove()) {
			if (player == 'b')
				return generateFirstMoves(iSize);
			else
				return generateSecondMoves(cBoard);
		}
		
		ArrayList<KonaneMove> moves = new ArrayList<KonaneMove>();
		
		// These are the integer arrays specifying direction
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		for (int r = 0; r < iSize; r++) {
			for (int c = 0; c < iSize; c++) {
				if (cBoard[r][c] == player) {					
					for (int i = 0; i < 4; i++){						
						moves.addAll(check(r, c, dr[i], dc[i], 1, opponent(player)));
					}
				}
			}
		}
		//System.out.println("Player " + player + "; moves available: " + moves.size() + newline);
		// some housekeeping. May not be necessary, but you never know
		while (moves.lastIndexOf(null) != -1)
			moves.remove(moves.lastIndexOf(null));
		
		return moves;
		
	} // end of generateMoves
	
	/**
	 * This creates a copy of the current state of the board
	 * and returns it as a 2 dimensional array of type int[row][col].
	 * Useful for manipulation of the board and checking of possibilities
	 * 
	 * @return A 2 dimensional array of type char[row][col]
	 */
	public char[][] getBoardCopy() {
		
		// copy of the board state
		char newBoard[][] = new char[iSize][iSize];
		
		// replicate the board in a new array. Must be done
		// because arrayClone does not deep copy multi-
		// dimensional arrays
		for (int i = 0; i < iSize; i++)
			System.arraycopy(cBoard[i], 0, newBoard[i], 0, iSize);
		
		return newBoard;
	} // end of getBoardCopy
	
	/**
	 * Plays one game of Konane with the specified players
	 * 
	 * @param p1 A Player object that will be player 1
	 * @param p2 A Player object that will be player 2
	 * @param show A boolean switch allowing the game to be shown visually
	 * @return A char that names the winner. 'b' for black win, 'w' for white win.
	 */
	public char playOneGame(Player p1, Player p2, boolean show) {
		try {
			this.reset();
		} catch (KonaneException e) {
			//this should never occur, but, y'know ;)
			return 'b';
		}
		
		p1.initialize('b');
		p2.initialize('w');
		char result = '.';
		char currentMove = 'b';
		
		while (true) {
			
			KonaneMove move;
			
			if (currentMove != 'w') {
				
				if (show) {
					// Show the board
					System.out.println(boardToString());
					System.out.println(p1.name + "'s (Black) move.");				
				}
				move = p1.makeMove(this);
				if (move.noMoves()) {
					result = 'w';
					break;
				}
				try {
					// Make the move
					makeMove(p1.colour, move.sourceRow(), move.sourceCol(), move.destinationRow(), move.destinationCol());
				} catch (KonaneException e) {
					if (show)
						System.out.println(e.errorMessage);
					// skips to the while loop again
					// while showing the error.
					show = true;
					currentMove = 'b';
					continue;
				}
			
				if (show) {
					// Show the board
					System.out.println(newline);
					String moveString = Integer.toString(move.sourceRow()) + ", " + 
										Integer.toString(move.sourceCol()) + " -> " + 
										Integer.toString(move.destinationRow()) + ", " + 
										Integer.toString(move.destinationCol());
					// Print the move
					System.out.println(moveString);
					System.out.println(newline);
					// print the board.
					System.out.println(boardToString());
				}
				currentMove = 'w';
			}
			if (currentMove != 'b') {
				if (show) {
					System.out.println(p2.name + "'s (White) move.");				
				}
				currentMove = 'w';
				move = p2.makeMove(this);
				if (move.noMoves()) {
					result = 'b';
					break;
				}
				try {
					// Make the move
					makeMove(p2.colour, move.sourceRow(), move.sourceCol(), move.destinationRow(), move.destinationCol());
				} catch (KonaneException e) {
					if (show)
						System.out.println(e.errorMessage);
					// skips to the while loop again
					// while showing the error.
					show = true;
					currentMove = 'w';
					continue;
				}
				if (show) {
					// Show the board
					System.out.println(newline);
					String moveString = Integer.toString(move.sourceRow()) + ", " + 
										Integer.toString(move.sourceCol()) + " -> " + 
										Integer.toString(move.destinationRow()) + ", " + 
										Integer.toString(move.destinationCol());
					// Print the move
					System.out.println(moveString);
					System.out.println(newline);
					// print the board.
					System.out.println(boardToString());
				}
				currentMove = 'b';
			}
			
		}
		if (show) {
			System.out.println(newline);
			System.out.println("Game Over.");
			System.out.println(newline);
			if (result == 'b') {
			 	System.out.println(p1.name + " is the winner!");
			} else {
				System.out.println(p2.name + " is the winner!");
			}
			System.out.println(newline);
		}
		
		return result;
	} // end of playOneGame
	
	/**
	 * Plays n games of Konane with the specified players
	 * 
	 * @param p1 A Player object that will be player 1
	 * @param p2 A Player object that will be player 2
	 * @param n An int with the number of games to be played
	 * @param show A boolean switch allowing the game to be shown visually
	 */
	public void playNGames(Player p1, Player p2, int n, boolean show) {
		
		p1.resetWins();
		p2.resetWins();
		
		char winner;
		
		// for each of the number of games specified
		for (int i = 0; i < Math.abs(n); i++) {
			System.out.println("Game " + Integer.toString(i + 1));
			System.out.println(newline);
			if (i % 2 == 0) {
				winner = playOneGame(p1, p2, show);
				if (winner == 'b') {
					p1.won();
				} else {
					p2.won();
				}					
			} else {
				winner = playOneGame(p2, p1, show);
				if (winner == 'b') {
					p2.won();
				} else {
					p1.won();
				}
			}
		}
		System.out.println(newline);
		if (p1.wins > p2.wins) {
			System.out.println(p1.name + " wins with " + Integer.toString(p1.wins) + " wins");
			System.out.println(p2.name + " loses with " + Integer.toString(p2.wins) + " wins");
		} else if (p2.wins > p1.wins) {
			System.out.println(p2.name + " wins with " + Integer.toString(p2.wins) + " wins");
			System.out.println(p1.name + " loses with " + Integer.toString(p1.wins) + " wins");
		} else if(p1.wins == p2.wins) {
			System.out.println("A draw with " + Integer.toString(p1.wins) + " wins each!");
		}
		System.out.println(newline);
	}
	
	//Properties
	/**
	 * Returns the size of the board as an int
	 */
	public int boardSize() { return iSize;}
	
} // End of Konane Class
