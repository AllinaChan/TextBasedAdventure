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
        ArrayList<Room> touchedPlaces= new ArrayList<>();
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
            System.out.println("Hello " + name + " Would you like to choose a difficulty or make a custom map?");
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
        int randWinH=randomHeightWin(height);
        int randWinW=randomWidthWin(width);
        map.getBoard()[randWinH][randWinW] = new WinningRoom(new Position(randWinH, randWinW));

        map.getBoard()[randWinH][randWinW-1] = new RoomWithWerewolf(new Position(randWinH, randWinW-1));

        addWolfRooms(map.getBoard(),amountOfWolves,new Position(randWinH, randWinW-1));

        //replaces (0,0)
        map.getBoard()[0][0]= new StartingRoom(new Position (0,0));

        //Key Rooms
        addKeyRooms(height, width,map.getBoard(),amountOfWolves);

        //Item rooms
        addBullet(height, width,map.getBoard());
        addGun(height, width,map.getBoard());
        addSword(height, width,map.getBoard());
        addHealthPotion(height, width,map.getBoard());


        //Setup player 1 and the input scanner

        initial = true;

        map.print();


        if (initial) {
            map.getBoard()[0][0].enterRoom(player1);
            while (gameOn) {
                String choices = "";

                String potion = "";
                String potion1 = "";
                if (player1.getPotions().size()>=1) {
                    potion = " or DRINK A HEALTH POTION";
                    potion1 = " or '6' for Drinking a Health Potion";
                }

                while (!choices.equals("1") || !choices.equals("2") || !choices.equals("3") || !choices.equals("4") || !choices.equals("5") || !choices.equals("6")) {
                    if (choices.equals("1") || choices.equals("2") || choices.equals("3") || choices.equals("4") || choices.equals("5") || choices.equals("6")) {
                        break;
                    }
                    System.out.println("");
                    System.out.println("Would you like to MOVE or CHECK YOUR INVENTORY or CHECK THE MAP LEGEND or CHECK YOUR HEALTH GAUGE or CHECK THE MAP" + potion);
                    System.out.println("type '1' for Move or '2' for Inventory Check or '3' for Legend or '4' for Health Check or '5' for Map Check" + potion1);
                    choices = in.nextLine();
                }
                if (choices.equals("1")) {
                    System.out.println("Where would you like to move? (Choose N, S, E, W)");
                    String move = in.nextLine();
                    Position oldPos2 = new Position(player1.getxLoc(), player1.getyLoc());
                    Position willGoPos = directionalOffset(move, oldPos2);
                    //Apparently, oldPos gets reset from directionalOffset, so I created a duplicate
                    Position oldPos = new Position(player1.getxLoc(), player1.getyLoc());
                    if (validMove(move, player1, map.getBoard(), "validORNah")) {
                        if (map.getBoard()[willGoPos.getX()][willGoPos.getY()].toString().equals("[W]")) {
                            if (hasEnoughFrags(amountOfWolves, player1) == false) {
                                System.out.println("You don't have enough Boss Key Fragments, go kill all the wolves.");
                            } else if (validMove(move, player1, map.getBoard())) {
                                Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                                System.out.println(" ");
                                System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                                map.print();
                            } else {
                                System.out.println("Please choose a valid move.");
                            }
                        } else if (map.getBoard()[willGoPos.getX()][willGoPos.getY()].toString().equals("[?]")) {
                            if (!player1.hasTrophy()) {
                                System.out.println("The door is locked indefinitely, go kill the Werewolf and see if it opens then");
                            } else if (validMove(move, player1, map.getBoard())) {

                                Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                                map.print();
                            } else {
                                System.out.println("Please choose a valid move.");
                            }
                        } else if (validMove(move, player1, map.getBoard())) {
                            Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                            if (currentRoom.getBroadcast().equals("sendBack")) {
                                player1.setxLoc(oldPos.getX());
                                player1.setyLoc(oldPos.getY());
                                currentRoom.leaveRoom(player1);
                                map.getRoom(oldPos.getX(), oldPos.getY()).enterRoom(player1);
                            }
                            if (currentRoom.getBroadcast().equals("canKillWerewolf")) {
                                System.out.println("Your gun is now loaded with a silver bullet. GO KILL THAT WEREWOLF!");
                            }
                            if (currentRoom.getBroadcast().equals("sendBackToSPAWN")) {
                                System.out.println("You woke up at the begining");
                                currentRoom.respawn(player1);
                                map.print();
                            }
                            System.out.println(" ");
                            System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                            map.print();
                        } else {
                            System.out.println("Please choose a valid move.");
                        }
                    }
                     else{
                        System.out.println("Please choose a valid move.");
                    }
                    } else if (choices.equals("2")) {
                        player1.printInv();
                    } else if (choices.equals("3")) {
                        System.out.println("[X] = You" + "\n" + "[K] = Room with a KEY" + "\n" + "[L] = Room with a lock, you can check what key ID you need by entering it" +
                                "\n" + "[W] = Werewolf" + "\n" + "[w] = Wolf" + "\n" + "[G] = Gun" + "\n" + "[B] = Silver Bullet" + "\n" + "[H] = Health Potion" + "\n" + "[S] = Sword" + "\n" + "[-] = Starting Room");
                    } else if (choices.equals("4")) {
                        System.out.println("You currently have: " + player1.getHealth() + " health points");
                    } else if (choices.equals("5")) {
                        map.print();
                    } else if (choices.equals("6")) {
                        Room currentRoom = map.getRoom(player1.getxLoc(), player1.getyLoc());
                        System.out.println("You finished drinking the Health Potion, now you are full health");
                        player1.getPotions().get(0).use(player1, "healPotion");
                    }
                }

        }
        in.close();
    }

    /**
     * takes a y value from the last three rows of the map
     * @param height- takes the height of the map
     * @return- a random vertical placement for the winning room
     */
    public static int randomHeightWin(int height)
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
     * @return- a random horizontal placement for the winning room
     */

    public static int randomWidthWin(int width)
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




    public static int randomHeight(int height)
    {
        int result= (int)(Math.random()*height);

        return result;
    }

    public static int randomWidth(int width)
    {
        int result= (int)(Math.random()*width);

        return result;
    }

    public static void addGun(int height, int width, Room[][] map)
    {
        int h = 0;
        int w= 0;

        while (isOccupied(new Position(h, w), map)) {
            h = randomHeight(height);
            w = randomWidth(width);
            if (!isOccupied(new Position(h, w), map)) {
                break;
            }
        }

        map[h][w] = new RoomWithGun(new Position(h, w));
    }
    public static void addHealthPotion(int height, int width, Room[][] map)
    {
        int h = 0;
        int w= 0;

        while (isOccupied(new Position(h, w), map)) {
            h = randomHeight(height);
            w = randomWidth(width);
            if (!isOccupied(new Position(h, w), map)) {
                break;
            }
        }

        map[h][w] = new RoomWithHealingPotion(new Position(h, w));
    }
    public static void addBullet(int height, int width, Room[][] map)
    {
        int h = 0;
        int w= 0;

        while (isOccupied(new Position(h, w), map)) {
            h = randomHeight(height);
            w = randomWidth(width);
            if (!isOccupied(new Position(h, w), map)) {
                break;
            }
        }

        map[h][w] = new RoomWithSilverBullet(new Position(h, w));
    }
    public static void addSword(int height, int width, Room[][] map)
    {
        int h = 0;
        int w= 0;

        while (isOccupied(new Position(h, w), map)) {
            h = randomHeight(height);
            w = randomWidth(width);
            if (!isOccupied(new Position(h, w), map)) {
                break;
            }
        }

        map[h][w] = new RoomWithSword(new Position(h, w));
    }

    public static void addKeyRooms(int height, int width, Room[][]map, String difficulty)
    {
        if(difficulty.equals("one"))
        {
            int h = 0;
            int w= 0;
            for(int i=0; i <3; i++) {
                while (isOccupied(new Position(h, w), map)) {
                    h = randomHeight(height);
                    w = randomWidth(width);
                    if (!isOccupied(new Position(h, w), map)) {
                        break;
                    }
                }

                map[h][w] = new ItemKeyRoom(new Position(h, w));
            }
        } else if(difficulty.equals("two"))
        {
            int h = 0;
            int w= 0;
            for(int i=0; i <7; i++) {
                while (isOccupied(new Position(h, w), map)) {
                    h = randomHeight(height);
                    w = randomWidth(width);
                    if (!isOccupied(new Position(h, w), map)) {
                        break;
                    }
                }

                map[h][w] = new ItemKeyRoom(new Position(h, w));

            }

        }else if(difficulty.equals("three"))
        {

            int h = 0;
            int w= 0;
            for(int i=0; i <11; i++) {
                while (isOccupied(new Position(h, w), map)) {
                    h = randomHeight(height);
                    w = randomWidth(width);
                    if (!isOccupied(new Position(h, w), map)) {
                        break;
                    }
                }

                map[h][w] = new ItemKeyRoom(new Position(h, w));

            }
        }

    }


    public static void addWolfRooms(Room[][] map, String difficulty, Position Werewolf)
    {
        Position oneWolfPos =directionalOffset("n", Werewolf);
        Position twoWolfPos = new Position(oneWolfPos.getX()-5,oneWolfPos.getY());
        Position threeWolfPos = new Position(oneWolfPos.getX(),oneWolfPos.getY()-5);
        if(difficulty.equals("one"))
        {

            ArrayList<Position> aroundWolf= oneWolfPos.getSurroundingCardinals();
            map[oneWolfPos.getX()][oneWolfPos.getY()]=new RoomWithWolf(new Position(oneWolfPos.getX(),oneWolfPos.getY()));

            for (Position position: aroundWolf)
            {
                if(!isOccupied(position, map))
                {
                    map[position.getX()][position.getY()]= new LockedRoom(new Position(position.getX(), position.getY()));
                }
            }

        }else if (difficulty.equals("two"))
        {
            twoWolfPos = new Position(oneWolfPos.getX()-3,oneWolfPos.getY()-3);

            ArrayList<Position> aroundWolf= oneWolfPos.getSurroundingCardinals();
            map[oneWolfPos.getX()][oneWolfPos.getY()]=new RoomWithWolf(new Position(oneWolfPos.getX(),oneWolfPos.getY()));

            for (Position position: aroundWolf)
            {
                if(!isOccupied(position, map))
                {
                    map[position.getX()][position.getY()]= new LockedRoom(new Position(position.getX(), position.getY()));
                }
            }

            ArrayList<Position> aroundWolf2= twoWolfPos.getSurroundingCardinals();
            map[twoWolfPos.getX()][twoWolfPos.getY()]=new RoomWithWolf(new Position(twoWolfPos.getX(),twoWolfPos.getY()));

            for (Position position: aroundWolf2)
            {
                if(!isOccupied(position, map))
                {
                    map[position.getX()][position.getY()]= new LockedRoom(new Position(position.getX(), position.getY()));
                }
            }

        }
        else if(difficulty.equals("three"))
        {
            twoWolfPos = new Position(oneWolfPos.getX()-8,oneWolfPos.getY()-2);
            threeWolfPos = new Position(oneWolfPos.getX()-2,oneWolfPos.getY()-5);

            ArrayList<Position> aroundWolf= oneWolfPos.getSurroundingCardinals();
            map[oneWolfPos.getX()][oneWolfPos.getY()]=new RoomWithWolf(new Position(oneWolfPos.getX(),oneWolfPos.getY()));

            for (Position position: aroundWolf)
            {
                if(!isOccupied(position, map))
                {
                    map[position.getX()][position.getY()]= new LockedRoom(new Position(position.getX(), position.getY()));
                }
            }

            ArrayList<Position> aroundWolf2= twoWolfPos.getSurroundingCardinals();
            map[twoWolfPos.getX()][twoWolfPos.getY()]=new RoomWithWolf(new Position(twoWolfPos.getX(),twoWolfPos.getY()));

            for (Position position: aroundWolf2)
            {
                if(!isOccupied(position, map))
                {
                    map[position.getX()][position.getY()]= new LockedRoom(new Position(position.getX(), position.getY()));
                }
            }

            ArrayList<Position> aroundWolf3= threeWolfPos.getSurroundingCardinals();
            map[threeWolfPos.getX()][threeWolfPos.getY()]=new RoomWithWolf(new Position(threeWolfPos.getX(),threeWolfPos.getY()));

            for (Position position: aroundWolf3)
            {
                if(!isOccupied(position, map))
                {
                    map[position.getX()][position.getY()]= new LockedRoom(new Position(position.getX(), position.getY()));
                }
            }

        }
    }

    public static boolean isOccupied(Position checkPos, Room[][] map)
    {
        boolean result = false;
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[W]"))
        {
            result = true;
        }
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[?]"))
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
        if (map[checkPos.getX()][checkPos.getY()].toString().equals("[X]"))
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
    public static boolean validMove(String move, Person p, Room[][] map,String checkIfValid)
    {
        move = move.toLowerCase().trim();
        switch (move) {
            case "n":
                if (p.getxLoc() > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case "e":
                if (p.getyLoc()< map[p.getyLoc()].length -1)
                {

                    return true;
                }
                else
                {
                    return false;
                }

            case "s":
                if (p.getxLoc() < map.length - 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            case "w":
                if (p.getyLoc() > 0)
                {
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