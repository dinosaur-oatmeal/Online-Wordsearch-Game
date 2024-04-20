package uta.cse3310;

// A player can be Player1, Player2, Player3, or Player4

public enum PlayerType {
    NOPLAYER(0),
    Player1(1),
    Player2(2),
    Player3(3),
    Player4(4);

    private final int value;

    PlayerType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
 
