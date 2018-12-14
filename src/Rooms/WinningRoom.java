package Rooms;

import Game.Position;
import Game.Runner;
import People.Person;

public class WinningRoom extends Room
{
    String contain;

    public WinningRoom(Position position) {
        super(position);
        this.contain="[ ]";
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
        System.out.println("WHAT? HOW DID YOU GET HERE? IF you dare, try a different difficulty" );
        Runner.gameOff();
    }


    public String getContain() {
        return contain;
    }
    public String toString() {
        return getContain();
    }

}
