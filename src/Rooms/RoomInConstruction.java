package Rooms;

import People.Person;

public class RoomInConstruction extends Room
{	Person occupant;
    int xLoc,yLoc;
    String contain;
    public RoomInConstruction(int x, int y)
    {
        super(x,y);
        this.contain="[...]";
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {
        System.out.println("THIS ROOM IS IN CONSTRUCTION! WAIT OUTSIDE");
        System.out.println("                             ___\n" +
                "                     /======/\n" +
                "            ____    //      \\___       ,/\n" +
                "             | \\\\  //           :,   ./\n" +
                "     |_______|__|_//            ;:; /\n" +
                "    _L_____________\\o           ;;;/\n" +
                "____(CCCCCCCCCCCCCC)____________-/___________________kg__\n" );

        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        contain= "[X]";
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
}