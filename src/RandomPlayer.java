	import ie.ul.konane.Konane;
	import ie.ul.konane.KonaneMove;
	import ie.ul.konane.Player;

	import java.util.ArrayList;
	import java.util.Random;

	/**
	 * This extends the abstract player class defined earlier. It is
	 * a simple implementation that picks the first move available. 
	 *
	 */
	public class RandomPlayer extends Player {
		
		/**
		 * Blank class constructor
		 *
		 */
		public RandomPlayer() {}
		
		/**
		 * Constructor that uses the name from the superclass
		 * @param pName A String with the name of the player
		 */
		public RandomPlayer(String pName) {super(pName);}
		
		/**
		 * Extended initialize function. Takes a side
		 * for the player 
		 * @param pColour A char that sets the side the player is on. 'b' for black, and 'w' for white
		 */
		public void initialize(char pColour) {
			colour = pColour;
		}
		
		/**
		 * Makes a move. In this implementation, this just picks
		 * a random move and makes it.
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
				return gameOver;
			}
			
			int iRandom;
			Random randomizer = new Random();
			
			iRandom = Math.abs(randomizer.nextInt());
			iRandom = iRandom % possibleMoves.size();			
			
			// return the random move
			return possibleMoves.get(iRandom);
			
		}
		
	}