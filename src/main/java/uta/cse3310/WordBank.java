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
   int wordsFilled, TCounter = 0;
   double diagDown, diagUp, vertUp, vertDown, horz = 0;
   public double density = 0.67;
   transient Random random = new Random();
   static final int RETRY = 100;
   int retry = 0;
   
   // ArrayList to store words
   transient ArrayList<String> words;
   
   // ArrayList that will store word locations
   transient List<WordLocation> locations = new ArrayList<>();

   public char[][] generateGrid()
   {
      // amount of words for board
      wordsToFill = (int)((gridSize * density) / 5);
      //System.out.println(wordsToFill);
      
      // initialize board (every cell is '-')
      char[][] board = new char[50][50];
      for(int i = 0; i < 50; i++)
      {
         for(int j = 0; j < 50; j++)
         {
            board[i][j] = '-';
         }
      }
      
      // array with chosen words for board
      words = selectWords();
      
      //System.out.println(words);
      
      int TCounterRow = 0;
      int TCounterColumn = 0;
      
      // loop until every cell is filled to meet density
      while(wordsFilled < wordsToFill && retry < 1000)
      {
         int RDVH = random.nextInt(5) + 1;
         //System.out.print(RDVH); 
         //System.out.println(cellsFilled);
         
         // fill T-shaped words first
         if(TCounter < 10)
         {
            for(int i = 0; i < 10; i++)
            {
               // get rid of word with x from being horizontal (no vertical match)
               if(words.get(i).charAt(2) == 'x')
               {
                  String temp = words.get(i);
                  words.remove(i);
                  words.add(temp);
               }
               
               ///System.out.println(TCounterRow + " " + TCounterColumn);
               
               TWords(board, words, words.get(i), TCounterRow, TCounterColumn);
               TCounterRow += 5;
               TCounterColumn += 5;
               TCounter++;
            }
         }

         int row = random.nextInt(board.length - 4);
         int column = random.nextInt(board.length - 4);
         boolean fail;
         
         switch(RDVH)
         {
            case 1:
               fail = diagonalDown(board, words.get(wordsFilled), row, column);
               if(fail)
                  retry++;
               break;
            case 2:
               fail = diagonalUp(board, words.get(wordsFilled), row, column);
               if(fail)
                  retry++;
               break;
            case 3:
               fail = verticalDown(board, words.get(wordsFilled), row, column);
               if(fail)
                  retry++;
               break;
            case 4:
               fail = verticalUp(board, words.get(wordsFilled), row, column);
               if(fail)
                  retry++;
               break;
            case 5:
               fail = horizontal(board, words.get(wordsFilled), row, column);
               if(fail)
                  retry++;
               break;
         }
      }
      
      //insertRandomLetters(board);
      
      checkBoard(board);
      
      return board;
   }
  
   // select a word from the word bank
   public ArrayList<String> selectWords()
   {      
      // read entire file into ArrayList to quickly grab locations
      ArrayList<String> inputFile = new ArrayList<>();
         
      // selection of random words
      ArrayList<String> words = new ArrayList<>();
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
         
         // loop for every position in words output array (+20 handles potential margin of error)
         while(j < wordsToFill + 20)
         {
            lineNumber = random.nextInt(700);
            
            // avoid selecting double words
            if(!locations.contains(lineNumber))
            {
               // all words are 5 letters
               line = inputFile.get(lineNumber);
               words.add(line);
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
      
      words.add("ERROR");
      return words;
   }
  
   // write the word diagonally down
   public boolean diagonalDown(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < RETRY)
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
            return false;
         }
      }

      return true;
   }
   
   // write the word diagonally up
   public boolean diagonalUp(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < RETRY)
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
            return false;
         }
      }

      return true;
   }

   // write the word vertically down
   public boolean verticalDown(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < RETRY)
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

            return false;
         }
      }

      return true;
   }
   
   // write the word vertically up
   public boolean verticalUp(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < RETRY)
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

            return false;
         }
      }

      return true;
   }

   // write the word horizontally
   public boolean horizontal(char[][] board, String word, int row, int column)
   {
      int failCounter = 0;
      
      // loop until valid option is found or 5 fails
      while(failCounter < RETRY)
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
            return false;
         }
      }

      return true;
   }
   
   // create T-shaped words
   public void TWords(char[][] board, List<String> words, String word, int row, int column)
   {
      // fill horizontal word and update stats
      for(int i = 0; i < 5; i++)
      {
         board[row][column + i] = word.charAt(i);
      }
      
      int start = row * 50 + column;
      int end = start + 4;
      
      locations.add(new WordLocation(start, end));
      wordsFilled++;
      horz++;
      
      Iterator<String> iterator = words.iterator();
      
      // loop through ArrayList of words
      while(iterator.hasNext())
      {
         String TWord = iterator.next();
         
         //System.out.println(TWord.charAt(0) + " " + word.charAt(2));
         
         // see if next word's first letter is equivalent to horizontal word's 3rd letter
         if(TWord.charAt(0) == word.charAt(2))
         {
            // place word vertically
            for(int k = 1; k < 5; k++)
            {
               board[row + k][column + 2] = TWord.charAt(k);
            }
            
            // update stats and remove word from list
            start = row * 50 + column + 2;
            end = start + (4 * 50);
            locations.add(new WordLocation(start, end));
            //System.out.println(locations);
            iterator.remove();
            
            wordsFilled++;
            vertDown++;
            break;
         }
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
      vertDown < 0.15 || vertUp < 0.15 || horz < 0.15 || TCounter < 9)
      {
         density = 0.67;
         diagDown = 0;
         diagUp = 0;
         vertUp = 0;
         vertDown = 0;
         horz = 0;
         wordsFilled = 0;
         TCounter = 0;
         retry = 0;
         
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
      System.out.printf("T-Shaped Words: %d", TCounter);
   }
   
   
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
      
      statistics();
   }*/
}