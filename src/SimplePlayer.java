import ie.ul.konane.Konane;
import ie.ul.konane.KonaneMove;
import ie.ul.konane.Player;

import java.util.ArrayList;

/**
 * This extends the abstract player class defined earlier. It is
 * a simple implementation that picks the first move available. 
 *
 */
public class SimplePlayer extends Player {
	
	/**
	 * Blank class constructor
	 *
	 */
	public SimplePlayer() {}
	
	/**
	 * Constructor that uses the name from the superclass
	 * @param pName A String with the name of the player
	 */
	public SimplePlayer(String pName) {super(pName);}
	
	/**
	 * Extended initialize function. Takes a side
	 * for the player 
	 * @param pColour A char that sets the side the player is on. 'b' for black, and 'w' for white
	 */
	public void initialize(char pColour) {
		colour = pColour;
	}
	
	/**
	 * Makes a move. In this simple implementation, this just picks
	 * from the top of the list
	 */
	public KonaneMove makeMove(Konane game) {
		// Initialize the array. The arrays are all templates (generics)
		// so for good practice, they are set to their type inside
		// the angled brackets. In this case the type is KonaneMove.
		ArrayList<KonaneMove> possibleMoves = new ArrayList<KonaneMove>();
		// Get the list of possible moves
		possibleMoves = game.generateMoves(colour);
		
		// if the list is empty
		if (possibleMoves.size() == 0) {
			KonaneMove gameOver = new KonaneMove(0, 0);
			gameOver.lostGame();
			possibleMoves.add(gameOver);
		}
			
		// return the first move
		return possibleMoves.get(0);		
	}	
}
