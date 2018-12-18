package Rooms;

import Game.Position;
import Items.Gun;
import Items.HealingPotion;
import Items.Sword;
import People.Person;

public class RoomWithSword extends Room{

    Sword sword;
    String broadcast;
    String contain;

    public RoomWithSword(Position position)
    {
        super(position);
        this.sword=new Sword();
        this.broadcast="";
        this.contain="[S]";
    }

    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {

        contain = "[X]";
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());

        if(broadcast.equals("sword"))
        {
            contain = "[X]";
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());

        }else {
            System.out.println("-------------------------");
            System.out.println("You found the sword! You can now kill wolfs without dying");
            sword.pickUp(x);
            System.out.println("--SWORD ACQUIRED--");
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());
            contain = "[X]";
            broadcast = "sword";
        }
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
    public String getBroadcast()
    {
        return broadcast;
    }

    @Override
    public String getContain() {
        return contain;
    }

    @Override
    public String toString() {
        return contain;
    }

}
