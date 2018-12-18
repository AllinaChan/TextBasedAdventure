package Rooms;

import Game.Position;
import People.Person;

public class EmptyRoom extends Room{

    String[] clues;
    String contain;

    public EmptyRoom(Position position)
    {
        super(position);
        this.clues= new String[]{"There is a puppy guarding the Escape, go pat it...",
        "Maybe you should gather every item on the map first", "Those wolves are feisty", "If you die, you just go back to the beginning",
        "I'm telling you, bullets flies faster than a Werewolf can pounce"};
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
        System.out.println("-------------------------");
        System.out.println(this.clues[(int)(Math.random()*clues.length)]);

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
    @Override
    public String toString() {
        return contain;
    }

}
