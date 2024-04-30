package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WholeGameTest extends TestCase 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public WholeGameTest(String testName) 
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() 
    {
        return new TestSuite(WholeGameTest.class);
    }

    private String update(GameSession G, String msg) 
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        UserEvent U = gson.fromJson(msg, UserEvent.class);
        G.update(U);
        String jsonString = gson.toJson(G);
        return jsonString;
    }

    public void testGameSession() {
        GameSession game = new GameSession(new Statistics());
        WordBank bank = new WordBank();
        String msg = new String();
        String result = new String();

        game.startGame();

        msg = "{\"GameId\":0,\"PlayerIdx\":\"Player1\",\"Button\":0,\"row\":0,\"column\":0,\"action\":\"selectCharacter\"}";
        result = update(game, msg);

        // check that density is correct
        assertTrue(result.contains("\"density\":0.67"));

        // see that Player1 was entered into the game
        assertTrue(result.contains("\"player\":\"Player1\""));

        // see that the gameId is 0 because that's the starting variable
        assertTrue(result.contains("\"gameId\":0"));

        // see that the running time is initialized
        assertTrue(result.contains("\"runningTime\":0"));

        msg = "{\"GameId\":0,\"PlayerIdx\":\"Player1\",\"Button\":0,\"row\":0,\"column\":0,\"action\":\"selectCharacter\"}";
        result = update(game, msg);

        msg = "{\"GameId\":0,\"PlayerIdx\":\"Player1\",\"Button\":25,\"row\":0,\"column\":5,\"action\":\"selectCharacter\"}";
        result = update(game, msg);

        // checking initial state of game with no words found
        assertTrue(result.contains("\"wordsFound\":0"));

        //System.out.println(result);

        assertFalse(result.contains("\"gameOver\":true"));
        assertTrue(result.contains("\"gameId\":0"));

        // test selection of characters
        msg = "{\"GameId\":0,\"PlayerIdx\":\"Player1\",\"Button\":1225,\"row\":24,\"column\":25,\"action\":\"selectCharacter\"}";
        result = update(game, msg);

        assertTrue(result.contains("\"Player1\""));

        msg = "{\"action\":\"checkBoard\"}";
        result = update(game, result);

        assertTrue(result.contains("\"board\":"));

        assertTrue(result.contains("\"Player1Score\":0"));
        assertTrue(result.contains("\"Player2Score\":0"));
        assertTrue(result.contains("\"Player3Score\":0"));
        assertTrue(result.contains("\"Player4Score\":0"));

// Testing player joining
//        msg = "{\"GameId\":0,\"PlayerIdx\":\"Player1\",\"action\":\"joinMatch\"}";
//        result = update(game, msg);

//        assertTrue(result.contains("\"Player1\":true"));

    }
}