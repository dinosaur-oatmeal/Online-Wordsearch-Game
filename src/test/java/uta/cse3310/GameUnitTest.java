package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

public class GameUnitTest extends TestCase
{
    public GameUnitTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(GameUnitTest.class);
    }

    ///////////////////////////////////
    // Unit tests for WordBank.java //
    /////////////////////////////////

    // check horizontal words can be placed on the board
    public void testHorizontal()
    {
        WordBank bank = new WordBank();

        // initialize board (every cell is '-')
        char[][] board = new char[50][50];
        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                board[i][j] = '-';
            }
        }

        // place in top left and bottom right
        bank.horizontal(board, "hello", 0, 0);
        bank.horizontal(board, "hello", 49, 45);

        // check that words are valid placements
        assertTrue(board[0][0] == 'h' && board[0][4] == 'o');
        assertTrue(board[49][45] == 'h' && board[49][49] == 'o');
    }

    // check vertical down words can be placed on the board
    public void testVerticalDown()
    {
        WordBank bank = new WordBank();

        // initialize board (every cell is '-')
        char[][] board = new char[50][50];
        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                board[i][j] = '-';
            }
        }

        // place in top left and bottom right
        bank.verticalDown(board, "hello", 0, 0);
        bank.verticalDown(board, "hello", 45, 49);

        // check that words are valid placements
        assertTrue(board[0][0] == 'h' && board[4][0] == 'o');
        assertTrue(board[45][49] == 'h' && board[49][49] == 'o');
    }

    // check vertical up words can be placed on the board
    public void testVerticalUp()
    {
        WordBank bank = new WordBank();

        // initialize board (every cell is '-')
        char[][] board = new char[50][50];
        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                board[i][j] = '-';
            }
        }

        // place in top left and bottom right
        bank.verticalUp(board, "hello", 0, 0);
        bank.verticalUp(board, "hello", 45, 49);

        // check that words are valid placements
        assertTrue(board[0][0] == 'o' && board[4][0] == 'h');
        assertTrue(board[45][49] == 'o' && board[49][49] == 'h');
    }

    // check diagonal down words can be placed on the board
    public void testDiagonalDown()
    {
        WordBank bank = new WordBank();

        // initialize board (every cell is '-')
        char[][] board = new char[50][50];
        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                board[i][j] = '-';
            }
        }

        // place in top left and bottom right
        bank.diagonalDown(board, "hello", 0, 0);
        bank.diagonalDown(board, "hello", 45, 45);

        // check that words are valid placements
        assertTrue(board[0][0] == 'h' && board[4][4] == 'o');
        assertTrue(board[45][45] == 'h' && board[49][49] == 'o');
    }

    // check diagonal up words can be placed on the board
    public void testDiagonalUp()
    {
        WordBank bank = new WordBank();

        // initialize board (every cell is '-')
        char[][] board = new char[50][50];
        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                board[i][j] = '-';
            }
        }

        // place as close to top left and bottom right as possible
        bank.diagonalUp(board, "hello", 0, 0);
        bank.diagonalUp(board, "hello", 45, 45);

        // check that words are valid placements
        assertTrue(board[4][0] == 'h' && board[0][4] == 'o');
        assertTrue(board[49][45] == 'h' && board[45][49] == 'o');
    }

    // test words aren't overwritten
    public void testOverwrite()
    {
        WordBank bank = new WordBank();

        // initialize board (every cell is '-')
        char[][] board = new char[50][50];
        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                board[i][j] = '-';
            }
        }

        // set locations to hello, and try to overwrite 5 times
        bank.horizontal(board, "hello", 0, 0);
        bank.horizontal(board, "scary", 0, 0);
        bank.verticalDown(board, "scary", 0, 0);
        bank.verticalUp(board, "scary", 0, 0);
        bank.diagonalDown(board, "scary", 0, 0);
        bank.diagonalUp(board, "scary", 0, 0);

        // check that the word wasn't overwritten
        assertTrue(board[0][0] == 'h' && board[0][1] == 'e' && board[0][2] == 'l');
        assertTrue(board[0][3] == 'l' && board[0][4] == 'o');
    }

    // check that game stats are fulfilled when a board is generated
    public void testStats()
    {
        WordBank bank = new WordBank();

        char[][] board = bank.generateGrid();

        // test that all stats are satisfied
        assertTrue(bank.density >= 0.6 && bank.diagDown >= 0.15 &&
        bank.diagUp >= 0.15 && bank.vertDown >= 0.15 &&
        bank.vertUp >= 0.15 && bank.horz >= 0.15);
    }

    // check that the entire board is filled with letters
    public void testLetters()
    {
        WordBank bank = new WordBank();

        char[][] board = bank.generateGrid();

        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                assertTrue(board[i][j] != '-');
            }
        }
    }

    // check to see that the board is generated in 1 second
    public void testGenerateBoard()
    {
        GameSession G = new GameSession(new Statistics());
        
        long startTime = System.nanoTime();
        G.bank.generateGrid();
        long endTime = System.nanoTime();

        // convert nanoseconds to milliseconds and then make sure under 1 second
        long difference = (startTime - endTime) / 1000000;
        assertTrue(difference < 1000);
    }

    //////////////////////////////////////
    // Unit tests for GameSession.java //
    ////////////////////////////////////

    // check that players can be added to the lobby
    public void testAddPlayer()
    {
        GameSession G = new GameSession(new Statistics());
        G.players[0] = PlayerType.Player1;

        // add players and test that they were added to correct positions
        G.addPlayer(PlayerType.Player2);
        G.addPlayer(PlayerType.Player3);
        G.addPlayer(PlayerType.Player4);

        assertTrue(G.players[0] == PlayerType.Player1);
        assertTrue(G.players[3] == PlayerType.Player4);
    }

    // check that lobby is full
    public void testLobbyFull()
    {
        GameSession G = new GameSession(new Statistics());

        G.players[0] = PlayerType.Player1;
        G.players[1] = PlayerType.Player2;

        // lobby is not full
        assertTrue(!G.isFull());

        G.players[3] = PlayerType.Player3;
        G.players[4] = PlayerType.Player4;

        // lobby is full
        assertTrue(G.isFull());
    }

    // check that the board is reset successfully
    public void testResest()
    {
        GameSession G = new GameSession(new Statistics());
        G.resetBoard();

        // all buttons should be NOPLAYER
        for(int i = 0; i < 2500; i++)
        {
            assertTrue(G.button[i] == PlayerType.NOPLAYER);
        }
    }

    // check that the board is created when a game is started
    public void testStartGame()
    {
        GameSession G = new GameSession(new Statistics());
        G.startGame();

        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                // board is not '-' or null characters
                assertTrue(G.board[i][j] != '-' && G.board[i][j] != '\u0000');
            }
        }
    }

    // check that characters can be selected
    public void testCharSelected()
    {
        GameSession G = new GameSession(new Statistics());

        char[][] board = new char[50][50];
        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                board[i][j] = '-';
            }
        }

        // write words onto the board
        G.bank.horizontal(board, "hello", 0, 0);
        G.bank.verticalDown(board, "hello", 45, 49);

        G.players[0] = PlayerType.Player1;
        G.players[1] = PlayerType.Player2;
        G.players[3] = PlayerType.Player3;
        G.players[4] = PlayerType.Player4;

        // select the first and last characters as Player2 and Player1
        G.charSelected(0, 1);
        G.charSelected(2499, 0);

        assertTrue(G.button[0] == PlayerType.Player2);
        assertTrue(G.button[2499] == PlayerType.Player1);
    }

    // check that a game can end
    public void testGameOver()
    {
        GameSession G = new GameSession(new Statistics());
        G.startGame();

        G.bank.wordsToFill = 100;
        G.wordsFound = 99;

        // game not over
        boolean test = G.charSelected(0, 1);
        assertTrue(test);

        // game should be over
        G.wordsFound++;
        test = G.charSelected(2499, 1);
        assertTrue(!test);
    }

    // check that a word can be found and can't be overwritten
    public void testWordFound()
    {
        GameSession G = new GameSession(new Statistics());
        G.startGame();
        WordLocation wordLoc = G.bank.locations.get(1);
        int lastLocation = wordLoc.getLastLocation();
        int location = wordLoc.getLocation();

        // word found
        boolean test = G.wordFound(lastLocation, location);
        assertTrue(test);

        // same word can't be found twice
        test = G.wordFound(lastLocation, location);
        assertTrue(!test);
    }

    // check that horizontal word positions are valid
    public void testHorizontalPositions()
    {
        GameSession G = new GameSession(new Statistics());
        
        List<Integer> horizontalPositions = G.getWordPositions(0, 4);
        assertTrue(Arrays.asList(0, 1, 2, 3, 4).equals(horizontalPositions));
    }

    // check that vertical down word positions are valid
    public void testVerticalDownPositions()
    {
        GameSession G = new GameSession(new Statistics());
        
        List<Integer> verticalDownPositions = G.getWordPositions(0, 200);
        assertTrue(Arrays.asList(0, 50, 100, 150, 200).equals(verticalDownPositions));
    }

    // check that vertical up word positions are valid
    public void testVerticalUpPositions()
    {
        GameSession G = new GameSession(new Statistics());
        
        List<Integer> verticalUpPositions = G.getWordPositions(0, 200);
        assertTrue(Arrays.asList(0, 50, 100, 150, 200).equals(verticalUpPositions));
    }

    // check that diagonal down word positions are valid
    public void testDiagonalDownPositions()
    {
        GameSession G = new GameSession(new Statistics());
        
        List<Integer> diagonalDownPositions = G.getWordPositions(0, 204);
        assertTrue(Arrays.asList(0, 51, 102, 153, 204).equals(diagonalDownPositions));
    }

    // check that diagonal up word positions are valid
    public void testDiagonalUpPositions()
    {
        GameSession G = new GameSession(new Statistics());
        
        List<Integer> diagonalUpPositions = G.getWordPositions(200, 4);
        assertTrue(Arrays.asList(200, 151, 102, 53, 4).equals(diagonalUpPositions));
    }
}