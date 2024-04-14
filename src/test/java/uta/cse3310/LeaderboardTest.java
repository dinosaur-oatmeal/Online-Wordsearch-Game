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
        leaderboard.updateLeaderboard("Player2", 3);
        leaderboard.updateLeaderboard("Player3");
        leaderboard.updateLeaderboard("Player1");
        leaderboard.updateLeaderboard("Player2");

        leaderboard.displayLeaderboard();

        // Check if the leaderboard is displayed in the correct sorted order
        assertTrue(leaderboard.scores.get("Player1") > leaderboard.scores.get("Player2"));
        assertTrue(leaderboard.scores.get("Player2") > leaderboard.scores.get("Player3"));
        System.out.println("testSortedLeaderboard completed");
    }
}
  
    

