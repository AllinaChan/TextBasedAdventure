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

    /**
     *
     * @param difficulty- Creating a board based on preset difficulties
     */
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

    /**
     *
     * @param length- User input- length of board
     * @param width- User input - width of board
     */

    public Board (int length, int width)
    {
        this.map= new Room[length][width];
    }

    public Room[][] getBoard()
    {
        return this.map;
    }

    public void print()
    {
        String row ="";
        for ( int i=0; i< this.map.length; i++)
        {
            row="";
            for (int j =0; j<map[i].length; j++)
            {
                row+=this.map[i][j].toString();
            }
            System.out.println(row);
        }
    }

}
