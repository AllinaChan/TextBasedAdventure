package Rooms;

import Game.Constants;
import Game.Position;
import People.Person;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class ClueRoom extends Room
{
    Person occupant;
    Position position;
    String contain;
    String id;

    public ClueRoom(Position position)
    {
        super(position);
        this.id= Constants.getNextRoomID();
        this.contain="[...]";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        contain= "[X]";
        Scanner in = new Scanner(System.in);
         String response="";
        while(!response.equals("yes")||!response.equals("no"))
        {
            System.out.println("Do you want tips? 'yes' or 'no'?");
         response= in.nextLine();
        }
        if(response.equals("yes"))
        {
            System.out.println(getClues());
        }
        if(response.equals("no"))
        {
            System.out.println("Then too bad!");
        }
    }

    public String getContain() {
        return contain;
    }

    public void leaveRoom(Person x)
    {
        occupant = null;
    }
    public String toString()
    {

        return getContain();
    }

    public String getClues() {

       String[] clues={"The winning room may be closer that you think...Or not",
               "If the a key has the same ID as a lock, the lock can be unlocked with that key",
               "Maybe look around you, examine the map, only to find that there ARE no clues...",
                ""};
       Random rand = new Random();
        int n = rand.nextInt(clues.length);

       return clues[n];
    }

}