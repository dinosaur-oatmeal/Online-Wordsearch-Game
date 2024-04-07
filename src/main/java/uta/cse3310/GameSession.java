package uta.cse3310;

import org.java_websocket.WebSocket;
import java.util.*;

public class GameSession
{
	public PlayerType players;
	public PlayerType[] button;
	public int gameId;
	public Statistics stats;

	WordBank bank = new WordBank();
	char[][] board;
	int wordsToFill = bank.wordsToFill;
	int wordsFilled = 0;
	int lastLocation, location = -1;

	public GameSession(Statistics s)
	{
		stats = s;
		button = new PlayerType[2500];
		resetBoard();
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

		// print for debugging
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 50; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void charSelected(int location)
	{
		// see if the current character is in the same word as the last one chosen (either direction)
		if(wordFound(lastLocation, location))
		{
			highlightWord();
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

				// see if the game has ended
				wordsFilled++;
				if(wordsFilled == wordsToFill)
				{
					gameOver();
				}

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
	public void gameOver()
	{
		//method to determine if the game is over
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