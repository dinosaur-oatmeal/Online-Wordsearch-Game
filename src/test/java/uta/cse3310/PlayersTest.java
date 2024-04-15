package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

public class PlayersTest extends TestCase {
    private Players players;

    public PlayersTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(PlayersTest.class);
    }

    public void setUp() {
        players = new Players();
    }



}
