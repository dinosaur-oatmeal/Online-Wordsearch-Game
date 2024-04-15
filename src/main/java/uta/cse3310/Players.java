package uta.cse3310;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Players {

    private String selectedColor;
    private int wordsFound;
    private Map<String, String> playerColors;
    private Map<String, Integer> wordsFoundByPlayer;

    public Players() {
        playerColors = new HashMap<>();
        wordsFoundByPlayer = new HashMap<>();
    }

    // Method to store player's nickname
    public void storeName(String nickname) {
        // Store the player's nickname in the playerColors map
        playerColors.put(nickname, null);
        System.out.println("Nickname: " + nickname);
    }

    public Set<String> getAllPlayerNames() {
        return playerColors.keySet();
    }

    // Method to update player's stats (e.g., words found)
    public void updateStats(String nickname, int wordsFound) {
        // Update the words found by the player
        wordsFoundByPlayer.put(nickname, wordsFoundByPlayer.getOrDefault(nickname, 0) + wordsFound);
        // You might want to do more with the stats, like calculating score
        // For now, let's just print the updated stats
        System.out.println("Words Found by " + nickname + ": " + wordsFoundByPlayer.get(nickname));
    }

    // Method to get the number of words found by a player
    public int getWordsFound(String nickname) {
        return wordsFoundByPlayer.getOrDefault(nickname, 0);
    }

   // Method to select color for the player
    public void selectPlayerColor(String nickname) {
        // List of available colors
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Orange", "Purple"};

        // Hash the nickname to get a random index within the range of colors array
        int hashCode = Math.abs(nickname.hashCode());
        int colorIndex = hashCode % colors.length;

        // Set the selected color corresponding to the generated index
        selectedColor = colors[colorIndex];
    }

    // Getter method to retrieve the selected color for a player
    public String getSelectedColor() {
        return selectedColor;
    }
}
