package Rooms;

import Game.Constants;
import Game.Position;
import Items.Key;
import People.Person;

import java.util.Random;
import java.util.Scanner;

public class ItemKeyRoom extends Room {
    Person occupant;
    Position position;
    String contain;
    Key key;
    String id;

    public ItemKeyRoom(Position position)
    {
     super(position);
     this.id=Constants.getNextRoomID();
     this.contain="[I]";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {
        System.out.println("You enter room with a key");
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        contain= "[X]";


        Scanner in = new Scanner(System.in);
        String response="";
        while(!response.equals("yes")||!response.equals("no"))
        {
            System.out.println("Do you want the key? 'yes' or 'no'?");
            response= in.nextLine();
        }
        if(response.equals("yes"))
        {
            x.addKey(key.getID());
        }
        if(response.equals("no"))
        {
            System.out.println("Then how are you gonna open that door");
        }
    }

    public String getContain() {
        return contain;
    }

    /**
     * Removes the player from the room.
     * @param x
     */
    public void leaveRoom(Person x)
    {
        occupant = null;
        contain="[I]";
    }
    public String toString()
    {
        return getContain();
    }

}