package Rooms;

import Game.Constants;
import Game.Position;
import People.Person;

public class LockedRoom extends Room {
    String id;
    String contain;
   String lockID;
   String broadcast;

    public LockedRoom(Position position)
    {
        super(position);
        this.id=Constants.getNextRoomID();
        this.lockID= Constants.getNextLockID();
        this.broadcast="";
        this.contain="[L]";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x)
    {
        if(x.getKeys().contains(lockID))
        {

            System.out.println("You have unlocked the door!");
            broadcast="unlocked";
        }
        else {
            System.out.println("You need key ID: "+ lockID+" to open this room");
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());
            contain = "[X]";
            broadcast="sendBack";
        }
    }


    public String getContain() {
        return contain;
    }
    /**
     * Removes the player from the room.
     * @param x
     */
    @Override
    public void leaveRoom(Person x)
    {
        occupant = null;
        contain= "[" +lockID+"]";
    }

    @Override
    public String toString()
    {
        return contain;
    }

}