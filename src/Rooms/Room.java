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

    /**
     * Allows board print() to be in a readable format
     * @return what the room contains, as a String, the symbol
     */
    public String toString()
    {
        return getContain();
    }

    //Getters of Position of the room, the String contain, and the String broadcast
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

    //If the player dies, then the player respawns at (0,0). Used by RoomWithWerewolf and RoomWithWolf
    public void respawn(Person x)
    {
        occupant = x;
        x.setxLoc(0);
        x.setyLoc(0);
        contain="[X]";
    }


}