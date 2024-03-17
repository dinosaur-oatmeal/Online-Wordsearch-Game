package uta.cse3310;

import java.util.Random;

public class WordBank{
  
  // Generate Grid 50*50
  public char[][] generateGrid() {
    char[][] grid = new char[50][50];
    grid[50][50] = '-';
    return grid;
  }
  
  //Select a word from the word bank  
  public String selectWord() {
    // Selection of word from Words.txt 
    return "WORD";
  }
  
  //Reverse a given word  
  public String reverse(String word) {
          // Implementation to reverse the word
  return new StringBuilder(word).reverse().toString();
  }
  
  //Write the word diagonal
  public void diagonal(String word) {
    //Process the word diagonally
  }

  //Write the word vertically
  public void vertical(String word) {
    //Process the word vertically
  }

  //Write the word horizontally
  public void horizontal(String word) {
    //Process the word horizontally
  }

  //Insert random letter in the board
  public void insertRandomLetters(char[][] board) {
    Random r = new Random();
  }

  //Checks the board to make sure stats are valid
  public void checkBoard(char[][] board) {

  }

  //Store user info
  public void statistics() {

  }
}