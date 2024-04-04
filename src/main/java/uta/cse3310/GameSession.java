package uta.cse3310;

public class GameSession
{
	WordBank bank = new WordBank();

	public void startGame()
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
	}
	
	public void charSelected(int firstLetter, int lastLetter)
	{
		//method of selecting characters
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
				return true;
			}
		}

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
	public void gameOver(int numberOfWords)
	{
		//method to determine if the game is over
	}
	public void update(UserEvent U)
	{
		//will update the game when a word is won by a player
	}
	
}