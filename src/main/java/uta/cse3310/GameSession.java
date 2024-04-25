package uta.cse3310;

import org.java_websocket.WebSocket;
import java.util.*;

public class GameSession
{
	public PlayerType player;
	public PlayerType[] players;
	transient public PlayerType[] button;
	public int gameId;
	public Statistics stats;
	private List<Message> chatLog = new ArrayList<>();
	//public int maxPlayers;

	WordBank bank = new WordBank();
	char[][] board;
	int wordsToFind;
	int wordsFound = 0;
	int lastLocation = -1;
	int Player1Score = 0;
	int Player2Score = 0;
	int Player3Score = 0;
	int Player4Score = 0;
	transient List<Integer> wordPositions;

	public GameSession(Statistics s)
	{
		stats = s;
		button = new PlayerType[2500];
		resetBoard();
		player = PlayerType.Player1;
		players = new PlayerType[4];
	}

	public void setPlayers(int maxPlayers)
	{
		//this.maxPlayers = maxPlayers;
		//this.players = new PlayerType[maxPlayers];
	}

	// adds players to array storing who's in what game
	public void addPlayer(PlayerType player)
	{
		if(player == PlayerType.Player1)
		{
			players[0] = player;
		}

		else if(player == PlayerType.Player2)
		{
			players[1] = player;
		}

		else if(player == PlayerType.Player3)
		{
			players[2] = player;
		}

		else
		{
			players[3] = player;
		}
	}

	public void addToChat (String sender, String message) {
		this.chatLog.add(new Message(sender, message));
	}

	public List<Message> getChatLog() {
		return this.chatLog;
	}

	// see if the player count is full
	public boolean isFull()
	{
		//return players[maxPlayers - 1] != null;
		return players[4 - 1] != null;
	}

	// set every button to NOPLAYER
	public void resetBoard()
	{
		for(int i = 0; i < button.length; i++)
		{
			button[i] = PlayerType.NOPLAYER;
		}
	}

	// calls WordGrid.java to execute
	public void startGame()
	{
		board = bank.generateGrid();
		//System.out.println(bank.locations);

		// print for debugging
		/*for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 50; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}*/
	}
	
	// gets called when a button is clicked on the website
	// location is the button position and typeInt is the PlayerType
	public boolean charSelected(int location, int typeInt)
	{
		wordsToFind = bank.wordsToFill;

		// set button from NOPLAYER to PlayerType
		button[location] = players[typeInt - 1];

		// see if the current character is in the same word as the last
		if(wordFound(lastLocation, location))
		{
			// find positions to highlight
			wordPositions = getWordPositions(lastLocation, location);
			//System.out.println(wordPositions);

			//System.out.println("\nWORD FOUND + " + players[typeInt] + "\n");

			// add to Player1 score
			if(typeInt == 1)
			{
				Player1Score++;
			}

			// add to Player2 score
			else if(typeInt == 2)
			{
				Player2Score++;
			}

			// add to Player3 score
			else if(typeInt == 3)
			{
				Player3Score++;
			}

			// add to Player4 score
			else if(typeInt == 4)
			{
				Player4Score++;
			}

			// add to total words found and see if the game is over
			wordsFound++;
		}

		else
		{
			wordPositions = null;
		}

		// update last character chosen
		lastLocation = location;

		// see if the game is over
		boolean gameOver = (wordsFound >= wordsToFind);
		return !gameOver;
	}
	
	// word was found
	public boolean wordFound(int lastLocation, int location)
	{
		// cast selected locations to a WordLocation object
		WordLocation selectedLocation = new WordLocation(lastLocation, location);

		//System.out.println("\nWord Location: " + selectedLocation + "\n");
		
		// loop through ArrayList of location structures
		for(int i = 0; i < bank.locations.size(); i++)
		{
			//System.out.println(bank.locations.get(i));
			WordLocation listLocation = bank.locations.get(i);

			// see if data is the same
			if(listLocation.equals(selectedLocation))
			{
				//System.out.println("\nFOUND\n");

				// doesn't allow words to be overwritten
				bank.locations.remove(i);
				wordsFound = (wordsFound + 1) / 2;

				return true;
			}
		}

		// word was not found
		return false;
	}

	// find locations to highlight words
	public List<Integer> getWordPositions(int start, int end)
	{
		List<Integer> positions = new ArrayList<>();

		int startRow = start / 50;
		int startColumn = start % 50;
		int endRow = end / 50;
		int endColumn = end % 50;

		// horizontal
		if(start / 50 == end / 50)
		{
			for(int i = Math.min(start, end); i <= Math.max(start, end); i++)
			{
				//button[i] = players[typeInt];
				positions.add(i);
			}
		}

		// vertical (works both up and down)
		else if(start % 50 == end % 50)
		{
			for(int i = Math.min(startRow, endRow); i <= Math.max(startRow, endRow); i++)
			{
				positions.add(i * 50 + startColumn);
			}
		}

		// diagonal down
		else if(endRow - startRow == endColumn - startColumn)
		{
			//int rowCount = Math.min(startRow, endRow);
			//int columnCount = Math.min(startColumn, endColumn);

			for(int i = 0; i <= Math.abs(endRow - startRow); i++)
			{
				positions.add((startRow + i) * 50 + (startColumn + i));
			}
		}

		// diagonal up
		else if(startRow - endRow == endColumn - startColumn)
		{
			for(int i = 0; i <= Math.abs(startRow - endRow); i++)
			{
				positions.add((startRow - i) * 50 + (startColumn + i));
			}
		}

		return positions;

	}

	// updates the game board to the newest event
	public void update(UserEvent U)
	{
		//will update the game when a word is won by a player
		System.out.println("The user event is " + U.PlayerIdx + " " + U.Button + "\n");

		if(button[U.Button] == PlayerType.NOPLAYER)
		{
			System.out.println("The button was 0, setting it to " + U.PlayerIdx);
			button[U.Button] = U.PlayerIdx;

			//charSelected(button[U.Button]);
		}
	}
	
}