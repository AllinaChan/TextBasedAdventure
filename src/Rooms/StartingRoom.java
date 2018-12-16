package Rooms;

import Game.Position;
import Game.Runner;
import People.Person;

public class StartingRoom extends Room
{
    String contain;
    String broadcast;
    public StartingRoom(Position position) {
        super(position);
        this.contain="[ ]";
        this.broadcast="";
    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        contain= "[X]";
        System.out.println("-ESCAPE ROOM INITIALIZATION COMPLETE-");
        System.out.println("Good luck adventurer! I will be sipping a cup of tea on the other side ;D");
    }

    public void leaveRoom(Person x)
    {
        occupant = null;
        contain="[ ]";
    }

    @Override
    public String getBroadcast() {
        return broadcast;
    }

    public String getContain() {
        return contain;
    }
    public String toString() {
        return getContain();
    }

}
