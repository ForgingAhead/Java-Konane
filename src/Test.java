import ie.ul.konane.*;
import java.lang.Integer;

public class Test {
	public static String newline = System.getProperty("line.separator");
	public static String tab = "   ";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("Before Parsing Args " + (args.length));
		// Parse the arguments
		ParseArgs arguments = new ParseArgs(args);
		
		
		
		// if help was requested, print help and quit
		if (arguments.help()) {
			printHelp();
			System.exit(0);
		}
		
		Player player1;
		Player player2;
		// If the 1st player was not selected, default to Random		
		if (!arguments.player1Selected()) {
			player1 = new RandomPlayer("Player 1 (Random)");
		} else {
			player1 = arguments.player1();
		}
		// If the 2nd player was not selected, default to Human
		if (!arguments.player2Selected()) {
			player2 = new HumanPlayer("Player 2 (Human)");
		} else {
			player2 = arguments.player2();
		}	
		
		
		try{
			// Set the board size
			Konane newGame = new Konane(arguments.boardSize());
			// Play the game
			newGame.playNGames(player1, 
								player2,
								arguments.numberOfGames(), true);
		} catch (KonaneException e){
			System.out.println(e.getError());
			System.out.println(newline);
			System.exit(0);
		}
		
		

	}

	public static void printHelp() {
		// Print out a list of arguments
		String help = "";
		
		help = "Name: Test" + newline +
				"Provides an interface to the Konane game class and the" + newline +
				"default player classes and sets up a game according to" + newline + 
				"the options listed below" +newline;
		System.out.println(help);
		
		help = "-n, --boardsize <size>" + newline +
				tab + "The size of the board to be played on. The board size" +
				newline + tab + "is n x n. n must be between 4 and 8, inclusive." +
				newline + tab + "Default size is 4" + newline;
		System.out.println(help);
		
		help = "-g, --numgames <number_of_games>" + newline +
				tab + "The number of games to be played. Default is 1" + newline;
		System.out.println(help);
		
		help = "-p1, --player1 <player1_type> Optional <player1_name>" + newline +
				"-p2, --player2 <player2_type> Optional <player2_name>" + newline +
				tab + "The information on the specified player. <player_type>"+ newline +
				tab + "must be specified, and can be Simple, Random or Human." + newline +
				tab + "<player_name> is optional, but if set will be the" + newline +
				tab + "players name. Default Player1 is Random with name" + newline +
				tab + "\"Player 1\" and default Player2 is Human with name" + newline + 
				tab + " \"Player 2\"." + newline;
		System.out.println(help);
		
		help = "-h, --help" + newline +
				tab + "Shows this help message" + newline;
		System.out.println(help);
	}
}

class ParseArgs {
	
	private int bSize = 4;
	private int numGames = 1;
	private Player player1;
	private Player player2;
	
	private boolean selected1 = false;
	private boolean selected2 = false;
	private boolean doHelp = false;
	
	public ParseArgs(String[] sArgs){
		
		int i = 0;
		int c = 0;
		//System.out.println("Parsing Args " + sArgs.length);
		
		// While there are still arguments to be parsed
		while (i < sArgs.length) {
			// loop count.
			c++;
			// Debug output
			//System.out.println(i + ", \"" + sArgs[i] + "\", " + sArgs.length);
			//System.out.println(sArgs[i].equalsIgnoreCase("-h"));
			
			// If -h or --help was entered, set the help flag
			// and exit
			if (sArgs[i].equalsIgnoreCase("-h") || sArgs[i].equalsIgnoreCase("--help")) {
				//System.out.println("help");
				doHelp = true;
				i = sArgs.length;
			// if -n or --boardsize is set, set the board size
			} else if (sArgs[i].equalsIgnoreCase("-n") || sArgs[i].equalsIgnoreCase("--boardsize")) {
				//System.out.println("Board Size");
				bSize = new Integer(sArgs[++i]).intValue();
				i++;
			// if -g or --numgames is set, set the number of games
			} else if (sArgs[i].equalsIgnoreCase("-g") || sArgs[i].equalsIgnoreCase("--numgames")) {
				//System.out.println("Number of games");
				numGames = new Integer(sArgs[++i]).intValue();
				i++;
			// if -p1 or --player1 is set, set the player 1 details
			} else if (sArgs[i].equalsIgnoreCase("-p1") || sArgs[i].equalsIgnoreCase("--player1")) {
				//System.out.println("Player 1");
				String player = new String(sArgs[++i]);
				// default name
				String name = "Player 1";
				
				// Set the player name depending on input
				if (sArgs[i].indexOf("-") == -1) {
					name = sArgs[i];
				}
				// Set the player type depending on input
				if (player.equalsIgnoreCase("human")) {
					player1 = new HumanPlayer(name);
				} else if (player.equalsIgnoreCase("random")) {
					player1 = new RandomPlayer(name);
				} else if (player.equalsIgnoreCase("simple")) {
					player1 = new SimplePlayer(name);
				}
				else if (player.equalsIgnoreCase("c10086617")) {
					player1 = new c10086617(name);
				}
				else if(player.equalsIgnoreCase("c10086617A")) {
					player1 = new c10086617A(name);
				}
				else if(player.equalsIgnoreCase("c10086617B")) {
					player1 = new c10086617BBB(name);
				}
				else if(player.equalsIgnoreCase("c10086617C")) {
					player1 = new c10086617CCC(name);
				}
				selected1 = true;
				i++;
			// if -p2 or --player2 is set, set the player 2 details
			}  else if (sArgs[i].equalsIgnoreCase("-p2") || sArgs[i].equalsIgnoreCase("--player2")) {
				//System.out.println("Player 2");
				String player = new String(sArgs[++i]);
				// default name
				String name = "Player 2";
				// Set the name depending on input
				if (sArgs[i].indexOf("-") == -1) {
					name = sArgs[i];
				}
				// set the player depending on input
				if (player.equalsIgnoreCase("human")) {
					player2 = new HumanPlayer(name);
				} else if (player.equalsIgnoreCase("random")) {
					player2 = new RandomPlayer(name);
				} else if (player.equalsIgnoreCase("simple")) {
					player2 = new SimplePlayer(name);
				}
				else if (player.equalsIgnoreCase("c10086617")) {
					player2 = new c10086617(name);
				}
				else if(player.equalsIgnoreCase("c10086617A")) {
					player2 = new c10086617A(name);
				}
				else if(player.equalsIgnoreCase("c10086617B")) {
					player2 = new c10086617BBB(name);
				}
				else if(player.equalsIgnoreCase("c10086617C")) {
					player2 = new c10086617CCC(name);
				}
				selected2 = true;
				i++;
			} else {
				System.out.println("\n******************************************");
				System.out.println("Invalid arguments!");
				System.out.println("******************************************\n");
				
				System.exit(0);				
			}
		} 		
	}
	
	public int boardSize() { return bSize;}
	public int numberOfGames() { return numGames;}
	public Player player1() { return player1;}
	public Player player2() { return player2;}
	public boolean help() { return doHelp;}
	public boolean player1Selected() {return selected1;}
	public boolean player2Selected() {return selected2;}
}