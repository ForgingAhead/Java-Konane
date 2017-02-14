package ie.ul.konane;

import java.lang.String;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * This is the abstract superclass for all Player types.
 * 
 * Any and all player implementations should extend this class.
 */

public abstract class Player {
	public String name = "Player";
	public int wins = 0;
	public char colour ='.';
	
	/**
	 * Blank class constructor
	 *
	 */
	public Player() {
		name = "Player";
		wins = 0;
		colour = '.';
	}
	
	/**
	 * Class constructor for the abstract class Player
	 * @param pName The name of the player
	 */
	public Player(String pName) {
		name = pName;
		wins = 0;
		colour = '.';
	}
	
	/**
	 * Resets the wins stat back to 0
	 *
	 */
	public void resetWins() {
		wins = 0;
	}
	
	/**
	 * Adds a win to the win stat
	 *
	 */
	public void won(){
		wins +=1;
	}
	
	/**
	 * Abstract method that needs to be extended. 
	 */
	public abstract void initialize(char pColour);

	/**
	 * Abstract method that calculates a valid move.
	 */
	public abstract KonaneMove makeMove(Konane game);
	
	/**
	 * Gets input from the command line as a string
	 * 
	 * @param text A String with the text to be displayed on the command line. 
	 * @return A String with the input from the command line. The String will be blank ("") if there was an error.
	 */
	public String getInput(String text) {
		
		String input = "";
		//	  prompt the user to enter their name
		System.out.print(text + ": ");
		
		//  open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    //  read the username from the command-line; need to use try/catch with the
	    //  readLine() method
	    try {
	    	input = br.readLine();
	    } catch (IOException ioe) {
	    	System.out.println("IO error trying to get input!");
	        input = "";
	    }

	    return input;
		
	}
	
}
