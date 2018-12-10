package Rooms;

import People.Person;

public class LockedRoom extends Room {

    Person occupant;
    int xLoc,yLoc;
    int id;
    String contain;
   int lockID;
    public LockedRoom(int x, int y, int id)
    {
        super(x,y);
        this.id=id;
        this.contain="[L]";
        this.lockID=getNewID();
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {

        System.out.println("You need a key for this room");
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        contain= "[X]";
    }

    public static int getNewID()
    {
        return 10;
    }


    public String getItem() {
        return  "LockedRoom ID: "+Integer.toString(id);
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
    }

    @Override
    public String toString()
    {
        return getContain();
    }

}