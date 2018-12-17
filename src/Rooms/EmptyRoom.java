package Rooms;

import Game.Position;
import People.Person;

public class EmptyRoom extends Room{

    String[] clues;
    String contains;

    public EmptyRoom(Position position)
    {
        super(position);
        this.clues= new String[]{"There is a puppy guarding the Escape, go pat it...",
        "Maybe you should gather every item on the map first", "Those wolves are feisty", "If you die, you just go back to the beginning",
        "I'm telling you, bullets flies faster than a Werewolf can pounce"};
        this.contains="[ ]";
    }


    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        System.out.println(this.clues[(int)(Math.random()*clues.length)]);
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        this.contains="[X]";
    }

    /**
     * Removes the player from the room.
     *
     * @param x
     */
@Override
    public void leaveRoom(Person x) {
        occupant = null;
        this.contain="[ ]";
    }

    @Override
    public String toString()
    {
        return contains;
    }
}
