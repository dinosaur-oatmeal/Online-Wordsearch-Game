package uta.cse3310;

import org.java_websocket.WebSocket;
import java.util.*;

public class GameSession
{
	public PlayerType player;
	public PlayerType[] players;
	public PlayerType[] button;
	public int gameId;
	public Statistics stats;

	WordBank bank = new WordBank();
	char[][] board;
	int wordsToFind = bank.wordsToFill;
	int wordsFound = 0;
	int lastLocation = -1;
	int Player1Score = 0;
	int Player2Score = 0;

	public GameSession(Statistics s)
	{
		stats = s;
		button = new PlayerType[2500];
		resetBoard();
		player = PlayerType.Player1;
		players = new PlayerType[5];
	}

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

	public boolean isFull()
	{
		return players[3] != null;
	}

	public void resetBoard()
	{
		for(int i = 0; i < button.length; i++)
		{
			button[i] = PlayerType.NOPLAYER;
		}
	}

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
	
	public void charSelected(int location, PlayerType type)
	{
		System.out.println("\n" + type + "\n");

		// see if the current character is in the same word as the last one chosen (either direction)
		if(wordFound(lastLocation, location))
		{
			// highlight the found word
			highlightWord();

			// add to Player1 score
			if(type == PlayerType.Player1)
			{
				Player1Score++;
			}

			// add to Player2 score
			if(type == PlayerType.Player2)
			{
				Player2Score++;
			}

			// add to total words found and see if the game is over
			wordsFound++;

			if(wordsFound == wordsToFind)
			{
				gameOver();
			}
		}

		// update last character chosen
		lastLocation = location;
	}
	
	public boolean wordFound(int lastLocation, int location)
	{
		// cast selected locations to a WordLocation object
		WordLocation selectedLocation = new WordLocation(lastLocation, location);
		
		// loop through ArrayList of location structures
		for(int i = 0; i < bank.locations.size(); i++)
		{
			// see if data is the same
			if(bank.locations.equals(selectedLocation))
			{
				// doesn't allow words to be overwritten
				bank.locations.remove(selectedLocation);

				return true;
			}
		}

		// word was not found
		return false;
	}	
	public void wordColor()
	{
		//will be how a color is picked
	}
	public void highlightWord()
	{
		//will highlight a word the players color
	}
	public boolean gameOver()
	{
		return wordsToFind == wordsFound;
	}
	public void update(UserEvent U)
	{
		//will update the game when a word is won by a player
		System.out.println("The user event is " + U.PlayerIdx + " " + U.Button);

		if(button[U.Button] == PlayerType.NOPLAYER)
		{
			System.out.println("The button was 0, setting it to " + U.PlayerIdx);
			button[U.Button] = U.PlayerIdx;

			//charSelected(button[U.Button]);
		}
	}
	
}