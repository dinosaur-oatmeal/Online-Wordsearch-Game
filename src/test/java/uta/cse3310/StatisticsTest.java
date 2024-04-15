package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StatisticsTest extends TestCase {
    private Statistics statistics;

    public StatisticsTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(StatisticsTest.class);
    }

    public void setUp() {
        statistics = new Statistics();
    }

    public void testStatistics() {
        // Create an instance of the Statistics class
        Statistics stats = new Statistics();

        // Test initial values
        assertEquals(0L, (long) stats.getRunningTime());
        assertEquals(0, (int) stats.getTotalGames());
        assertEquals(0, (int) stats.getGamesInProgress());

        // Test setting and getting running time
        stats.setRunningTime(1000L);
        assertEquals(1000L, (long) stats.getRunningTime());

        // Test setting and getting total games
        stats.setTotalGames(10);
        assertEquals(10, (int) stats.getTotalGames());

        // Test setting and getting games in progress
        stats.setGamesInProgress(5);
        assertEquals(5, (int) stats.getGamesInProgress());

        // Test incrementing total games
        stats.incrementTotalGames();
        assertEquals(11, (int) stats.getTotalGames());

        // Test decrementing total games
        stats.decrementTotalGames();
        assertEquals(10, (int) stats.getTotalGames());

        // Test incrementing games in progress
        stats.incrementGamesInProgress();
        assertEquals(6, (int) stats.getGamesInProgress());

        // Test decrementing games in progress
        stats.decrementGamesInProgress();
        assertEquals(5, (int) stats.getGamesInProgress());

        // Test updating running time
        stats.updateRunningTime(500L);
        assertEquals(1500L, (long) stats.getRunningTime());
    }
}