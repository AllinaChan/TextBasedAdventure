package Rooms;

import Game.Position;
import Items.Gun;
import People.Person;

public class RoomWithGun extends Room{

    Gun gun;
    String broadcast;
    String contain;

    public RoomWithGun(Position position)
    {
        super(position);
       this.gun=new Gun();
       this.broadcast="";
       this.contain="[G]";
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

        if (broadcast.equals("gun")) {
            contain = "[X]";
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());

        } else {
            System.out.println("-------------------------");
            System.out.println("You found the gun!");
            gun.fusionPickUp(x);
            System.out.println("--GUN ACQUIRED--");
            occupant = x;
            x.setxLoc(this.position.getX());
            x.setyLoc(this.position.getY());
            contain = "[X]";
            if (x.getAttack() >= 10000) {
                broadcast = "canKillWerewolf";
            }
            broadcast = "gun";
        }
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
        return contain;
    }

}
