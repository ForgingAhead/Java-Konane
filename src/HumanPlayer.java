import ie.ul.konane.Konane;
import ie.ul.konane.KonaneMove;
import ie.ul.konane.Player;
import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;

	/**
	 * This extends the abstract player class defined earlier. It is
	 * an implementation that prompts a human player for a move. 
	 *
	 */
	public class HumanPlayer extends Player {
		
		/**
		 * Blank class constructor
		 *
		 */
		public HumanPlayer() {}
		
		/**
		 * Constructor that uses the name from the superclass
		 * @param pName A String with the name of the player
		 */
		public HumanPlayer(String pName) {super(pName);}
		
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
			
			boolean validInput = false;
			KonaneMove humanMove = null;
			
			// repeat until the player enters a valid move
			while (!validInput) {
				String sInput = getInput("Enter r1, c1, r2, c2 (or -1 to concede)");		
				
				// If the game is conceded
				if (sInput.indexOf("-1") != -1) {
					KonaneMove gameOver = new KonaneMove(0, 0);
					gameOver.lostGame();
					return gameOver;
				}
				
				// otherwise
				String[] splitInput = sInput.split(",");
				
				// using try / catch to deal with invalid input exceptions
				try {
					if (game.openingMove()) {				
						Integer ir1 = new Integer(splitInput[0].trim());
						Integer ic1 = new Integer(splitInput[1].trim());
						
						humanMove = new KonaneMove(ir1.intValue(),
																ic1.intValue(),
																ir1.intValue(),
																ic1.intValue());
							
					} else {
						Integer ir1 = new Integer(splitInput[0].trim());
						Integer ic1 = new Integer(splitInput[1].trim());
						Integer ir2 = new Integer(splitInput[2].trim());
						Integer ic2 = new Integer(splitInput[3].trim());
						
						humanMove = new KonaneMove(ir1.intValue(),
															ic1.intValue(),
															ir2.intValue(),
															ic2.intValue());
						
					}
					
					// if we reach here the player has entered valid input
					// we now need to check if this is a valid move
					if (validHumanMove(humanMove, possibleMoves))	
						validInput = true;
					else
						System.out.println("Invalid Move!");
				} catch (NumberFormatException e) {
					System.out.println("Invalid Input!");
				}
			};
			
			return humanMove;
		}
		
		// compare a move to the list of valid moves
		// return true if the move is a valid move, false otherwise
		public boolean validHumanMove(KonaneMove myMove, ArrayList<KonaneMove> possMoves) {
			Iterator<KonaneMove> itr = possMoves.iterator();
			
			while (itr.hasNext()){
		    	KonaneMove validMove = itr.next();

				if ((myMove.sourceRow() == validMove.sourceRow()) &&
					(myMove.sourceCol() == validMove.sourceCol()) &&
					(myMove.destinationRow() == validMove.destinationRow()) &&
					(myMove.destinationCol() == validMove.destinationCol()))
					return true;
		    }
			
			return false;
		}
	}