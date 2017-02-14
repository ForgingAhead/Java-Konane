import ie.ul.konane.Konane;
import ie.ul.konane.KonaneException;
import ie.ul.konane.KonaneMove;
import ie.ul.konane.Player;
//import java.lang.String;
import java.util.ArrayList;

/**
 * This extends the abstract player class defined earlier. It is
 * an implementation using minmax that picks the best move according to the evaluation. 
 *
 */
public class c10086617A extends Player {
	
	/**
	 * Blank class constructor
	 *
	 */
	public c10086617A() {}
	
	/**
	 * Constructor that uses the name from the superclass
	 * @param pName A String with the name of the player
	 */
	public c10086617A(String pName) {super(pName);}
	
	/**
	 * Extended initialize function. Takes a side
	 * for the player, and sets the name to "10086617".
	 * @param pColour A char that sets the side the player is on. 'b' for black, and 'w' for white
	 */
	public void initialize(char pColour) {
		colour = pColour;
		name = "10086617AAA";
	}
	
	/**
	 * Makes a move. In this minmax implementation, it provides an evaluation function to estimate
	 * every possible move to a certain depth and pick the best move according to minmax strategy.
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
			int ply = 4; // 
			best = (int)maxVal(game,ply,ply);
		}

		// return the best move estimated by using minmax
		return possibleMoves.get(best);		
	}	
	
	/**
	 * Using a heuristic estimation method to evaluate the value of a move to the current board
	 * the heuristic function is f(i) = 2*h(i) - g(i) where h(i) is the number of possible moves 
	 * for player and g(i) is the number of possible moves for opponent
	 * @param game the current konane game
	 * @param cutOff determines whether the minmax can finish
	 * @return the maximum estimated utility value
	 */
	private float maxVal(Konane game, int cutOff, int ply) {
		--cutOff;
		if(cutOff == -1) 
			return utility(game);
		else {
			ArrayList<KonaneMove> possibleMoves = new ArrayList<KonaneMove>();
			possibleMoves = game.generateMoves(this.colour);
			// if the list is empty
			if (possibleMoves.size() == 0) {
				return -1000;
			}
			float estimatesMax = -1000;
			int index=0;
			for(int i=0; i<possibleMoves.size(); i++) {
				Konane newGame = new Konane(game);
				try {				
					newGame.makeMove(colour, possibleMoves.get(i));	// apply one move to the newGame				
				} catch(KonaneException e) {
					System.out.println(e.getError());
				}
				float tmp = minVal(newGame,cutOff,ply);
				if(estimatesMax < tmp) { // find the max value from the min level
					estimatesMax = tmp;
					index = i; // record the index for use of finding the action more easily
				}
			}
			if (cutOff == ply-1)// indicates this is the first ply, return the index to find the action more quickly.
				return index;
			else return estimatesMax; 
		}
	}
	
	/**
	 * calculate the minimum utility value that can get after opponent plays
	 * @param game the current konane game
	 * @param cutOff determines whether the minmax can finish
	 * @return the minimum estimated utility value
	 */
	private float minVal(Konane game,int cutOff,int ply){
		--cutOff;
		if(cutOff == -1) 
			return utility(game);
		else {
			ArrayList<KonaneMove> possibleMoves = new ArrayList<KonaneMove>();
			possibleMoves = game.generateMoves(game.opponent(colour));
			// if the list is empty
			if (possibleMoves.size() == 0) {
				return 1000;
			}
			float estimatesMin=1000;
			int index=0;
			for(int i=0; i<possibleMoves.size(); i++) {
				Konane newGame = new Konane(game);
				try {
					newGame.makeMove(newGame.opponent(colour),possibleMoves.get(i));
				} catch(KonaneException e) {
					System.out.println(e.getError());
				}
				float tmp = maxVal(newGame,cutOff,ply);
				if(estimatesMin > tmp) {
					estimatesMin = tmp;
					index = i;
				}
			}
			if (cutOff ==ply-1)
				return index;
			else return estimatesMin;
		}
	}
	
	/**
	 * the heuristic function to estimate the value of the current game state after apply a certain move
	 * @param game the current game
	 * @return the estimated utility value of the current board
	 */
	private float utility(Konane game) {
		float mySize = (game.generateMoves(colour)).size();
		float opSize = (game.generateMoves(game.opponent(colour)).size());
		return mySize - opSize;
	}
}



