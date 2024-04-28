
// This is example code provided to CSE3310 Fall 2022
// You are free to use as is, or changed, any of the code provided

// Please comply with the licensing requirements for the
// open source packages being used.

// This code is based upon, and derived from the this repository
//            https:/thub.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package uta.cse3310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.time.Instant;
import java.time.Duration;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uta.cse3310.WordBankGame;


public class App extends WebSocketServer
{

  // All games currently underway on this server are stored in
  // the vector activeGames
  private Vector<GameSession> activeGames = new Vector<GameSession>();

  private int gameId = 1;

  private int connectionId = 0;

  private Instant startTime;

  private Statistics stats;

  private int maxPlayers;

  ArrayList<String> users = new ArrayList<>();

  public App(int port)
  {
    super(new InetSocketAddress(port));
  }

  public App(InetSocketAddress address)
  {
    super(address);
  }

  public App(int port, Draft_6455 draft)
  {
    super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
  }

  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake)
  {
    // only get connectionId when connection is made
    // sent when client clicks button to enter lobby
    connectionId++;
    //System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote)
  {
    System.out.println(conn + " has closed");

    // Retrieve the game tied to the websocket connection and remove
    GameSession G = conn.getAttachment();
    activeGames.remove(G);
    G = null;
  }

  @Override
  public void onMessage(WebSocket conn, String message)
  {
    //System.out.println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "-" + " " + escape(message));

    // Bring in the data from the webpage
    // A UserEvent is all that is allowed at this point
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    UserEvent U = gson.fromJson(message, UserEvent.class);

    // user event for when client joins game
    if("joinGame".equals(U.getAction()))
    {
      //System.out.println(U.getAction());
      ServerEvent E = new ServerEvent();
      GameSession G = null;

      // see what game they're trying to join
      int maxPlayers = U.getMaxPlayers();

      // match found
      for(GameSession i : activeGames)
      {
        // lobby is open to accept new players
        if(!i.isFull())
        {
          G = i;
          //System.out.println("found a match");
        }

      }

      // new game initialization
      if(G == null)
      {
        G = new GameSession(stats);
        G.gameId = gameId;
        gameId++;

        G.player = PlayerType.Player1;

        G.setPlayers(maxPlayers);
        G.players[0] = PlayerType.Player1;
        activeGames.add(G);
        System.out.println("Starting a new game");
      }

      // join existing game
      else
      {
          System.out.println("Joining existing game");

          // add player 2
          if(G.players[0] != null && G.players[1] == null)
          {
            G.player = PlayerType.Player2;
            G.players[1] = PlayerType.Player2;
            System.out.println("Player 2 added");
          }

          // add player 3
          else if(G.players[1] != null && G.players[2] == null)
          {
            G.player = PlayerType.Player3;
            G.players[2] = PlayerType.Player3;
            System.out.println("Player 3 added");
          }

          // add player 4
          else
          {
            G.player = PlayerType.Player4;
            G.players[3] = PlayerType.Player4;
            System.out.println("Player 4 added");
          }

          // start game once lobby is full
          if(G.isFull())
          {
            G.startGame();
          }
      }

      E.YouAre = G.player;
      E.gameId = G.gameId;

      // allows the websocket to give us the Game when a message arrives..
      // it stores a pointer to G, and will give that pointer back to us
      // when we ask for it
      conn.setAttachment(G);

      // note only send to the single connection
      String jsonString = gson.toJson(E);
      conn.send(jsonString);

      String boardJson = gson.toJson(G.board);
      //System.out.println(boardJson);
      conn.send(boardJson);

      //System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + connectionId + " " + escape(jsonString));
      
      // Update the running time
      stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());
      // The state of the game has changed, so lets send it to everyone
      jsonString = gson.toJson(G);
      //System.out.println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
      broadcast(jsonString);

      // return from function because nothing below is needed
      return;
    }


    // Get our Game Object
    GameSession G = conn.getAttachment();
    
    // print message to see what's being passed from Index.html
    System.out.println(message);

    // selectCharacter action from Index.html
    if("selectCharacter".equals(U.getAction()))
    {
      //System.out.println("char selected");
      int row = U.getRow();
      int column = U.getColumn();
      int gameId = U.GameId;

      // convert PlayerType to an int (easier to deal with than enum)
      int typeInt = U.PlayerIdx.getValue();

      //System.out.println("\n Row: " + row + " Column: " + column + " Type: " + typeInt + "\n");

      // call charSelected function in GameSession.java
      boolean character = G.charSelected(row * 50 + column, typeInt);

      // broadcast gameOver function to Index.html if wordsFilled = wordsToFill
      if(!character)
      {
        String jsonString;
        jsonString = gson.toJson(G);

        // manually add action to the end of jsonString for it to be parsed by client
        // (easiest way I found to append data to the end of the string manually)
        int endOfString = jsonString.lastIndexOf('}');
        jsonString = jsonString.substring(0, endOfString) + ", \"action\": \"gameOver\"" + jsonString.substring(endOfString);
        broadcast(jsonString);
      }

      // find word positions
      List<Integer> wordPositions = G.wordPositions;
      //System.out.println("\n" + wordPositions + "\n");

      // word found and should send all associated buttons to be highlighted
      if(wordPositions != null)
      {
        sendHighlightPositions(conn, wordPositions, typeInt, gameId);
      }

      // word not found, so only send selected position to be highlighted
      else
      {
        wordPositions = new ArrayList<>();
        wordPositions.add(row * 50 + column);
        sendHighlightPositions(conn, wordPositions, typeInt, gameId);
      }
    }

    // stats action from Index.html
    if("stats".equals(U.getAction()))
    {
      String jsonString;
      jsonString = gson.toJson(G);

      // manually add action to the end of jsonString for it to be parsed by client
      int endOfString = jsonString.lastIndexOf('}');
      jsonString = jsonString.substring(0, endOfString) + ", \"action\": \"showStats\"" + jsonString.substring(endOfString);
      //System.out.println("\n" + jsonString + "\n");

      // only send to client who requested to see the stats
      conn.send(jsonString);
    }

    if("newMessage".equals(U.getAction()))
    {
      // Get the message text from the UserEvent
      String newMessage = U.getMessage();

      // Create a message object to be sent to all clients
      Map<String, Object> messageObject = new HashMap<>();
      messageObject.put("action", "displayMessage");
      messageObject.put("message", newMessage);
      String jsonString = gson.toJson(messageObject);

      // Broadcast the message to all connected clients
      broadcast(jsonString);
    }

    if("userJoin".equals(U.getAction()))
    {
      users.add(U.getUsername());
      Map<String, ArrayList> userList = new HashMap<>();
      userList.put("userList", users);
      String jsonString = gson.toJson(userList);
      //System.out.println(jsonString);
      broadcast(jsonString);
      System.out.println(users);
      return;
    }

    if("userLeave".equals(U.getAction()))
    {
      users.remove(U.getUsername());
      Map<String, Object> userList = new HashMap<>();
      userList.put("userList", userList);
      String jsonString = gson.toJson(userList);
      broadcast(jsonString);
      return;
    }

    // Update the running time
    stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

    // update the game's status
    G.update(U);

    // send out the game state every time to everyone
    String jsonString;
    jsonString = gson.toJson(G);

    //System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
    broadcast(jsonString);
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message)
  {
    System.out.println(conn + ": " + message);
  }

  @Override
  public void onError(WebSocket conn, Exception ex)
  {
    ex.printStackTrace();
    if (conn != null)
    {
      // some errors like port binding failed may not be assignable to a specific
      // websocket
    }
  }

  @Override
  public void onStart()
  {
    setConnectionLostTimeout(0);
    stats = new Statistics();
    startTime = Instant.now();
  }

  private String escape(String S)
  {
    // turns " into \"
    String retval = new String();
    // this routine is very slow.
    // but it is not called very often
    for (int i = 0; i < S.length(); i++)
    {
      Character ch = S.charAt(i);
      if (ch == '\"')
      {
        retval = retval + '\\';
      }
      retval = retval + ch;
    }
    return retval;
  }

  // method to send positions to index.html
  public void sendHighlightPositions(WebSocket conn, List<Integer> positions, int typeInt, int gameId)
  {
    Gson gson = new Gson();

    // ArrayList of maps containing string to be parsed and object data
    List<Map<String, Object>> positionsWithIdx = new ArrayList<>();
    GameSession G = null;

    // get associated game in activeGames vector
    for(GameSession i : activeGames)
    {
      if(gameId == i.gameId)
      {
        G = i;
      }
    }
    
     // loop through input ArrayList
    for (Integer position : positions)
    {
        // store position and PlayerIdx to be parsed in Index.html
        Map<String, Object> positionEntry = new HashMap<>();
        positionEntry.put("position", position);
        positionEntry.put("PlayerIdx", typeInt);
        positionsWithIdx.add(positionEntry);
    }

    // store action, positions, and scores
    Map<String, Object> message = new HashMap<>();
    message.put("action", "highlightWords");
    message.put("positions", positionsWithIdx);
    message.put("gameId", gameId);
    message.put("Player1Score", G.Player1Score);
    message.put("Player2Score", G.Player2Score);
    message.put("Player3Score", G.Player3Score);
    message.put("Player4Score", G.Player4Score);
    String jsonString = gson.toJson(message);

    //System.out.println("\n" + jsonString + "\n");

    // send to everyone (client will decide if its' relevant)
    broadcast(jsonString);
  }

  public static void main(String[] args)
  {
    // Set up the http server
    int port = 9013;
    HttpServer H = new HttpServer(port, "./html");
    H.start();
    System.out.println("http Server started on port: " + port);

    // create and start the websocket server
    port = 9113;
    App A = new App(port);
    A.setReuseAddr(true);
    A.start();
    System.out.println("websocket Server started on port: " + port);

  }
}