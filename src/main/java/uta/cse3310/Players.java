package uta.cse3310;
    
public class Players {

  // Method to store player's nickname
  public void storeName(String nickname) {
    this.nickname = nickname;  
  }

  // Method to update player's stats (e.g., words found)
  public void updateStats(int wordFound) {
    // Update the player's score 
    this.score += wordFound;  
  }

  // Method to select color for the player 
  public String selectPlayerColor(String nickname) {
    availableColors ;
    return availableColors;
  }
}
