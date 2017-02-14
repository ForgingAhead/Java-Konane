import ie.ul.konane.Konane;
import ie.ul.konane.KonaneException;
import ie.ul.konane.KonaneMove;
import ie.ul.konane.Player;
//import java.lang.String;
import java.util.ArrayList;

/**
 * This extends the abstract player class defined earlier. It is
 * an implementation using minimax that picks the best move according to the evaluation. 
 *
 */
public class c10086617 extends Player {
	
	/**
	 * Blank class constructor
	 *
	 */
	public c10086617() {}
	
	/**
	 * Constructor that uses the name from the superclass
	 * @param pName A String with the name of the player
	 */
	public c10086617(String pName) {super(pName);}
	
	/**
	 * Extended initialize function. Takes a side
	 * for the player, and sets the name to "10086617".
	 * @param pColour A char that sets the side the player is on. 'b' for black, and 'w' for white
	 */
	public void initialize(char pColour) {
		colour = pColour;
		name = "10086617";
	}
	
	/**
	 * Makes a move. In this minimax implementation, it provides an evaluation function to estimate
	 * every possible move to a certain depth and pick the best move according to minimax strategy.
	 * @param game the current game
	 * @return a move chosen by the player
	 */
	public KonaneMove makeMove(Konane game) {
		// Initialize the array. The arrays are all templates (generics)
		// so for good practice, they are set to their type inside
		// the angled brackets. In this case the type is KonaneMove.
		ArrayList<KonaneMove> possibleMoves = new ArrayList<KonaneMove>();
		// Get the list of possible moves
		possibleMoves = game.generateMoves(colour);
		int best=0;
		// if the list is empty
		if (possibleMoves.size() == 0) {
			KonaneMove gameOver = new KonaneMove(0, 0);
			gameOver.lostGame();
			possibleMoves.add(gameOver);
		} else {
			int ply = 4; // depth of ply used in the minimax algorithm
			best = maxVal(game,ply,ply); // the index of the best move
		}

		/* return the best move estimated by using minimax */
		return possibleMoves.get(best);		
	}	
	
	/**
	 *calculate the maximum utility value that can get after opponent plays
	 * @param game the current konane game
	 * @param cutOff determines whether the minimax can finish
	 * @return the maximum estimated utility value
	 */
	private int maxVal(Konane game, int cutOff, int ply) {
		--cutOff;
		if(cutOff == -1) // already reached to depth "ply"
			return utility(game);
		else {
			ArrayList<KonaneMove> possibleMoves = new ArrayList<KonaneMove>();
			possibleMoves = game.generateMoves(this.colour);
			
			/* if the list is empty, which means it has lost the game and we dont want this, so give
			it a small enough value to avoid player choose this move*/
			if (possibleMoves.size() == 0) {
				return -32768;
			}
			
			int estimatesMax = -32768; // the maximum estimated value
			int index=0; 
			for(int i=0; i<possibleMoves.size(); i++) {
				Konane newGame = new Konane(game); // make a copy of the current game
				try {				
					newGame.makeMove(colour, possibleMoves.get(i));	// apply one move to the newGame				
				} catch(KonaneException e) {
					System.out.println(e.getError());
				}
				int tmp = minVal(newGame,cutOff,ply); //get the estimated value from the lower min level
				if(estimatesMax < tmp) { // find the max value from the min level
					estimatesMax = tmp;
					index = i; // record the index for use of finding the action more easily
				}
			}
			
			if (cutOff == ply-1)// indicates this is the first ply, return the index in order to find the action more quickly.
				return index;
			else return estimatesMax; // return the maximum estimated value
		}
	}
	
	/**
	 * calculate the minimum utility value that can get after opponent plays
	 * @param game the current konane game
	 * @param cutOff determines whether the minmax can finish
	 * @return the minimum estimated utility value
	 */
	private int minVal(Konane game,int cutOff,int ply){
		--cutOff;
		if(cutOff == -1) // already reached to depth "ply"
			return utility(game);
		else {
			ArrayList<KonaneMove> possibleMoves = new ArrayList<KonaneMove>();
			possibleMoves = game.generateMoves(game.opponent(colour));
			
			/* if the list is empty, which means it has lost the game and we dont want this, so give
			it a big enough value to avoid the player choose this move */
			if (possibleMoves.size() == 0) {
				return 32767;
			}
			
			int estimatesMin=32767;
			int index=0;
			for(int i=0; i<possibleMoves.size(); i++) {
				Konane newGame = new Konane(game); //make a copy of the current game
				try {
					newGame.makeMove(newGame.opponent(colour),possibleMoves.get(i));// apply one move to the newGame
				} catch(KonaneException e) {
					System.out.println(e.getError());
				}
				int tmp = maxVal(newGame,cutOff,ply); //get the estimated value from lower max level
				if(estimatesMin > tmp) { // try to find the maximum value
					estimatesMin = tmp;
					index = i; // record the index of the maximum value
				}
			}
			
			if (cutOff ==ply-1) // indicates this is the first ply, return the index in order to find the action more quickly.
				return index;
			else return estimatesMin;
		}
	}
	
	
	/**
	 * the heuristic function to estimate the value of the current game state after apply a certain move
	 * @param game the current game
	 * @return the estimated utility value of the current board
	 */
	private int utility(Konane game) {
		if(game.boardSize() == 7 || game.boardSize() == 8) {
			int mySize = (game.generateMoves(colour)).size();
			int opSize = (game.generateMoves(game.opponent(colour)).size());	
			return 2*mySize - opSize;// the heuristic function to calculate the estimated utility value
		}
		else {
			int myMovablePieces = movablePieces(game,colour); // number of this player's movable pieces on current board of game
			int opMovablePieces = movablePieces(game,game.opponent(colour)); // number of opponent's movable pieces
			return 2*myMovablePieces - opMovablePieces;// the heuristic function to calculate the estimated utility value
		}
	}
	
	/**
	 * calculate the number of movable pieces on current board of the game for the parameter player
	 * @param game the current game
	 * @param player in char type representing which player it is.
	 * @return the number of movable pieces
	 */
	private int movablePieces(Konane game, char player) {
		int num = game.boardSize();		
		char temp[][] = game.getBoardCopy();
		/*declare a bigger board in order to avoid out of array boundary problem when check movable pieces*/
		char board[][] = new char[num+2][num+2]; 
		int i,j;
		
		/* copy the game board to the new board and set other position to '@'*/
		for(i=1; i<num+1; i++)
			for(j=1; j<num+1; j++) {
				board[i][j] = temp[i-1][j-1]; //
			}
		for(i=0; i<num+2; i++) {
			board[0][i] = board[i][0] = board[num+1][i] = board[i][num+1] = '@';
		}
		
		/* These are the integer arrays specifying direction */
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		int count = 0; // count the number of movable pieces
		char opponent = (player=='w')?'b':'w'; // get the opponent of the player
		for(i=1; i<num+1; i++) 
			for(j=1; j<num+1; j++) {
				if(board[i][j] == player) { // the target pieces
					for(int k=0; k<4; k++) { // four possible directions to move	
						/* if the target piece is adjacent to an opponent piece and 
						 * has another empty position next to the opponent in the same direction
						 * then this pieces is movable and continue to count other pieces*/
						if((board[i+dr[k]][j+dc[k]] == opponent)
								&&(board[i+dr[k]+dr[k]][j+dc[k]+dc[k]] == '.')) {
							count++;
							break;
						}		
					}
				}
			}
		return count;
	}
}



