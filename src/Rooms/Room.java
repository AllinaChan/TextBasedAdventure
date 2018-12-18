package Rooms;

import Game.Position;
import Items.HealingPotion;
import People.Person;

public abstract class Room {
    Person occupant;
    Position position;
    String broadcast;
    String contain;

    public Room(Position position) {
       this.position=position;
       this.broadcast="";
       this.contain="[ ]";
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
        this.contain="[X]";
    }
    /**
     * Removes the player from the room.
     *
     * @param x
     */

    public void leaveRoom(Person x) {
        occupant = null;
        this.contain="[ ]";
    }

    public String toString()
    {
        return getContain();
    }
    public Position getPosition()
    {
        return position;
    }

    public String getContain() {
        return contain;
    }

    public String getBroadcast()
    {
        return broadcast;
    }

    public void respawn(Person x)
    {
        occupant = x;
        x.setxLoc(0);
        x.setyLoc(0);
        contain="[X]";
    }


}