package uta.cse3310;

public class WordBankGame {
    private String nickname;
    private UserAuthentication userAuth;
    private Players players;
    private Lobby lobby;
    private Leaderboard leaderboard;
    private GameSession gameSession;

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
        return 0;
    }
}



