package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

public class LeaderboardTest extends TestCase
{
    public LeaderboardTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(LeaderboardTest.class);
    }

    // test that players can be added to the leaderboard
    public void testAddPlayer()
    {
        Leaderboard leaderboard = new Leaderboard();

        leaderboard.addPlayer("Player1");
        leaderboard.addPlayer("Player2");

        assertTrue(leaderboard.leaderboardList.get(0).containsKey("Player1"));
        assertTrue(leaderboard.leaderboardList.get(1).containsKey("Player2"));
    }

    // test to see if there can be multiple of same player in leaderboard
    public void testPlayerReuse()
    {
        Leaderboard leaderboard = new Leaderboard();

        leaderboard.addPlayer("Player1");
        leaderboard.addPlayer("Player2");
        leaderboard.addPlayer("Player1");
        leaderboard.addPlayer("Player1");
        leaderboard.addPlayer("Player3");
        leaderboard.addPlayer("Player1");

        assertTrue(leaderboard.leaderboardList.size() == 3);
    }

    // test to see if scores are updated correctly
    public void testUpdateScore()
    {
        Leaderboard leaderboard = new Leaderboard();

        leaderboard.addPlayer("Player1");
        leaderboard.addPlayer("Player2");
        leaderboard.addPlayer("Player3");

        leaderboard.updateScore("Player1");
        leaderboard.updateScore("Player1");
        leaderboard.updateScore("Player2");
        leaderboard.updateScore("Player1");

        int Player1Score = leaderboard.leaderboardList.get(0).get("Player1");
        int Player2Score = leaderboard.leaderboardList.get(1).get("Player2");
        int Player3Score = leaderboard.leaderboardList.get(2).get("Player3");

        assertTrue(Player1Score == 3);
        assertTrue(Player2Score == 1);
        assertTrue(Player3Score == 0);
    }

    // test 5 values in descending order
    public void testDisplayLeaderboard()
    {
        Leaderboard leaderboard = new Leaderboard();

        leaderboard.addPlayer("Player1");
        leaderboard.addPlayer("Player2");
        leaderboard.addPlayer("Player3");
        leaderboard.addPlayer("Player4");
        leaderboard.addPlayer("Player5");
        leaderboard.addPlayer("Player6");

        leaderboard.updateScore("Player5");
        leaderboard.updateScore("Player1");
        leaderboard.updateScore("Player2");
        leaderboard.updateScore("Player3");
        leaderboard.updateScore("Player4");
        leaderboard.updateScore("Player5");
        leaderboard.updateScore("Player3");
        leaderboard.updateScore("Player2");
        leaderboard.updateScore("Player1");
        leaderboard.updateScore("Player5");
        leaderboard.updateScore("Player1");
        leaderboard.updateScore("Player3");
        leaderboard.updateScore("Player1");
        leaderboard.updateScore("Player1");
        leaderboard.updateScore("Player3");

        List<Map<String, Integer>> lBoard = leaderboard.displayLeaderboard();

        // check board size
        assertTrue(lBoard.size() == 5);

        // check sorting
        for(int i = 0; i < lBoard.size() - 1; i++)
        {
            int score1 = lBoard.get(i).values().iterator().next();
            int score2 = lBoard.get(i + 1).values().iterator().next();
            assertTrue(score1 >= score2);
        }

    }
}
  
    

   

