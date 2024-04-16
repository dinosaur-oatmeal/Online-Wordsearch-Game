package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

public class GameSessionTest extends TestCase
{
    public GameSessionTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(GameSessionTest.class);
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