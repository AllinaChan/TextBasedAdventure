package Rooms;

import Game.Position;
import People.Person;

public abstract class Room {
    Person occupant;
    Position position;

    public Room(Position position) {
       this.position=position;
    }

    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Person entering
     */
    public void enterRoom(Person x) {
        System.out.println("You enter a plain old room");
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
    }
    /**
     * Removes the player from the room.
     *
     * @param x
     */
    public void leaveRoom(Person x) {
        occupant = null;
    }

    public String toString()
    {
        return "[ ]";
    }

    public abstract String getBroadcast();


}