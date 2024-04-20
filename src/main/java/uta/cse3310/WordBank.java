package uta.cse3310;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordBank
{
   int gridSize = 2500;
   int wordsToFill;
   int wordsFilled = 0;
   double diagDown, diagUp, vertUp, vertDown, horz = 0;
   double density = 0.67;
   private transient Random random = new Random();
   
   // arraylist that will store word locations
   List<WordLocation> locations = new ArrayList<>();

   public char[][] generateGrid()
   {
      // amount of words for board
      wordsToFill = (int)((gridSize * density) / 5);
      //System.out.println(wordsToFill);
      
      // array with chosen words for board
      String[] words = selectWords();
      
      // initialize board (every cell is '-')
      char[][] board = new char[50][50];
      for(int i = 0; i < 50; i++)
      {
         for(int j = 0; j < 50; j++)
         {
            board[i][j] = '-';
         }
      }
      
      //System.out.println(cellsToFill);
      
      // loop until every cell is filled to meet density
      while(wordsFilled < wordsToFill)
      {
         int RDVH = random.nextInt(5) + 1;
         //System.out.print(RDVH); 
         //System.out.println(cellsFilled);

         int row = random.nextInt(board.length - 4);
         int column = random.nextInt(board.length - 4);
         
         switch(RDVH)
         {
            case 1:
               diagonalDown(board, words[wordsFilled], row, column);
               break;
            case 2:
               diagonalUp(board, words[wordsFilled], row, column);
               break;
            case 3:
               verticalDown(board, words[wordsFilled], row, column);
               break;
            case 4:
               verticalUp(board, words[wordsFilled], row, column);
               break;
            case 5:
               horizontal(board, words[wordsFilled], row, column);
               break;
         }
      }
      
      //insertRandomLetters(board);
      
      checkBoard(board);
      
      return board;
   }
  
   // select a word from the word bank
   public String[] selectWords()
   {      
      // read entire file into ArrayList to quickly grab locations
      ArrayList<String> inputFile = new ArrayList<>();
         
      // selection of random words
      String[] words = new String[wordsToFill];
      String line;
         
      // track line locations of selected words in inputFile
      HashSet<Integer> locations = new HashSet<>();
      int lineNumber, j = 0;
      
      // open file
      try(BufferedReader reader = new BufferedReader(new FileReader("WordList.txt")))
      {
         // dump file content into list to be easily searched
         while((line = reader.readLine()) != null)
         {
            inputFile.add(line);
         }
         
         // loop for every position in words output array
         while(j < wordsToFill)
         {
            lineNumber = random.nextInt(700);
            
            // avoid selecting double words
            if(!locations.contains(lineNumber))
            {
               // all words are 5 letters
               line = inputFile.get(lineNumber);
               words[j] = line;
               //System.out.println(words[j]);
               
               locations.add(lineNumber);
               j++;
            }
         }
         
         return words;
      }
      
      catch(IOException e)
      {
         e.printStackTrace();
      }
      
      words[0] = "ERROR";
      return words;
   }
  
   // write the word diagonally down
   public void diagonalDown(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < 5)
      {
         // don't overwrite data
         boolean valid = true;
         for(int i = 0; i < 5; i++)
         {
            if(board[row + i][column + i] != '-')
            {
               valid = false;
               
               // choose new location if it was invalid and add fail
               row = random.nextInt(board.length - 4);
               column = random.nextInt(board.length - 4);
               failCounter++;
               
               break;
            }
         }
            
         // place word onto board
         if(valid)
         {
            for(int i = 0; i < 5; i++)
            {
               board[row + i][column + i] = word.charAt(i);
            }
            
            int start = row * 50 + column;
            int end = start + (4 * 50) + 4;
            locations.add(new WordLocation(start, end));
            // add to variables for statistics
            wordsFilled++;
            diagDown++;
         }
         
         break;
      }
   }
   
   // write the word diagonally up
   public void diagonalUp(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < 5)
      {
         // don't overwrite data
         boolean valid = true;
         for(int i = 0; i < 5; i++)
         {
            if(board[row + 4 - i][column + i] != '-')
            {
               valid = false;
               
               // choose new location if it was invalid and add fail
               row = random.nextInt(board.length - 4);
               column = random.nextInt(board.length - 4);
               failCounter++;
               
               break;
            }
         }
            
         // place word onto board
         if(valid)
         {
            for(int i = 0; i < 5; i++)
            {
               board[row + 4 - i][column + i] = word.charAt(i);
            }
            
            int end = row * 50 + column;
            int start = end + (4 * 50);
            locations.add(new WordLocation(start, end));
            
            // add to variables for statistics
            wordsFilled++;
            diagUp++;
         }
         
         break;
      }
   }

   // write the word vertically down
   public void verticalDown(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < 5)
      {
         // don't overwrite data
         boolean valid = true;
         for(int i = 0; i < 5; i++)
         {
            if(board[row + i][column] != '-')
            {
               valid = false;
               
               // choose new location if it was invalid and add fail
               row = random.nextInt(board.length - 4);
               column = random.nextInt(board.length);
               failCounter++;
               
               break;
            }
         }
            
         // place word onto board
         if(valid)
         {
            for(int i = 0; i < 5; i++)
            {
               board[row + i][column] = word.charAt(i);
            }
            
            int start = row * 50 + column;
            int end = start + (4 * 50);
            locations.add(new WordLocation(start, end));
            
            // add to variables for statistics
            wordsFilled++;
            vertDown++;
         }
            
         break;
      }
   }
   
   // write the word vertically up
   public void verticalUp(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < 5)
      {
         // don't overwrite data
         boolean valid = true;
         for(int i = 0; i < 5; i++)
         {
            if(board[row + i][column] != '-')
            {
               valid = false;
               
               // choose new location if it was invalid and add fail
               row = random.nextInt(board.length - 4);
               column = random.nextInt(board.length);
               failCounter++;
               
               break;
            }
         }
            
         // place word onto board
         if(valid)
         {
            for(int i = 0; i < 5; i++)
            {
               board[row + i][column] = word.charAt(4 - i);
            }
            
            int end = row * 50 + column;
            int start = end + (4 * 50);
            locations.add(new WordLocation(start, end));
            
            // add to variables for statistics
            wordsFilled++;
            vertUp++;
         }
            
         break;
      }
   }

   // write the word horizontally
   public void horizontal(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < 5)
      {
         // don't overwrite data
         boolean valid = true;
         for(int i = 0; i < 5; i++)
         {
            if(board[row][column + i] != '-')
            {
               valid = false;
               
               // choose new location if it was invalid and add fail
               row = random.nextInt(board.length);
               column = random.nextInt(board.length - 4);
               failCounter++;
               
               break;
            }
         }
            
         // place word onto board
         if(valid)
         {
            for(int i = 0; i < 5; i++)
            {
               board[row][column + i] = word.charAt(i);
            }
            
            int start = row * 50 + column;
            int end = start + 4;
            locations.add(new WordLocation(start, end));
            
            // add to variables for statistics
            wordsFilled++;
            horz++;
         }
            
         break;
      }
   }

   // insert random letter in the board
   public void insertRandomLetters(char[][] board)
   {
      final String alphabet = "abcdefghijklmnopqrstuvwxyz";
      int letterToInsert;
      
      for(int i = 0; i < 50; i++)
      {
         for(int j = 0; j < 50; j++)
         {
            if(board[i][j] == '-')
            {
               letterToInsert = random.nextInt(alphabet.length());
               board[i][j] = alphabet.charAt(letterToInsert);
            }
         }
      }
   }

   // checks the board to make sure stats are valid
   // regenerates grid of stats aren't valid
   public void checkBoard(char[][] board)
   {
      density = (double)(wordsFilled * 5) / gridSize;
      diagDown = diagDown / wordsFilled;
      diagUp = diagUp / wordsFilled;
      vertDown = vertDown / wordsFilled;
      vertUp = vertUp / wordsFilled;
      horz = horz / wordsFilled;
      
      if(density < 0.6 || diagDown < 0.15 || diagUp < 0.15 ||
      vertDown < 0.15 || vertUp < 0.15 || horz < 0.15)
      {
         density = 0.67;
         diagDown = 0;
         diagUp = 0;
         vertUp = 0;
         vertDown = 0;
         horz = 0;
         wordsFilled = 0;
         
         generateGrid();
      }
   }

   // print board statistics
   public void statistics()
   {   
      System.out.printf("Density: %.2f\n", density);
      System.out.printf("Diagonal Down: %.2f\n", diagDown);
      System.out.printf("Diagonal Up: %.2f\n", diagUp);
      System.out.printf("Vertical Down: %.2f\n", vertDown);
      System.out.printf("Vertical Up: %.2f\n", vertUp);
      System.out.printf("Horizontal: %.2f\n", horz);
   }
   
   /////////
   /*
   
   static char[][] board;
   
   public static void main(String args[])
   {
      board = generateGrid();
      
      Iterator<WordLocation> itr = locations.listIterator();
      
      while(itr.hasNext())
      {
         System.out.println(itr.next());
      }

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
   
   */
   //////////
}