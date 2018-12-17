package Rooms;

import Game.Position;
import Items.Gun;
import Items.HealingPotion;
import People.Person;

public class RoomWithHealingPotion extends Room{

    HealingPotion healingPotion;
    String broadcast;
    String contains;

    public RoomWithHealingPotion(Position position)
    {
        super(position);
        this.healingPotion=new HealingPotion();
        this.broadcast="";
        this.contains="[H]";
    }

    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {
        System.out.println("You found a healing potion! If you drink it you can get full health");
        healingPotion.pickUp(x);
        System.out.println("--HEAL POTION ACQUIRED--");
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        contains="[X]";
    }

    public HealingPotion getHealingPotion() {
        return healingPotion;
    }

    /**
     * Removes the player from the room.
     *
     * @param x
     */
    @Override
    public void leaveRoom(Person x) {
        contains="[ ]";
        occupant = null;
    }
    @Override
    public String toString() {
        return contains;
    }

}
