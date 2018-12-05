package Rooms;

import People.Person;

public class ItemRoom extends Room {
    Person occupant;
    int xLoc,yLoc;
    String contain;
    //needs an item

    public ItemRoom(int x, int y)
    {
     super(x,y);
     this.contain="[I]";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {
        System.out.println("You enter room with some items");
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
        contain="[I]";
    }
    public String toString()
    {
        return getContain();
    }

}