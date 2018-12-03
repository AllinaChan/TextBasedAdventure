package Game;

import People.Person;
import Rooms.Room;
import Rooms.RoomInConstruction;
import Rooms.WinningRoom;

public class Board
{
    private Room[][] map;
    private String difficulty;
    private int length;
    private int width;

    public Board (String difficulty)
    {
        this.difficulty=difficulty;
        if (difficulty.equals("E")||difficulty.equals("e"))
        {
            this.map= new Room[5][5];
        }

        if (difficulty.equals("M")||difficulty.equals("m"))
        {
            this.map= new Room[10][10];
        }

        if (difficulty.equals("H")||difficulty.equals("h"))
        {
            this.map= new Room[15][15];
        }
    }


    public Board (int length, int width)
    {
        this.map= new Room[length][width];
    }

    public Room[][] getBoard()
    {
        return this.map;
    }



}
