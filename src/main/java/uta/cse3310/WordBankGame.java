package uta.cse3310;

public class WordBankGame {
    public PlayerType Players;
    public PlayerType CurrentTurn;
    public int GameId;

    public Statistics Stats;
    public String[] Msg;

    public String nickname;
    public UserAuthentication userAuth;
    public Players players;
    public Lobby lobby;
    public Leaderboard leaderboard;
    public GameSession gameSession;

    WordBankGame(Statistics s)
    {
        Stats = s;
        Players = PlayerType.Player1;
        CurrentTurn = PlayerType.NOPLAYER;
        Msg = new String[2];
        Msg[0] = "Waiting for other player to join";
        Msg[1] = "";
    }

    public WordBankGame(String nickname) {
        this.nickname = nickname;
        this.userAuth = new UserAuthentication();
        this.players = new Players();
        this.lobby = new Lobby();
        this.leaderboard = new Leaderboard();
        this.gameSession = new GameSession();
    }

    public int openSpots() {
        // Calculate open spots in the game
        return 0; // Placeholder
    }

    public void tick() {
        // Advances game 
    }
}