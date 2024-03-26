package uta.cse3310;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordBank
{
   static int gridSize = 2500;
   static int cellsToFill;
   static int cellsFilled = 0;
   static double diagDown, diagUp, vertUp, vertDown, horz = 0;
   static double density = 0.67;
   static Random random = new Random();

   // generate Board 50*50
   public static char[][] generateGrid()
   {
      cellsToFill = (int)(gridSize * density);
      
      // initialize board
      char[][] board = new char[50][50];
      for(int i = 0; i < 50; i++)
      {
         for(int j = 0; j < 50; j++)
         {
            board[i][j] = '-';
         }
      }
      
      //System.out.println(cellsToFill);
      
      while(cellsFilled < cellsToFill)
      {
         String wordToInsert = selectWord();
         //System.out.println(wordToInsert);
         
         int RDVH = random.nextInt(5) + 1;
         //System.out.print(RDVH); 
         
         switch(RDVH)
         {
            case 1:
               diagonalDown(board, wordToInsert);
               break;
            case 2:
               diagonalUp(board, wordToInsert);
               break;
            case 3:
               verticalDown(board, wordToInsert);
               break;
            case 4:
               verticalUp(board, wordToInsert);
               break;
            case 5:
               horizontal(board, wordToInsert);
               break;
         }
      }
      
      density = (double)cellsFilled / gridSize;
      
      //insertRandomLetters(board);
      
      checkBoard(board);
      
      return board;
   }
  
   // select a word from the word bank
   public static String selectWord()
   {
      // find random line number
      int totalLine = 700;
      int chosenLine = random.nextInt(totalLine) + 1;
    
      // selection of word from WordList.txt
      try(BufferedReader reader = new BufferedReader(new FileReader("C:/Users/maber/OneDrive/Documents/Programming/Programming C/3310 Fundamentals of Software Engineering/cse3310_sp24_group_13/src/main/java/uta/cse3310/WordList.txt")))
      {
         String line = reader.readLine();
         int currentLine = 0;
         while(line != null)
         {
            currentLine++;
            if(currentLine == chosenLine)
            {
               // all words are 5 letters
               return line;
            }
            
            line = reader.readLine();
         }
      }
     
      // print error message if opening the file fails
      catch(IOException e)
      {
         e.printStackTrace();
      }
    
      return "ERROR";
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
            
            cellsFilled += word.length();
            diagDown += word.length();
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
            
            cellsFilled += word.length();
            diagUp += word.length();
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
            
            cellsFilled += word.length();
            vertDown += word.length();
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
            
            cellsFilled += word.length();
            vertUp += word.length();
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
            
            cellsFilled += word.length();
            horz += word.length();
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
   public static void checkBoard(char[][] board)
   {
      diagDown = diagDown / cellsFilled;
      diagUp = diagUp / cellsFilled;
      vertDown = vertDown / cellsFilled;
      vertUp = vertUp / cellsFilled;
      horz = horz / cellsFilled;
      
      if(diagDown < 0.15 || diagUp < 0.15 ||
      vertDown < 0.15 || vertUp < 0.15 || horz < 0.15)
      {
         diagDown = 0;
         diagUp = 0;
         vertUp = 0;
         vertDown = 0;
         horz = 0;
         cellsFilled = 0;
         
         generateGrid();
      }
   }

   // store board info
   public static void statistics()
   {  
      System.out.printf("Density: %.2f\n", density);
      System.out.printf("Diagonal Down: %.2f\n", diagDown);
      System.out.printf("Diagonal Up: %.2f\n", diagUp);
      System.out.printf("Vertical Down: %.2f\n", vertDown);
      System.out.printf("Vertical Up: %.2f\n", vertUp);
      System.out.printf("Horizontal: %.2f\n", horz);
   }
}