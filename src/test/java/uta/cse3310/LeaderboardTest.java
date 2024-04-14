package uta.cse3310;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

public class LeaderboardTest extends TestCase {
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
    leaderboard.updateLeaderboard("Player1");
    leaderboard.updateLeaderboard("Player2");
    leaderboard.updateLeaderboard("Player1");

    assertEquals(2, leaderboard.scores.get("Player1").intValue());
    assertEquals(1, leaderboard.scores.get("Player2").intValue());
    System.out.println("testUpdateLeaderboard completed");
  }

  public void testDisplayLeaderboard() {
        leaderboard.updateLeaderboard("Player1");
        leaderboard.updateLeaderboard("Player2");
        leaderboard.updateLeaderboard("Player3");
        leaderboard.updateLeaderboard("Player1");
        
        leaderboard.displayLeaderboard();
    
        assertEquals(3, leaderboard.scores.size()); 
        System.out.println("testDisplayLeaderboard completed");
    }

  public void testSortedLeaderboard() {
        leaderboard.updateLeaderboard("Player1");
        leaderboard.updateLeaderboard("Player2");
        leaderboard.updateLeaderboard("Player3");
        leaderboard.updateLeaderboard("Player1");
        leaderboard.updateLeaderboard("Player2");

        leaderboard.displayLeaderboard();

       Iterator<Map.Entry<String, Integer>> it = leaderboard.scores.entrySet().iterator();
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
}
  
    

   

