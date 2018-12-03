package Game;

import People.Person;
import Rooms.Room;
import Rooms.RoomInConstruction;
import Rooms.WinningRoom;

import java.util.Scanner;

public class Runner {


    private static boolean gameOn = true;

    public static void main(String[] args)
    {
        Room[][] building= new Room[5][5];
        Scanner in = new Scanner(System.in);
        System.out.println("WELCOME TO THE ESCAPE ROOM");
        System.out.println("I will devise a intricate room for you");
        System.out.println("Let's start with your name...What's your name?");
        String name = in.nextLine();

        System.out.println("hElLo "+ name+ " Would you like to choose a difficulty or make a custom map?");
        System.out.println("Please type 'd' for choosing a difficulty AND 'c' for custom map");
        String boardChoice= in.nextLine();

        if(boardChoice.equals("d")) {
            System.out.println("Please select a difficulty by typing:");
            System.out.println("'n' for a 5x5 layout | 'm' for a 10x10 layout | 'h' for a 15x15 layout");
            String difficulty = in.nextLine();
            Board map = new Board(difficulty);
            building= map.getBoard();
        }

        if(boardChoice.equals("c")) {
            System.out.println("Please give me the length of the map. Don't make it >20");
            int length= in.nextInt();
            System.out.println("Please give me the width of the map. Don't make it >20");
            int width= in.nextInt();
            Board map = new Board(length, width);
            building= map.getBoard();
        }

        //Fill the building with normal rooms
        for (int x = 0; x< building.length; x++)
        {
            for (int y = 0; y < building[x].length; y++)
            {
                building[x][y] = new Room(x,y);
            }
        }

        //Create a random winning room.
        int x = (int)(Math.random()*building.length);
        int y = (int)(Math.random()*building.length);
        building[x][y] = new WinningRoom(x, y);

        int z = (int)(Math.random()*building.length);
        int a = (int)(Math.random()*building.length);
        building[x][y] = new RoomInConstruction(z, a);


        //Setup player 1 and the input scanner
        Person player1 = new Person( name,0,0);
        building[0][0].enterRoom(player1);
        while(gameOn)
        {
            System.out.println("Where would you like to move? (Choose N, S, E, W)");
            String move = in.nextLine();
            if(validMove(move, player1, building))
            {
                System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                System.out.println(map(player1.getxLoc(),player1.getyLoc()));
            }
            else {
                System.out.println("Please choose a valid move.");
            }


        }
        in.close();
    }

    public static String map(int x, int y)
    {
        String[][] map = new String[5][5];
        for (int i = 0; i<map.length; i++ )
        {
            for (int j =0; j< map[i].length; j++)
            {
                map[i][j]="O";
            }
        }
        map[x][y]= "X";

        String result="";
        for (int i = 0; i<map.length; i++ )
        {
            for (int j =0; j< map[i].length; j++)
            {
                result = result+map[i][j];
            }
            result=result+"\n";
        }
        return result;
    }

    /**
     * Checks that the movement chosen is within the valid game map.
     * @param move the move chosen
     * @param p person moving
     * @param map the 2D array of rooms
     * @return
     */
    public static boolean validMove(String move, Person p, Room[][] map)
    {
        move = move.toLowerCase().trim();
        switch (move) {
            case "n":
                if (p.getxLoc() > 0)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }
            case "e":
                if (p.getyLoc()< map[p.getyLoc()].length -1)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }

            case "s":
                if (p.getxLoc() < map.length - 1)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }

            case "w":
                if (p.getyLoc() > 0)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }
            default:
                break;

        }
        return true;
    }
    public static void gameOff()
    {
        gameOn = false;
    }



}