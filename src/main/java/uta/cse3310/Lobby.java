package uta.cse3310;

import java.util.HashMap;
import java.util.Map;

public class Lobby {
    private Map<String, String> players; // Nickname, Player

    public Lobby() {
        players = new HashMap<>();
    }

    public void waitingPlayers() {
        // Return the list of players waiting in the lobby
        for (String nickname : players.keySet()) {
            System.out.println(nickname);
        }
    }


    public void addPlayer(String nickname) {
        // Add a new player to the lobby
        if (!players.containsKey(nickname)) {
            players.put(nickname, nickname);
            System.out.println("Player " + nickname + " added to the lobby.");
        } else {
            System.out.println("Player " + nickname + " already exists in the lobby.");
        }
    }

    public String messagePlayer(String nickname, String message) {
        // Send a message to a specific player
        if (players.containsKey(nickname)) {
            System.out.println("Sending message to " + nickname + ": " + message);
            return "Message sent to " + nickname;
        } else {
            System.out.println("Player " + nickname + " not found in the lobby.");
            return "Player not found";
        }
    }

    public void joinMatch(String nickname) {
        if (players.containsKey(nickname)) {
            players.remove(nickname);
            System.out.println("Player " + nickname + " has joined a match.");
        } else {
            System.out.println("Player " + nickname + " not found in the lobby.");
        }
    }
}
