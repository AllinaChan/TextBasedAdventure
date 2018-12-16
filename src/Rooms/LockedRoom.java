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
        this.contain="[L]";
        this.lockID= Constants.getNextLockID();
        this.broadcast="";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {

        System.out.println("You need a key for this room");
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        contain= "[X]";
    }


    @Override
    public String getBroadcast() {
        return broadcast;
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
        contain="[ ]";
    }

    @Override
    public String toString()
    {
        return getContain();
    }

}