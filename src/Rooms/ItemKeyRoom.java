package Rooms;

import Game.Constants;
import Game.Position;
import Items.Key;
import People.Person;

import java.util.Random;
import java.util.Scanner;

public class ItemKeyRoom extends Room {
    String contain;
    Key key;
    String id;
    String broadcast;

    public ItemKeyRoom(Position position)
    {
     super(position);
     this.id=Constants.getNextRoomID();
     this.contain="[K]";
     this.key= new Key();
     this.broadcast="";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {
        if(broadcast.equals("keyGot"))
        {
            contain = "[X]";
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());

        }else {
            if (contain.equals("[ ]")) {
                contain = "[X]";
                System.out.println("You got send back");
            } else {
                System.out.println("You enter room with a key");
                occupant = x;
                x.setxLoc(this.position.getX());
                x.setyLoc(this.position.getY());
                contain = "[X]";


                Scanner in = new Scanner(System.in);
                String response = "";
                while (!response.equals("yes") || !response.equals("no")) {
                    if (response.equals("yes") || response.equals("no")) {
                        break;
                    }
                    System.out.println("-------------------------");
                    System.out.println("Do you want the key? 'yes' or 'no'?");
                    response = in.nextLine();
                    response = response.toLowerCase().trim();
                }
                if (response.equals("yes")) {
                    System.out.println("Key ID: " + key.getID());
                    x.addKey(key.getID());
                    broadcast = "keyGot";
                }
                if (response.equals("no")) {
                    System.out.println("Then how are you gonna open that door");
                    broadcast = "sendBack";
                }
            }
        }
    }



    public String getContain() {
        return contain;
    }
    public String getBroadcast()
    {
        return broadcast;
    }
    /**
     * Removes the player from the room.
     * @param x
     */
    @Override
    public void leaveRoom(Person x)
    {
        occupant = null;
        contain="[ ]";
        if(broadcast.equals("sendBack"))
        {
            contain="[K]";
        }

    }
    public String toString()
    {
        return contain;
    }

}