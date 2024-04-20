package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

public class WordBankTest extends TestCase
{
    public WordBankTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(WordBankTest.class);
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
    /*public void testLetters()
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
    }*/

    // check to see that the board is generated in 1 second
    public void testGenerateBoard()
    {
        GameSession G = new GameSession(new Statistics());
        
        long startTime = System.nanoTime();
        G.bank.generateGrid();
        long endTime = System.nanoTime();

        // convert to seconds and check generation time under 1 second
        long difference = (startTime - endTime) / 1000000;
        assertTrue(difference < 1000);
    }
}
