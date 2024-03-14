package uta.cse3310;

import java.util.Random;

public class WordBank {
  
  // Generate Grid 50*50
  public char[][] generateGrid() {
    char[][] grid = generateGridImpl(50, 50, '-');
    return grid;
  }
  
  //Select a word from the word bank  
  public String selectWord() {
    // Selection of word from Words.txt 
    return "WORD";
  
  //Reverse a given word  
  public String reverse(String word) {
          // Implementation to reverse the word
  return new StringBuilder(word).reverse().toString();
  
  //Write the word diagonal
  public void diagonal(String word) {
    //Process the word diagonally
  }

  //Write the word vertically
  public void vertial(String word) {
    //Process the word vertically
  }

  //Insert random letter in the board
  public void insertRandomLetters(char[][] board) {
    Random r = new Random();
  }

  //
  public void checkBoard(char[][] board) {

  }

  //Store user info
  public void statistics() {

  }
    
    

  
    
    

  

  
  
