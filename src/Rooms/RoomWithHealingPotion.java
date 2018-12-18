package Rooms;

import Game.Position;
import Items.Gun;
import Items.HealingPotion;
import People.Person;

public class RoomWithHealingPotion extends Room{

    HealingPotion healingPotion;
    String broadcast;
    String contain;

    public RoomWithHealingPotion(Position position)
    {
        super(position);
        this.healingPotion=new HealingPotion();
        this.broadcast="";
        this.contain="[H]";
    }

    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {
        if (broadcast.equals("potion")) {
            contain = "[X]";
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());

        } else {
            System.out.println("-------------------------");
            System.out.println("You found a healing potion! If you drink it you can get full health");
            healingPotion.pickUp(x);
            System.out.println("--HEAL POTION ACQUIRED--");
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());
            contain = "[X]";
            broadcast = "potion";
        }
    }

    public HealingPotion getHealingPotion() {
        return healingPotion;
    }
    public String getBroadcast()
    {
        return broadcast;
    }

    /**
     * Removes the player from the room.
     *
     * @param x
     */
    @Override
    public void leaveRoom(Person x) {
        contain="[ ]";
        occupant = null;
    }
    @Override
    public String toString() {
        return getContain();
    }

}
