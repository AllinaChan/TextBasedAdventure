package Game;

import People.Person;
import Rooms.ItemKeyRoom;
import Rooms.Room;
import Rooms.ClueRoom;
import Rooms.WinningRoom;

import java.util.Scanner;

public class Runner {

    private static boolean gameOn = true;

    public static void main(String[] args) {
        boolean initial = false;
        String difficulty="";
        int height=0;
        int width=0;
        Board map = new Board(0, 0);
        Scanner in = new Scanner(System.in);
        System.out.println("WELCOME TO THE ESCAPE ROOM");
        System.out.println("I will devise a intricate room for you");
        System.out.println("Let's start with your name...What's your name?");
        String name = in.nextLine();
        String boardChoice="";

        while(!boardChoice.equals("d") || !boardChoice.equals("c")) {
            if(boardChoice.equals("d")||boardChoice.equals("c"))
            {
                break;
            }
            System.out.println("hElLo " + name + " Would you like to choose a difficulty or make a custom map?");
            System.out.println("Please type 'd' for choosing a difficulty AND 'c' for custom map");
            boardChoice = in.nextLine();
        }

        if (boardChoice.equals("d")) {
            while(!difficulty.equals("e")&&!difficulty.equals("E")&&!difficulty.equals("m")&&!difficulty.equals("M")&&!difficulty.equals("h")&&!difficulty.equals("H")) {
                System.out.println("Please select a difficulty by typing:");
                System.out.println("'e' for a 5x5 layout | 'm' for a 10x10 layout | 'h' for a 15x15 layout");
                difficulty = in.nextLine();
                map = new Board(difficulty);
            }
        }

        if (boardChoice.equals("c")) {
            while(height==0 && width==0) {
                System.out.println("Please give me the length of the map. Don't make it >50");
                height = in.nextInt();
                System.out.println("Please give me the width of the map. Don't make it >50");
                width = in.nextInt();
                map = new Board(height, width);
            }
        }



        //Fill the map.getBoard() with item rooms
        for (int x = 0; x < map.getBoard().length; x++) {
            for (int y = 0; y < map.getBoard()[x].length; y++) {
                map.getBoard()[x][y] = new ItemKeyRoom( new Position (x,y));
            }
        }

        //Create winning room.
        map.getBoard()[height+1][width+1] = new WinningRoom(new Position(height+1, width+1));



        //Setup player 1 and the input scanner
        Person player1 = new Person(name, new Position(0,0));
        initial = true;
        map.print();
        System.out.println(player1==null);
        if (initial) {
            map.getBoard()[0][0].enterRoom(player1);
            while (gameOn) {
                System.out.println("Where would you like to move? (Choose N, S, E, W)");
                String move = in.nextLine();
                if (validMove(move, player1, map.getBoard())) {
                    System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                    map.print();
                } else {
                    System.out.println("Please choose a valid move.");
                }
            }
        }
        in.close();
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
    public static void gameOn()
    {
        gameOn = true;
    }



}