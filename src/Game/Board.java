package Game;

import Rooms.Room;

public class Board
{
    private Room[][] map;
    private String difficulty;
    private int height;
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
            this.height=5;
            this.width=5;
            this.map= new Room[5][5];
        }

        if (difficulty.equals("M")||difficulty.equals("m"))
        {
            this.height=10;
            this.width=10;
            this.map= new Room[10][10];
        }

        if (difficulty.equals("H")||difficulty.equals("h"))
        {
            this.height=15;
            this.width=15;
            this.map= new Room[15][15];
        }
    }

    /**
     *
     * @param height- User input- length of board
     * @param width- User input - width of board
     */

    public Board (int height, int width)
    {
        this.height=height;
        this.width=height;
        this.map= new Room[height][width];
    }

    public Room[][] getBoard()
    {
        return this.map;
    }

    /**
     * @param y - y-coordinate of Position
     * @param x - x-coordinate of Position
     * @param room - type of room
     */
    public void setBoard(int y, int x, Room room)
    {
        this.map[y][x] = room;
    }

    /**
     * @return - Get the height of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return - Get the width of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Prints the board
     */
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

    /**
     * @param xPos - xPosition of the room
     * @param yPos - yPosition of the room
     * @return - The room at the position
     */
    public Room getRoom(int xPos, int yPos)
    {
        Room result= null;

        result=this.map[xPos][yPos];

        return result;
    }
}
