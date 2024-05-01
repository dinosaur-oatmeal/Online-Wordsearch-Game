package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

/*public class LeaderboardTest extends TestCase {
    private Leaderboard leaderboard;

    public LeaderboardTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LeaderboardTest.class);
    }

    public void setUp() {
        leaderboard = new Leaderboard();
    }

    public void testUpdateLeaderboard() {
        leaderboard.updateLeaderboard("Player1", 1);
        leaderboard.updateLeaderboard("Player2", 1);
        leaderboard.updateLeaderboard("Player1", 2);

        assertTrue(leaderboard.getScores().get("Player1") == 2);
        assertTrue(leaderboard.getScores().get("Player2") == 1);

        System.out.println("testUpdateLeaderboard completed");
    }

    public void testDisplayLeaderboard() {
        leaderboard.updateLeaderboard("Player1", 1);
        leaderboard.updateLeaderboard("Player2", 2);
        leaderboard.updateLeaderboard("Player3", 3);
        leaderboard.updateLeaderboard("Player1", 4);

        leaderboard.displayLeaderboard();

        assertTrue(leaderboard.getScores().size() == 3);

        System.out.println("testDisplayLeaderboard completed");
    }

    public void testSortedLeaderboard() {
        leaderboard.updateLeaderboard("Player1", 1);
        leaderboard.updateLeaderboard("Player2", 2);
        leaderboard.updateLeaderboard("Player3", 3);
        leaderboard.updateLeaderboard("Player1", 4);
        leaderboard.updateLeaderboard("Player2", 5);

        leaderboard.displayLeaderboard();

        Iterator<Map.Entry<String, Integer>> it = leaderboard.getScores().entrySet().iterator();
        Integer prevScore = null;

        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            Integer currentScore = entry.getValue();

            if (prevScore != null) {
                assertTrue(prevScore >= currentScore); // Check if previous score is greater than or equal to current
            }

            prevScore = currentScore;
        }

        System.out.println("testSortedLeaderboard completed");
    }
}*/
