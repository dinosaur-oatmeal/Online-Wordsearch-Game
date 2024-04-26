package uta.cse3310;
// User events are sent from the webpage to the server

public class UserEvent {
    int GameId; // the game ID on the server
    PlayerType PlayerIdx; // either Player1, Player2, Player3, or Player4
    int Button; // button number from 0 to 2500
    int row;
    int column;
    String action;
    int maxPlayers;

    UserEvent() {

    }

    UserEvent(int _GameId, PlayerType _PlayerIdx, int _Button, int _row, int _column, String _action, int _maxPlayers) {
        GameId = _GameId;
        PlayerIdx = _PlayerIdx;
        Button = _Button;
        row = _row;
        column = _column;
        action = _action;
        maxPlayers = _maxPlayers;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public int getMaxPlayers()
    {
        return maxPlayers;
    }

    public int getGameId()
    {
        return GameId;
    }
}