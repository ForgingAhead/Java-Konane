package ie.ul.konane;

/**
 * Custom exception class that descends from Java's Exception class.
 * 
 * Error number and their meanings as follows
 * <BR><BR>
 * 0.	No exception <BR>
 * 1.	Origin must contain the player <BR>
 * 2.	Target must be blank (i.e. no player occupying the target)<BR>
 * 3.	The location specified is not within the bounds of the board<BR>
 * 4.	--<BR>
 * 5.	Invalid board size (needs to be between 4x4 and 8x8)<BR>
 * 6.	Invalid second move
 * 7.	Invalid first move
 * 8.	Target is not an opponent<BR>
 */
public class KonaneException extends Exception
{
	private static final long serialVersionUID = 3129030122675547055L;
	String errorMessage;
	int errorNum;

	//Default constructor - initializes instance variable to unknown
	public KonaneException()
	{
		super();             // call superclass constructor
		errorMessage = "unknown";
		errorNum = 0;
	}

	//	Constructor receives some kind of message that is saved in an instance variable.
	public KonaneException(int eNum, String err)
	{
		super(err);     // call super class constructor
 		errorMessage = err;  // save message
 		errorNum = eNum;
	}
  
	//public method, callable by exception catcher. It returns the error message.
	public String getError()
	{
		return errorMessage;
	}
	// public method that returns the error number. Easier to
	// use in code than a long string
	public int getErrorNumber() {
		return errorNum;
	}
}