package uta.cse3310;
// User events are sent from the webpage to the server

public class UserEvent {
    int GameId; // the game ID on the server
    PlayerType PlayerIdx; // either an XPLAYER or an OPLAYER
    int Button; // button number from 0 to 8

    UserEvent() {
        GameId = 0;
        PlayerIdx = PlayerType.NOPLAYER;
        Button = 0;
    }

    UserEvent(int _GameId, PlayerType _PlayerIdx, int _Button) {
        GameId = _GameId;
        PlayerIdx = _PlayerIdx;
        Button = _Button;
    }
}