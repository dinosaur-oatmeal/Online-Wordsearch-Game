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
        
    }
