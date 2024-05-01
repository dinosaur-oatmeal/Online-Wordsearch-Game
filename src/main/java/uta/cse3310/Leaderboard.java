package uta.cse3310;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private static final int MAX_ENTRIES = 5;
    public Map<String, Integer> scores = new HashMap<>();
    private PriorityQueue<Entry<String, Integer>> topScores;

    public Leaderboard() {
        topScores = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public void displayLeaderboard() {
        System.out.println("Leaderboard (Top " + MAX_ENTRIES + "):");

        // Create a sorted list of entries from the topScores PriorityQueue
        List<Entry<String, Integer>> sortedEntries = new ArrayList<>(topScores);
        sortedEntries.sort((a, b) -> {
            int scoreComparison = b.getValue().compareTo(a.getValue());
            if (scoreComparison != 0) {
                return scoreComparison;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        });

        // Print the sorted entries
        for (Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void updateLeaderboard(String nickname, int score) {
        if (scores.containsKey(nickname)) {
            int currentScore = scores.get(nickname);
            if (score > currentScore) {
                topScores.remove(new AbstractMap.SimpleEntry<>(nickname, currentScore));
                scores.put(nickname, score);
                topScores.offer(new AbstractMap.SimpleEntry<>(nickname, score));
            }
        } else {
            scores.put(nickname, score);
            topScores.offer(new AbstractMap.SimpleEntry<>(nickname, score));
            if (topScores.size() > MAX_ENTRIES) {
                topScores.poll();
            }
        }
    }
    public List<Map<String, Object>> getLeaderboardData() {
        List<Map<String, Object>> leaderboardData = new ArrayList<>();
        for (Entry<String, Integer> entry : topScores) {
            Map<String, Object> playerData = new HashMap<>();
            playerData.put("nickname", entry.getKey());
            playerData.put("score", entry.getValue());
            leaderboardData.add(playerData);
        }
        return leaderboardData;
    }
}

}

