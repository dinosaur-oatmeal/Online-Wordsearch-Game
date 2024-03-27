//package uta.cse3310;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordBank
{
   static int gridSize = 2500;
   static int wordsToFill;
   static int wordsFilled = 0;
   static double diagDown, diagUp, vertUp, vertDown, horz = 0;
   static double density = 0.67;
   static Random random = new Random();
   
   // arraylist that will store word locations
   static List<WordLocation> locations = new ArrayList<>();

   public static char[][] generateGrid()
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
         
         switch(RDVH)
         {
            case 1:
               diagonalDown(board, words[wordsFilled]);
               break;
            case 2:
               diagonalUp(board, words[wordsFilled]);
               break;
            case 3:
               verticalDown(board, words[wordsFilled]);
               break;
            case 4:
               verticalUp(board, words[wordsFilled]);
               break;
            case 5:
               horizontal(board, words[wordsFilled]);
               break;
         }
      }
      
      //insertRandomLetters(board);
      
      checkBoard(board);
      
      return board;
   }
  
   // select a word from the word bank
   public static String[] selectWords()
   {
      // initialize array to size to match density
      String[] words = new String[wordsToFill];
      //System.out.println(words.length);
      
      // selection of word from WordList.txt
      try(BufferedReader reader = new BufferedReader(new FileReader("WordList.txt")))
      {
         for(int i = 0; i < words.length; i++)
         {
            String line = reader.readLine();

               // all words are 5 letters
               words[i] = line;
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
   public static void diagonalDown(char[][] board, String word)
   {
      int row = random.nextInt(board.length - 4);
      int column = random.nextInt(board.length - 4);
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
            
            locations.add(new WordLocation(row, column, row + 4, column + 4));
            
            // add to variables for statistics
            wordsFilled++;
            diagDown++;
         }
         
         break;
      }
   }
   
   // write the word diagonally up
   public static void diagonalUp(char[][] board, String word)
   {
      int row = random.nextInt(board.length - 4);
      int column = random.nextInt(board.length - 4);
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
            
            locations.add(new WordLocation(row + 4, column, row, column + 4));
            
            // add to variables for statistics
            wordsFilled++;
            diagUp++;
         }
         
         break;
      }
   }

   // write the word vertically down
   public static void verticalDown(char[][] board, String word)
   {
      int row = random.nextInt(board.length - 4);
      int column = random.nextInt(board.length);
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
            
            locations.add(new WordLocation(row, column, row + 4, column));
            
            // add to variables for statistics
            wordsFilled++;
            vertDown++;
         }
            
         break;
      }
   }
   
   // write the word vertically up
   public static void verticalUp(char[][] board, String word)
   {
      int row = random.nextInt(board.length - 4);
      int column = random.nextInt(board.length);
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
            
            locations.add(new WordLocation(row, column, row + 4, column));
            
            // add to variables for statistics
            wordsFilled++;
            vertUp++;
         }
            
         break;
      }
   }

   // write the word horizontally
   public static void horizontal(char[][] board, String word)
   {
      int row = random.nextInt(board.length);
      int column = random.nextInt(board.length - 4);
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
            
            locations.add(new WordLocation(row, column, row, column + 4));
            
            // add to variables for statistics
            wordsFilled++;
            horz++;
         }
            
         break;
      }
   }

   // insert random letter in the board
   public static void insertRandomLetters(char[][] board)
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
   public static void checkBoard(char[][] board)
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
   public static void statistics()
   {   
      System.out.printf("Density: %.2f\n", density);
      System.out.printf("Diagonal Down: %.2f\n", diagDown);
      System.out.printf("Diagonal Up: %.2f\n", diagUp);
      System.out.printf("Vertical Down: %.2f\n", vertDown);
      System.out.printf("Vertical Up: %.2f\n", vertUp);
      System.out.printf("Horizontal: %.2f\n", horz);
   }
   
   // debugging outputs
   public static void main(String args[])
   {
      char[][] bank = generateGrid();
      statistics();
      
      for(int i = 0; i < 50; i++)
      {
         for(int j = 0; j < 50; j++)
         {
            System.out.print(bank[i][j] + " ");
         }
         
         System.out.print("\n");
      }
      
      /*
      for (WordLocation location : locations)
      {
         System.out.println(location);
      }*/
   }
}