package Rooms;

import People.Person;

public class LockedRoom extends Room {
    Person occupant;
    int xLoc,yLoc;
    int id;
    String contain;
    //A lock that would have a id similar to the key
    public LockedRoom(int x, int y, int id)
    {
        super(x,y);
        this.id=id;
        this.contain="[L]";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {
        System.out.println("You enter a plain old room");
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        contain= "[X]";
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