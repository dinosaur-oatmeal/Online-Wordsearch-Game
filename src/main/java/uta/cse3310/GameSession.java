package uta.cse3310;

import org.java_websocket.WebSocket;
import java.util.*;

public class GameSession
{
	private Map<WebSocket, PlayerType> players = new HashMap<>();
	private int MAX_PLAYERS;
	private boolean gameStarted = false;
	WordBank bank = new WordBank();
	int wordsToFill = bank.wordsToFill;
	int wordsFilled = 0;
	int lastRow, lastColumn = -1;

	public GameSession(int maxPlayers)
	{
		this.MAX_PLAYERS = maxPlayers;
	}

	public boolean addPlayer(WebSocket conn, PlayerType type)
	{
		if(players.size() < MAX_PLAYERS)
		{
			players.put(conn, type);
			return true;
		}

		return false;
	}

	public boolean isFull()
	{
		return players.size() == MAX_PLAYERS;
	}

	public int getPlayerCount()
	{
		return players.size();
	}

	public char[][] startGame()
	{
		char[][] board = bank.generateGrid();

		// print for debugging
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 50; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}

		return board;
	}
	
	public void charSelected(int row, int column)
	{
		// see if the current character is in the same word as the last one chosen (either direction)
		if(wordFound(lastRow, lastColumn, row, column) ||
		wordFound(row, column, lastRow, lastColumn))
		{
			highlightWord();
		}

		// update last character chosen
		lastRow = row;
		lastColumn = column;
	}
	
	public boolean wordFound(int startRow, int startColumn, int endRow, int endColumn)
	{
		// cast selected locations to a WordLocation object
		WordLocation selectedLocation = new WordLocation(startRow, startColumn, endRow, endColumn);
		
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
		System.out.println("TEST");
	}
	
}