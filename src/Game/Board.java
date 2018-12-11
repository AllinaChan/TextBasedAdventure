package Game;

import Rooms.Room;

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
            this.length=5;
            this.width=5;
            this.map= new Room[5][5];
        }

        if (difficulty.equals("M")||difficulty.equals("m"))
        {
            this.length=10;
            this.width=10;
            this.map= new Room[10][10];
        }

        if (difficulty.equals("H")||difficulty.equals("h"))
        {
            this.length=15;
            this.width=15;
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
        this.length=length;
        this.width=width;
        this.map= new Room[length][width];
    }

    public Room[][] getBoard()
    {
        return this.map;
    }

    public void print()
    {
        String result="";
        for (int i = 0; i<map.length; i++ )
        {
            result="";
            for (int j =0; j< map[i].length; j++)
            {
                result = result+this.map[i][j].toString();
            }
            System.out.println(result);
        }
    }

}
