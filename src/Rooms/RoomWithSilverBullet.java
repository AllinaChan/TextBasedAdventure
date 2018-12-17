package Rooms;

import Game.Position;
import Items.Gun;
import Items.SilverBullet;
import People.Person;

public class RoomWithSilverBullet extends Room{

    SilverBullet bullet;
    String broadcast;
    String contains;

    public RoomWithSilverBullet(Position position)
    {
        super(position);
        this.bullet=new SilverBullet();
        this.broadcast="";
        this.contains="[B]";
    }

    /**
     * Method controls the results when a person enters this room.
     *
     * @param x the Person entering
     */
    @Override
    public void enterRoom(Person x) {
        System.out.println("You found the silver bullet!");
        bullet.fusionPickUp(x);
        System.out.println("--SILVER BULLET ACQUIRED--");
        occupant = x;
        x.setxLoc(this.position.getX());
        x.setyLoc(this.position.getY());
        contains="[X]";
        if(x.getAttack()>=10000)
        {
            broadcast="canKillWerewolf";
        }
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
