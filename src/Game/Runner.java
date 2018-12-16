package Game;

import People.Person;
import Rooms.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Runner {

    private static boolean gameOn = true;

    public static void main(String[] args) {
        boolean initial = false;
        String difficulty="";
        int height=0;
        int width=0;
        ArrayList<Position> touchedPlaces= new ArrayList<>();
        Board map = new Board(0, 0);
        Scanner in = new Scanner(System.in);
        System.out.println("WELCOME TO THE MONSTER HUNT");
        System.out.println("Hello adventurer, What's your name?");
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
            boardChoice=boardChoice.toLowerCase().trim();
        }

        if (boardChoice.equals("d")) {
            while(!difficulty.equals("e")&&!difficulty.equals("E")&&!difficulty.equals("m")&&!difficulty.equals("M")&&!difficulty.equals("h")&&!difficulty.equals("H")) {
                System.out.println("Please select a difficulty by typing:");
                System.out.println("'e' for a 5x5 layout | 'm' for a 10x10 layout | 'h' for a 15x15 layout");
                difficulty = in.nextLine();
                map = new Board(difficulty);
                height=map.getHeight();
                width=map.getWidth();
            }
        }

        if (boardChoice.equals("c")) {
            while(height==0 && width==0) {
                System.out.println("Please give me the height of the map. Don't make it >50");
                height = in.nextInt();
                System.out.println("Please give me the width of the map. Don't make it >50");
                width = in.nextInt();
                map = new Board(height, width);
            }
        }

        String amountOfWolves="";
        if(map.getHeight()<=5 && map.getWidth()<=5)
        {
            amountOfWolves="one";
        }
        else if(map.getHeight()<15 && map.getWidth()<15)
        {
            amountOfWolves="two";
        }
         else  if(map.getHeight()>=15 && map.getWidth()>=15)
        {
            amountOfWolves="three";
        }

        String gotIt="";
         while(!gotIt.equals("yes")|| !gotIt.equals("no")) {
             if(gotIt.equals("yes"))
             {
                 break;
             }
             System.out.println("Your goal of the game is to slay the Werewolf");
             System.out.println("To do so, you would need the BOSS KEY by killing " + amountOfWolves + " wolf(s) with a sword");
             System.out.println("However, a sword will not be able to damage the Werewolf...You would need both a GUN and a SILVER BULLET");
             System.out.println("Got it? 'yes' or 'no'");
             gotIt = in.nextLine();
             gotIt = gotIt.toLowerCase().trim();
         }

        //Fill the map.getBoard() with item rooms
        for (int x = 0; x < map.getBoard().length; x++) {
            for (int y = 0; y < map.getBoard()[x].length; y++) {
                map.getBoard()[x][y] = new ItemKeyRoom( new Position (x,y));
            }
        }
        //Create winning room.
        int randWinH=randomHeight(height);
        int randWinW=randomWidth(width);
        map.getBoard()[randWinH][randWinW] = new WinningRoom(new Position(randWinH, randWinW));


        //replaces (0,0)
        map.getBoard()[0][0]= new StartingRoom(new Position (0,0));

        //Setup player 1 and the input scanner
        Person player1 = new Person(name, new Position(0,0));
        initial = true;

        //DELETE THIS LATER THE PRINT MAP
        map.print();


        if (initial) {
            map.getBoard()[0][0].enterRoom(player1);
            while (gameOn) {
                String choices = "";

                while (!choices.equals("1") || !choices.equals("2")|| !choices.equals("3")) {
                    if(choices.equals("1") || choices.equals("2") || choices.equals("3"))
                    {
                        break;
                    }
                    System.out.println("What would you like to do?");
                    System.out.println("Would you like to MOVE or CHECK YOUR INVENTORY? type '1' for Move or '2' for Inventory Check or '3' for Legend");
                    choices = in.nextLine();
                }
                    if (choices.equals("1")) {
                        System.out.println("Where would you like to move? (Choose N, S, E, W)");
                        String move = in.nextLine();
                        Position oldPos = new Position(player1.getxLoc(), player1.getyLoc());
                        if (validMove(move, player1, map.getBoard())) {
                            Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                            if (currentRoom.getBroadcast().equals("sendBack")) {
                                player1.setxLoc(oldPos.getX());
                                player1.setyLoc(oldPos.getY());
                                currentRoom.leaveRoom(player1);
                                map.getRoom(oldPos.getX(), oldPos.getY()).enterRoom(player1);
                            }
                            System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                            touchedPlaces.add(new Position(player1.getxLoc(), player1.getyLoc()));
                            System.out.println(touchedPlaces.get(0));
                            map.print();
                        } else {
                            System.out.println("Please choose a valid move.");
                        }
                    } else if (choices.equals("2")) {
                        player1.printInv();
                    } else if (choices.equals("3")){
                        System.out.println("[K] = a Room with a KEY" +"\n"+ "[L] = a Locked Room"+ "\n" +"[W] = Werewolf"+ "\n" +"[w] = Wolf");
                    }

            }
        }
        in.close();
    }

    /**
     * takes a y value from the last three rows of the map
     * @param height- takes the height of the map
     * @return- a random vertical placement of the room
     */
    public static int randomHeight(int height)
    {
        int l= height-2;
        int f=height-3;

        int result=0;


        int randomNumb = (int)(Math.random());

        if(randomNumb==0)
        {
            result= l;
        }
        if(randomNumb==1)
        {
            result= f;
        }

        return result;
    }

    /**
     * takes a x value from the last four columns of the map
     * @param width- takes the width of the map
     * @return- a random horizontal placement of the room
     */

    public static int randomWidth(int width)
    {
        int l= width-2;
        int g= width-3;
        int f=width-4;
        int result=0;


        int randomNumb = (int)(Math.random()*2);

        if(randomNumb==0)
        {
            result= l;
        }
        if(randomNumb==1)
        {
            result= g;
        }
        if(randomNumb==2)
        {
            result= f;
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
    public static void gameOn()
    {
        gameOn = true;
    }



}