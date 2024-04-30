package uta.cse3310;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.AbstractMap;

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
        for (Entry<String, Integer> entry : topScores) {
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
}
