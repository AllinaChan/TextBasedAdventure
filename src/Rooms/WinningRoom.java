package Rooms;

import Game.Position;
import Game.Runner;
import People.Person;

public class WinningRoom extends Room
{
    String contain;
    String broadcast;

    //The escape
    public WinningRoom(Position position) {
        super(position);
        contain="[?]";
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
        contain = "[X]";
        System.out.println("-------------------------");
        System.out.println("CONGRATULATIONS! YOU HAVE MADE IT! Thanks for playing...");
        Runner.gameOff();

    }


    @Override
    public String getBroadcast() {
        return broadcast;
    }



    @Override
    public String toString() {
        return contain;
    }

}
