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
             System.out.println("In that cave there is a werewolf that has been terrorizing the village. Please help us get rid of it!");
             System.out.println("To do that, you would need the BOSS KEY (to enter its lair) by killing " + amountOfWolves + " wolf(s) with a sword");
             System.out.println("However, a sword will not be able to damage the Werewolf...You would need both a GUN and a SILVER BULLET");
             System.out.println("Got it? 'yes' or 'no'");
             gotIt = in.nextLine();
             gotIt = gotIt.toLowerCase().trim();
         }

        Person player1 = new Person(name, new Position(0,0));

        //Fill the map.getBoard() with item rooms
        for (int x = 0; x < map.getBoard().length; x++) {
            for (int y = 0; y < map.getBoard()[x].length; y++) {
                map.getBoard()[x][y] = new EmptyRoom( new Position (x,y));
            }
        }
        //Create winning room.
        int randWinH=randomHeight(height);
        int randWinW=randomWidth(width);
        map.getBoard()[randWinH][randWinW] = new WinningRoom(new Position(randWinH, randWinW));
        map.getBoard()[randWinH][randWinW-1] = new RoomWithWerewolf(new Position(randWinH, randWinW-1));

        addWolfRooms(height,width,map.getBoard(),difficulty,player1,new Position(randWinH, randWinW-1));

        //replaces (0,0)
        map.getBoard()[0][0]= new StartingRoom(new Position (0,0));

        //Setup player 1 and the input scanner

        initial = true;

        map.print();


        if (initial) {
            map.getBoard()[0][0].enterRoom(player1);
            while (gameOn) {
                String choices = "";

                String potion="";
                String potion1="";
                if(player1.getPotions().contains("healingPotion"))
                {
                    potion= " or DRINK A HEALTH POTION";
                    potion1=" or '6' for Drinking a Health Potion";
                }

                while (!choices.equals("1") || !choices.equals("2")|| !choices.equals("3")|| !choices.equals("4")|| !choices.equals("5")|| !choices.equals("6")) {
                    if(choices.equals("1") || choices.equals("2") || choices.equals("3")|| choices.equals("4")|| choices.equals("5")|| choices.equals("6"))
                    {
                        break;
                    }
                    System.out.println("");
                    System.out.println("Would you like to MOVE or CHECK YOUR INVENTORY or CHECK THE MAP LEGEND or CHECK YOUR HEALTH GAUGE or CHECK THE MAP" + potion);
                    System.out.println("type '1' for Move or '2' for Inventory Check or '3' for Legend or '4' for Health Check or '5' for Map Check"+potion1);
                    choices = in.nextLine();
                }
                    if (choices.equals("1")) {
                        System.out.println("Where would you like to move? (Choose N, S, E, W)");
                        String move = in.nextLine();
                        Position oldPos = new Position(player1.getxLoc(), player1.getyLoc());
                        Position willGoPos= directionalOffset(move, oldPos);
                        if(map.getBoard()[willGoPos.getX()][willGoPos.getY()].toString().equals("[W]"))
                        {
                            if(hasEnoughFrags(amountOfWolves,player1)==false)
                            {
                                System.out.println("You don't have enough Boss Key Fragments, go kill all the wolves.");
                            } else if (validMove(move, player1, map.getBoard())) {

                                Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                                if (currentRoom.getBroadcast().equals("sendBack")) {
                                    player1.setxLoc(oldPos.getX());
                                    player1.setyLoc(oldPos.getY());
                                    currentRoom.leaveRoom(player1);
                                    map.getRoom(oldPos.getX(), oldPos.getY()).enterRoom(player1);
                                }
                                if(currentRoom.getBroadcast().equals("canKillWerewolf"))
                                {
                                    System.out.println("Your gun is now loaded with a silver bullet. GO KILL THAT WEREWOLF!");
                                }
                                if(currentRoom.getBroadcast().equals("sendBackToSPAWN"))
                                {
                                    System.out.println("You woke up at the begining");
                                    currentRoom.respawn(player1);
                                    map.print();
                                }
                                if(currentRoom.getBroadcast().equals("unlocked"))
                                {
                                    map.getBoard()[currentRoom.getPosition().getX()][currentRoom.getPosition().getY()]= new EmptyRoom(new Position(currentRoom.getPosition().getX(), currentRoom.getPosition().getY()));
                                }
                                System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                                touchedPlaces.add(new Position(player1.getxLoc(), player1.getyLoc()));
                                map.print();
                            } else {
                                System.out.println("Please choose a valid move.");
                            }
                        }
                        else if(map.getBoard()[willGoPos.getX()][willGoPos.getY()].toString().equals("[?]"))
                        {
                            if(!player1.hasTrophy())
                            {
                                System.out.println("The door is locked indefinitely, go kill the Werewolf and see if it opens then");
                            }
                            else if (validMove(move, player1, map.getBoard())) {

                                Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                                if (currentRoom.getBroadcast().equals("sendBack")) {
                                    player1.setxLoc(oldPos.getX());
                                    player1.setyLoc(oldPos.getY());
                                    currentRoom.leaveRoom(player1);
                                    map.getRoom(oldPos.getX(), oldPos.getY()).enterRoom(player1);
                                }
                                if(currentRoom.getBroadcast().equals("canKillWerewolf"))
                                {
                                    System.out.println("Your gun is now loaded with a silver bullet. GO KILL THAT WEREWOLF!");
                                }
                                if(currentRoom.getBroadcast().equals("sendBackToSPAWN"))
                                {
                                    System.out.println("You woke up at the begining");
                                    currentRoom.respawn(player1);
                                    map.print();
                                }
                                if(currentRoom.getBroadcast().equals("unlocked"))
                                {
                                    map.getBoard()[currentRoom.getPosition().getX()][currentRoom.getPosition().getY()]= new EmptyRoom(new Position(currentRoom.getPosition().getX(), currentRoom.getPosition().getY()));
                                }
                                System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                                touchedPlaces.add(new Position(player1.getxLoc(), player1.getyLoc()));
                                map.print();
                            } else {
                                System.out.println("Please choose a valid move.");
                            }
                        }
                        else if (validMove(move, player1, map.getBoard())) {

                            Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                            if (currentRoom.getBroadcast().equals("sendBack")) {
                                player1.setxLoc(oldPos.getX());
                                player1.setyLoc(oldPos.getY());
                                currentRoom.leaveRoom(player1);
                                map.getRoom(oldPos.getX(), oldPos.getY()).enterRoom(player1);
                            }
                            if(currentRoom.getBroadcast().equals("canKillWerewolf"))
                            {
                                System.out.println("Your gun is now loaded with a silver bullet. GO KILL THAT WEREWOLF!");
                            }
                            if(currentRoom.getBroadcast().equals("unlocked"))
                            {
                                map.getBoard()[currentRoom.getPosition().getX()][currentRoom.getPosition().getY()]= new EmptyRoom(new Position(currentRoom.getPosition().getX(), currentRoom.getPosition().getY()));
                            }
                            if(currentRoom.getBroadcast().equals("sendBackToSPAWN"))
                            {
                                System.out.println("You woke up at the begining");
                                currentRoom.respawn(player1);
                                map.print();
                            }
                            System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                            touchedPlaces.add(new Position(player1.getxLoc(), player1.getyLoc()));
                            map.print();
                        } else {
                            System.out.println("Please choose a valid move.");
                        }
                    } else if (choices.equals("2")) {
                        player1.printInv();
                    } else if (choices.equals("3")){
                        System.out.println("[X] = You"+"\n"+"[K] = Room with a KEY" +"\n"+ "[id] = Room with a lock, match room id with key id to unlock"+
                                "\n" +"[W] = Werewolf"+ "\n" +"[w] = Wolf" +"\n"+ "[G] = Gun"+"\n"+"[B] = Silver Bullet"+"\n"+ "[H] = Health Potion" +"\n"+"[S] = Sword");
                    }
                    else if(choices.equals("4"))
                    {
                        System.out.println("You currently have: "+player1.getHealth()+ " health points");
                    }
                    else if(choices.equals("5"))
                    {
                        map.print();
                    }
                    else if(choices.equals("6"))
                    {
                        Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                        System.out.println("You finished drinking the Health Potion, now you are full health");
                        //currentRoom.getHealingPotion()
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

        int result=0;


        int randomNumb = 0;

        if(randomNumb==0)
        {
            result= l;
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
        int result=0;


        int randomNumb = (int)(Math.random()*1);

        if(randomNumb==0)
        {
            result= l;
        }
        if(randomNumb==1)
        {
            result= g;
        }

        return result;
    }


    public static void addWolfRooms(int height, int width, Room[][] map, String difficulty, Person x, Position Werewolf)
    {
        Position oneWolfPos =directionalOffset("n", Werewolf);
        if(difficulty.equals("one"))
        {
            map[oneWolfPos.getX()][oneWolfPos.getY()]=new RoomWithWolf(new Position(oneWolfPos.getX(),oneWolfPos.getY()));
        }else if (difficulty.equals("two"))
        {
            map[oneWolfPos.getX()][oneWolfPos.getY()]=new RoomWithWolf(new Position(oneWolfPos.getX(),oneWolfPos.getY()));
        }
        else if(difficulty.equals("three"))
        {
            map[oneWolfPos.getX()][oneWolfPos.getY()]=new RoomWithWolf(new Position(oneWolfPos.getX(),oneWolfPos.getY()));
        }
    }

    public static boolean isOccupied(Position checkPos, Room[][] map)
    {
        boolean result = false;
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[W]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[w]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[G]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[B]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[H]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[S]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[K]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[L]"))
        {
            result = true;
        }
        return result;
    }

    public static Position directionalOffset(String direction, Position source)
    {
        Position result = source;
        direction=direction.toLowerCase().trim();
        if(direction.equals("n"))
        {
            result.setX(result.getX()-1);
        }else if(direction.equals("s"))
        {
            result.setX(result.getX()+1);
        }else if(direction.equals("w"))
        {
            result.setY(result.getY()-1);
        }else if(direction.equals("e"))
        {
            result.setY(result.getY()+1);
        }
        return result;
    }

    public static boolean hasEnoughFrags(String difficulty, Person x)
    {
        boolean result=false;
        if(difficulty.equals("one"))
        {
            if(x.getFragments().size()==1)
            {
                result=true;
            }
        }
       else if(difficulty.equals("two"))
        {
            if(x.getFragments().size()==2)
            {
                result=true;
            }
        }
        else if(difficulty.equals("three"))
        {
            if(x.getFragments().size()==3)
            {
                result=true;
            }
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