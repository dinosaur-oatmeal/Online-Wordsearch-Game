package uta.cse3310;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Leaderboard {
    protected Map<String, Integer> scores; // Nickname, Score

    public Leaderboard() {
        scores = new HashMap<>();
    }
    public void displayLeaderboard() {
        // Implementation goes here
        PriorityQueue<Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(scores.entrySet());

        // Print the leaderboard
        System.out.println("Leaderboard:");
        while (!pq.isEmpty()) {
            Entry<String, Integer> player = pq.poll();
            System.out.println(player.getKey() + ": " + player.getValue());
        }
    }

    public void updateLeaderboard(String nickname) {
        // Implementation goes here
           if (scores.containsKey(nickname)) {
            scores.put(nickname, scores.get(nickname) + 1);
        } else {
            scores.put(nickname, 1);
        }
    }
}
