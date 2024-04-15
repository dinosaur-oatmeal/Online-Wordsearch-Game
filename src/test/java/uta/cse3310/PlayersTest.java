package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Set;

public class PlayersTest extends TestCase {
    private Players players;

    public PlayersTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(PlayersTest.class);
    }

    public void setUp() {
        players = new Players();
    }

    public void testStoreName() {
        players.storeName("Player1");
        // Since storeName method only prints the nickname, you can't directly test its functionality
        // But you can test if it runs without errors
        System.out.println("testStoreName completed");
    }

    public void testGetAllPlayerNames() {
        players.storeName("Player1");
        players.storeName("Player2");
        players.storeName("Player3");

        Set<String> playerNames = players.getAllPlayerNames();

        assertTrue(playerNames.contains("Player1"));
        assertTrue(playerNames.contains("Player2"));
        assertTrue(playerNames.contains("Player3"));
        assertFalse(playerNames.contains("Player4")); // Ensure a player that wasn't added is not in the set

        System.out.println("testGetAllPlayerNames completed");
    }

    public void testUpdateStats() {
        players.updateStats("Player1", 5);
        // Since updateStats method only prints the updated stats, you can't directly test its functionality
        // But you can test if it runs without errors
        System.out.println("testUpdateStats completed");
    }

    public void testGetWordsFound() {
        players.updateStats("Player1", 5); // Let's say Player1 found 5 words
        players.updateStats("Player2", 7); // Let's say Player2 found 7 words

        assertEquals(5, players.getWordsFound("Player1"));
        assertEquals(7, players.getWordsFound("Player2"));
        assertEquals(0, players.getWordsFound("Player3")); // Player3 hasn't found any words yet

        System.out.println("testGetWordsFound completed");
    }

 public void testSelectPlayerColor() {
        // Create an instance of the Players class
        Players player = new Players();

        // Test nickname
        String nickname = "testNickname";

        // Select color for the player
        player.selectPlayerColor(nickname);

        // Get the selected color
        String selectedColor = player.getSelectedColor();

        // Validate that the selected color is not null
        assertNotNull(selectedColor);

        // Validate that the selected color is one of the available colors
        String[] availableColors = {"Red", "Green", "Blue", "Yellow", "Orange", "Purple"};
        boolean colorFound = false;
        for (String color : availableColors) {
            if (color.equals(selectedColor)) {
                colorFound = true;
                break;
            }
        }
        assertTrue("Selected color is one of the available colors", colorFound);
    }
}




