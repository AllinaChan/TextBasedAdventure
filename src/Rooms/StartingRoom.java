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
        this.contain="[X]";
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
        System.out.println("While you risk your life, I will be back at the village sipping on a cup of tea.");
        System.out.println("*Whispering* it sure is smart asking someone to kill a Werewolf during a full moon...");

    }

    @Override
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
