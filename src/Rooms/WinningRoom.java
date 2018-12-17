package Rooms;

import Game.Position;
import Game.Runner;
import People.Person;

public class WinningRoom extends Room
{
    String contain;
    String broadcast;
    public WinningRoom(Position position) {
        super(position);
        this.contain="[?]";
        this.broadcast="";
    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        if(x.getFragments().size()>3) {
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());
            contain = "[X]";
            System.out.println("CONGRATULATIONS! YOU HAVE MADE IT! Thanks for playing...");
            Runner.gameOff();
        } else{

        }
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
