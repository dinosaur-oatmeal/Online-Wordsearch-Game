package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class LobbyTest extends TestCase {
    private Lobby lobby;

    public LobbyTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LobbyTest.class);
    }

    public void setUp() {
        lobby = new Lobby();
    }

    public void testAddPlayer() {
        lobby.addPlayer("Player1");
        lobby.addPlayer("Player2");

        assertTrue(lobby.players.containsKey("Player1"));
        assertTrue(lobby.players.containsKey("Player2"));

        lobby.addPlayer("Player1"); 
        assertEquals(2, lobby.players.size());
        System.out.println("testAddPlayer completed");
    }

    public void testWaitingPlayers() {
        lobby.addPlayer("Player1");
        lobby.addPlayer("Player2");
        lobby.addPlayer("Player3");

        lobby.waitingPlayers();
        System.out.println("testWaitingPlayers completed");
    }

    public void testMessagePlayer() {
        lobby.addPlayer("Player1");
        lobby.addPlayer("Player2");

        String message = lobby.messagePlayer("Player1", "Hello!");
        assertEquals("Message sent to Player1", message);

        message = lobby.messagePlayer("Player3", "Hello!");
        assertEquals("Player not found", message);
        System.out.println("testMessagePlayer completed");
    }

    public void testJoinMatch() {
        lobby.addPlayer("Player1");
        lobby.addPlayer("Player2");

        lobby.joinMatch("Player1");
        assertFalse(lobby.players.containsKey("Player1"));
        assertTrue(lobby.players.containsKey("Player2"));

        lobby.joinMatch("Player3"); // Player not found
        assertEquals(1, lobby.players.size());
        System.out.println("testJoinMatch completed");
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
        System.out.println("All Lobby tests completed");
    }
}
